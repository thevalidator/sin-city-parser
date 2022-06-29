//package ru.thekrechetofficial.sincityparser.service.proxy;


// THIS IS FOR FREE PROXIES LIST (NEED TO MODIFY)




///*
// * The Krechet Software
// */
//package ru.thekrechetofficial.sincityparser.service;
//
//import java.time.Instant;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.thekrechetofficial.sincityparser.entity.Proxy;
//import ru.thekrechetofficial.sincityparser.util.ProxyExtractor;
//
///**
// * @author theValidator <the.validator@yandex.ru>
// */
////@Service
//public class ProxyManagerImpl implements ProxyManager {
//
//    private long timestamp;
//    private final ProxyExtractor extractor;
//    private final ProxyCheckerImpl checker;
//    private final Set<Proxy> actualProxies;
//    private Proxy proxy;
//
//    @Autowired
//    public ProxyManagerImpl(ProxyExtractor extractor, ProxyCheckerImpl checker) {
//        this.extractor = extractor;
//        this.checker = checker;
//        this.actualProxies = new HashSet<>();
//        this.timestamp = 0L;
//        //this.proxy = null;
//        this.proxy = new Proxy("109.248.7.9", 10015);
//    }
//    
//    @Override
//    public Proxy getProxy() {
//        if (proxy == null) {
//            findAndSetProxy();
//        }
//        return proxy;
//    }
//
//    @Override
//    public void changeProxy() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public boolean checkProxy() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//    
//    
//
//    public void removeProxy(Proxy p) {
//        actualProxies.remove(p);
//    }
//    
//    public int getProxyCount() {
//        return actualProxies.size();
//    }
//
//    public int updateProxyList() {
//
//        int startSize = actualProxies.size();
//        actualProxies.addAll(extractor.getProxiesList());
//        int endSize = actualProxies.size();
//
//        int proxiesAdded = endSize - startSize;
//        if (proxiesAdded > 0) {
//            timestamp = System.currentTimeMillis();
//        }
//
//        return proxiesAdded;
//    }
//
//    public String getLastUpdateTime() {
//        return Instant.ofEpochMilli(timestamp)
//                .atZone(ZoneId.ofOffset("UTC", ZoneOffset.of("+03")))
//                .toLocalDateTime().toString();
//    }
//
//    public List<Proxy> getProxyList() {
//        return List.copyOf(actualProxies);
//    }
//
//    public Proxy getWorkingProxy() {
//
//        while (true) {
//
//            for (Proxy p : getProxyList()) {
//                System.out.print("\tCHECKING: " + p);
//                if (checker.isProxyWorking(p)) {
//                    System.out.println("\t\tGOOD");
//                    return p;
//                } else {
//                    System.out.println("\t\tBAD");
//                    actualProxies.remove(p);
//                }
//            }
//            updateProxyList();
//            System.out.println("LIST NOT VALID, UPDATED");
//
//        }
//        
//    }
//    
//    public void findAndSetProxy() {
//        proxy = getWorkingProxy();
//    }
//
//    
//
//}
