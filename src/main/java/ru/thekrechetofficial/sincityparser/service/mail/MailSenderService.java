/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.mail;


/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public interface MailSenderService {
    
    void testSend(String email);
    
    void sendInvitation(String... emails);
    
}
