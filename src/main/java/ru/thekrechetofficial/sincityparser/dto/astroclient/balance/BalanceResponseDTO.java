/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.dto.astroclient.balance;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class BalanceResponseDTO {
    
    private String status;
    private BalanceData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BalanceData getData() {
        return data;
    }

    public void setData(BalanceData data) {
        this.data = data;
    }
    
    

}
