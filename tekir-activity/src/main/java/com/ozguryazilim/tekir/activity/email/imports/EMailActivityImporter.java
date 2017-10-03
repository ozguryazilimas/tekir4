/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports;

import com.ozguryazilim.tekir.activity.ActivityRepository;
import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.ActivityStatus;
import com.ozguryazilim.tekir.entities.EMailActivity;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verilen eposta mesajını Parse edip EMail Activity olarak işler
 * @author Hakan Uygun
 */
@Dependent
public class EMailActivityImporter implements Serializable{
    
    private static final Logger LOG = LoggerFactory.getLogger(EMailActivityImporter.class);
    
    @Inject
    private ActivityRepository activityRepository;
    
    @Inject
    private ContactRepository contactRepository;
    
    @Inject
    private ContactInformationRepository informationRepository;
    
    public void importMail( String mail ){
    
        EMailParser parser = new EMailParser();
        try {
            EMailMessage message = parser.parse(mail);
            
            EMailActivity activity = new EMailActivity();
            
            activity.setMessageId(message.getMessageId());
            activity.setReplyId(message.getReplyId());
            activity.setForwardId(message.getForwardId());
            
            activity.setSubject(message.getSubject());
            activity.setBody(message.getContent());
            activity.setDate(message.getDate());
            activity.setDueDate(message.getDate());
            
            activity.setStatus(ActivityStatus.DRAFT);
            
            //From/To/CC/BCC ve diğerleri
            activity.setFrom(message.getFrom().toString());
            activity.setTo(message.getToList().toString());
            activity.setCc(message.getCcList().toString());
            activity.setBcc(message.getBccList().toString());
            
           //informationRepository.findByMailAddress
           
           //From kısmında domain name kontrolü yaparak bizden mi karşıdan mı olduğuna bakılabilir. Domain ismi için kahve conf'a girmeli
           //To kısmına bakılarak kimi ilgilendirdiğine bakılabilir. Hatta onun çalıştığı şirket de doğrudan corp olarak atanabilir
           //Geri kalan herkes mention listesine girecek
           
           //Hangi kullanıcıya assign edileceği gene from/to kısmından bulunmalı ( yöne bağlı olarak )
           
           //Ayrıca message id üzerinden daha önce kaydedilmiş mail activity aranacak. eğer bulunur ise ilgili kısımları oradan alınan bilgilerle doldurulacak.
           
           
           
           //Attachmentlar attachment olarak activity'ye bağlanacak
           
           //Feed'ler ve sorumlu kişi bilgilendirmesi yapılacak
           
            activityRepository.save(activity);
            
            
            
        } catch (MessagingException | IOException ex) {
            LOG.error( "Mail Import Error", ex );
        }
        
    }
    
    
}
