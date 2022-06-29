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
import ru.thekrechetofficial.sincityparser.task.SchedulingParseTask;
//import java.util.concurrent.TimeUnit;
//import ru.thekrechetofficial.sincityparser.core.NLAdsManager;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Service
public class ParseTask implements SchedulingConfigurer {
    
    
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static final Random random = new Random();
    private final SchedulingParseTask task;
    

    @Autowired
    public ParseTask(SchedulingParseTask task) {
        this.task = task;
    }
    

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        
        taskRegistrar.addTriggerTask(
//                () -> {//NLAdsManager.setIsParsing(true);
//                    System.out.println(" SCHEDULED TASK START"); try {
//                    TimeUnit.SECONDS.sleep(10);
//                    System.out.println(" SCHEDULED TASK END");
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(ParseTask.class.getName()).log(Level.SEVERE, null, ex);
//                    } finally {
//                        //NLAdsManager.setIsParsing(false);
//                    }
//                },
                task,
                //() -> System.out.println("task"),
                (TriggerContext triggerContext) -> {
                    
                    //long plusTime = 60_000 * 1;
                    long plusTime = 60_000 * 15;        // 15 minutes
                    LocalTime lt = LocalTime.now();
                    if (lt.isAfter(LocalTime.of(1, 30)) && lt.isBefore(LocalTime.of(9, 30))) {
                        plusTime = 60_000 * 60 * 2;     // 2 hours
                    } else if (lt.isAfter(LocalTime.of(9, 30)) && lt.isBefore(LocalTime.of(12, 30))) {
                        plusTime = 60_000 * 60;         // 1 hour
                    } else if (lt.isAfter(LocalTime.of(12, 30)) && lt.isBefore(LocalTime.of(17, 30))) {
                        plusTime = 60_000 * 30;         // 30 minutes
                    }
                    
                    //System.out.println(">>>  SCHEDULED ");
                    //plusTime = 60_000 * 1;
                    
                    //long plusRandomMin = 0;   
                    long plusRandomMin = 60_000 * random.nextLong(4);
                    Date startDate = new Date();
                    startDate.setTime(new Date().getTime() + plusTime + plusRandomMin);
                    
                    SchedulingParseTask.setNextRun(formatter.format(startDate));
                    
                    return startDate;
                });
        
    }

}
