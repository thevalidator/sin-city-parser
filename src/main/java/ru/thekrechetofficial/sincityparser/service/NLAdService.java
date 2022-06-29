/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service;

import java.util.List;
import ru.thekrechetofficial.sincityparser.entity.NLAd;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public interface NLAdService {
    
    void save(NLAd ad);
    void saveAll(List<NLAd> ad);

    List<String> getExist(List<String> parsedOffers);
    
}
