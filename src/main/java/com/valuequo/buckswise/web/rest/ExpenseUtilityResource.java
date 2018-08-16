package com.valuequo.buckswise.web.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    				this.userid = jObj1.getInt("userid");
//    				System.out.println(" output: " + this.eName);
    				
    				utilityService.save(this.eName, this.eValue, this.userid);		
    			}
    			
    		} else {
    			JSONObject jObj = new JSONObject(utility);
    			this.eName = entry.getKey();
    			this.eValue = entry.getValue().toString();
    			this.userid = jObj.getInt("userid");
    			utilityService.save(this.eName, this.eValue, this.userid);
    		}
    	}
        return null;
    }
    
    @GetMapping("/getutility/{userid}")
    public List<Utility> getIncome(@PathVariable int userid) {
 	   return utilityService.getDetail(userid);
    }

}
