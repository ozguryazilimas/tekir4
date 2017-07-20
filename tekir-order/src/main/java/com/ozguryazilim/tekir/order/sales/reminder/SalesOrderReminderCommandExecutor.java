/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.sales.reminder;

import com.ozguryazilim.tekir.order.reminder.OrderReminderCommandType;
import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.order.sales.SalesOrderRepository;
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
 * Kapanmamış satış siparişleri için belirtilen süreden sonra hatırlatma mesajı gönderir.
 * 
 * Satış siparişi oluşturulduğu tarihten itibaren {@link SalesOrderReminderCommand#interval} 
 * olarak belirtilen süre geçtikten sonra hatırlatma gönderilir.
 * 
 * WEB kanalından tek tek gönderirken e-posta olarak topluca her kişiye 1
 * e-posta gönderir.
 *
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-28
 * @see AbstractCommandExecuter
 * @see CommandExecutor
 * @see SalesOrderReminderCommand
 */
@CommandExecutor(command = SalesOrderReminderCommand.class)
public class SalesOrderReminderCommandExecutor extends AbstractCommandExecuter<SalesOrderReminderCommand> {

    private static final Logger LOG = LoggerFactory.getLogger(SalesOrderReminderCommandExecutor.class);

    @Inject
    private SalesOrderRepository repository;

    @Inject
    private CommandSender commandSender;
    
    @Inject 
    private FormatedMessage messages;

    /**
     * Satış Siparişi komutunu çalıştırır.
     * 
     * {@inheritDoc}
     * 
     * {@link SalesOrderReminderCommand#getInterval()}'dan gelen süreden sonra
     * {@link SalesOrderRepository#findUnclosedOrder(java.util.Date)} metodundan
     * elde edilen kapanmamış olan satış siparişleri web kanalı ve mail olarak 
     * {@link FormatedMessage#getFormatedMessage(java.lang.String, java.lang.Object...)} ile biçimlendirilmiş
     * {@link CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command)}
     * metoduyla bildirim olarak gönderilmektedir.
     * 
     * @param command Çalıştırılacak satış siparişi komutu 
     * @see SalesOrder
     * @see NotificationCommand
     * @see SalesOrderReminderCommand#getInterval() 
     * @see DateUtils#getDateBeforePeriod(java.lang.String, java.util.Date)
     * @see DateUtils#getDateAfterPeriod(java.lang.String, java.util.Date)
     * @see OrderReminderCommandType
     * @see SalesOrderRepository#findUnclosedOrder(java.util.Date) 
     * @see FormatedMessage#getFormatedMessage(java.lang.String, java.lang.Object[]) 
     * @see CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command) 
     */
    @Override
    public void execute(SalesOrderReminderCommand command) {

        //Toplu bildirim için biriktirme alanı. Kullanıcı ve gönderilecek listesi.
        Map<String, List<SalesOrder>> cumulatives = new HashMap<>();
        
        Date date = null;
        
        // Sipariş komutu tipi ve aralığına göre şimdiki zamanla hangi tarihin arasında
        // arama yapılacağını hesaplıyoruz.
        if(OrderReminderCommandType.UPCOMING == command.getType()) {
            date = DateUtils.getDateAfterPeriod(command.getInterval(), new Date());
        } 
        else if(OrderReminderCommandType.EXPIRED == command.getType()) {
            date = DateUtils.getDateBeforePeriod(command.getInterval(), new Date());
        }

        List<SalesOrder> ls = repository.findUnclosedOrder(date);

        //TODO: Owner dışında aslında grubun tamamına göndermek de bir başka seçenek ( ki bu tercih edilebilir )
        // Owner dışı gruba önderme de sorun var. Grup bir ağaç ve nereye kadar gönderileceği biraz dertli. Belki manager'a gönderme yöntemi seçilebilir.
        // Ya da parametre olarak gönderim yapılacak grup seçimi istenebilir bilemiyorum.
        for (SalesOrder salesOrder : ls) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");

            nc.setTarget("cs=user;id=".concat(salesOrder.getOwner()));
            nc.setSubject(messages.getFormatedMessage("SalesOrderReminderCommand.subject.single", salesOrder.getVoucherNo()));

            Map<String, Object> params = new HashMap<>();

            params.put("AccountName", salesOrder.getAccount().getName());
            params.put("AccountId", salesOrder.getAccount().getId());

            params.put("VoucherId", salesOrder.getId());
            params.put("VoucherTopic", salesOrder.getTopic());
            params.put("VoucherNo", salesOrder.getVoucherNo());

            nc.setParams(params);

            commandSender.sendCommand(nc);

            //Toplu gönderim için biriktirme
            List<SalesOrder> salesOrders = cumulatives.get(salesOrder.getOwner());
            if (salesOrders == null) {
                salesOrders = new ArrayList<>();
                cumulatives.put(salesOrder.getOwner(), salesOrders);
            }
            salesOrders.add(salesOrder);

        }

        for (Map.Entry<String, List<SalesOrder>> entry : cumulatives.entrySet()) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");
            nc.setTemplate("CUMULATIVE");

            nc.setTarget("cs=user;id=".concat(entry.getKey()));
            nc.setSubject(messages.getFormatedMessage("SalesOrderReminderCommand.subject.cumulative"));

            Map<String, Object> params = new HashMap<>();

            params.put("SalesOrders", entry.getValue());

            nc.setParams(params);

            commandSender.sendCommand(nc);
        }

    }

}
