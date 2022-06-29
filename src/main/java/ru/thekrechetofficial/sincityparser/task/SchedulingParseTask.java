/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.thekrechetofficial.sincityparser.core.NLAdsManager;
import ru.thekrechetofficial.sincityparser.service.proxy.ProxyManager;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Component
public class SchedulingParseTask implements Runnable {

    private static volatile String nextRun;
    private volatile static boolean isParsing = false;
    private volatile static LocalDateTime lastParse = LocalDateTime.MIN;
    private static final Logger LOGGER = LogManager.getLogger(SchedulingParseTask.class);
    
    private final NLAdsManager adsManager;
    private final ProxyManager proxyManager;

    @Autowired
    public SchedulingParseTask(NLAdsManager adsManager, ProxyManager proxyManager) {
        this.adsManager = adsManager;
        this.proxyManager = proxyManager;
    }

    public void parseTask() {

        if (isParsing == false) {

            try {

                if (!proxyManager.checkProxy()) {
                    proxyManager.changeProxy();
                    TimeUnit.SECONDS.sleep(3);
                }
                isParsing = true;
                adsManager.parseAndSaveAds();
                lastParse = LocalDateTime.now();
                
            } catch (IOException | IllegalArgumentException ex) {
                proxyManager.changeProxy();
                LOGGER.error(ex.getMessage());
            } catch (InterruptedException ex) {
                LOGGER.error(ex.getMessage());
            } finally {
                isParsing = false;
            }
        }

    }

    @Override
    public void run() {

        parseTask();
    }

    public static LocalDateTime getLastParse() {
        return lastParse;
    }

    public static boolean isParsing() {
        return isParsing;
    }

    public static String getNextRun() {
        return nextRun;
    }

    public static void setNextRun(String nextRun) {
        SchedulingParseTask.nextRun = nextRun;
    }
    
}
