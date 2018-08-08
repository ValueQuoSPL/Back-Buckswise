package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Insurance;
import com.valuequo.buckswise.repository.InsuranceRepository;

@Service
public class InsuranceService {

	@Autowired
	private InsuranceRepository insuranceRepository;
	public Insurance save(int userid, String name, String insuranceName, String issure, String pMode, String pName, String prName,
			String premium, String prTerm, String sDate, String sum, String term) {
		
		Insurance insurance = new Insurance(userid, name, insuranceName, issure, pMode, pName, prName, premium, prTerm, sDate, sum, term);
		insurance.setName(name);
		insurance.setInsuranceName(insuranceName);
		insurance.setIssuer(issure);
		insurance.setpMode(pMode);
		insurance.setpName(pName);
		insurance.setPremiumName(prName);
		insurance.setPremium(premium);
		insurance.setPterm(prTerm);
		insurance.setsDate(sDate);
		insurance.setSum(sum);
		insurance.setTerm(term);
		insurance.setUserid(userid);
		insuranceRepository.save(insurance);
		
		return null;
	}
	public List<Insurance> getDetail(int userid) {
		return insuranceRepository.findByUserid(userid);
	}

	
}
