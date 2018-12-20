package com.ozguryazilim.tekir.activity.reminder;

import com.ozguryazilim.tekir.activity.ActivityRepository;
import com.ozguryazilim.tekir.entities.Activity;
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
 * Kapanmamış aktiviteler için belirtilen süreden sonra hatırlatma mesajı gönderir.
 * 
 * Aktivite oluşturulduğu tarihten itibaren {@link ActivityReminderCommand#interval} 
 * olarak belirtilen süre geçtikten sonra hatırlatma gönderilir.
 * 
 * WEB kanalından tek tek gönderirken e-posta olarak topluca her kişiye 1
 * e-posta gönderir.
 *
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-21
 * @see AbstractCommandExecuter
 * @see CommandExecutor
 * @see ActivityReminderCommand
 */
@CommandExecutor(command = ActivityReminderCommand.class)
public class ActivityReminderCommandExecutor extends AbstractCommandExecuter<ActivityReminderCommand> {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityReminderCommandExecutor.class);

    @Inject
    private ActivityRepository repository;

    @Inject
    private CommandSender commandSender;
    
    @Inject 
    private FormatedMessage messages;

    /**
     * Aktivite komutunu çalıştırır.
     * 
     * {@inheritDoc}
     * 
     * {@link ActivityReminderCommand#getInterval()}'dan gelen süreden sonra
     * {@link ActivityRepository#findExpiredActivities(java.util.Date)} metodundan
     * elde edilen kapanmamış olan aktiviteler web kanalı ve mail olarak 
     * {@link FormatedMessage#getFormatedMessage(java.lang.String, java.lang.Object...)} ile biçimlendirilmiş
     * {@link CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command)}
     * metoduyla bildirim olarak gönderilmektedir.
     * 
     * @param command Çalıştırılacak aktivite komutu 
     * @see Activity
     * @see NotificationCommand
     * @see ActivityReminderCommand#getInterval() 
     * @see DateUtils#getDateAfterPeriod(java.lang.String, java.util.Date)
     * @see ActivityRepository#findExpiredActivities(java.util.Date) 
     * @see FormatedMessage#getFormatedMessage(java.lang.String, java.lang.Object...) 
     * @see CommandSender#sendCommand(com.ozguryazilim.telve.messagebus.command.Command) 
     */
    @Override
    public void execute(ActivityReminderCommand command) {

        //Toplu bildirim için biriktirme alanı. Kullanıcı ve gönderilecek listesi.
        Map<String, List<Activity>> cumulatives = new HashMap<>();

        Date date = DateUtils.getDateAfterPeriod(command.getInterval(), new Date());

        List<Activity> ls = repository.findExpiredActivities(date);

        //TODO: Owner dışında aslında grubun tamamına göndermek de bir başka seçenek ( ki bu tercih edilebilir )
        // Owner dışı gruba önderme de sorun var. Grup bir ağaç ve nereye kadar gönderileceği biraz dertli. Belki manager'a gönderme yöntemi seçilebilir.
        // Ya da parametre olarak gönderim yapılacak grup seçimi istenebilir bilemiyorum.
        for (Activity activity : ls) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");

            nc.setTarget("cs=user;id=".concat(activity.getAssignee()));
            nc.setSubject(messages.getFormatedMessage("ActivityReminderCommand.subject.single", activity.getDueDate(), activity.getSubject()));

            Map<String, Object> params = new HashMap<>();

            params.put("PersonName", activity.getPerson().getName());
            params.put("PersonId", activity.getPerson().getId());

            params.put("ActivityId", activity.getId());
            params.put("ActivitySubject", activity.getSubject());
            params.put("ActivityBody", activity.getBody());
            params.put("ActivityAssignee", activity.getAssignee());

            nc.setParams(params);

            commandSender.sendCommand(nc);

            //Toplu gönderim için biriktirme
            List<Activity> activities = cumulatives.get(activity.getAssignee());
            if (activities == null) {
                activities = new ArrayList<>();
                cumulatives.put(activity.getAssignee(), activities);
            }
            activities.add(activity);

        }

        for (Map.Entry<String, List<Activity>> entry : cumulatives.entrySet()) {
            NotificationCommand nc = new NotificationCommand();
            nc.setNotificationClass(command.getClass().getSimpleName());
            nc.setSender("SYSTEM");
            nc.setTemplate("CUMULATIVE");

            nc.setTarget("cs=user;id=".concat(entry.getKey()));
            nc.setSubject(messages.getFormatedMessage("ActivityReminderCommand.subject.cumulative"));

            Map<String, Object> params = new HashMap<>();

            params.put("Activities", entry.getValue());

            nc.setParams(params);

            commandSender.sendCommand(nc);
        }

    }

}
