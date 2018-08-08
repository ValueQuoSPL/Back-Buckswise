package com.valuequo.buckswise.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "payment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "product_info")
    private String productInfo;

    @Column(name = "s_url")
    private String sUrl;

    @Column(name = "f_url")
    private String fUrl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public Payment amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFirstName() {
        return firstName;
    }

    public Payment firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String geteMail() {
        return eMail;
    }

    public Payment eMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public Payment phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public Payment productInfo(String productInfo) {
        this.productInfo = productInfo;
        return this;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getsUrl() {
        return sUrl;
    }

    public Payment sUrl(String sUrl) {
        this.sUrl = sUrl;
        return this;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public String getfUrl() {
        return fUrl;
    }

    public Payment fUrl(String fUrl) {
        this.fUrl = fUrl;
        return this;
    }

    public void setfUrl(String fUrl) {
        this.fUrl = fUrl;
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
        Payment pay = (Payment) o;
        if (pay.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pay.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", firstName='" + getFirstName() + "'" +
            ", eMail='" + geteMail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", productInfo='" + getProductInfo() + "'" +
            ", sUrl='" + getsUrl() + "'" +
            ", fUrl='" + getfUrl() + "'" +
            "}";
    }
}