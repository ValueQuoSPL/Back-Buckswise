package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Cash entity.
 */
public class CashDTO implements Serializable {

    private Long id;

    private String amount;

    private String cash_source;

    private String notes;

    private Long userid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCash_source() {
        return cash_source;
    }

    public void setCash_source(String cash_source) {
        this.cash_source = cash_source;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CashDTO cashDTO = (CashDTO) o;
        if(cashDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cashDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CashDTO{" +
            "id=" + getId() +
            ", amount='" + getAmount() + "'" +
            ", cash_source='" + getCash_source() + "'" +
            ", notes='" + getNotes() + "'" +
            ", userid=" + getUserid() +
            "}";
    }
}
