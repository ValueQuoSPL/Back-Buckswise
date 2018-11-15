package com.valuequo.buckswise.web.rest;

import java.util.Date;
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

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Loananddebt;
import com.valuequo.buckswise.service.LoananddebtService;

/**
 * Loananddebt controller
 */
@RestController
@RequestMapping("/api/loananddebt")
public class LoananddebtResource {

    private final Logger log = LoggerFactory.getLogger(LoananddebtResource.class);

    /**
    * POST loanDebt
     * @throws JSONException 
    */
    
    @Autowired
    private LoananddebtService loananddebtService;
    
    private String amount;
	private String appname;
	private String checkType;
	private String itype;
	private String ldate;
	private String lenderName;
	private String ltype;
	private String rdate;
	private String roi;
	private String tenure;
	private Long id;
	private int Userid;
	private String Outstandingpricipal;
    
    @PostMapping("/loan-debt")
    public String loanDebt(@RequestBody Map<String, Object>[] data) throws JSONException {
  	
    	for(Map<String, Object> entry: data) {
    		System.out.println(entry);
    		System.out.println(entry.get("amnt"));
    		this.amount = entry.get("amnt").toString();
    		System.out.println("amount is :-" + amount);
    		System.out.println(entry.get("app"));
    		this.appname = entry.get("app").toString();
    		System.out.println(entry.get("check"));
    		this.checkType = entry.get("check").toString();
    		System.out.println(entry.get("itype"));
    		this.itype = entry.get("itype").toString();
    		System.out.println(entry.get("ldate"));
    		this.ldate = entry.get("ldate").toString();
    		System.out.println(entry.get("lender"));
    		this.lenderName = entry.get("lender").toString();
    		System.out.println(entry.get("ltype"));
    		this.ltype = entry.get("ltype").toString();
    		System.out.println(entry.get("rdate"));
    		this.rdate = entry.get("rdate").toString();
    		System.out.println(entry.get("roi"));
    		this.roi = entry.get("roi").toString();
    		System.out.println(entry.get("tenure"));
    		this.tenure = entry.get("tenure").toString();
    		this.Userid = (int) entry.get("userid");
    		System.out.println("Userid: " + Userid);
    		this.Outstandingpricipal = entry.get("OutstandingPricipal").toString();
    		
    		loananddebtService.save(this.Userid, this.amount, this.appname, this.checkType, this.itype, this.ldate, this.lenderName, this.ltype, this.rdate, this.roi, this.tenure, this.Outstandingpricipal);
    		
    	}
   
        return null;
    }

    @GetMapping("/getloandebt/{userid}")
    @Timed
    public List<Loananddebt> getloananddebt(@PathVariable int userid){
    	return loananddebtService.getDetail(userid);
    }
    
    @GetMapping("/getlaondebtId/{id}")
    @Timed
    public List<Loananddebt> getloadebt(@PathVariable Long id){
    	return loananddebtService.getDetailById(id);
    }
    
    
    @PutMapping("/putloandebt/{uid}")
    @Timed
    public String putloandebt(@PathVariable Long uid, @RequestBody Map<String, Object> update) throws JSONException {
    	System.out.println("update data is" + update);

    		JSONObject jObj = new JSONObject(update);
    		this.amount = jObj.get("amnt").toString();
    		System.out.println("updatedatais:" + this.amount);
    		this.appname = jObj.get("applicant").toString();
    		this.checkType = jObj.get("check").toString();
    		this.id = jObj.getLong("id");
    		this.itype = jObj.get("intrest_type").toString();
    		this.ldate = jObj.get("ldate").toString();
    		this.lenderName = jObj.get("lender").toString();
    		this.ltype = jObj.get("loan_type").toString();
    		this.rdate = jObj.get("rdate").toString();
    		this.roi = jObj.get("roi").toString();
    		this.tenure = jObj.get("tenure").toString();
    		this.Userid = jObj.getInt("userid");
    	
    		loananddebtService.update(this.amount, this.appname, this.checkType, this.id, this.itype, this.ldate, this.lenderName, this.ltype, this.rdate, this.roi, this.tenure, this.Userid, uid);
    		
    	return null;
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
    	loananddebtService.delete(id);
    	return null;
    }
}
