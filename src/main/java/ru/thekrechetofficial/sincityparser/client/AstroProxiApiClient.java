/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.thekrechetofficial.sincityparser.client.config.AstroProxiApiClientConfig;
import ru.thekrechetofficial.sincityparser.dto.astroclient.balance.BalanceResponseDTO;
import ru.thekrechetofficial.sincityparser.dto.astroclient.port.PortResponseDTO;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
@FeignClient(name="${proxy.api.name}", 
            url="${proxy.api.url}", 
            configuration = AstroProxiApiClientConfig.class)
public interface AstroProxiApiClient {
    
    @GetMapping("/ports")
    PortResponseDTO getProxyData();
    
    @GetMapping("/ports/{id}/newip")
    String renewExternalIP(@PathVariable String id);
    
    @GetMapping("/balance")
    BalanceResponseDTO getBalanceData();
    
}
