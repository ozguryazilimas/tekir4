/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote.reminder;

import com.ozguryazilim.tekir.quote.QuoteRepository;
import com.ozguryazilim.tekir.quote.QuoteViewModel;
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
 * Tarihi yaklaşan ya da geçen teklifler için hatırlatma mesajı gönderir.
 *
 * WEB kanalından tek tek gönderirken e-posta olarak topluca her kişiye 1
 * e-posta gönderir.
 *
 * @author Hakan Uygun
 */
@CommandExecutor(command = QuoteReminderCommand.class)
public class QuoteReminderCommandExecutor extends AbstractCommandExecuter<QuoteReminderCommand> {

    private static final Logger LOG = LoggerFactory.getLogger(QuoteReminderCommandExecutor.class);

    @Inject
    private QuoteRepository repository;

    @Inject
    private CommandSender commandSender;
    
    @Inject 
    private FormatedMessage messages;

    @Override
    public void execute(QuoteReminderCommand command) {

        //Toplu bildirim için biriktirme alanı. Kullanıcı ve gönderilecek listesi.
        Map<String, List<QuoteViewModel>> cumulatives = new HashMap<>();

        Date date = DateUtils.getDateAfterPeriod(command.getInterval(), new Date());

        List<QuoteViewModel> ls = repository.findExiperedQuotes(date);

        //TODO: Owner dışında aslında grubun tamamına göndermek de bir başka seçenek ( ki bu tercih edilebilir )
        // Owner dışı gruba önderme de sorun var. Grup bir ağaç ve nereye kadar gönderileceği biraz dertli. Belki manager'a gönderme yöntemi seçilebilir.
        // Ya da parametre olarak gönderim yapılacak grup seçimi istenebilir bilemiyorum.
        for (QuoteViewModel quote : ls) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");

            nc.setTarget("cs=user;id=".concat(quote.getOwner()));
            nc.setSubject(messages.getFormatedMessage("QuoteReminderCommand.subject.single", quote.getVoucherNo()));

            Map<String, Object> params = new HashMap<>();

            params.put("Account", quote.getAccount().getName());
            params.put("AccountId", quote.getAccount().getId());

            params.put("VoucherId", quote.getId());
            params.put("VoucherTopic", quote.getTopic());
            params.put("VoucherNo", quote.getVoucherNo());

            nc.setParams(params);

            commandSender.sendCommand(nc);

            //Toplu gönderim için biriktirme
            List<QuoteViewModel> quotes = cumulatives.get(quote.getOwner());
            if (quotes == null) {
                quotes = new ArrayList<>();
                cumulatives.put(quote.getOwner(), quotes);
            }
            quotes.add(quote);

        }

        for (Map.Entry<String, List<QuoteViewModel>> entry : cumulatives.entrySet()) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");
            nc.setTemplate("CUMULATIVE");

            nc.setTarget("cs=user;id=".concat(entry.getKey()));
            nc.setSubject(messages.getFormatedMessage("QuoteReminderCommand.subject.cumulative"));

            Map<String, Object> params = new HashMap<>();

            params.put("Quotes", entry.getValue());

            nc.setParams(params);

            commandSender.sendCommand(nc);
        }

    }

}
