package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RiskLifeInsurance entity.
 */
public class RiskLifeInsuranceDTO implements Serializable {

    private Long id;

    private Long userid;
    
    private String expense_cover;

    private String risk_coverage;

    private String total;

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

	public String getExpense_cover() {
        return expense_cover;
    }

    public void setExpense_cover(String expense_cover) {
        this.expense_cover = expense_cover;
    }

    public String getRisk_coverage() {
        return risk_coverage;
    }

    public void setRisk_coverage(String risk_coverage) {
        this.risk_coverage = risk_coverage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RiskLifeInsuranceDTO riskLifeInsuranceDTO = (RiskLifeInsuranceDTO) o;
        if(riskLifeInsuranceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), riskLifeInsuranceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RiskLifeInsuranceDTO{" +
            "id=" + getId() +
            "userid=" + getUserid() + "'" +
            ", expense_cover='" + getExpense_cover() + "'" +
            ", risk_coverage='" + getRisk_coverage() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
}
