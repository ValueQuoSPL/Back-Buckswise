package com.valuequo.buckswise.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Userplan entity.
 */
public class UserplanDTO implements Serializable {

    private Long id;

    private Integer uid;

    private String promocode;

    private LocalDate applyDate;

    private String discount;

    private String paid;

    private String plan;

    private LocalDate expiryDate;

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

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserplanDTO userplanDTO = (UserplanDTO) o;
        if (userplanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userplanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserplanDTO{" +
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
