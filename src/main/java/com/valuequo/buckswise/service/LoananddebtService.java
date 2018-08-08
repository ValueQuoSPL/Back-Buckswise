package com.valuequo.buckswise.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Loananddebt;
import com.valuequo.buckswise.repository.LoananddebtRepository;

@Service
public class LoananddebtService {

	
	@Autowired
	private LoananddebtRepository loananddebtRepository;
	
	public Loananddebt save(int userid, String amount, String appName, String checkType, String itype, String ldate,
			String lenderName, String ltype, String rdate, String roi, String tenure) {
		
		Loananddebt ld = new Loananddebt( userid, amount, appName, checkType, itype, ldate, lenderName, ltype, rdate, roi, tenure);
		ld.setUserid(userid);
		ld.setAmount(amount);
		ld.setAppName(appName);
		ld.setCheckType(checkType);
		ld.setItype(itype);
		ld.setLdate(ldate);
		ld.setLenderName(lenderName);
		ld.setLtype(ltype);
		ld.setRdate(rdate);
		ld.setRoi(roi);
		ld.setTenure(tenure);
		loananddebtRepository.save(ld);
		return null;
		
	}

	public List<Loananddebt> getDetail(int userid) {
		return loananddebtRepository.findByUserid(userid);
	}

	
}
