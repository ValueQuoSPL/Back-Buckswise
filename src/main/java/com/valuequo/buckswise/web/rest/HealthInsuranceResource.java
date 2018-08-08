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

import com.valuequo.buckswise.domain.Health;
import com.valuequo.buckswise.service.HealthService;

/**
 * HealthInsurance controller
 */
@RestController
@RequestMapping("/api/health-insurance")
public class HealthInsuranceResource {

    private final Logger log = LoggerFactory.getLogger(HealthInsuranceResource.class);

    /**
    * POST health
    */
    
    @Autowired
    private HealthService healthService;
    
    @PostMapping("/health")
    public String health(@RequestBody Map<String, Object>[] health) {
    	
    	for(Map<String, Object> entry: health) {
    		String insureName = entry.get("iName").toString();
    		String issuer = entry.get("issuer").toString();
    		String policyMode = entry.get("pMode").toString();
    		String policyName = entry.get("pMode").toString();
    		String policyNumber = entry.get("poNo").toString();
    		String premiumName = entry.get("prName").toString();
    		String premium = entry.get("premium").toString();
    		String premiumTerm = entry.get("pterm").toString();
    		String date = entry.get("sDate").toString();
    		String sum = entry.get("sum").toString();
    		int userid = (int) entry.get("userid");
    		healthService.save(userid, insureName, issuer, policyMode, policyName, policyNumber, premiumName, premium, premiumTerm, date, sum);
    		
    		}
        return null;
    }

    @RequestMapping("/get/{userid}")
    public List<Health> getHealth(int userid) {
    	return healthService.getDetail(userid); 
    }
  
}
