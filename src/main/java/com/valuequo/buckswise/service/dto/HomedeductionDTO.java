package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Homededuction entity.
 */
public class HomedeductionDTO implements Serializable {

    private Long id;

    private Integer homeloan;

    private Integer prncpalloan;

    private Integer rentclm;

    private Integer remintrst;

    private Integer rentclmgg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHomeloan() {
        return homeloan;
    }

    public void setHomeloan(Integer homeloan) {
        this.homeloan = homeloan;
    }

    public Integer getPrncpalloan() {
        return prncpalloan;
    }

    public void setPrncpalloan(Integer prncpalloan) {
        this.prncpalloan = prncpalloan;
    }

    public Integer getRentclm() {
        return rentclm;
    }

    public void setRentclm(Integer rentclm) {
        this.rentclm = rentclm;
    }

    public Integer getRemintrst() {
        return remintrst;
    }

    public void setRemintrst(Integer remintrst) {
        this.remintrst = remintrst;
    }

    public Integer getRentclmgg() {
        return rentclmgg;
    }

    public void setRentclmgg(Integer rentclmgg) {
        this.rentclmgg = rentclmgg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HomedeductionDTO homedeductionDTO = (HomedeductionDTO) o;
        if(homedeductionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), homedeductionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HomedeductionDTO{" +
            "id=" + getId() +
            ", homeloan=" + getHomeloan() +
            ", prncpalloan=" + getPrncpalloan() +
            ", rentclm=" + getRentclm() +
            ", remintrst=" + getRemintrst() +
            ", rentclmgg=" + getRentclmgg() +
            "}";
    }
}
