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
    		String policyName = entry.get("pName").toString();
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

    @GetMapping("/get/{userid}")
    public List<Health> getHealth(@PathVariable int userid) {
    	return healthService.getDetail(userid); 
    }
    
    @PutMapping("/puthealth/{uid}")
    public String putHealth(@PathVariable Long uid, @RequestBody Map<String, Object> update) throws JSONException {
    	JSONObject jObj = new JSONObject(update);
    		String insureName = jObj.get("iName").toString();
    		String issuer = jObj.get("issuer").toString();
    		String policyMode = jObj.get("pMode").toString();
    		String policyName = jObj.get("pName").toString();
    		String policyNumber = jObj.get("poNo").toString();
    		String premiumName = jObj.get("prName").toString();
    		String premium = jObj.get("premium").toString();
    		String premiumTerm = jObj.get("pterm").toString();
    		String date = jObj.get("sDate").toString();
    		String sum = jObj.get("sum").toString();
    		int userid = (int) jObj.get("userid");
    		Long id = jObj.getLong("id");
    		healthService.update(userid, insureName, issuer, policyMode, policyName, policyNumber, premiumName, premium, premiumTerm, date, sum, id, uid);

    	return null;
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
    	healthService.delete(id);
    	return null;
    }
  
}
