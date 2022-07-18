/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.task;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.thekrechetofficial.sincityparser.service.mail.MailSenderService;
import ru.thekrechetofficial.sincityparser.service.nl.InviteService;


/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Component
public class ScheduledSendInviteTask implements Runnable {
    
    private static final Logger LOGGER = LogManager.getLogger(ScheduledSendInviteTask.class);
    private final InviteService inviteService;
    private final MailSenderService mailService;

    @Autowired
    public ScheduledSendInviteTask(InviteService inviteService, MailSenderService mailService) {
        this.inviteService = inviteService;
        this.mailService = mailService;
    }
    

    @Override
    public void run() {
        
        List<String> emails = inviteService.getDistinctEmails();
        if (!emails.isEmpty()) {
            mailService.sendInvitation(emails.toArray(String[]::new));
            inviteService.saveAll(emails);
        }
        
        LOGGER.info("INVINTATIONS: {}", emails.size());
        
    }
    
}
