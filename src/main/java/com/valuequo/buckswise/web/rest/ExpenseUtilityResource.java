package com.valuequo.buckswise.web.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
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

import com.valuequo.buckswise.domain.Income;
import com.valuequo.buckswise.domain.Utility;
import com.valuequo.buckswise.service.UtilityService;

/**
 * ExpenseUtility controller
 */
@RestController
@RequestMapping("/api/expense-utility")
public class ExpenseUtilityResource {

    private final Logger log = LoggerFactory.getLogger(ExpenseUtilityResource.class);

    /**
    * POST utility
     * @throws JSONException 
    */
    
    @Autowired
    private UtilityService utilityService;
    
    private String eName;
    private String eValue;
    private int userid;
    
    @PostMapping("/utility")
    public String utility(@RequestBody Map<String, Object> utility) throws JSONException {
    	System.out.println("data :" + utility);
    	
    	boolean flag = true;
    	
    	for(Map.Entry<String, Object> entry: utility.entrySet()) {
    		if(flag == true) {
    			
    			flag = false;

    			JSONObject jObj = new JSONObject(utility);
    			JSONArray jData = jObj.getJSONArray("dynamicUtility");
    			int length = jData.length();
    			for(int i=0; i<length; i++) {
    				JSONObject jObj1 = jData.getJSONObject(i);
    				this.eName = jObj1.getString("name");
    				this.eValue = jObj1.getString("value");
    				this.userid = jObj.getInt("userid");
    				if(this.eName == "userid") {
        				return null;
        			}
        			else {
        				utilityService.save(this.eName, this.eValue, this.userid);
        			}
    			}
    			
    		} else {
    			JSONObject jObj = new JSONObject(utility);
    			this.eName = entry.getKey();
    			this.eValue = entry.getValue().toString();
    			this.userid = jObj.getInt("userid");
    			if(this.eName == "userid") {
    				return null;
    			}
    			else {
    				utilityService.save(this.eName, this.eValue, this.userid);
    			}
    		}
    	}
        return null;
    }
    
    @GetMapping("/getutility/{userid}")
    public List<Utility> getIncome(@PathVariable int userid) {
 	   return utilityService.getDetail(userid);
    }

    @PutMapping("/pututility/{userid}")
    public String updateUtility(@PathVariable Long userid, @RequestBody Map<String, Object> update) throws JSONException {
    	System.out.println("pututility " + update);
    	boolean flag = true; 
    	for(Map.Entry<String, Object> entry: update.entrySet()) {
    		if(flag == true) {
    			
    			flag = false;
    			
    			JSONObject jObj = new JSONObject(update);
    			JSONArray jData = jObj.getJSONArray("dynamicUtility");
    			int length = jData.length();
    			for(int i=0; i<length; i++) {
    				JSONObject jObj1 = jData.getJSONObject(i);
    				this.eName = jObj1.getString("name");
    				this.eValue = jObj1.getString("value");
    				this.userid = jObj.getInt("userid");
	    			utilityService.update(this.eName, this.eValue, this.userid, userid);		
	    		}
    			
    		}else {
    			JSONObject jObj = new JSONObject(update);
    			this.eName = entry.getKey();
    			this.eValue = entry.getValue().toString();
    			this.userid = jObj.getInt("userid");
    			if(this.eName == "userid") {
    				return null;
    			}
    			else {
    				utilityService.update(this.eName, this.eValue, this.userid, userid);
    			}
    			
    		}
    	}
    	return null;
    }
    
    @DeleteMapping("/deleteutility/{id}")
    public String delete(@PathVariable Long id) {
    	utilityService.delete(id);
    	return null;
    }
 }
