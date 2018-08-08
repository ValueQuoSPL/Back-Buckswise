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
import org.springframework.web.bind.annotation.PostMapping;
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
	private int Userid;
    
    @PostMapping("/loan-debt")
    public String loanDebt(@RequestBody Map<String, Object>[] data) throws JSONException {
    	
    	
    	
    	for(Map<String, Object> entry: data) {
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
//    		entry.get("");
    		loananddebtService.save(this.Userid, this.amount, this.appname, this.checkType, this.itype, this.ldate, this.lenderName, this.ltype, this.rdate, this.roi, this.tenure);
    		
    	}
   
        return null;
    }

    @RequestMapping("/getloandebt/{userid}")
    @Timed
    public List<Loananddebt> getloananddebt(int userid){
    	return loananddebtService.getDetail(userid);
    }
}
