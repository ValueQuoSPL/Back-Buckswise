package com.valuequo.buckswise.web.rest;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Insurance;
import com.valuequo.buckswise.service.InsuranceService;

/**
 * Insurancepayment controller
 */
@RestController
@RequestMapping("/api/insurancepayment")
public class InsurancepaymentResource {

    private final Logger log = LoggerFactory.getLogger(InsurancepaymentResource.class);

    /**
    * POST insuancePayment
    */
    
    @Autowired
    private InsuranceService insuranceService;
    
    @PostMapping("/insuance-payment")
    public String insuancePayment(@RequestBody Map<String, Object>[] insurance) {
    	for(Map<String, Object> entry: insurance) {
    		
    		String name = entry.get("iName").toString();
    		String insuranceName=entry.get("ins_name").toString();
    		String issure=entry.get("issuer").toString();
    		String pMode=entry.get("pMode").toString();
    		String pName=entry.get("pName").toString();
    		String prName=entry.get("prName").toString();
    		String premium=entry.get("premium").toString();
    		String prTerm=entry.get("pterm").toString();
    		String sDate= entry.get("sDate").toString();
    		System.out.println(sDate);
    		String sum=entry.get("sum").toString();
    		String term=entry.get("term").toString();
    		int userid=(int) entry.get("userid");
    		
    		insuranceService.save(userid, name, insuranceName, issure, pMode, pName, prName, premium, prTerm, sDate, sum, term);
    	}
        return null;
    }
    
    @RequestMapping("/getinsurane/{userid}")
    @Timed
    public List<Insurance> getinsurance(int userid){
    	return insuranceService.getDetail(userid);
    }

}
