/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.mail.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.thekrechetofficial.sincityparser.service.mail.MailSenderService;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {

    private static final Logger LOGGER = LogManager.getLogger(MailSenderServiceImpl.class.getName());
    private final JavaMailSender mailSender;
    private final String emailBody;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender mailSender, @Value("${resource.mail.body.path}") String emailBody) {
        this.mailSender = mailSender;
        this.emailBody = emailBody;
    }

    @Override
    public void testSend(String email) {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email);
        msg.setSubject("Testing from illusion");
        msg.setText("YO! If you see this - everything is fine!");

        mailSender.send(msg);

    }

    @Override
    public void sendInvitation(String... emails) {

        try {

            StringBuilder sb = new StringBuilder();
            
            try ( InputStream is = this.getClass().getClassLoader().getResourceAsStream(emailBody);
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr)) 
            {

                if (is == null) {
                    throw new FileNotFoundException(emailBody + " is not found");
                }
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }

            String text = sb.toString();

            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setBcc(emails);
            helper.setFrom("sincitynightsbot@gmail.com", "Sin City");
            helper.setSubject("Intimcity");
            helper.setText(text, true);

            mailSender.send(msg);

        } catch (MessagingException ex) {

            LOGGER.error("Error sending email: {}", ex.getMessage());
        } catch (IOException ex) {
            LOGGER.error("File error: {}", ex.getMessage());
        }

    }

}
