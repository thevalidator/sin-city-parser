/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.connection;

import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import ru.thekrechetofficial.sincityparser.entity.Proxy;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public interface ConnectionService {
    
    Map<String, String> getCoockies() throws IOException;
    
    Connection getConnection(Proxy proxy);
    
    Connection getProxyConnection(String url);
    
    Connection getConnectionForProxyCheck(String url, Proxy proxy);
    
}
