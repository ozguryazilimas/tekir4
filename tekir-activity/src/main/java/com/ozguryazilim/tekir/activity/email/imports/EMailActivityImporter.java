/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports;

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
import com.ozguryazilim.telve.auth.UserService;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.sequence.SequenceManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
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

    public void importMail(String mail) {

        EMailParser parser = new EMailParser();
        try {
            EMailMessage message = parser.parse(mail);

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


            //To kısmına bakılarak kimi ilgilendirdiğine bakılabilir. Hatta onun çalıştığı şirket de doğrudan corp olarak atanabilir
            //Geri kalan herkes mention listesine girecek
            //From kısmında domain name kontrolü yaparak bizden mi karşıdan mı olduğuna bakılabilir. Domain ismi için kahve conf'a girmeli
            addContactMention(message.getFrom().getAddress(), activity);

            List<ContactEMail> emails = informationRepository.findByEmail(message.getFrom().getAddress());
            for (ContactEMail cemail : emails) {
                Contact contact = cemail.getContact();
                if (contact instanceof AbstractPerson) {
                    activity.setPerson((AbstractPerson) contact);
                } else if (contact instanceof Corporation) {
                    activity.setCorporation((Corporation) contact);
                }
            }


            //Burada primary nasıl seçilecek?
            for (InternetAddress adr : message.getToList()) {
                addContactMention(adr.getAddress(), activity);
            }

            for (InternetAddress adr : message.getCcList()) {
                addContactMention(adr.getAddress(), activity);
            }

            //BCC'ye gerek var mı?
            for (InternetAddress adr : message.getBccList()) {
                addContactMention(adr.getAddress(), activity);
            }



            //FIXME: doğru kullanıcıya atanacak.
            //Hangi kullanıcıya assign edileceği gene from/to kısmından bulunmalı ( yöne bağlı olarak )
            //String userName = userService.getUsersByEmail("from/to");
            activity.setAssignee("telve");

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


            activityRepository.save(activity);

            //Attachmentlar attachment olarak activity'ye bağlanacak
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


            //Feed'ler ve sorumlu kişi bilgilendirmesi yapılacak


        } catch (MessagingException | IOException | AttachmentNotFoundException | AttachmentException ex) {
            LOG.error("Mail Import Error", ex);
        }

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
