package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * A DTO for the Mutualfund entity.
 */
public class MutualFundDTO implements Serializable {

    private Long id;

    private int userid;

    private String mfscheme;

    private String folionumber;

    private String holdingdays;

    private String purchesprice;

    private String currentvalue;

    private String gainloss;

    private String absolutereturn;

    private String cagr;

    private String available;
    
    private Date p_date;
    
    private String frequency;
    
    private String type;
    
    private String unitbalance;

    public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnitbalance() {
		return unitbalance;
	}

	public void setUnitbalance(String unitbalance) {
		this.unitbalance = unitbalance;
	}

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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMfscheme() {
        return mfscheme;
    }

    public void setMfscheme(String mfscheme) {
        this.mfscheme = mfscheme;
    }

    public String getFolionumber() {
        return folionumber;
    }

    public void setFolionumber(String folionumber) {
        this.folionumber = folionumber;
    }

    public String getHoldingdays() {
        return holdingdays;
    }

    public void setHoldingdays(String holdingdays) {
        this.holdingdays = holdingdays;
    }

    public String getPurchesprice() {
        return purchesprice;
    }

    public void setPurchesprice(String purchesprice) {
        this.purchesprice = purchesprice;
    }

    public String getCurrentvalue() {
        return currentvalue;
    }

    public void setCurrentvalue(String currentvalue) {
        this.currentvalue = currentvalue;
    }

    public String getGainloss() {
        return gainloss;
    }

    public void setGainloss(String gainloss) {
        this.gainloss = gainloss;
    }

    public String getAbsolutereturn() {
        return absolutereturn;
    }

    public void setAbsolutereturn(String absolutereturn) {
        this.absolutereturn = absolutereturn;
    }

    public String getCagr() {
        return cagr;
    }

    public void setCagr(String cagr) {
        this.cagr = cagr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MutualFundDTO mutualfundDTO = (MutualFundDTO) o;
        if(mutualfundDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mutualfundDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "MutualFundDTO [id=" + id + ", userid=" + userid + ", mfscheme=" + mfscheme + ", folionumber="
				+ folionumber + ", holdingdays=" + holdingdays + ", purchesprice=" + purchesprice + ", currentvalue="
				+ currentvalue + ", gainloss=" + gainloss + ", absolutereturn=" + absolutereturn + ", cagr=" + cagr
				+ ", available=" + available + ", p_date=" + p_date + ", frequency=" + frequency + ", type=" + type
				+ ", unitbalance=" + unitbalance + "]";
	}

      }

