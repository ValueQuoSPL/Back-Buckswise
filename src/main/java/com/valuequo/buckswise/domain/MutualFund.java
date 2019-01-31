package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * A MutualFund.
 */
@Entity
@Table(name = "mutualfund")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MutualFund implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "mfscheme")
    private String mfscheme;

    @Column(name = "folionumber")
    private String folionumber;

    @Column(name = "holdingdays")
    private String holdingdays;

    @Column(name = "purchesprice")
    private String purchesprice;

    @Column(name = "currentvalue")
    private String currentvalue;

    @Column(name = "gainloss")
    private String gainloss;

    @Column(name = "absolutereturn")
    private String absolutereturn;

    @Column(name = "cagr")
    private String cagr;

    @Column(name = "purches_date")
    private Date p_date;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "frequency")
    private String frequency;
    
    @Column(name = "unitbalance")
    private String unitbalance;
    
    @Column(name = "available")
    private String available;
    
    @Column(name = "amcname")
    private String amcname;
    
    @Column(name = "sipday")
    private String sipday;

	@Column(name = "sipamount")
    private String sipamount;
    
    @Column(name = "schemecode")
    private String schemecode;
    
    @Column(name = "navatpurchase")
    private String navatpurchase;

    public String getNavatpurchase() {
		return navatpurchase;
	}

	public void setNavatpurchase(String navatpurchase) {
		this.navatpurchase = navatpurchase;
	}

	public String getSipday() {
		return sipday;
	}

	public void setSipday(String sipday) {
		this.sipday = sipday;
	}

	public String getSipamount() {
		return sipamount;
	}

	public void setSipamount(String sipamount) {
		this.sipamount = sipamount;
	}
	

	public String getSchemecode() {
		return schemecode;
	}

	public void setSchemecode(String schemecode) {
		this.schemecode = schemecode;
	}

	public String getAmcname() {
		return amcname;
	}

	public void setAmcname(String amcname) {
		this.amcname = amcname;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getUnitbalance() {
		return unitbalance;
	}

	public void setUnitbalance(String unitbalance) {
		this.unitbalance = unitbalance;
	}

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

    public MutualFund userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getMfscheme() {
        return mfscheme;
    }

    public MutualFund mfscheme(String mfscheme) {
        this.mfscheme = mfscheme;
        return this;
    }

    public void setMfscheme(String mfscheme) {
        this.mfscheme = mfscheme;
    }

    public String getFolionumber() {
        return folionumber;
    }

    public MutualFund folionumber(String folionumber) {
        this.folionumber = folionumber;
        return this;
    }

    public void setFolionumber(String folionumber) {
        this.folionumber = folionumber;
    }

    public String getHoldingdays() {
        return holdingdays;
    }

    public MutualFund holdingdays(String holdingdays) {
        this.holdingdays = holdingdays;
        return this;
    }

    public void setHoldingdays(String holdingdays) {
        this.holdingdays = holdingdays;
    }

    public String getPurchesprice() {
        return purchesprice;
    }

    public MutualFund purchesprice(String purchesprice) {
        this.purchesprice = purchesprice;
        return this;
    }

    public void setPurchesprice(String purchesprice) {
        this.purchesprice = purchesprice;
    }

    public String getCurrentvalue() {
        return currentvalue;
    }

    public MutualFund currentvalue(String currentvalue) {
        this.currentvalue = currentvalue;
        return this;
    }

    public void setCurrentvalue(String currentvalue) {
        this.currentvalue = currentvalue;
    }

    public String getGainloss() {
        return gainloss;
    }

    public MutualFund gainloss(String gainloss) {
        this.gainloss = gainloss;
        return this;
    }

    public void setGainloss(String gainloss) {
        this.gainloss = gainloss;
    }

    public String getAbsolutereturn() {
        return absolutereturn;
    }

    public MutualFund absolutereturn(String absolutereturn) {
        this.absolutereturn = absolutereturn;
        return this;
    }

    public void setAbsolutereturn(String absolutereturn) {
        this.absolutereturn = absolutereturn;
    }

    public String getCagr() {
        return cagr;
    }

    public MutualFund cagr(String cagr) {
        this.cagr = cagr;
        return this;
    }

    public void setCagr(String cagr) {
        this.cagr = cagr;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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
        MutualFund MutualFund = (MutualFund) o;
        if (MutualFund.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), MutualFund.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "MutualFund [id=" + id + ", userid=" + userid + ", mfscheme=" + mfscheme + ", folionumber=" + folionumber
				+ ", holdingdays=" + holdingdays + ", purchesprice=" + purchesprice + ", currentvalue=" + currentvalue
				+ ", gainloss=" + gainloss + ", absolutereturn=" + absolutereturn + ", cagr=" + cagr + ", p_date="
				+ p_date + ", type=" + type + ", frequency=" + frequency + ", unitbalance=" + unitbalance
				+ ", available=" + available + ", amcname=" + amcname + ", sipday=" + sipday + ", sipamount="
				+ sipamount + ", schemecode=" + schemecode + ", navatpurchase=" + navatpurchase + "]";
	}   
}
