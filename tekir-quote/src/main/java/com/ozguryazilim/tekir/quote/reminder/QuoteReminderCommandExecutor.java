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
import com.ozguryazilim.telve.notification.NotificationCommand;
import com.ozguryazilim.telve.utils.DateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@CommandExecutor(command = QuoteReminderCommand.class)
public class QuoteReminderCommandExecutor extends AbstractCommandExecuter<QuoteReminderCommand>{

    private static final Logger LOG = LoggerFactory.getLogger(QuoteReminderCommandExecutor.class);
    
    @Inject
    private QuoteRepository repository;
    
    @Inject
    private CommandSender commandSender;
    
    @Override
    public void execute(QuoteReminderCommand command) {
        Date date = DateUtils.getDateAfterPeriod(command.getInterval(), new Date());
        
        List<QuoteViewModel> ls = repository.findExiperedQuotes(date);
        
        //TODO: Toplu hatırlatma e-postası daha iyi olabilir tek tek göndermekten
        //TODO: Owner dışında aslında grubun tamamına göndermek de bir başka seçenek ( ki bu tercih edilebilir )
        
        for( QuoteViewModel quote : ls ){
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");
            
            nc.setTarget("cs=user;id=".concat(quote.getOwner()));
            nc.setSubject("Geçerlilik süresi dolmak üzere olan teklif : " + quote.getVoucherNo());
            
            Map<String, Object> params = new HashMap<>();
            
            params.put("Account", quote.getAccount().getName());
            params.put("AccountId", quote.getAccount().getId());
            
            params.put("VoucherId", quote.getId());
            params.put("VoucherTopic", quote.getTopic());
            params.put("VoucherNo", quote.getVoucherNo());
            
            nc.setParams(params);
            
            commandSender.sendCommand(nc);
        }
        
    }
    
}
