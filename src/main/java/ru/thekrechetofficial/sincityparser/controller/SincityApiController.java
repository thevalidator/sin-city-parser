/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.controller;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.thekrechetofficial.sincityparser.api.ApiBalanceResponse;
import ru.thekrechetofficial.sincityparser.api.ApiPortResponse;
import ru.thekrechetofficial.sincityparser.client.AstroProxiApiClient;
import ru.thekrechetofficial.sincityparser.dto.astroclient.balance.BalanceResponseDTO;
import ru.thekrechetofficial.sincityparser.dto.astroclient.port.PortResponseDTO;
import ru.thekrechetofficial.sincityparser.dto.astroclient.port.Port;
import ru.thekrechetofficial.sincityparser.service.mail.MailSenderService;
import ru.thekrechetofficial.sincityparser.service.nl.InviteService;
import ru.thekrechetofficial.sincityparser.service.proxy.ProxyManager;
import ru.thekrechetofficial.sincityparser.task.ScheduledParseTask;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@RestController
public class SincityApiController {
    
    private final ProxyManager manager;
    private final AstroProxiApiClient apiClient;
    private final ScheduledParseTask parseTask;
    private final MailSenderService mailService;
    private final InviteService inviteService;

    @Autowired
    public SincityApiController(ProxyManager manager,
            AstroProxiApiClient apiClient,
            ScheduledParseTask parseTask,
            MailSenderService mailService,
            InviteService inviteService) {
        this.manager = manager;
        this.apiClient = apiClient;
        this.parseTask = parseTask;
        this.mailService = mailService;
        this.inviteService = inviteService;
    }

    @GetMapping("/api/status")
    public ResponseEntity<?> getStatus() {
        Map<String, String> map = new HashMap<>();
        map.put("parsing now", String.valueOf(ScheduledParseTask.isParsing()));
        map.put("last parse", ScheduledParseTask.getLastParse().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        map.put("next parse", ScheduledParseTask.getNextRun());
        
        return ResponseEntity.ok(map);
        
    }
    
    @GetMapping("/api/schedule")
    public ResponseEntity<?> getSchedule() {
        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, String> schedule = new TreeMap<>();
        schedule.put("01:30-09:30", "120 min");
        schedule.put("09:30-12:30", "60 min");
        schedule.put("12:30-17:30", "30 min");
        schedule.put("17:30-01:30", "15 min");
        map.put("schedule", schedule);
        
        return ResponseEntity.ok(map);
        
    }
    
    @GetMapping("/api/parse")
    public void startParse() {
        if (ScheduledParseTask.isParsing() == false) {
            parseTask.run();
        }
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "HELLO";
    }

    @GetMapping("/api/proxy/renew")
    public String changeProxy() {
        return manager.changeProxy();
    }
    
    @GetMapping("/api/proxy/check")
    public ResponseEntity<?> checkProxy() {
        Map<String, String> map = new HashMap<>();
        
        if (manager.checkProxy()) {
            map.put("working", "true");
        } else {
            map.put("working", "false");
        }
        
        return ResponseEntity.ok(map);
    }

    @GetMapping("/api/proxy/info")
    public ApiPortResponse getProxyInfo() {
        PortResponseDTO incomeResponse = apiClient.getProxyData();
        Port data = incomeResponse.getData().getPorts().get(0);

        ApiPortResponse response = new ApiPortResponse();
        response.setCountry(data.getCountry());
        response.setNetwork(data.getNetwork());
        response.setIp(data.getNode().get("ip"));
        response.setPort(data.getPorts().get("http"));
        response.setUsed(data.getTraffic().get("used_mb") + " Mb");
        response.setTotal(data.getTraffic().get("total_mb") + " Mb");

        return response;
    }

    @GetMapping("/api/proxy/balance")
    public ApiBalanceResponse getBalanceInfo() {
        BalanceResponseDTO incomeResponce = apiClient.getBalanceData();

        ApiBalanceResponse response = new ApiBalanceResponse();
        response.setStatus(incomeResponce.getStatus());
        response.setBalance(incomeResponce.getData().getBalance());
        response.setCurrency(incomeResponce.getData().getCurrency());

        return response;
    }
    
    @GetMapping("/api/mail/test/{email}")
    public String sendEmail(@PathVariable String email) {
        
        mailService.testSend(email);
        
        return "success";

    }
    
    @GetMapping("/api/invite/next")
    public String getNextCount() {
        
        List<String> emails = inviteService.getDistinctEmails();
        
        return "size: " + emails.size();

    }

}
