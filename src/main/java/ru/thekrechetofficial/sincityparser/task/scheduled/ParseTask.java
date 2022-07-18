/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.task.scheduled;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import ru.thekrechetofficial.sincityparser.task.ScheduledParseTask;
import ru.thekrechetofficial.sincityparser.task.ScheduledSendInviteTask;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Service
public class ParseTask implements SchedulingConfigurer {
    
    
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static final Random random = new Random();
    private final ScheduledParseTask task;
    private final ScheduledSendInviteTask inviteTask;
    

    @Autowired
    public ParseTask(ScheduledParseTask task, ScheduledSendInviteTask inviteTask) {
        this.task = task;
        this.inviteTask = inviteTask;
    }
    

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        
        taskRegistrar.addCronTask(inviteTask, "0 5 13,19 * * *");
        
        taskRegistrar.addTriggerTask(task,
                (TriggerContext triggerContext) -> {
                    
                    long plusTime = 60_000 * 15;        // 15 minutes
                    LocalTime lt = LocalTime.now();
                    if (lt.isAfter(LocalTime.of(1, 30)) && lt.isBefore(LocalTime.of(9, 30))) {
                        plusTime = 60_000 * 60 * 2;     // 2 hours
                    } else if (lt.isAfter(LocalTime.of(9, 30)) && lt.isBefore(LocalTime.of(12, 30))) {
                        plusTime = 60_000 * 60;         // 1 hour
                    } else if (lt.isAfter(LocalTime.of(12, 30)) && lt.isBefore(LocalTime.of(17, 30))) {
                        plusTime = 60_000 * 30;         // 30 minutes
                    }
                      
                    long plusRandomMin = 60_000 * random.nextLong(4);
                    Date startDate = new Date();
                    startDate.setTime(new Date().getTime() + plusTime + plusRandomMin);
                    
                    ScheduledParseTask.setNextRun(formatter.format(startDate));
                    
                    return startDate;
                });
        
    }
    
}
