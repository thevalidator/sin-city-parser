/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.thekrechetofficial.sincityparser.dto.OfferDTO;
import ru.thekrechetofficial.sincityparser.entity.NLAd;
import ru.thekrechetofficial.sincityparser.service.connection.ConnectionService;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Service
public class NLParseServiceImpl implements NLParseService {

    private static final Logger LOGGER = LogManager.getLogger(NLParseServiceImpl.class.getName());
    private static final Pattern TIME = Pattern.compile("(?<day>\\d{2})."
                                        + "(?<month>\\d{2}).(?<year>\\d{4}) в "
                                        + "(?<hour>\\d{2}):(?<minute>\\d{2}$)");
    private static Matcher matcher;
    private final ConnectionService connectionService;
    private final String domain;

    @Autowired
    public NLParseServiceImpl(ConnectionService connectionService,
            @Value("${resource.nlads}") String domain) {
        this.connectionService = connectionService;
        this.domain = domain;
    }

    @Override
    public List<String> getAdsOffers(Gender from, Map<String, String> coockies) throws IOException {

        String offersURL = domain + "/offers.php?from=" + from.getValue() + "&to=&section=" + Section.ALL.getValue();

        Document html = connectionService
                            .getProxyConnection(offersURL)
                            .cookies(coockies)
                            .referrer(domain + "/offers.php")
                            .get();
        
        Element offersBlock = html.selectFirst("body > table.hdr14 > tbody > tr:nth-child(1) > td > div:nth-child(6)");
        
        if (offersBlock == null) {
            throw new IOException("Offers block is empty...");
        }

        Elements links = offersBlock.getElementsByTag("a");
        List<String> parsedOffers = new ArrayList<>();

        for (Element link : links) {
            
            String offerId = link.attr("href").substring(13);
            parsedOffers.add(offerId);
        }

        return parsedOffers;

    }

    @Override
    public NLAd getAd(OfferDTO offer, Map<String, String> coockies) throws IOException, IllegalArgumentException {

        String offerURL = domain + "/offer.php?id=" + offer.getOfferId();

        Document html = connectionService
                .getProxyConnection(offerURL)
                .cookies(coockies)
                .referrer(domain + "/offers.php?from=" + offer.getFrom().getValue() + "&to=&section=" + Section.ALL.getValue())
                .get();

        Element adName = html.selectFirst("body > table.hdr14 > tbody > tr:nth-child(1) > td > h1");
        Element adData = html.selectFirst("body > table.hdr14 > tbody > tr:nth-child(1) > td > table > tbody > tr > td > div");
        
        if (adData == null || adName == null) {
            LOGGER.error("Empty html section for offer: {}", offer.getOfferId());
            throw new IOException("Ad's block is empty. Offer: " + offer.getOfferId());
        }

        Elements textElem = adData.select("div.ar12");
        Elements placeAndContactElem = adData.select("div:not([class])");

        String title = adName.text();
        String text;
        String place = null;
        String contact = null;
        String dateBlock = adData.select("div > table > tbody > tr > td").text();

        if (textElem.size() > 1) {
            text = textElem.get(1).text();
        } else {
            text = textElem.text();
        }

        if (placeAndContactElem.size() > 1) {
            place = placeAndContactElem.get(0).text();
            contact = placeAndContactElem.get(1).text();
        } else if (placeAndContactElem.size() == 1) {
            place = "Не указано";
            contact = placeAndContactElem.get(0).text();
        }

        LocalDateTime timeStamp = null;
        matcher = TIME.matcher(dateBlock);
        if (matcher.find()) {
            
            int day = Integer.parseInt(matcher.group("day"));
            int month = Integer.parseInt(matcher.group("month"));
            int year = Integer.parseInt(matcher.group("year"));
            int hour = Integer.parseInt(matcher.group("hour"));
            int minute = Integer.parseInt(matcher.group("minute"));
            
            timeStamp = LocalDateTime.of(year, month, day, hour, minute);

        } else {
            LOGGER.error("DateTime parse problem for offer: {}", offer.getOfferId());
            throw new IllegalArgumentException("No matches for timeStamp found: " + dateBlock);
        }

        NLAd ad = new NLAd(offer.getOfferId(), title, text, place, contact, timeStamp, offer.getFrom());

        return ad;
    }

}
