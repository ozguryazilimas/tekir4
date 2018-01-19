/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity.reminder;

import com.ozguryazilim.tekir.opportunity.OpportunityRepository;
import com.ozguryazilim.tekir.opportunity.OpportunityViewModel;
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
 * Kapanmamış fırsatlar için belirtilen süreden sonra hatırlatma mesajı gönderir.
 * 
 * Fırsat oluşturulduğu tarihten itibaren {@link OpportunityReminderCommand#interval} 
 * olarak belirtilen süre geçtikten sonra hatırlatma gönderilir.
 * 
 * WEB kanalından tek tek gönderirken e-posta olarak topluca her kişiye 1
 * e-posta gönderir.
 *
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-20
 * @see AbstractCommandExecuter
 * @see CommandExecutor
 * @see OpportunityReminderCommand
 */
@CommandExecutor(command = OpportunityReminderCommand.class)
public class OpportunityReminderCommandExecutor extends AbstractCommandExecuter<OpportunityReminderCommand> {

    private static final Logger LOG = LoggerFactory.getLogger(OpportunityReminderCommandExecutor.class);

    @Inject
    private OpportunityRepository repository;

    @Inject
    private CommandSender commandSender;
    
    @Inject 
    private FormatedMessage messages;

    /**
     * Fırsat komutunu çalıştırır.
     * 
     * {@inheritDoc}
     * 
     * {@link OpportunityReminderCommand#getInterval()}'dan gelen süreden sonra
     * {@link OpportunityRepository#findUnclosedOpportunities(java.util.Date)} metodundan
     * elde edilen kapanmamış olan fırsatlar web kanalı ve mail olarak 
     * {@link FormatedMessage#getFormatedMessage(java.lang.String, java.lang.Object...)} ile biçimlendirilmiş
     * {@link CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command)}
     * metoduyla bildirim olarak gönderilmektedir.
     * 
     * @param command Çalıştırılacak fırsat komutu 
     * @see OpportunityViewModel
     * @see NotificationCommand
     * @see OpportunityReminderCommand#getInterval() 
     * @see DateUtils#getDateBeforePeriod(java.lang.String, java.util.Date)
     * @see OpportunityRepository#findUnclosedOpportunities(java.util.Date) 
     * @see FormatedMessage#getMessage(java.lang.String, java.lang.Object[]) 
     * @see CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command) 
     */
    @Override
    public void execute(OpportunityReminderCommand command) {

        //Toplu bildirim için biriktirme alanı. Kullanıcı ve gönderilecek listesi.
        Map<String, List<OpportunityViewModel>> cumulatives = new HashMap<>();

        Date date = DateUtils.getDateBeforePeriod(command.getInterval(), new Date());

        List<OpportunityViewModel> ls = repository.findUnclosedOpportunities(date);

        //TODO: Owner dışında aslında grubun tamamına göndermek de bir başka seçenek ( ki bu tercih edilebilir )
        // Owner dışı gruba önderme de sorun var. Grup bir ağaç ve nereye kadar gönderileceği biraz dertli. Belki manager'a gönderme yöntemi seçilebilir.
        // Ya da parametre olarak gönderim yapılacak grup seçimi istenebilir bilemiyorum.
        for (OpportunityViewModel opportunity : ls) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");

            nc.setTarget("cs=user;id=".concat(opportunity.getOwner()));
            nc.setSubject(messages.getFormatedMessage("OpportunityReminderCommand.subject.single", opportunity.getVoucherNo()));

            Map<String, Object> params = new HashMap<>();

            params.put("Account", opportunity.getAccount().getName());
            params.put("AccountId", opportunity.getAccount().getId());

            params.put("VoucherId", opportunity.getId());
            params.put("VoucherTopic", opportunity.getTopic());
            params.put("VoucherNo", opportunity.getVoucherNo());

            nc.setParams(params);

            commandSender.sendCommand(nc);

            //Toplu gönderim için biriktirme
            List<OpportunityViewModel> opportunities = cumulatives.get(opportunity.getOwner());
            if (opportunities == null) {
                opportunities = new ArrayList<>();
                cumulatives.put(opportunity.getOwner(), opportunities);
            }
            opportunities.add(opportunity);

        }

        for (Map.Entry<String, List<OpportunityViewModel>> entry : cumulatives.entrySet()) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");
            nc.setTemplate("CUMULATIVE");

            nc.setTarget("cs=user;id=".concat(entry.getKey()));
            nc.setSubject(messages.getFormatedMessage("OpportunityReminderCommand.subject.cumulative"));

            Map<String, Object> params = new HashMap<>();

            params.put("Opportunities", entry.getValue());

            nc.setParams(params);

            commandSender.sendCommand(nc);
        }

    }

}
