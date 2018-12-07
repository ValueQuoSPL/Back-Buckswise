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

    @Column(name = "bankname")
    private String bankname;

    @Column(name = "intrestrate")
    private String intrestrate;

    @Column(name = "accoounttype")
    private String accoounttype;

    @Column(name = "accountname")
    private String accountname;

    @Column(name = "handloanname")
    private String handloanname;

    @Column(name = "cashsource")
    private String cashsource;

    @Column(name = "amount")
    private String amount;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "notes")
    private String notes;

    @Column(name = "extrafield")
    private String extrafield;

    @Column(name = "available")
    private String available;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getAvailable() {
        return available;
    }

    public String getBankname() {
        return bankname;
    }

    public Cash bankname(String bankname) {
        this.bankname = bankname;
        return this;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getIntrestrate() {
        return intrestrate;
    }

    public Cash intrestrate(String intrestrate) {
        this.intrestrate = intrestrate;
        return this;
    }

    public void setIntrestrate(String intrestrate) {
        this.intrestrate = intrestrate;
    }

    public String getAccoounttype() {
        return accoounttype;
    }

    public Cash accoounttype(String accoounttype) {
        this.accoounttype = accoounttype;
        return this;
    }

    public void setAccoounttype(String accoounttype) {
        this.accoounttype = accoounttype;
    }

    public String getAccountname() {
        return accountname;
    }

    public Cash accountname(String accountname) {
        this.accountname = accountname;
        return this;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getHandloanname() {
        return handloanname;
    }

    public Cash handloanname(String handloanname) {
        this.handloanname = handloanname;
        return this;
    }

    public void setHandloanname(String handloanname) {
        this.handloanname = handloanname;
    }

    public String getCashsource() {
        return cashsource;
    }

    public Cash cashsource(String cashsource) {
        this.cashsource = cashsource;
        return this;
    }

    public void setCashsource(String cashsource) {
        this.cashsource = cashsource;
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

    public String getExtrafield() {
        return extrafield;
    }

    public Cash extrafield(String extrafield) {
        this.extrafield = extrafield;
        return this;
    }

    public void setExtrafield(String extrafield) {
        this.extrafield = extrafield;
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
            ", bankname='" + getBankname() + "'" +
            ", intrestrate='" + getIntrestrate() + "'" +
            ", accoounttype='" + getAccoounttype() + "'" +
            ", accountname='" + getAccountname() + "'" +
            ", handloanname='" + getHandloanname() + "'" +
            ", cashsource='" + getCashsource() + "'" +
            ", amount='" + getAmount() + "'" +
            ", userid='" + getUserid() + "'" +
            ", notes='" + getNotes() + "'" +
            ", extrafield='" + getExtrafield() + "'" +
            "}";
    }
}
