package com.valuequo.buckswise.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

import org.springframework.stereotype.Component;

/**
 * A DTO for the SavingScheme entity.
 */
@Component
public class SavingSchemeDTO implements Serializable {

//    private Long id;
	private String num;

    private String organisation_name;

    private String investor_name;

    private String divident_type;

    private String amount_invested;

    private String rate_of_interest;

    private String tenure;

    private LocalDate start_date;

    private LocalDate end_date;

    private String fund_value;

    private LocalDate as_of_date;

    private String notes;
    
    private String type;
    
    

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOrganisation_name() {
        return organisation_name;
    }

    public void setOrganisation_name(String organisation_name) {
        this.organisation_name = organisation_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public String getDivident_type() {
        return divident_type;
    }

    public void setDivident_type(String divident_type) {
        this.divident_type = divident_type;
    }

    public String getAmount_invested() {
        return amount_invested;
    }

    public void setAmount_invested(String amount_invested) {
        this.amount_invested = amount_invested;
    }

    public String getRate_of_interest() {
        return rate_of_interest;
    }

    public void setRate_of_interest(String rate_of_interest) {
        this.rate_of_interest = rate_of_interest;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getFund_value() {
        return fund_value;
    }

    public void setFund_value(String fund_value) {
        this.fund_value = fund_value;
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
    
    public void setType() {
    	this.type = type;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//
//        SavingSchemeDTO savingSchemeDTO = (SavingSchemeDTO) o;
//        return !(organisation_name != null ? !organisation_name.equals(savingSchemeDTO.organisation_name) : savingSchemeDTO.organisation_name != null);
//
//    }
//
//    @Override
//    public int hashCode() {
//    	return organisation_name != null ? organisation_name.hashCode() : 0;
//    }

    @Override
    public String toString() {
        return "SavingSchemeDTO{" +
            "Num=" + getNum() +
            ", organisation_name='" + getOrganisation_name() + "'" +
            ", investor_name='" + getInvestor_name() + "'" +
            ", divident_type='" + getDivident_type() + "'" +
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
