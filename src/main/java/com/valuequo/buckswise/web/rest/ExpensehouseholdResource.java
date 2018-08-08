package com.valuequo.buckswise.web.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Household;
import com.valuequo.buckswise.service.HouseHoldService;

/**
 * Expensehousehold controller
 */
@RestController
@RequestMapping("/api/expensehousehold")
public class ExpensehouseholdResource {

    private final Logger log = LoggerFactory.getLogger(ExpensehouseholdResource.class);

    /**
    * POST household
    */
    @Autowired
    private HouseHoldService houseHoldService;
    private String hName;
    private String hValue;
    private int userid;
    
    @PostMapping("/household")
    public String household(@RequestBody Map<String, Object> household) throws JSONException {
    	
    	System.out.println("output is : " + household);
    	boolean flag = true;
    	
    	for(Map.Entry<String, Object> entry: household.entrySet()) {
    		if(flag == true) {
    			flag = false;
    			
    			JSONObject jObj = new JSONObject(household);
    			JSONArray jData = jObj.getJSONArray("dynamicHousehold");
    			int length = jData.length();
    			for(int i=0; i<length; i++) {
    				JSONObject jObj1 = jData.getJSONObject(i);
    				this.hName = jObj1.getString("name");
    				this.hValue = jObj1.getString("value");
    				this.userid = jObj.getInt("userid");
        			System.out.println("userid is :-" + this.userid);
    				
    				houseHoldService.save(this.hName, this.hValue, this.userid);	
    			}
    			
    		} else {
    			
    			this.hName = entry.getKey();
    			this.hValue = entry.getValue().toString();
    			JSONObject jObj = new JSONObject(household);
    			this.userid = jObj.getInt("userid");
    			System.out.println("userid is in else :-" + this.userid);
    			houseHoldService.save(this.hName, this.hValue, this.userid);
    		}
    	}
        return null;
    }
    
    @RequestMapping("/get/{userid}")
    @Timed
    public List<Household> getHousehold(int userid){
    	return houseHoldService.getDetail(userid);
    }
}
