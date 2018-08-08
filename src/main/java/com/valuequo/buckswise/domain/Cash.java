package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Cash.
 */
@Entity
@Table(name = "cash")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "cash_source")
    private String cash_source;

    @Column(name = "notes")
    private String notes;

    @Column(name = "userid")
    private Long userid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public Cash amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCash_source() {
        return cash_source;
    }

    public Cash cash_source(String cash_source) {
        this.cash_source = cash_source;
        return this;
    }

    public void setCash_source(String cash_source) {
        this.cash_source = cash_source;
    }

    public String getNotes() {
        return notes;
    }

    public Cash notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getUserid() {
        return userid;
    }

    public Cash userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cash cash = (Cash) o;
        if (cash.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cash.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cash{" +
            "id=" + getId() +
            ", amount='" + getAmount() + "'" +
            ", cash_source='" + getCash_source() + "'" +
            ", notes='" + getNotes() + "'" +
            ", userid=" + getUserid() +
            "}";
    }
}
