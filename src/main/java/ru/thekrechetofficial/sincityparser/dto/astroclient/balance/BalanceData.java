/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.dto.astroclient.balance;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class BalanceData {
    
    private double balance;
    private String currency;

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
