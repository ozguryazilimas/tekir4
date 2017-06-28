/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase.reminder;

import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.order.config.OrderPages.Purchase;
import com.ozguryazilim.tekir.order.purchase.PurchaseOrderRepository;
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
 * Kapanmamış satın alım siparişleri için belirtilen süreden sonra hatırlatma mesajı gönderir.
 * 
 * Satın Alım siparişi oluşturulduğu tarihten itibaren {@link PurchaseOrderReminderCommand#interval} 
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
 * @see PurchaseOrderReminderCommand
 */
@CommandExecutor(command = PurchaseOrderReminderCommand.class)
public class PurchaseOrderReminderCommandExecutor extends AbstractCommandExecuter<PurchaseOrderReminderCommand> {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseOrderReminderCommandExecutor.class);

    @Inject
    private PurchaseOrderRepository repository;

    @Inject
    private CommandSender commandSender;
    
    @Inject 
    private FormatedMessage messages;

    /**
     * Satın Alım Siparişi komutunu çalıştırır.
     * 
     * {@inheritDoc}
     * 
     * {@link PurchaseOrderReminderCommand#getInterval()}'dan gelen süreden sonra
     * {@link PurchaseOrderRepository#findExpiredOrder(java.util.Date)} metodundan
     * elde edilen kapanmamış olan satın alım siparişleri web kanalı ve mail olarak 
     * {@link FormatedMessage#getFormatedMessage(java.lang.String, java.lang.Object...)} ile biçimlendirilmiş
     * {@link CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command)}
     * metoduyla bildirim olarak gönderilmektedir.
     * 
     * @param command Çalıştırılacak satın alım siparişi komutu 
     * @see PurchaseOrder
     * @see NotificationCommand
     * @see PurchaseOrderReminderCommand#getInterval() 
     * @see DateUtils#getDateBeforePeriod(java.lang.String, java.util.Date)
     * @see PurchaseOrderRepository#findExpiredOrder(java.util.Date) 
     * @see FormatedMessage#getFormatedMessage(java.lang.String, java.lang.Object[]) 
     * @see CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command) 
     */
    @Override
    public void execute(PurchaseOrderReminderCommand command) {

        //Toplu bildirim için biriktirme alanı. Kullanıcı ve gönderilecek listesi.
        Map<String, List<PurchaseOrder>> cumulatives = new HashMap<>();

        Date date = DateUtils.getDateAfterPeriod(command.getInterval(), new Date());

        List<PurchaseOrder> ls = repository.findExpiredOrder(date);

        //TODO: Owner dışında aslında grubun tamamına göndermek de bir başka seçenek ( ki bu tercih edilebilir )
        // Owner dışı gruba önderme de sorun var. Grup bir ağaç ve nereye kadar gönderileceği biraz dertli. Belki manager'a gönderme yöntemi seçilebilir.
        // Ya da parametre olarak gönderim yapılacak grup seçimi istenebilir bilemiyorum.
        for (PurchaseOrder purchaseOrder : ls) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");

            nc.setTarget("cs=user;id=".concat(purchaseOrder.getOwner()));
            nc.setSubject(messages.getFormatedMessage("PurchaseOrderReminderCommand.subject.single", purchaseOrder.getVoucherNo()));

            Map<String, Object> params = new HashMap<>();

            params.put("AccountName", purchaseOrder.getAccount().getName());
            params.put("AccountId", purchaseOrder.getAccount().getId());

            params.put("VoucherId", purchaseOrder.getId());
            params.put("VoucherTopic", purchaseOrder.getTopic());
            params.put("VoucherNo", purchaseOrder.getVoucherNo());

            nc.setParams(params);

            commandSender.sendCommand(nc);

            //Toplu gönderim için biriktirme
            List<PurchaseOrder> purchaseOrders = cumulatives.get(purchaseOrder.getOwner());
            if (purchaseOrders == null) {
                purchaseOrders = new ArrayList<>();
                cumulatives.put(purchaseOrder.getOwner(), purchaseOrders);
            }
            purchaseOrders.add(purchaseOrder);

        }

        for (Map.Entry<String, List<PurchaseOrder>> entry : cumulatives.entrySet()) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");
            nc.setTemplate("CUMULATIVE");

            nc.setTarget("cs=user;id=".concat(entry.getKey()));
            nc.setSubject(messages.getFormatedMessage("PurchaseOrderReminderCommand.subject.cumulative"));

            Map<String, Object> params = new HashMap<>();

            params.put("PurchaseOrders", entry.getValue());

            nc.setParams(params);

            commandSender.sendCommand(nc);
        }

    }

}
