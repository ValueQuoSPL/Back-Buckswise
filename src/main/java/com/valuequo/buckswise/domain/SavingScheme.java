package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A SavingScheme.
 */
@Entity
@Table(name = "saving")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SavingScheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "uid")
    private Long uid;
    
    @Column(name = "num")
    private String num;
    
    @Column(name = "organisation_name")
    private String organisation_name;

    @Column(name = "investor_name")
    private String investor_name;

    @Column(name = "dividend_type")
    private String dividend_type;

    @Column(name = "amount_invested")
    private String amount_invested;

    @Column(name = "rate_of_interest")
    private String rate_of_interest;

    @Column(name = "tenure")
    private String tenure;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "fund_value")
    private String fund_value;

    @Column(name = "as_of_date")
    private LocalDate as_of_date;

    @Column(name = "notes")
    private String notes;

    @Column(name = "type")
    private String type;

    @Column(name = "available")
    private String available;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public void setAvailable(String available) {
        this.available = available;
    }

    public String getAvailable() {
        return available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUid() {
    	return uid;
    }
    public SavingScheme UID(Long uid) {
        this.uid = uid;
        return this;
    }
    
    public void setUid(Long uid) {
    	this.uid = uid;
    }
    
    public String getNum() {
    	return num;
    }
    public SavingScheme num(String num) {
        this.num = num;
        return this;
    }
    
    public void setNum(String num) {
    	this.num = num;
    }
   
    public String getOrganisation_name() {
        return organisation_name;
    }

    public SavingScheme organisation_name(String organisation_name) {
        this.organisation_name = organisation_name;
        return this;
    }

    public void setOrganisation_name(String organisation_name) {
        this.organisation_name = organisation_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public SavingScheme investor_name(String investor_name) {
        this.investor_name = investor_name;
        return this;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public String getDividend_type() {
        return dividend_type;
    }

    public SavingScheme dividend_type(String dividend_type) {
        this.dividend_type = dividend_type;
        return this;
    }

    public void setDividend_type(String dividend_type) {
        this.dividend_type = dividend_type;
    }

    public String getAmount_invested() {
        return amount_invested;
    }

    public SavingScheme amount_invested(String amount_invested) {
        this.amount_invested = amount_invested;
        return this;
    }

    public void setAmount_invested(String amount_invested) {
        this.amount_invested = amount_invested;
    }

    public String getRate_of_interest() {
        return rate_of_interest;
    }

    public SavingScheme rate_of_interest(String rate_of_interest) {
        this.rate_of_interest = rate_of_interest;
        return this;
    }

    public void setRate_of_interest(String rate_of_interest) {
        this.rate_of_interest = rate_of_interest;
    }

    public String getTenure() {
        return tenure;
    }

    public SavingScheme tenure(String tenure) {
        this.tenure = tenure;
        return this;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public SavingScheme start_date(LocalDate start_date) {
        this.start_date = start_date;
        return this;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public SavingScheme end_date(LocalDate end_date) {
        this.end_date = end_date;
        return this;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getFund_value() {
        return fund_value;
    }

    public SavingScheme fund_value(String fund_value) {
        this.fund_value = fund_value;
        return this;
    }

    public void setFund_value(String fund_value) {
        this.fund_value = fund_value;
    }

    public LocalDate getAs_of_date() {
        return as_of_date;
    }

    public SavingScheme as_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
        return this;
    }

    public void setAs_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
    }

    public String getNotes() {
        return notes;
    }

    public SavingScheme notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String getType() {
    	return type;
    }
    public SavingScheme type(String type) {
        this.type = type;
        return this;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
   
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        SavingScheme savingScheme = (SavingScheme) o;
//        
//        return !(organisation_name != null ? !organisation_name.equals(savingScheme.organisation_name) : savingScheme.organisation_name != null);
////        return Objects.equals(getId(), savingScheme.getId());
//    }
//
//    @Override
//    public int hashCode() {
//    	return organisation_name != null ? organisation_name.hashCode() : 0;
////        return Objects.hashCode(getId());
//    }

    @Override
    public String toString() {
        return "SavingScheme{" +
            "id="  +
            ", uid='" + getUid() + "'" +
            ", num='" + getNum() + "'" +	
            ", organisation_name='" + getOrganisation_name() + "'" +
            ", investor_name='" + getInvestor_name() + "'" +
            ", divident_type='" + getDividend_type() + "'" +
            ", amount_invested='" + getAmount_invested() + "'" +
            ", rate_of_interest='" + getRate_of_interest() + "'" +
            ", tenure='" + getTenure() + "'" +
            ", start_date='" + getStart_date() + "'" +
            ", end_date='" + getEnd_date() + "'" +
            ", fund_value='" + getFund_value() + "'" +
            ", as_of_date='" + getAs_of_date() + "'" +
            ", notes='" + getNotes() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
