/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.entity;

import java.util.Objects;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class Proxy {
    
    private final String ip;
    private final int port;

    public Proxy(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return ip + ":" + port;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.ip);
        hash = 67 * hash + this.port;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proxy other = (Proxy) obj;
        if (this.port != other.port) {
            return false;
        }
        return Objects.equals(this.ip, other.ip);
    }

    

}
