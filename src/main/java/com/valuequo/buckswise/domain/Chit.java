package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Chit.
 */
@Entity
@Table(name = "chit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Chit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chit_holder_name")
    private String chit_holder_name;

    @Column(name = "chit_name")
    private String chit_name;

    @Column(name = "chit_start_date")
    private LocalDate chit_start_date;

    @Column(name = "chit_value")
    private String chit_value;

    @Column(name = "current_value")
    private String current_value;

    @Column(name = "notes")
    private String notes;

    @Column(name = "tenure")
    private String tenure;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "monthly_investment")
    private String monthly_investment;

    @Column(name = "available")
    private String available;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonthly_investment() {
		return monthly_investment;
	}

	public void setMonthly_investment(String monthly_investment) {
		this.monthly_investment = monthly_investment;
	}

    public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getChit_holder_name() {
        return chit_holder_name;
    }

    public Chit chit_holder_name(String chit_holder_name) {
        this.chit_holder_name = chit_holder_name;
        return this;
    }

    public void setChit_holder_name(String chit_holder_name) {
        this.chit_holder_name = chit_holder_name;
    }

    public String getChit_name() {
        return chit_name;
    }

    public Chit chit_name(String chit_name) {
        this.chit_name = chit_name;
        return this;
    }

    public void setChit_name(String chit_name) {
        this.chit_name = chit_name;
    }

    public LocalDate getChit_start_date() {
        return chit_start_date;
    }

    public Chit chit_start_date(LocalDate chit_start_date) {
        this.chit_start_date = chit_start_date;
        return this;
    }

    public void setChit_start_date(LocalDate chit_start_date) {
        this.chit_start_date = chit_start_date;
    }

    public String getChit_value() {
        return chit_value;
    }

    public Chit chit_value(String chit_value) {
        this.chit_value = chit_value;
        return this;
    }

    public void setChit_value(String chit_value) {
        this.chit_value = chit_value;
    }

    public String getCurrent_value() {
        return current_value;
    }

    public Chit current_value(String current_value) {
        this.current_value = current_value;
        return this;
    }

    public void setCurrent_value(String current_value) {
        this.current_value = current_value;
    }

    public String getNotes() {
        return notes;
    }

    public Chit notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTenure() {
        return tenure;
    }

    public Chit tenure(String tenure) {
        this.tenure = tenure;
        return this;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public Long getUserid() {
        return userid;
    }

    public Chit userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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
        Chit chit = (Chit) o;
        if (chit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Chit{" +
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
