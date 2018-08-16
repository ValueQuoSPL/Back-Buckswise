package com.valuequo.buckswise.web.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.domain.GeneralInsurance;
import com.valuequo.buckswise.service.GeneralInsuranceService;

/**
 * GeneralInsurance controller
 */
@RestController
@RequestMapping("/api/general-insurance")
public class GeneralInsuranceResource {

    private final Logger log = LoggerFactory.getLogger(GeneralInsuranceResource.class);

    /**
    * POST general
    */
    
    @Autowired
    private GeneralInsuranceService generalInsuranceService;
    
    @PostMapping("/general")
    public String general(@RequestBody Map<String, Object>[] general) {
    	
    	for(Map<String, Object> entry: general) {
    		
    		String insureName = entry.get("iName").toString();
    		String policyName = entry.get("pName").toString();
    		String issuer = entry.get("issuer").toString();
    		String policyDate = entry.get("pdate").toString();
    		String policyNumber = entry.get("poNo").toString();
    		String premiumName = entry.get("prName").toString();
    		String premium = entry.get("premium").toString();
    		String premiumTerm = entry.get("pterm").toString();
    		String sum = entry.get("sum").toString();
    		int userid = (int) entry.get("userid");
    		generalInsuranceService.save(userid, insureName, policyName, issuer, policyDate, policyNumber, premiumName, premium, premiumTerm,sum);
    		}
    	
        return null;
    }
    
    @GetMapping("/get/{userid}")
    public List<GeneralInsurance> getGeneral(@PathVariable int userid){
    	return generalInsuranceService.getDetail(userid);
    	
    }

}
