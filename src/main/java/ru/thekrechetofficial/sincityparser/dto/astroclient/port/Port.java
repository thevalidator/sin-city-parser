/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.dto.astroclient.port;

import java.util.Map;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class Port {

    private int id;
    private String name;
    private String createdAt;
    private Map<String, String> node;
    private Map<String, String> ports;
    private String country;
    private String network;
    private Map<String, String> access;
    private Map<String, String> traffic;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Map<String, String> getNode() {
        return node;
    }

    public void setNode(Map<String, String> node) {
        this.node = node;
    }

    public Map<String, String> getPorts() {
        return ports;
    }

    public void setPorts(Map<String, String> ports) {
        this.ports = ports;
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

    public Map<String, String> getAccess() {
        return access;
    }

    public void setAccess(Map<String, String> access) {
        this.access = access;
    }

    public Map<String, String> getTraffic() {
        return traffic;
    }

    public void setTraffic(Map<String, String> traffic) {
        this.traffic = traffic;
    }
    
    
}
