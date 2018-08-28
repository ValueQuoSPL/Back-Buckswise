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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.domain.Income;
import com.valuequo.buckswise.service.IncomeService;

/**
 * Income controller
 */
@RestController
@RequestMapping("/api/income")
public class IncomeResource {

    private final Logger log = LoggerFactory.getLogger(IncomeResource.class);

    /**
    * POST income
    */
    
    @Autowired
    private IncomeService incomeService;
    
    private String uName;
    private String uValue;
    private int userid;

	@PostMapping("/income")
    public String income(@RequestBody Map<String, Object> stuffs) throws JSONException {    	
    	
    	boolean flag = true;
    	for(Map.Entry<String, Object> entry: stuffs.entrySet()) {
    		if(flag == true)
    		{
    			flag = false;
    			
    			JSONObject jObj = new JSONObject(stuffs);   
    	    	JSONArray ja_data = jObj.getJSONArray("dynamicIncome");
    	    	System.out.println("ja_data is : " + ja_data);
    	    	int length = ja_data.length();
    	    	System.out.println("length : " + length);
    	    	for(int i=0; i<length; i++) {
    	    		
    	    		JSONObject jObj1 = ja_data.getJSONObject(i);
    	    		this.uName = jObj1.get("name").toString();
    	    		this.uValue = jObj1.get("value").toString();
					this.userid = jObj.getInt("userid");
					System.out.println(this.userid);
    	    		incomeService.save(this.uName, this.uValue, this.userid);
    	    		
    	    	}    			
    		}
    		else
    		{
    			JSONObject jObj = new JSONObject(stuffs);
    			this.uName = entry.getKey();
    			this.uValue = entry.getValue().toString();
    			this.userid = jObj.getInt("userid");
    			incomeService.save(this.uName, this.uValue, this.userid);
    		}
    	}    	   	
        return null;
   }

   @GetMapping("/getincome/{userid}")
   public List<Income> getIncome(@PathVariable int userid) {
	   return incomeService.getDetail(userid);
   }
   
   @PutMapping("/putincome/{userid}")
   public String updateIncome(@PathVariable Long userid, @RequestBody Map<String, Object> stuff) throws JSONException {
	  
	   boolean flag = true;
	   
	   for(Map.Entry<String, Object> entry: stuff.entrySet()) {
		
		   if(flag == true) {
			
			   flag = false;
			
			JSONObject jObj = new JSONObject(stuff);   
   	    	JSONArray jadata = jObj.getJSONArray("dynamicIncome");
   	    	int length = jadata.length();
   	    	System.out.println("length : " + length);
   	    	for(int i=0; i<length; i++) {
   	    		
   	    		JSONObject jObj1 = jadata.getJSONObject(i);
   	    		this.uName = jObj1.get("name").toString();
   	    		this.uValue = jObj1.get("value").toString();
   	    		this.userid = jObj.getInt("userid");
   	    		incomeService.update(this.uName, this.uValue, this.userid, userid);
   	    		
   	    	} 
		   }else {
			   
			JSONObject jObj = new JSONObject(stuff);
   			this.uName = entry.getKey();
   			this.uValue = entry.getValue().toString();
   			this.userid = jObj.getInt("userid");
   			incomeService.update(this.uName, this.uValue, this.userid, userid);
		   }
	   }
	   return null;
   }
	
}
