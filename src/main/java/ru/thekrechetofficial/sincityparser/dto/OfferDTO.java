/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.dto;

import java.util.Objects;
import ru.thekrechetofficial.sincityparser.service.parser.Gender;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class OfferDTO {
    
    private final String offerId;
    private final Gender from;

    public OfferDTO(String offerId, Gender from) {
        this.offerId = offerId;
        this.from = from;
    }

    public String getOfferId() {
        return offerId;
    }

    public Gender getFrom() {
        return from;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final OfferDTO other = (OfferDTO) obj;
        if (!Objects.equals(this.offerId, other.offerId)) {
            return false;
        }
        return this.from == other.from;
    }
    
    

}
