package com.valuequo.buckswise.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "nav")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Component
public class Amfi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schemecode")
    private String SchemeCode;
   
    @Column(name = "amc_code")
    private String AMC_code;

    @Column(name = "isindivpayoutisingrowth")
    private String ISINDivPayoutISINGrowth;

    @Column(name = "isindivreinvestment")
    private String ISINDivReinvestment;
    
    @Column(name = "schemename")
    private String SchemeName;

    @Column(name = "netassetvalue")
    private String NetAssetValue;

    @Column(name = "date")
    private String Date;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the schemeCode
     */
    public String getSchemeCode() {
        return SchemeCode;
    }

    /**
     * @param schemeCode the schemeCode to set
     */
    public void setSchemeCode(String schemeCode) {
        SchemeCode = schemeCode;
    }

    /**
     * @return the iSINDivPayoutISINGrowth
     */
    public String getISINDivPayoutISINGrowth() {
        return ISINDivPayoutISINGrowth;
    }

    /**
     * @param iSINDivPayoutISINGrowth the iSINDivPayoutISINGrowth to set
     */
    public void setISINDivPayoutISINGrowth(String iSINDivPayoutISINGrowth) {
        ISINDivPayoutISINGrowth = iSINDivPayoutISINGrowth;
    }

    /**
     * @return the iSINDivReinvestment
     */
    public String getISINDivReinvestment() {
        return ISINDivReinvestment;
    }

    /**
     * @param iSINDivReinvestment the iSINDivReinvestment to set
     */
    public void setISINDivReinvestment(String iSINDivReinvestment) {
        ISINDivReinvestment = iSINDivReinvestment;
    }

    /**
     * @return the schemeName
     */
    public String getSchemeName() {
        return SchemeName;
    }

    /**
     * @param schemeName the schemeName to set
     */
    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    /**
     * @return the netAssetValue
     */
    public String getNetAssetValue() {
        return NetAssetValue;
    }

    /**
     * @param netAssetValue the netAssetValue to set
     */
    public void setNetAssetValue(String netAssetValue) {
        NetAssetValue = netAssetValue;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        Date = date;
    }

    /**
     * @return the aMC_code
     */
    public String getAMC_code() {
        return AMC_code;
    }

    /**
     * @param aMC_code the aMC_code to set
     */
    public void setAMC_code(String aMC_code) {
        AMC_code = aMC_code;
    }


}