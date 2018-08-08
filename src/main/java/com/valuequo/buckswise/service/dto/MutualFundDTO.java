package com.valuequo.buckswise.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MutualFund entity.
 */
public class MutualFundDTO implements Serializable {

    private Long id;

    private String fund_name;

    private String investor_name;

    private LocalDate purchase_date;

    private String no_of_units;

    private String nav;
    
    private Long userid;
    
    

    public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(LocalDate purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getNo_of_units() {
        return no_of_units;
    }

    public void setNo_of_units(String no_of_units) {
        this.no_of_units = no_of_units;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MutualFundDTO mutualFundDTO = (MutualFundDTO) o;
        if(mutualFundDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mutualFundDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MutualFundDTO{" +
            "id=" + getId() +
            "id=" + getUserid() +
            ", fund_name='" + getFund_name() + "'" +
            ", investor_name='" + getInvestor_name() + "'" +
            ", purchase_date='" + getPurchase_date() + "'" +
            ", no_of_units='" + getNo_of_units() + "'" +
            ", nav='" + getNav() + "'" +
            "}";
    }
}
