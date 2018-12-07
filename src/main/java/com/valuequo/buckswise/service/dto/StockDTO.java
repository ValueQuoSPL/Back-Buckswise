package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Stock entity.
 */
public class StockDTO implements Serializable {

    private Long id;

    private Long userid;
    
    private String company_name;

    private String investor_name;

    private String no_of_shares;

    private String share_price;

    private String availble;

    private String notes;

    public Long getId() {
        return id;
    }

    /**
     * @return the availble
     */
    public String getAvailble() {
        return availble;
    }

    /**
     * @param availble the availble to set
     */
    public void setAvailble(String availble) {
        this.availble = availble;
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

	public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public String getNo_of_shares() {
        return no_of_shares;
    }

    public void setNo_of_shares(String no_of_shares) {
        this.no_of_shares = no_of_shares;
    }

    
    public String getShare_price() {
		return share_price;
	}

	public void setShare_price(String share_price) {
		this.share_price = share_price;
	}

	public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StockDTO stockDTO = (StockDTO) o;
        if(stockDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stockDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StockDTO{" +
            "id=" + getId() +
            "userid=" + getUserid() +
            ", company_name='" + getCompany_name() + "'" +
            ", investor_name='" + getInvestor_name() + "'" +
            ", no_of_shares='" + getNo_of_shares() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
