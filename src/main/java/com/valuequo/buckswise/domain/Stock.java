package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Stock.
 */
@Entity
@Table(name = "stock")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "investor_name")
    private String investor_name;

    @Column(name = "no_of_shares")
    private String no_of_shares;
    
    @Column(name = "share_price")
    private String share_price;

    @Column(name = "notes")
    private String notes;

    @Column(name = "available")
    private String available;

    @Column(name = "userid")
    private Long userid;
    
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

    public String getCompany_name() {
        return company_name;
    }

    public Stock company_name(String company_name) {
        this.company_name = company_name;
        return this;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public Stock investor_name(String investor_name) {
        this.investor_name = investor_name;
        return this;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public String getNo_of_shares() {
        return no_of_shares;
    }

    public Stock no_of_shares(String no_of_shares) {
        this.no_of_shares = no_of_shares;
        return this;
    }

    public void setNo_of_shares(String no_of_shares) {
        this.no_of_shares = no_of_shares;
    }

    public String getNotes() {
        return notes;
    }

    public Stock notes(String notes) {
        this.notes = notes;
        return this;
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
    
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public String getShare_price() {
		return share_price;
	}

	public void setShare_price(String share_price) {
		this.share_price = share_price;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        if (stock.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Stock{" +
            "id=" + getId() +
            "id=" + getUserid() +
            ", company_name='" + getCompany_name() + "'" +
            ", investor_name='" + getInvestor_name() + "'" +
            ", no_of_shares='" + getNo_of_shares() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
