package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Home entity.
 */
public class HomeDTO implements Serializable {

    private Long id;

    private Integer uid;

    private String hoamloan;

    private String prncpalloan;

    private String rentclm;

    private String remintrst;

    private String rentclmgg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getHoamloan() {
        return hoamloan;
    }

    public void setHoamloan(String hoamloan) {
        this.hoamloan = hoamloan;
    }

    public String getPrncpalloan() {
        return prncpalloan;
    }

    public void setPrncpalloan(String prncpalloan) {
        this.prncpalloan = prncpalloan;
    }

    public String getRentclm() {
        return rentclm;
    }

    public void setRentclm(String rentclm) {
        this.rentclm = rentclm;
    }

    public String getRemintrst() {
        return remintrst;
    }

    public void setRemintrst(String remintrst) {
        this.remintrst = remintrst;
    }

    public String getRentclmgg() {
        return rentclmgg;
    }

    public void setRentclmgg(String rentclmgg) {
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

        HomeDTO homeDTO = (HomeDTO) o;
        if(homeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), homeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HomeDTO{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", hoamloan='" + getHoamloan() + "'" +
            ", prncpalloan='" + getPrncpalloan() + "'" +
            ", rentclm='" + getRentclm() + "'" +
            ", remintrst='" + getRemintrst() + "'" +
            ", rentclmgg='" + getRentclmgg() + "'" +
            "}";
    }
}
