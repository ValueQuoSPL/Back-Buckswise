package com.valuequo.buckswise.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Promocode entity.
 */
public class PromocodeDTO implements Serializable {

    private Long id;

    private String plan;

    private String promocode;

    private LocalDate expiryDate;

    private String discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PromocodeDTO promocodeDTO = (PromocodeDTO) o;
        if (promocodeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), promocodeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PromocodeDTO{" +
            "id=" + getId() +
            ", plan='" + getPlan() + "'" +
            ", promocode='" + getPromocode() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", discount='" + getDiscount() + "'" +
            "}";
    }
}
