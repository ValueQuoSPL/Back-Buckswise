package com.valuequo.buckswise.web.rest;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/api/life")
public class InsurancepaymentResource {

    private final Logger log = LoggerFactory.getLogger(InsurancepaymentResource.class);

    /**
    * POST insuancePayment
    */
    
    @Autowired
    private InsuranceService insuranceService;
    
    @PostMapping("/postlife")
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
    		String sum=entry.get("sum").toString();
    		String term=entry.get("term").toString();
    		String policyNumber=entry.get("policynumber").toString();
    		int userid=(int) entry.get("userid");

    		insuranceService.save(userid, name, insuranceName, issure, pMode, pName, prName, premium, prTerm, sDate, sum, term, policyNumber);
    	}
        return null;
    }
    
    @GetMapping("/getlife/{userid}")
    @Timed
    public List<Insurance> getinsurance(@PathVariable int userid){
    	return insuranceService.getDetail(userid);
    }
    
    @PutMapping("/putlife/{uid}")
    public String updateLife(@PathVariable Long uid, @RequestBody Map<String, Object> update) throws JSONException {
    	    JSONObject jObj = new JSONObject(update);
	    		String name = jObj.get("type").toString();
	    		String insuranceName=jObj.get("ins_name").toString();
	    		String issure=jObj.get("issuer").toString();
	    		String pMode=jObj.get("premium_mode").toString();
	    		String pName=jObj.get("policy_name").toString();
	    		String prName=jObj.get("proposer_name").toString();
	    		String premium=jObj.get("premium").toString();
	    		String prTerm=jObj.get("premium_term").toString();
	    		String sDate= jObj.get("start_date").toString();
	    		System.out.println(sDate);
	    		String sum=jObj.get("sum").toString();
	    		String term=jObj.get("policy_term").toString();
	    		String policynumber=jObj.get("policynumber").toString();
	    		int userid=(int) jObj.get("userid");
	    		Long id = jObj.getLong("id");

    		insuranceService.update(userid, name, insuranceName, issure, pMode, pName, prName, premium, prTerm, sDate, sum, term, policynumber, id, uid);

    	return null;
    }

    @DeleteMapping("/deletelife/{id}")
    public String delete(@PathVariable Long id) {
    	insuranceService.delete(id);
    	return null;
    }
}
