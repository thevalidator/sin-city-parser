/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.thekrechetofficial.sincityparser.dto.OfferDTO;
import ru.thekrechetofficial.sincityparser.entity.NLAd;
import ru.thekrechetofficial.sincityparser.service.nl.NLAdService;
import ru.thekrechetofficial.sincityparser.service.connection.ConnectionService;
import ru.thekrechetofficial.sincityparser.service.parser.NLParseService;
import ru.thekrechetofficial.sincityparser.service.parser.Gender;
import ru.thekrechetofficial.sincityparser.service.proxy.ProxyManager;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Component
public class NLAdsManager {

    private static final Logger LOGGER = LogManager.getLogger(NLAdsManager.class.getName());
    private static final Random random = new Random();
    private final NLParseService parseService;
    private final ProxyManager manager;
    private final NLAdService service;
    private final ConnectionService connService;

    @Autowired
    public NLAdsManager(NLParseService parseService,
            ProxyManager manager,
            NLAdService service,
            ConnectionService connService) {

        this.manager = manager;
        this.parseService = parseService;
        this.service = service;
        this.connService = connService;

    }

    public int parseAndSaveAds() throws IOException, IllegalArgumentException, InterruptedException {

        Map<String, String> cookies = connService.getCookies();
        Set<OfferDTO> offers = scanOffers(cookies);
        LOGGER.info("Found new offers: {}", offers.size());
        int counter = 0;

        List<String> ads = new ArrayList<>();

        for (OfferDTO offer : offers) {

            NLAd ad;
            try {
                ad = parseService.getAd(offer, cookies);
                LOGGER.info("\tsaving offer: {}", ad.getOfferId());
                saveAd(ad);
                ads.add(ad.getOfferId());
                counter++;
            } catch (IOException ex) {
                //saveAds(ads);
                LOGGER.info("Error found, new offers forced saved: {}", counter);

                ads.removeAll(service.getExist(ads));
                if (!ads.isEmpty()) {
                    for (String o : ads) {
                        LOGGER.error("\tCS  ERROR offer LOG: {}", o);
                    }
                } else {
                    LOGGER.info("\tCS  PARSING OK LOG!");
                }
                

                throw ex;
            }

            if (counter % 70 == 0) {
                manager.changeProxy();
            }
            
            TimeUnit.SECONDS.sleep(random.nextInt(3) + 1);

        }

        ads.removeAll(service.getExist(ads));
        LOGGER.info("Total new offers saved: {}", counter);
        if (!ads.isEmpty()) {
            for (String o : ads) {
                LOGGER.error("\t ERROR offer LOG: {}", o);
            }
        } else {
            LOGGER.info("\t PARSING OK LOG!");
        }

        // LOGGER.info("Total new offers saved: {}", counter);
        return counter;

    }

    private Set<OfferDTO> scanOffers(Map<String, String> cookies) throws IOException, InterruptedException {

        Set<OfferDTO> offers = new HashSet<>();

        for (Gender from : Gender.values()) {

            List<String> parsedOffers = parseService.getAdsOffers(from, cookies);
            TimeUnit.SECONDS.sleep(1);
            List<String> existOffers = service.getExist(parsedOffers);
            parsedOffers.removeAll(existOffers);

            for (String offer : parsedOffers) {
                offers.add(new OfferDTO(offer, from));
            }

        }

        return offers;

    }

    private void saveAds(List<NLAd> ads) {
        service.saveAll(ads);
    }

    private void saveAd(NLAd ad) {
        service.save(ad);
    }

}
