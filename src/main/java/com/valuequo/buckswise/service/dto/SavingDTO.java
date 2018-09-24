package com.valuequo.buckswise.service.dto;

import java.time.LocalDate;

import com.valuequo.buckswise.domain.SavingScheme;

public class SavingDTO {
	
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Long userId;
	
	private String num;

    private String organisation_name;

    private String investor_name;

    private String dividend_type;

    private String amount_invested;

    private String rate_of_interest;

    private String tenure;

    private LocalDate start_date;

    private LocalDate end_date;

    private String fund_value;

    private LocalDate as_of_date;

    private String notes;
    
    private String type;
    
    

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getDividend_type() {
		return dividend_type;
	}

	public void setDividend_type(String dividend_type) {
		this.dividend_type = dividend_type;
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

	public void setType(String type) {
		this.type = type;
	}

	
	public SavingDTO() {
		super();
	}

	public SavingDTO(SavingScheme savingScheme ) {
		this.userId = savingScheme.getUid();
		this.num = savingScheme.getNum();
		this.organisation_name = savingScheme.getOrganisation_name();
		this.investor_name = savingScheme.getInvestor_name();
		this.dividend_type = savingScheme.getDividend_type();
		this.amount_invested = savingScheme.getAmount_invested();
		this.rate_of_interest = savingScheme.getRate_of_interest();
		this.tenure = savingScheme.getTenure();
		this.start_date = savingScheme.getStart_date();
		this.end_date = savingScheme.getEnd_date();
		this.fund_value = savingScheme.getFund_value();
		this.as_of_date = savingScheme.getAs_of_date();
		this.notes = savingScheme.getNotes();
		this.type = savingScheme.getType();
	}

	@Override
	public String toString() {
		return "SavingDTO [num=" + num + ", organisation_name=" + organisation_name + ", investor_name=" + investor_name
				+ ", divident_type=" + dividend_type + ", amount_invested=" + amount_invested + ", rate_of_interest="
				+ rate_of_interest + ", tenure=" + tenure + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", fund_value=" + fund_value + ", as_of_date=" + as_of_date + ", notes=" + notes + ", type=" + type
				+ "]";
	}
    
    

}
