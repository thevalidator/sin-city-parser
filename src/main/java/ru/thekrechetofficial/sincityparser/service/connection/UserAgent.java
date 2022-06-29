/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.connection;

import java.util.Random;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
public enum UserAgent {
    AGENT_001 ("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36"), 
    AGENT_01 ("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36"),
    //AGENT_02 ("Mozilla/5.0 (Macintosh; Intel Mac OS X 12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36"),
    //AGENT_03 ("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36"),
    //AGENT_04 ("Mozilla/5.0 (Macintosh; Intel Mac OS X 12.4; rv:101.0) Gecko/20100101 Firefox/101.0"),
    AGENT_05 ("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:101.0) Gecko/20100101 Firefox/101.0"),
    AGENT_06 ("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36 Edg/102.0.1245.33"),
    //AGENT_07 ("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.43 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45"),
    AGENT_08 ("Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.43 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.36"),
    AGENT_09 ("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.43 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36 OPR/85.0.4341.18"),           //+
    //AGENT_10 ("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:97.0) Gecko/20100101 Firefox/97.0"),
    //AGENT_11 ("Mozilla/5.0 (Windows NT 6.3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36"),
    AGENT_12 ("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36"),
    //AGENT_13 ("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0"),
    AGENT_14 ("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, как Gecko) Chrome/80.0.3987.149 Safari/537.36 OPR/67.0.3575.115"),
    AGENT_15 ("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, как Gecko) Chrome/80.0.3987.163 Safari/537.36 OPR/67.0.3575.137");
    
    private final String value;

    private UserAgent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static String getRandomValue() {
        
//        int size = UserAgent.values().length;
//        Random random = new Random();
//        
//        int randomIndex = random.nextInt(size);
//        UserAgent[] agents = UserAgent.values();
//        
//        return agents[randomIndex].getValue();

        return "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";
        
    }
    
    
}
