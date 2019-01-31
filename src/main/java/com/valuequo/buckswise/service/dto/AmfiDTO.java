package com.valuequo.buckswise.service.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AmfiDTO {
    private String SchemeCode;
    private String ISINDivPayoutISINGrowth;
    private String ISINDivReinvestment;
    private String SchemeName;
    private String NetAssetValue;
    private String Date;
    private String day2;

    /**
     * @return the schemeCode
     */
    public String getSchemeCode() {
        return SchemeCode;
    }

    /**
     * @return the day2
     */
    public String getDay2() {
        return day2;
    }

    /**
     * @param day2 the day2 to set
     */
    public void setDay2(String day2) {
        this.day2 = day2;
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
    
}