///*
// * The Krechet Software
// */
//package ru.thekrechetofficial.sincityparser.service.proxy;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import ru.thekrechetofficial.sincityparser.entity.Proxy;
//
///**
// * @author theValidator <the.validator@yandex.ru>
// */
//@Service
//public class OpenProxyListExtractorImpl implements ProxyExtractor {
//    
//    private final String proxyUrl;
//    private final String regex = ".+\\s(?<ip>(\\d{1,3}\\.){3}\\d{1,3}):(?<port>(\\d{2,4})) \\d+ms (?!RU).+";
//    private final Pattern pattern = Pattern.compile(regex);
//
//    
//    public OpenProxyListExtractorImpl(@Value("${resource.proxy}") String proxyUrl) {
//        this.proxyUrl = proxyUrl;
//    }
//
//    @Override
//    public List<Proxy> getProxiesList() {
//
//        List<Proxy> proxiesList = new ArrayList<>();
//
//        try {
//            URL urlObject = new URL(proxyUrl);
//            URLConnection urlConnection = urlObject.openConnection();
//            InputStream inputStream = urlConnection.getInputStream();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF8"));
//            String currentLine;
//
//            Matcher matcher;
//            while ((currentLine = reader.readLine()) != null) {
//                matcher = pattern.matcher(currentLine);
//                if (matcher.find()) {
//                    String ip = matcher.group("ip");
//                    //String port = matcher.group("port");
//                    Integer port = Integer.parseInt(matcher.group("port"));
//                    proxiesList.add(new Proxy(ip, port));
//                }
//            }
//
//        } catch (MalformedURLException ex) {
//            //TODO: log exception
//            Logger.getLogger(OpenProxyListExtractorImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            //TODO: log exception
//            Logger.getLogger(OpenProxyListExtractorImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return proxiesList;
//    }
//
//}
