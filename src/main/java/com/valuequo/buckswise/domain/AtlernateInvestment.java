package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A AtlernateInvestment.
 */
@Entity
@Table(name = "atlernate_investment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AtlernateInvestment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num")
    private String num;

    @Column(name = "fund_name")
    private String fund_name;

    @Column(name = "investor_name")
    private String investor_name;

    @Column(name = "p_date")
    private LocalDate p_date;

    @Column(name = "amount_invested")
    private String amount_invested;

    @Column(name = "market_value")
    private String market_value;

    @Column(name = "as_of_date")
    private LocalDate as_of_date;

    @Column(name = "notes")
    private String notes;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "available")
    private String available;

    @Column(name = "user_id")
    private Long userId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getNum() {
        return num;
    }

    public AtlernateInvestment num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFund_name() {
        return fund_name;
    }

    public AtlernateInvestment fund_name(String fund_name) {
        this.fund_name = fund_name;
        return this;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public AtlernateInvestment investor_name(String investor_name) {
        this.investor_name = investor_name;
        return this;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public LocalDate getp_date() {
        return p_date;
    }

    public AtlernateInvestment p_date(LocalDate p_date) {
        this.p_date = p_date;
        return this;
    }

    public void setp_date(LocalDate p_date) {
        this.p_date = p_date;
    }

    public String getAmount_invested() {
        return amount_invested;
    }

    public AtlernateInvestment amount_invested(String amount_invested) {
        this.amount_invested = amount_invested;
        return this;
    }

    public void setAmount_invested(String amount_invested) {
        this.amount_invested = amount_invested;
    }

    public String getMarket_value() {
        return market_value;
    }

    public AtlernateInvestment market_value(String market_value) {
        this.market_value = market_value;
        return this;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public LocalDate getAs_of_date() {
        return as_of_date;
    }

    public AtlernateInvestment as_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
        return this;
    }

    public void setAs_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
    }

    public String getNotes() {
        return notes;
    }

    public AtlernateInvestment notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public AtlernateInvestment type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public AtlernateInvestment userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        AtlernateInvestment atlernateInvestment = (AtlernateInvestment) o;
        if (atlernateInvestment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atlernateInvestment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AtlernateInvestment{" +
            "id=" + getId() +
            ", num='" + getNum() + "'" +
            ", fund_name='" + getFund_name() + "'" +
            ", investor_name='" + getInvestor_name() + "'" +
            ", p_date='" + getp_date() + "'" +
            ", amount_invested='" + getAmount_invested() + "'" +
            ", market_value='" + getMarket_value() + "'" +
            ", as_of_date='" + getAs_of_date() + "'" +
            ", notes='" + getNotes() + "'" +
            ", type='" + getType() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
