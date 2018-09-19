package com.valuequo.buckswise.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FutureOption entity.
 */
public class FutureOptionDTO implements Serializable {

    private Long id;

    private String num;

    private Long userid;

    private String investor_name;

    private String investment_type;

    private String asset_type;

    private String asset_name;

    private String contract_m_value;

    private String contract_p_value;

    private String no_of_contracts;

    private LocalDate as_of_date;

    private LocalDate p_date;

    private String notes;

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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public String getInvestment_type() {
        return investment_type;
    }

    public void setInvestment_type(String investment_type) {
        this.investment_type = investment_type;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getContract_m_value() {
        return contract_m_value;
    }

    public void setContract_m_value(String contract_m_value) {
        this.contract_m_value = contract_m_value;
    }

    public String getContract_p_value() {
        return contract_p_value;
    }

    public void setContract_p_value(String contract_p_value) {
        this.contract_p_value = contract_p_value;
    }

    public String getNo_of_contracts() {
        return no_of_contracts;
    }

    public void setNo_of_contracts(String no_of_contracts) {
        this.no_of_contracts = no_of_contracts;
    }

    public LocalDate getAs_of_date() {
        return as_of_date;
    }

    public void setAs_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
    }

    public LocalDate getp_date() {
        return p_date;
    }

    public void setp_date(LocalDate p_date) {
        this.p_date = p_date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FutureOptionDTO futureOptionDTO = (FutureOptionDTO) o;
        if(futureOptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), futureOptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FutureOptionDTO{" +
            "id=" + getId() +
            ", num='" + getNum() + "'" +
            ", userid=" + getUserid() +
            ", investor_name='" + getInvestor_name() + "'" +
            ", investment_type='" + getInvestment_type() + "'" +
            ", asset_type='" + getAsset_type() + "'" +
            ", asset_name='" + getAsset_name() + "'" +
            ", contract_m_value='" + getContract_m_value() + "'" +
            ", contract_p_value='" + getContract_p_value() + "'" +
            ", no_of_contracts='" + getNo_of_contracts() + "'" +
            ", as_of_date='" + getAs_of_date() + "'" +
            ", p_date='" + getp_date() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
