package com.ozguryazilim.tekir.activity.email.imports;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.activity.email.imports.model.EMailMessage;
import com.ozguryazilim.tekir.activity.email.imports.model.EMailAttacment;
import com.ozguryazilim.tekir.activity.ActivityFeature;
import com.ozguryazilim.tekir.activity.ActivityRepository;
import com.ozguryazilim.tekir.activity.email.imports.model.MeetingFile;
import com.ozguryazilim.tekir.activity.email.imports.model.MeetingFileParseException;
import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.ActivityDirection;
import com.ozguryazilim.tekir.entities.ActivityMention;
import com.ozguryazilim.tekir.entities.ActivityStatus;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.EMailActivity;
import com.ozguryazilim.tekir.entities.MeetingActivity;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Attendee;
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
    private static final String ATTENDEE = "ATTENDEE";

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

            MeetingFile meetingFile = getMeetingFile(message);
            if (meetingFile != null) {
                processMeetingActivity(message, meetingFile);
            } else {
                createEmailActivity(message);
            }
        } catch (MessagingException | IOException | AttachmentNotFoundException |
                AttachmentException | MeetingFileParseException | MimeTypeParseException ex) {
            LOG.error("Mail Import Error", ex);
        }
    }

    private void processMeetingActivity(EMailMessage message, MeetingFile meetingFile) throws MeetingFileParseException {
        MeetingActivity activity;
        Calendar calendar = meetingFile.getCalendar();
        String method = calendar.getProperty("METHOD").getValue();
        if ("CANCEL".equalsIgnoreCase(method)) {
            cancelMeetingActivity(meetingFile);
            return;
        }
        activity = createMeetingActivity(message, meetingFile);

        if ("REPLY".equalsIgnoreCase(method)) {
            updateMeetingActivity(activity, meetingFile);
        }
    }

    private void updateMeetingActivity(MeetingActivity activity, MeetingFile meetingFile) throws MeetingFileParseException {
        // bazı durumlarda katılımcı listesi toplantı isteğinde eksik gelebiliyor. Ya da sonradan
        // katılımcı eklenebiliyor. REPLY geldikçe yeni katılımcıları ekleyelim
        String attendees = activity.getAttendees();
        Calendar calendar = meetingFile.getCalendar();
        VEvent event = (VEvent) calendar.getComponent(Component.VEVENT);
        PropertyList<Attendee> attendeeList = event.getProperties(ATTENDEE);
        StringBuilder sb = new StringBuilder(attendees);
        for (Attendee attendee : attendeeList) {
            String email = attendee.getCalAddress().getSchemeSpecificPart();
            if (!attendees.contains(email)) {
                sb.append(", ");
                sb.append(email);
                addContactMention(email, activity);
            }
        }
    }

    private void cancelMeetingActivity(MeetingFile meetingFile) throws MeetingFileParseException {
        Calendar calendar = meetingFile.getCalendar();
        VEvent event = (VEvent) calendar.getComponent(Component.VEVENT);
        String uid = event.getUid().getValue();
        List<MeetingActivity> activities = activityRepository.findByReferenceId(uid, MeetingActivity.class);

        for (MeetingActivity activity : activities) {
            activity.setStatus(ActivityStatus.FAILED);
            activity.setStatusReason("Canceled");
        }
    }

    private MeetingActivity createMeetingActivity(EMailMessage message, MeetingFile meetingFile) throws MeetingFileParseException {
        Calendar calendar = meetingFile.getCalendar();
        VEvent event = (VEvent) calendar.getComponent(Component.VEVENT);
        String uid = event.getUid().getValue();
        List<MeetingActivity> activities = activityRepository.findByReferenceId(uid, MeetingActivity.class);
        if (activities.size() > 0) {
            return activities.get(0);
        }

        MeetingActivity activity = new MeetingActivity();
        activity.setActivityNo(sequenceManager.getNewSerialNumber("ACT", 6));
        String organizerAddress = event.getOrganizer().getCalAddress().getSchemeSpecificPart();
        ActivityDirection direction = resolveDirection(organizerAddress, null);
        activity.setDirection(direction);

        activity.setReferenceId(uid);
        activity.setSubject(event.getSummary().getValue());
        activity.setLocation(event.getLocation().getValue());
        activity.setBody(event.getDescription().getValue());
        activity.setDate(message.getDate());
        Date start = event.getStartDate().getDate();
        Date end = event.getEndDate().getDate();
        activity.setDueDate(start);
        activity.setDuration((end.getTime() - start.getTime()) / 1000);

        //attendee
        PropertyList<Attendee> attendeeList = event.getProperties(ATTENDEE);
        StringBuilder attendees = new StringBuilder();
        for (int i = 0; i < attendeeList.size(); i++) {
            Attendee attendee = attendeeList.get(i);
            String email = attendee.getCalAddress().getSchemeSpecificPart();
            attendees.append(email);
            if (i != attendeeList.size() - 1) {
                attendees.append(", ");
            }
            addContactMention(email, activity);
        }

        activity.setAttendees(attendees.toString());
        activityRepository.save(activity);
        return activity;
    }

    private void createEmailActivity(EMailMessage message) throws AttachmentException, AttachmentNotFoundException {
        EMailActivity activity = new EMailActivity();

        activity.setActivityNo(sequenceManager.getNewSerialNumber("ACT", 6));

        activity.setReferenceId(message.getMessageId());
        activity.setReplyId(message.getReplyId());
        activity.setForwardId(message.getForwardId());

        activity.setSubject(message.getSubject());
        activity.setBody(message.getContent());
        activity.setDate(message.getDate());
        activity.setDueDate(message.getDate());

        activity.setStatus(ActivityStatus.SUCCESS);

        //From/To/CC/BCC ve diğerleri
        activity.setFrom(addressToString(message.getFrom()));
        activity.setTo(addressToString(message.getToList()));
        activity.setCc(addressToString(message.getCcList()));
        activity.setBcc(addressToString(message.getBccList()));

        String[] toList = message.getToList().stream().map(address -> {
            return address.getAddress();
        }).toArray(String[]::new);

        ActivityDirection direction = resolveDirection(message.getFrom().getAddress(), toList);
        activity.setDirection(direction);

        boolean contactsAdded = false;
        // bize gelen mailler icin baglantiyi from kısmından al
        if (direction == ActivityDirection.INCOMING) {
            addContacts(message.getFrom().getAddress(), activity);
        } else {
            addContactMention(message.getFrom().getAddress(), activity);
        }

        for (InternetAddress adr : message.getToList()) {
            //giden mail ise ilk bulduğunla eşleştir
            if (direction == ActivityDirection.OUTGOING && !contactsAdded) {
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
        if (direction == ActivityDirection.INCOMING) {
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
            //kime atanacağı bulunamayan mailler tam ilişkilendirilemediğinden durumu kestirilemiyor yapıyoruz
            activity.setStatus(ActivityStatus.UNRESOLVED);
        }


        //Ayrıca message id üzerinden daha önce kaydedilmiş mail activity aranacak. eğer bulunur ise ilgili kısımları oradan alınan bilgilerle doldurulacak.
        if (message.isReply()) {
            List<EMailActivity> acts = activityRepository.findByReferenceId(message.getReplyId(), EMailActivity.class);
            if (!acts.isEmpty()) {
                FeaturePointer fp = acts.get(0).getRegarding();
                activity.setRegarding(fp);
            }
        } else if (message.isForwarded()) {
            List<EMailActivity> acts = activityRepository.findByReferenceId(message.getForwardId(), EMailActivity.class);
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


    /**
     * Email içerisinden RFC5545e uygun toplantı dosyasını ayıklayarak döner
     *
     * @param message
     * @return
     */
    private MeetingFile getMeetingFile(EMailMessage message) throws MimeTypeParseException {
        for (EMailAttacment attacment : message.getAttachments()) {
            MimeType mimeType = new MimeType(attacment.getMimeType());
            if ("calendar".equals(mimeType.getSubType()) || "ics".equals(mimeType.getSubType())) {
                return new MeetingFile(attacment);
            }
        }
        return null;
    }

    private boolean isImportedBefore(String messageId) {
        List<Activity> activities = activityRepository.findByReferenceId(messageId);
        for (Activity activity : activities) {
            if (activity instanceof EMailActivity) {
                return true;
            }
        }
        return false;
    }

    protected ActivityDirection resolveDirection(String fromAddress, String[] toAddresses) {
        ActivityDirection direction = resolveDirectionByDomain(fromAddress, toAddresses);

        if (direction != ActivityDirection.NONE) {
            return direction;
        }

        return resolveDirectionByContacts(fromAddress);
    }

    private ActivityDirection resolveDirectionByContacts(String from) {
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

        // biz göndermediysek bize gelmiştir varsayımı
        return ownEmails.contains(from) ? ActivityDirection.OUTGOING : ActivityDirection.INCOMING;
    }

    private boolean addContacts(String email, Activity activity) {
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

    private ActivityDirection resolveDirectionByDomain(String from, String[] toList) {
        if (emailDomain == null || emailDomain.isEmpty()) {
            return ActivityDirection.NONE;
        }

        if (isDomainMatches(from)) {
            return ActivityDirection.OUTGOING;
        }

        if (toList != null) {
            for (String address : toList) {
                if (isDomainMatches(address)) {
                    return ActivityDirection.INCOMING;
                }
            }
        }

        return ActivityDirection.NONE;
    }

    private boolean isDomainMatches(String email) {
        String[] parts = email.split("@");
        return parts.length == 2 && this.emailDomain.equals(parts[1]);
    }

    protected void addContactMention(String email, Activity activity) {
        List<ContactEMail> infos = informationRepository.findByEmail(email);
        //Burada aslında bir tane bulmak lazım.

        for (ContactEMail info : infos) {
            Long contactId = info.getContact().getId();
            for (ActivityMention m : activity.getMentions()) {
                if (m.getFeaturePointer() != null && m.getFeaturePointer().getPrimaryKey() == contactId) {
                    // zaten tanımlanmış
                    return;
                }
            }

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
