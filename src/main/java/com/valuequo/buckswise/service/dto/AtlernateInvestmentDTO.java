package com.valuequo.buckswise.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AtlernateInvestment entity.
 */
public class AtlernateInvestmentDTO implements Serializable {

    private Long id;

    private String num;

    private String fund_name;

    private String investor_name;

    private LocalDate p_date;

    private String amount_invested;

    private String market_value;

    private LocalDate as_of_date;

    private String notes;

    private String type;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public LocalDate getp_date() {
        return p_date;
    }

    public void setp_date(LocalDate p_date) {
        this.p_date = p_date;
    }

    public String getAmount_invested() {
        return amount_invested;
    }

    public void setAmount_invested(String amount_invested) {
        this.amount_invested = amount_invested;
    }

    public String getMarket_value() {
        return market_value;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public LocalDate getAs_of_date() {
        return as_of_date;
    }

    public void setAs_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AtlernateInvestmentDTO atlernateInvestmentDTO = (AtlernateInvestmentDTO) o;
        if(atlernateInvestmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atlernateInvestmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AtlernateInvestmentDTO{" +
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
