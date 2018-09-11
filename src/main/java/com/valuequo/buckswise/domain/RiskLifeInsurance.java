package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RiskLifeInsurance.
 */
@Entity
@Table(name = "risk_life_insurance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RiskLifeInsurance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userid;
    
    @Column(name = "expense_cover")
    private String expense_cover;

    @Column(name = "risk_coverage")
    private String risk_coverage;

    @Column(name = "total")
    private String total;

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

	public String getExpense_cover() {
        return expense_cover;
    }

    public RiskLifeInsurance expense_cover(String expense_cover) {
        this.expense_cover = expense_cover;
        return this;
    }

    public void setExpense_cover(String expense_cover) {
        this.expense_cover = expense_cover;
    }

    public String getRisk_coverage() {
        return risk_coverage;
    }

    public RiskLifeInsurance risk_coverage(String risk_coverage) {
        this.risk_coverage = risk_coverage;
        return this;
    }

    public void setRisk_coverage(String risk_coverage) {
        this.risk_coverage = risk_coverage;
    }

    public String getTotal() {
        return total;
    }

    public RiskLifeInsurance total(String total) {
        this.total = total;
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
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
        RiskLifeInsurance riskLifeInsurance = (RiskLifeInsurance) o;
        if (riskLifeInsurance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), riskLifeInsurance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RiskLifeInsurance{" +
            "id=" + getId() +
            ", userid=" + getUserid() + "'" +
            ", expense_cover='" + getExpense_cover() + "'" +
            ", risk_coverage='" + getRisk_coverage() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
}
