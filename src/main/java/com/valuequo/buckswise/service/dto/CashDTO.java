package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Cash entity.
 */
public class CashDTO implements Serializable {

    private Long id;

    private String bankname;

    private String intrestrate;

    private String accoounttype;

    private String accountname;

    private String handloanname;

    private String cashsource;

    private String amount;

    private Long userid;

    private String notes;

    private String extrafield;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getIntrestrate() {
        return intrestrate;
    }

    public void setIntrestrate(String intrestrate) {
        this.intrestrate = intrestrate;
    }

    public String getAccoounttype() {
        return accoounttype;
    }

    public void setAccoounttype(String accoounttype) {
        this.accoounttype = accoounttype;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getHandloanname() {
        return handloanname;
    }

    public void setHandloanname(String handloanname) {
        this.handloanname = handloanname;
    }

    public String getCashsource() {
        return cashsource;
    }

    public void setCashsource(String cashsource) {
        this.cashsource = cashsource;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getExtrafield() {
        return extrafield;
    }

    public void setExtrafield(String extrafield) {
        this.extrafield = extrafield;
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
