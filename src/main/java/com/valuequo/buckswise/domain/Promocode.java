package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Promocode.
 */
@Entity
@Table(name = "promocode")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Promocode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_plan")
    private String plan;

    @Column(name = "promocode")
    private String promocode;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "discount")
    private String discount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public Promocode plan(String plan) {
        this.plan = plan;
        return this;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPromocode() {
        return promocode;
    }

    public Promocode promocode(String promocode) {
        this.promocode = promocode;
        return this;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Promocode expiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDiscount() {
        return discount;
    }

    public Promocode discount(String discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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
        Promocode promocode = (Promocode) o;
        if (promocode.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), promocode.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Promocode{" +
            "id=" + getId() +
            ", plan='" + getPlan() + "'" +
            ", promocode='" + getPromocode() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", discount='" + getDiscount() + "'" +
            "}";
    }
}
