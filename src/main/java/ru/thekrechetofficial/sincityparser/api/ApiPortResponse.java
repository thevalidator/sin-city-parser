/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.api;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class ApiPortResponse {
    
    private String country;
    private String network;
    private String ip;
    private String port;
    private String used;
    private String total;

    public ApiPortResponse() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
