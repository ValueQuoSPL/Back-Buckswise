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

import com.valuequo.buckswise.domain.Entermntandtravel;
import com.valuequo.buckswise.service.EntermntandtravelService;

/**
 * ExpenseEntermntandtravel controller
 */
@RestController
@RequestMapping("/api/expense-entermntandtravel")
public class ExpenseEntermntandtravelResource {

    private final Logger log = LoggerFactory.getLogger(ExpenseEntermntandtravelResource.class);

    /**
    * POST entermentandtravel
    */
    @Autowired
    private EntermntandtravelService entermntandtravelService;
    
    private String entName;
    private String entValue;
    private int userid;
    
    @PostMapping("/entermentandtravel")
    public String entermentandtravel(@RequestBody Map<String, Object> eAndt) throws JSONException {
        
    	boolean flag = true;
    	
    	for(Map.Entry<String, Object> entry: eAndt.entrySet()) {
    		if(flag == true) {
    			flag = false;
    			
    			JSONObject jObj = new JSONObject(eAndt);
    			JSONArray jData = jObj.getJSONArray("dynamicTravel");
    			int length = jData.length();
    			for(int i=0; i<length; i++) {
    				JSONObject jObj1 = jData.getJSONObject(i);
    				this.entName = jObj1.getString("name");
    				this.entValue = jObj1.getString("value");
    				this.userid = jObj.getInt("userid");
        			System.out.println("userid is :-" + this.userid);
    				
        			entermntandtravelService.save(this.entName, this.entValue, this.userid);	
    			}
    			
    		} else {
    			
    			this.entName = entry.getKey();
    			this.entValue = entry.getValue().toString();
    			JSONObject jObj = new JSONObject(eAndt);
    			this.userid = jObj.getInt("userid");
    			System.out.println("userid is in else :-" + this.userid);
    			entermntandtravelService.save(this.entName, this.entValue, this.userid);
    		}
    	}
    	return null;
    }

    @GetMapping("/get/{userid}")
    public List<Entermntandtravel> getEnt(@PathVariable int userid) {
    	return entermntandtravelService.getDetail(userid);
    }
}
