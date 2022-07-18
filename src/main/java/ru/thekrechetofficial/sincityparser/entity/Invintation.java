/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Entity
@Table(name="invintation")
public class Invintation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    @Column(name = "invintation_date")
    private LocalDateTime invintationDate;

    public Invintation() {
    }

    public Invintation(String email, LocalDateTime invintationDate) {
        this.email = email;
        this.invintationDate = invintationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getInvintationDate() {
        return invintationDate;
    }

    public void setInvintationDate(LocalDateTime invintationDate) {
        this.invintationDate = invintationDate;
    }

}
