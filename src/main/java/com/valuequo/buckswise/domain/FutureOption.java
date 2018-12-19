package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A FutureOption.
 */
@Entity
@Table(name = "future_option")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FutureOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num")
    private String num;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "investor_name")
    private String investor_name;

    @Column(name = "investment_type")
    private String investment_type;

    @Column(name = "asset_type")
    private String asset_type;

    @Column(name = "asset_name")
    private String asset_name;

    @Column(name = "contract_m_value")
    private String contract_m_value;

    @Column(name = "contract_p_value")
    private String contract_p_value;

    @Column(name = "no_of_contracts")
    private String no_of_contracts;

    @Column(name = "as_of_date")
    private LocalDate as_of_date;

    @Column(name = "p_date")
    private LocalDate p_date;

    @Column(name = "notes")
    private String notes;

    @Column(name = "available")
    private String available;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public FutureOption num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Long getUserid() {
        return userid;
    }

    public FutureOption userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public FutureOption investor_name(String investor_name) {
        this.investor_name = investor_name;
        return this;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public String getInvestment_type() {
        return investment_type;
    }

    public FutureOption investment_type(String investment_type) {
        this.investment_type = investment_type;
        return this;
    }

    public void setInvestment_type(String investment_type) {
        this.investment_type = investment_type;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public FutureOption asset_type(String asset_type) {
        this.asset_type = asset_type;
        return this;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public FutureOption asset_name(String asset_name) {
        this.asset_name = asset_name;
        return this;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getContract_m_value() {
        return contract_m_value;
    }

    public FutureOption contract_m_value(String contract_m_value) {
        this.contract_m_value = contract_m_value;
        return this;
    }

    public void setContract_m_value(String contract_m_value) {
        this.contract_m_value = contract_m_value;
    }

    public String getContract_p_value() {
        return contract_p_value;
    }

    public FutureOption contract_p_value(String contract_p_value) {
        this.contract_p_value = contract_p_value;
        return this;
    }

    public void setContract_p_value(String contract_p_value) {
        this.contract_p_value = contract_p_value;
    }

    public String getNo_of_contracts() {
        return no_of_contracts;
    }

    public FutureOption no_of_contracts(String no_of_contracts) {
        this.no_of_contracts = no_of_contracts;
        return this;
    }

    public void setNo_of_contracts(String no_of_contracts) {
        this.no_of_contracts = no_of_contracts;
    }

    public LocalDate getAs_of_date() {
        return as_of_date;
    }

    public FutureOption as_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
        return this;
    }

    public void setAs_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
    }

    public LocalDate getp_date() {
        return p_date;
    }

    public FutureOption p_date(LocalDate p_date) {
        this.p_date = p_date;
        return this;
    }

    public void setp_date(LocalDate p_date) {
        this.p_date = p_date;
    }

    public String getNotes() {
        return notes;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getAvailable() {
        return available;
    }

    public FutureOption notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        FutureOption futureOption = (FutureOption) o;
        if (futureOption.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), futureOption.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FutureOption{" +
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
