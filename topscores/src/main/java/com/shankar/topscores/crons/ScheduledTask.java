package com.shankar.topscores.crons;


import com.shankar.topscores.service.CronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTask {
    @Autowired
    private CronService cronService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**Cron to delete Extra Elements**/
//    @Scheduled(cron = "0 10 1 * * ?")
    @Scheduled(cron = "0 42 12 * * ?")
    public void scheduleTaskWithCronExpression() {
        cronService.runCron();
        logger.info("Cron Task for Deletion ran :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
}
