/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.activity.email.imports.model.EMailMessage;
import com.ozguryazilim.tekir.activity.email.imports.model.EMailAttacment;
import com.ozguryazilim.tekir.activity.ActivityFeature;
import com.ozguryazilim.tekir.activity.ActivityRepository;
import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.*;
import com.ozguryazilim.telve.attachment.AttachmentContext;
import com.ozguryazilim.telve.attachment.AttachmentDocument;
import com.ozguryazilim.telve.attachment.AttachmentException;
import com.ozguryazilim.telve.attachment.AttachmentNotFoundException;
import com.ozguryazilim.telve.attachment.AttachmentStore;
import com.ozguryazilim.telve.attachment.AttacmentContextProviderSelector;
import com.ozguryazilim.telve.attachment.qualifiers.FileStore;
import com.ozguryazilim.telve.auth.UserInfo;
import com.ozguryazilim.telve.auth.UserService;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.sequence.SequenceManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import javax.activation.MimeType;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verilen eposta mesajını Parse edip EMail Activity olarak işler
 *
 * @author Hakan Uygun
 */
@Dependent
public class EMailActivityImporter implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(EMailActivityImporter.class);
    private static final String MAIL_DOMAIN = "mail.domain";
    private static final String MAIL_DEFAULT_USER = "mail.default.user";
    /**
     * gelen mail
     */
    private static final int INBOX = 0;
    /**
     * giden mail
     */
    private static final int OUTBOX = 1;

    private static final int UNKNOWN = -1;

    private Set<String> ownEmails;

    @Inject
    private ActivityRepository activityRepository;

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private ContactInformationRepository informationRepository;

    @Inject
    private UserService userService;

    @Inject
    private AttacmentContextProviderSelector providerSelector;

    @Inject
    private SequenceManager sequenceManager;

    @Inject
    @FileStore
    private AttachmentStore store;

    @Inject
    private Kahve kahve;

    private String emailDomain;

    @PostConstruct
    private void init() {
        this.emailDomain = kahve.get(MAIL_DOMAIN, "").getAsString();
    }

    public void importMail(String mail) {

        EMailParser parser = new EMailParser();
        try {
            EMailMessage message = parser.parse(mail);
            if (isImportedBefore(message.getMessageId())) {
                LOG.info("Message with messageId (" + message.getMessageId() + ") already imported");
                return;
            }

            if (isMeeting(message)) {
                createMeetingActivity(message);
            } else {
                createEmailActivity(message);
            }
        } catch (MessagingException | IOException | AttachmentNotFoundException | AttachmentException ex) {
            LOG.error("Mail Import Error", ex);
        }
    }

    private void createMeetingActivity(EMailMessage message) {
        MeetingActivity activity = new MeetingActivity();
        activity.setActivityNo(sequenceManager.getNewSerialNumber("ACT", 6));


    }

    private void createEmailActivity(EMailMessage message) throws AttachmentException, AttachmentNotFoundException {
        EMailActivity activity = new EMailActivity();

        activity.setActivityNo(sequenceManager.getNewSerialNumber("ACT", 6));

        activity.setMessageId(message.getMessageId());
        activity.setReplyId(message.getReplyId());
        activity.setForwardId(message.getForwardId());

        activity.setSubject(message.getSubject());
        activity.setBody(message.getContent());
        activity.setDate(message.getDate());
        activity.setDueDate(message.getDate());

        activity.setStatus(ActivityStatus.DRAFT);

        //From/To/CC/BCC ve diğerleri
        activity.setFrom(addressToString(message.getFrom()));
        activity.setTo(addressToString(message.getToList()));
        activity.setCc(addressToString(message.getCcList()));
        activity.setBcc(addressToString(message.getBccList()));

        int direction = resolveDirection(message);

        boolean contactsAdded = false;
        // bize gelen mailler icin baglantiyi from kısmından al
        if (direction == INBOX) {
            addContacts(message.getFrom().getAddress(), activity);
        } else {
            addContactMention(message.getFrom().getAddress(), activity);
        }

        for (InternetAddress adr : message.getToList()) {
            //giden mail ise ilk bulduğunla eşleştir
            if (direction == OUTBOX && !contactsAdded) {
                contactsAdded = addContacts(adr.getAddress(), activity);
            }
            addContactMention(adr.getAddress(), activity);
        }

        for (InternetAddress adr : message.getCcList()) {
            addContactMention(adr.getAddress(), activity);
        }

        for (InternetAddress adr : message.getBccList()) {
            addContactMention(adr.getAddress(), activity);
        }

        String assignee = null;
        if (direction == INBOX) {
            assignee = userService.getUserByEmail(message.getFrom().getAddress());
        } else {
            for (InternetAddress adr : message.getToList()) {
                assignee = userService.getUserByEmail(adr.getAddress());
                if (assignee != null) {
                    break;
                }
            }
        }

        if (assignee != null) {
            activity.setAssignee(assignee);
        } else {
            //bulunamayanların kime atanacağı kahveden çekiliyor
            activity.setAssignee(kahve.get(MAIL_DEFAULT_USER).getAsString());
        }


        //Ayrıca message id üzerinden daha önce kaydedilmiş mail activity aranacak. eğer bulunur ise ilgili kısımları oradan alınan bilgilerle doldurulacak.
        if (message.isReply()) {
            List<EMailActivity> acts = activityRepository.findByMessageId(message.getReplyId());
            if (!acts.isEmpty()) {
                FeaturePointer fp = acts.get(0).getRegarding();
                activity.setRegarding(fp);
            }
        } else if (message.isForwarded()) {
            List<EMailActivity> acts = activityRepository.findByMessageId(message.getForwardId());
            if (!acts.isEmpty()) {
                FeaturePointer fp = acts.get(0).getRegarding();
                activity.setRegarding(fp);
            }
        }

        //Hala bir regarding bulunamamış ise Subject parse edilip bulunması denebilir mi?
        //Ama yazılı olan VoucherNo'yu hangi tablolarda arayacağız?
        //hüseyin: içerik üzerinden eşleştirme şimdilik yapmayalım.

        activityRepository.save(activity);

        //Attachmentlar attachment olarak activity'ye bağlanacak
        addAttachments(activity, message);

        //Feed'ler ve sorumlu kişi bilgilendirmesi yapılacak
    }

    protected void addAttachments(Activity activity, EMailMessage message) throws AttachmentException, AttachmentNotFoundException {
        for (EMailAttacment atch : message.getAttachments()) {
            FeaturePointer fp = new FeaturePointer();
            fp.setBusinessKey(activity.getActivityNo());
            fp.setFeature(ActivityFeature.class.getSimpleName());
            fp.setPrimaryKey(activity.getId());
            AttachmentContext context = providerSelector.getAttachmentContextProvider(fp, activity).getContext(fp, activity);
            AttachmentDocument document = new AttachmentDocument();

            document.setName(atch.getName());
            document.setMimeType(atch.getMimeType());

            store.addDocument(context, store.getFolder(context, ""), document, atch.getContent());
        }
    }


    private boolean isMeeting(EMailMessage message) {
        for (EMailAttacment attacment : message.getAttachments()) {
            if ("application/ics".equals(attacment.getMimeType())) {
                return true;
            }
        }
        return false;
    }

    private boolean isImportedBefore(String messageId) {
        List<EMailActivity> activities = activityRepository.findByMessageId(messageId);
        return activities.size() > 0;
    }

    protected int resolveDirection(EMailMessage message) {
        int direction = resolveDirectionByDomain(message);

        if (direction != UNKNOWN) {
            return direction;
        }

        return resolveDirectionByContacts(message);
    }

    private int resolveDirectionByContacts(EMailMessage message) {
        if (ownEmails == null) {
            ownEmails = new HashSet<>();
            //TODO kullanıcı maillerini çekmek için daha efektif bir yol bul
            for (String loginName : userService.getLoginNames()) {
                UserInfo ui = userService.getUserInfo(loginName);
                if (ui.getEmail() != null) {
                    ownEmails.add(ui.getEmail());
                }
            }

            List<Contact> employes = contactRepository.findByRole("EMPLOYEE");
            for (Contact employee : employes) {
                if (employee.getPrimaryEmail() != null) {
                    ownEmails.add(employee.getPrimaryEmail().getEmailAddress());
                }
            }
        }

        String from = message.getFrom().getAddress();

        // biz göndermediysek bize gelmiştir varsayımı
        return ownEmails.contains(from) ? OUTBOX : INBOX;
    }

    private boolean addContacts(String email, EMailActivity activity) {
        List<ContactEMail> emails = informationRepository.findByEmail(email);
        for (ContactEMail ce : emails) {
            Contact contact = ce.getContact();
            if (contact instanceof AbstractPerson) {
                AbstractPerson person = (AbstractPerson) contact;
                activity.setPerson(person);
                activity.setCorporation(person.getCorporation());
                return true;
            } else if (contact instanceof Corporation) {
                activity.setCorporation((Corporation) contact);
                return true;
            }
        }
        return false;
    }

    private int resolveDirectionByDomain(EMailMessage message) {
        if (this.emailDomain.isEmpty()) {
            return UNKNOWN;
        }

        if (isDomainMatches(message.getFrom().getAddress())) {
            return OUTBOX;
        }

        for (InternetAddress address : message.getToList()) {
            if (isDomainMatches(address.getAddress())) {
                return INBOX;
            }
        }

        return UNKNOWN;
    }

    private boolean isDomainMatches(String email) {
        String[] parts = email.split("@");
        return parts.length == 2 && this.emailDomain.equals(parts[1]);
    }

    protected void addContactMention(String email, EMailActivity activity) {
        List<ContactEMail> infos = informationRepository.findByEmail(email);
        //Burada aslında bir tane bulmak lazım.
        for (ContactEMail info : infos) {
            ActivityMention mention = new ActivityMention();

            FeaturePointer pp = new FeaturePointer();
            pp.setBusinessKey(info.getContact().getName());
            pp.setPrimaryKey(info.getContact().getId());
            pp.setFeature(FeatureRegistery.getFeatureClass(info.getContact().getClass()).getSimpleName());

            mention.setFeaturePointer(pp);
            mention.setActivity(activity);

            activity.getMentions().add(mention);
        }
    }


    private String addressToString(InternetAddress address) {
        StringBuilder sb = new StringBuilder();
        if (address.getPersonal() != null) {
            sb.append(address.getPersonal());
        }
        sb.append(" <").append(address.getAddress()).append(">");
        return sb.toString();
    }

    private String addressToString(List<InternetAddress> addresses) {
        StringBuilder sb = new StringBuilder();
        Iterator<InternetAddress> it = addresses.iterator();
        while (it.hasNext()) {
            sb.append(addressToString(it.next()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
