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

import com.valuequo.buckswise.domain.Miscelleonous;
import com.valuequo.buckswise.service.MiscellenousService;

/**
 * Miscellaneous controller
 */
@RestController
@RequestMapping("/api/miscellaneous")
public class MiscellaneousResource {

    private final Logger log = LoggerFactory.getLogger(MiscellaneousResource.class);

    /**
    * POST miscellenous
    */
    @Autowired
    private MiscellenousService miscellenousService;
    
    private String mName;
    private String mValue;
    private Long userid;
    
    @PostMapping("/miscellenous")
    public String miscellenous(@RequestBody Map<String, Object> mllnous) throws JSONException {
    	
    	boolean flag = true;
    	
    	for(Map.Entry<String, Object> entry: mllnous.entrySet()) {
    		if(flag == true) {
    			flag = false;
    			
    			JSONObject jObj = new JSONObject(mllnous);
    			JSONArray jData = jObj.getJSONArray("dynamicMisc");
    			int length = jData.length();
    			for(int i=0; i<length; i++) {
    				JSONObject jObj1 = jData.getJSONObject(i);
    				this.mName = jObj1.getString("name");
    				this.mValue = jObj1.getString("value");
    				this.userid = jObj.getLong("userid");
        			System.out.println("userid is :-" + this.userid);
        			if(this.mName == "userid") {
        				return null;
        			}
        			else {
        				miscellenousService.save(this.mName, this.mValue, this.userid);
        			}
        				
    			}
    			
    		} else {
    			
    			this.mName = entry.getKey();
    			this.mValue = entry.getValue().toString();
    			JSONObject jObj = new JSONObject(mllnous);
    			this.userid = jObj.getLong("userid");
    			System.out.println("userid is in else :-" + this.userid);
    			if(this.mName == "userid") {
    				return null;
    			}
    			else {
    				miscellenousService.save(this.mName, this.mValue, this.userid);
    			}
    			
    		}
    	}
    	
        return null;
    }

    @GetMapping("/get/{userid}")
    public List<Miscelleonous> getMiscellenous(@PathVariable Long userid) {
    	return miscellenousService.getDetail(userid);
    }
    
    @PutMapping("/putmisc/{userid}")
    public String updateMisc(@PathVariable Long userid, @RequestBody Map<String, Object> updateMisc) throws JSONException {
    	boolean flag = true;
    	
    	for(Map.Entry<String, Object> entry: updateMisc.entrySet()) {
    		if(flag == true) {
    			flag = false;
    			
    			JSONObject jObj = new JSONObject(updateMisc);
    			JSONArray jData = jObj.getJSONArray("dynamicMisc");
    			int length = jData.length();
    			for(int i=0; i<length; i++) {
    				JSONObject jObj1 = jData.getJSONObject(i);
    				this.mName = jObj1.getString("name");
    				this.mValue = jObj1.getString("value");
    				this.userid = jObj.getLong("userid");
        			System.out.println("userid is :-" + this.userid);
        			if(this.mName == "userid") {
        				return null;
        			}
        			else {
        				miscellenousService.update(this.mName, this.mValue, this.userid);
        			}
   			}
    			
    		} else {
    			
    			this.mName = entry.getKey();
    			this.mValue = entry.getValue().toString();
    			JSONObject jObj = new JSONObject(updateMisc);
    			this.userid = jObj.getLong("userid");
    			System.out.println("userid is in else :-" + this.userid);
    			if(this.mName == "userid") {
    				return null;
    			}
    			else {
    				miscellenousService.update(this.mName, this.mValue, this.userid);
    			}
    			
    		}
    	}
    	return null;
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
    	miscellenousService.delete(id);
    	return "Deleted";
    }
}
