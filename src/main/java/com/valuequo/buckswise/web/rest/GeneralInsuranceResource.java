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
    		String proposer = entry.get("proposer").toString();
    		int userid = (int) entry.get("userid");
    		generalInsuranceService.save(userid, insureName, policyName, issuer, policyDate, policyNumber, premiumName, premium, premiumTerm,sum, proposer);
    		}
    	
        return null;
    }

    @GetMapping("/get/{userid}")
    public List<GeneralInsurance> getGeneral(@PathVariable int userid){
    	return generalInsuranceService.getDetail(userid);    	
    }
    
    @PutMapping("/putgeneral/{uid}")
    public String updateGeneralInsurence(@PathVariable Long uid, @RequestBody Map<String, Object> update) throws JSONException {
    	System.out.println(update);
    	JSONObject jObj = new JSONObject(update);
    		String insureName = jObj.get("ins_obj").toString();
    		System.out.println(insureName);
    		String policyName = jObj.get("policy_name").toString();
    		String issuer = jObj.get("issuer").toString();
    		String policyDate = jObj.get("start_date").toString();
    		String policyNumber = jObj.get("policy_no").toString();
    		String premiumName = jObj.get("premium_mode").toString();
    		String premium = jObj.get("premium").toString();
    		String premiumTerm = jObj.get("policy_term").toString();
    		String sum = jObj.get("sum").toString();
    		String proposer = jObj.get("proposer").toString();
    		int userid = (int) jObj.get("userid");
    		System.out.println(userid);
    		Long id = jObj.getLong("id");
    		 generalInsuranceService.update(userid, insureName, policyName, issuer, policyDate, policyNumber, premiumName, premium, premiumTerm, sum, proposer, id, uid);
    	
    	return null;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
    	generalInsuranceService.delete(id);
    	return null;
    }
}
