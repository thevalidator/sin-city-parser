/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.nl;

import java.util.List;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public interface InviteService {
    
    List<String> getDistinctEmails();

    public void saveAll(List<String> emails);
    
}
