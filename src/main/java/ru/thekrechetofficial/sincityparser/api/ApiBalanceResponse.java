/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.api;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class ApiBalanceResponse {
    
    private String status;
    private double balance;
    private String currency;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
