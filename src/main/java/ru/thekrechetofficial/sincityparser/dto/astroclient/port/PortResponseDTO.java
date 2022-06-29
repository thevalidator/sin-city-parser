/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.dto.astroclient.port;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class PortResponseDTO {
    
    private String status;
    private PortData data;

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PortData getData() {
        return data;
    }

    public void setData(PortData data) {
        this.data = data;
    }
    
    

}
