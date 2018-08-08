package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A MutualFund.
 */
@Entity
@Table(name = "mutual_fund")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MutualFund implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fund_name")
    private String fund_name;

    @Column(name = "investor_name")
    private String investor_name;

    @Column(name = "purchase_date")
    private LocalDate purchase_date;

    @Column(name = "no_of_units")
    private String no_of_units;

    @Column(name = "nav")
    private String nav;
    
    @Column(name = "userid")
    private Long userid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getFund_name() {
        return fund_name;
    }

    public MutualFund fund_name(String fund_name) {
        this.fund_name = fund_name;
        return this;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public MutualFund investor_name(String investor_name) {
        this.investor_name = investor_name;
        return this;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public LocalDate getPurchase_date() {
        return purchase_date;
    }

    public MutualFund purchase_date(LocalDate purchase_date) {
        this.purchase_date = purchase_date;
        return this;
    }

    public void setPurchase_date(LocalDate purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getNo_of_units() {
        return no_of_units;
    }

    public MutualFund no_of_units(String no_of_units) {
        this.no_of_units = no_of_units;
        return this;
    }

    public void setNo_of_units(String no_of_units) {
        this.no_of_units = no_of_units;
    }

    public String getNav() {
        return nav;
    }

    public MutualFund nav(String nav) {
        this.nav = nav;
        return this;
    }

    public void setNav(String nav) {
        this.nav = nav;
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
        MutualFund mutualFund = (MutualFund) o;
        if (mutualFund.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mutualFund.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MutualFund{" +
            "id=" + getId() +
            "userid=" + getUserid() +
            ", fund_name='" + getFund_name() + "'" +
            ", investor_name='" + getInvestor_name() + "'" +
            ", purchase_date='" + getPurchase_date() + "'" +
            ", no_of_units='" + getNo_of_units() + "'" +
            ", nav='" + getNav() + "'" +
            "}";
    }
}
