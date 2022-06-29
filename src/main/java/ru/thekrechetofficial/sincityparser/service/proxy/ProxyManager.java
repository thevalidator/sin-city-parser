/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.proxy;

import ru.thekrechetofficial.sincityparser.entity.Proxy;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public interface ProxyManager {
    
    Proxy getProxy();
    
    String changeProxy();
    
    boolean checkProxy();    
    
}
