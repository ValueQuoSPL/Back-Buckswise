package com.valuequo.buckswise.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Chit entity.
 */
public class ChitDTO implements Serializable {

    private Long id;

    private String chit_holder_name;

    private String chit_name;

    private LocalDate chit_start_date;

    private String chit_value;

    private String current_value;

    private String notes;

    private String tenure;

    private String monthly_investment;

    private String available;

    public String getMonthly_investment() {
		return monthly_investment;
	}

	public void setMonthly_investment(String monthly_investment) {
		this.monthly_investment = monthly_investment;
	}

	private Long userid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChit_holder_name() {
        return chit_holder_name;
    }

    public void setChit_holder_name(String chit_holder_name) {
        this.chit_holder_name = chit_holder_name;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getChit_name() {
        return chit_name;
    }

    public void setChit_name(String chit_name) {
        this.chit_name = chit_name;
    }

    public LocalDate getChit_start_date() {
        return chit_start_date;
    }

    public void setChit_start_date(LocalDate chit_start_date) {
        this.chit_start_date = chit_start_date;
    }

    public String getChit_value() {
        return chit_value;
    }

    public void setChit_value(String chit_value) {
        this.chit_value = chit_value;
    }

    public String getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(String current_value) {
        this.current_value = current_value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChitDTO chitDTO = (ChitDTO) o;
        if(chitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChitDTO{" +
            "id=" + getId() +
            ", chit_holder_name='" + getChit_holder_name() + "'" +
            ", chit_name='" + getChit_name() + "'" +
            ", chit_start_date='" + getChit_start_date() + "'" +
            ", chit_value='" + getChit_value() + "'" +
            ", current_value='" + getCurrent_value() + "'" +
            ", notes='" + getNotes() + "'" +
            ", tenure='" + getTenure() + "'" +
            ", userid=" + getUserid() +
            "}";
    }
}
