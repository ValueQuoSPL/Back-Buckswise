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

	public Loananddebt update(String amount, String appname, String checkType, Long id, String itype, String ldate,
			String lenderName, String ltype, String rdate, String roi, String tenure, int userid, Long uid) {
		if(userid == uid) {
			List<Loananddebt> byId = loananddebtRepository.findById(id);
			for(Loananddebt loan: byId) {
				Long tableId = loan.getId();
				System.out.println("tableId:"+tableId);
				if(tableId == id) {
					loan.setAmount(amount);
					loan.setAppName(appname);
					loan.setCheckType(checkType);
					loan.setItype(ltype);
					loan.setLdate(ldate);
					loan.setLenderName(lenderName);
					loan.setRoi(roi);
					loan.setTenure(tenure);
					loan.setUserid(userid);
					loan.setLtype(ltype);
					loan.setItype(itype);
					loananddebtRepository.save(loan);
				}
			}
		}
		return null;
	}

	public void delete(Long id) {
		loananddebtRepository.delete(id);
	}

	
}
