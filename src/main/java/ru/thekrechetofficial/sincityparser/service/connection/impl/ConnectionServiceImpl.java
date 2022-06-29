/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.connection.impl;

import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.thekrechetofficial.sincityparser.entity.Proxy;
import ru.thekrechetofficial.sincityparser.service.connection.ConnectionService;
import ru.thekrechetofficial.sincityparser.service.connection.UserAgent;
import ru.thekrechetofficial.sincityparser.service.proxy.ProxyManager;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Service
public class ConnectionServiceImpl implements ConnectionService {

    private static final int TIMEOUT = 45_000;
    private final ProxyManager proxyManger;
    private final String domain;

    @Autowired
    public ConnectionServiceImpl(ProxyManager proxyManger, @Value("${resource.nlads}") String domain) {
        this.proxyManger = proxyManger;
        this.domain = domain;
    }

    @Override
    public Map<String, String> getCookies() throws IOException {
        
        Connection.Response response = Jsoup.connect(domain + "/offers.php")     //@Value("${resource.nlads}") String domain
                .proxy(proxyManger.getProxy().getIp(), proxyManger.getProxy().getPort())
                .userAgent(UserAgent.getRandomValue())
                .referrer(domain + "/")
                .timeout(TIMEOUT)
                .ignoreHttpErrors(true).execute();
        
        return response.cookies();
        
    }

    @Override
    public Connection getConnection(Proxy proxy) {

        return Jsoup.connect(domain)
                .proxy(proxy.getIp(), proxy.getPort())
                .userAgent(UserAgent.getRandomValue())
                .timeout(TIMEOUT)
                .ignoreHttpErrors(true);

    }

    @Override
    public Connection getProxyConnection(String url) {

        String agent = UserAgent.getRandomValue();

        return Jsoup.connect(url)
                .proxy(proxyManger.getProxy().getIp(), proxyManger.getProxy().getPort())
                .userAgent(agent)
                .timeout(TIMEOUT)
                .ignoreHttpErrors(true);

    }

    @Override
    public Connection getConnectionForProxyCheck(String url, Proxy proxy) {

        return Jsoup.connect(url)
                .proxy(proxy.getIp(), proxy.getPort())
                .userAgent(UserAgent.AGENT_01.getValue())
                .timeout(TIMEOUT)
                .ignoreHttpErrors(true)
                .maxBodySize(10_000);

    }

}
