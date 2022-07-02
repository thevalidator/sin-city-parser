/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import ru.thekrechetofficial.sincityparser.dto.OfferDTO;
import ru.thekrechetofficial.sincityparser.entity.NLAd;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public interface NLParseService {
    
    NLAd getAd(OfferDTO offer, Map<String, String> cookies) throws IOException;

    List<String> getAdsOffers(Gender from, Map<String, String> cookies) throws IOException;
    
}
