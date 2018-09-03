package com.valuequo.buckswise.web.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.domain.Credit;
import com.valuequo.buckswise.service.CreditService;

/**
 * Creditcard controller
 */
@RestController
@RequestMapping("/api/creditcard")
public class CreditcardResource {

    private final Logger log = LoggerFactory.getLogger(CreditcardResource.class);

    /**
    * POST credit
    */
    
    @Autowired
    private CreditService creditService;
    
    @PostMapping("/credit")
    public String credit(@RequestBody Map<String, Object>[] credit) {
    	
    	for(Map<String, Object> entry: credit) {
    		String bank = entry.get("bank").toString();
    		String type = entry.get("type").toString();
    		String roi = entry.get("roi").toString();
    		String balance = entry.get("balance").toString();
    		String lt = entry.get("limit").toString();
    		String pay = entry.get("monthly_pay").toString();
    		String usage = entry.get("monthly_usage").toString();
    		int userid = (int) entry.get("userid");
    		creditService.save(userid, bank, balance, type, roi, lt, pay, usage);
    	}
        return null;
    }

    @GetMapping("/getcredit/{userid}")
    public List<Credit> getcredit(@PathVariable int userid) {
    	return creditService.getDetail(userid);
    }
    
    @PutMapping("/putcredit/{uid}")
    public String putCredit(@PathVariable Long uid, @RequestBody Map<String, Object>[] update) {
    	for(Map<String, Object> entry: update) {
    		String bank = entry.get("bank").toString();
    		String type = entry.get("type").toString();
    		String roi = entry.get("roi").toString();
    		String balance = entry.get("balance").toString();
    		String lt = entry.get("lt").toString();
    		String pay = entry.get("pay").toString();
    		String usage = entry.get("usage").toString();
    		int userid = (int) entry.get("userid");
    		int id = (int) entry.get("id");
    		creditService.update(userid, bank, balance, type, roi, lt, pay, usage, uid, id);
    	}
    	return null;
    	
    }
    
    @DeleteMapping("/deletecredit/{id}")
    public String deleteCredit(@PathVariable Long id) {
    	creditService.delete(id);
    	return "Deleted!";
    }
}
