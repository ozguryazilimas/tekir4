/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.lead.reminder;

import com.ozguryazilim.tekir.lead.LeadRepository;
import com.ozguryazilim.tekir.lead.LeadViewModel;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import com.ozguryazilim.telve.messagebus.command.CommandSender;
import com.ozguryazilim.telve.messages.FormatedMessage;
import com.ozguryazilim.telve.notification.NotificationCommand;
import com.ozguryazilim.telve.utils.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kapanmamış ipuçları için belirtilen süreden sonra hatırlatma mesajı gönderir.
 * 
 * İpucu oluşturulduğu tarihten itibaren {@link LeadReminderCommand#interval} 
 * olarak belirtilen süre geçtikten sonra hatırlatma gönderilir.
 * 
 * WEB kanalından tek tek gönderirken e-posta olarak topluca her kişiye 1
 * e-posta gönderir.
 *
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-19
 * @see AbstractCommandExecuter
 * @see CommandExecutor
 * @see LeadReminderCommand
 */
@CommandExecutor(command = LeadReminderCommand.class)
public class LeadReminderCommandExecutor extends AbstractCommandExecuter<LeadReminderCommand> {

    private static final Logger LOG = LoggerFactory.getLogger(LeadReminderCommandExecutor.class);
    
    @Inject
    private LeadRepository repository;
    
    @Inject
    private CommandSender commandSender;
    
    @Inject 
    private FormatedMessage messages;
    
    /**
     * İpucu komutunu çalıştırır.
     * 
     * {@inheritDoc}
     * 
     * {@link LeadReminderCommand#getInterval()}'dan gelen süreden sonra
     * {@link LeadRepository#findUnclosedLeads(java.util.Date)} metodundan
     * elde edilen kapanmamış olan ipuçları web kanalı ve mail olarak 
     * {@link FormatedMessage#getFormatedMessage(java.lang.String, java.lang.Object...)} ile biçimlendirilmiş
     * {@link CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command)}
     * metoduyla bildirim olarak gönderilmektedir.
     * 
     * @param command Çalıştırılacak ipucu komutu 
     * @see LeadViewModel
     * @see NotificationCommand
     * @see LeadReminderCommand#getInterval() 
     * @see DateUtils#getDateBeforePeriod(java.lang.String, java.util.Date)
     * @see LeadRepository#findUnclosedLeads(java.util.Date) 
     * @see FormatedMessage#getMessage(java.lang.String, java.lang.Object[]) 
     * @see CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command) 
     */
    @Override
    public void execute(LeadReminderCommand command) {
        //Toplu bildirim için biriktirme alanı. Kullanıcı ve gönderilecek listesi.
        Map<String, List<LeadViewModel>> cumulatives = new HashMap<>();

        Date date = DateUtils.getDateBeforePeriod(command.getInterval(), new Date());
        
        List<LeadViewModel> ls = repository.findUnclosedLeads(date);
           
        //TODO: Owner dışında aslında grubun tamamına göndermek de bir başka seçenek ( ki bu tercih edilebilir )
        // Owner dışı gruba önderme de sorun var. Grup bir ağaç ve nereye kadar gönderileceği biraz dertli. Belki manager'a gönderme yöntemi seçilebilir.
        // Ya da parametre olarak gönderim yapılacak grup seçimi istenebilir bilemiyorum.
        for (LeadViewModel lead : ls) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");

            nc.setTarget("cs=user;id=".concat(lead.getOwner()));
            nc.setSubject(messages.getFormatedMessage("LeadReminderCommand.subject.single", lead.getVoucherNo()));

            Map<String, Object> params = new HashMap<>();

            params.put("RelatedPersonName", lead.getRelatedPersonName());
            params.put("RelatedPersonSurname", lead.getRelatedPersonSurname());

            params.put("VoucherId", lead.getId());
            params.put("VoucherTopic", lead.getTopic());
            params.put("VoucherNo", lead.getVoucherNo());

            nc.setParams(params);

            commandSender.sendCommand(nc);

            //Toplu gönderim için biriktirme
            List<LeadViewModel> leads = cumulatives.get(lead.getOwner());
            if (leads == null) {
                leads = new ArrayList<>();
                cumulatives.put(lead.getOwner(), leads);
            }
            leads.add(lead);

        }

        for (Map.Entry<String, List<LeadViewModel>> entry : cumulatives.entrySet()) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");
            nc.setTemplate("CUMULATIVE");

            nc.setTarget("cs=user;id=".concat(entry.getKey()));
            nc.setSubject(messages.getFormatedMessage("LeadReminderCommand.subject.cumulative"));

            Map<String, Object> params = new HashMap<>();

            params.put("Leads", entry.getValue());

            nc.setParams(params);

            commandSender.sendCommand(nc);
        }
        
    }
    
}
