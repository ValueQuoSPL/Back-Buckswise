package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Userplan.
 */
@Entity
@Table(name = "userplan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Userplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "promocode")
    private String promocode;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Column(name = "discount")
    private String discount;

    @Column(name = "paid")
    private String paid;

    @Column(name = "jhi_plan")
    private String plan;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public Userplan uid(Long uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPromocode() {
        return promocode;
    }

    public Userplan promocode(String promocode) {
        this.promocode = promocode;
        return this;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public Userplan applyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getDiscount() {
        return discount;
    }

    public Userplan discount(String discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPaid() {
        return paid;
    }

    public Userplan paid(String paid) {
        this.paid = paid;
        return this;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getPlan() {
        return plan;
    }

    public Userplan plan(String plan) {
        this.plan = plan;
        return this;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Userplan expiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
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
        Userplan userplan = (Userplan) o;
        if (userplan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userplan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Userplan{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", promocode='" + getPromocode() + "'" +
            ", applyDate='" + getApplyDate() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", paid='" + getPaid() + "'" +
            ", plan='" + getPlan() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            "}";
    }
}
