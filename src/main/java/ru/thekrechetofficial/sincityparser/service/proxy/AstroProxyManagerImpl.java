/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.proxy;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.thekrechetofficial.sincityparser.client.AstroProxiApiClient;
import ru.thekrechetofficial.sincityparser.entity.Proxy;
import ru.thekrechetofficial.sincityparser.service.connection.UserAgent;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Service
public class AstroProxyManagerImpl implements ProxyManager {

    private static final Logger LOGGER = LogManager.getLogger(AstroProxyManagerImpl.class);
    private final String portId;
    private final AstroProxiApiClient apiClient;
    private final Proxy proxy;
    private final String domain;
    private long timestamp;

    @Autowired
    public AstroProxyManagerImpl(
            @Value("${proxy.api.port.port}") String proxyPort,
            @Value("${proxy.api.port.ip}") String proxyIp,
            @Value("${proxy.api.port.id}") String portId,
            @Value("${resource.nlads}") String domain,                  // delete after fix checkProxy()
            AstroProxiApiClient apiClient
    ) {
        this.portId = portId;
        this.apiClient = apiClient;
        this.proxy = new Proxy(proxyIp, Integer.valueOf(proxyPort));
        this.domain = domain;
        timestamp = System.currentTimeMillis();
    }

    @Override
    public Proxy getProxy() {
        return proxy;
    }

    @Override
    public String changeProxy() {
        
        if (System.currentTimeMillis() - timestamp < 30_000) {
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        
        String newIP = apiClient.renewExternalIP(portId);
        timestamp = System.currentTimeMillis();
        //LOGGER.info("external proxy IP changed: {}", newIP);
        
        return newIP;
    }

    @Override
    public boolean checkProxy() {

        boolean result = false;

        try {

            Connection.Response r = Jsoup.connect(domain)
                    .proxy(proxy.getIp(), proxy.getPort())
                    .userAgent(UserAgent.getRandomValue())
                    .timeout(45_000)
                    .ignoreHttpErrors(true).execute();
            if (r.statusCode() == HttpStatus.OK.value()) {
                result = true;
            }

        } catch (IOException ex) {
            LOGGER.error("check proxy error: {}", ex.getMessage());
        } finally {
            return result;
        }


        //TODO: try to make check method with connection manager

    }

}
