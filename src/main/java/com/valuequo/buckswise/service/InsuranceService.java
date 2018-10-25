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
			String premium, String prTerm, String sDate, String sum, String term, String policyNumber) {
		
		Insurance insurance = new Insurance(userid, name, insuranceName, issure, pMode, pName, prName, premium, prTerm, sDate, sum, term, policyNumber);
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
		insurance.setPolicynumber(policyNumber);
		insuranceRepository.save(insurance);
		
		return null;
	}
	public List<Insurance> getDetail(int userid) {
		return insuranceRepository.findByUserid(userid);
	}
	public Insurance update(int userid, String name, String insuranceName, String issure, String pMode, String pName,
			String prName, String premium, String prTerm, String sDate, String sum, String term, String policynumber, Long id, Long uid) {
		if(userid == uid) {
			List<Insurance> insurance = insuranceRepository.findById(id);
			for(Insurance ins: insurance) {
				Long tableId = ins.getId();
				if(tableId == id) {
					ins.setName(name);
					ins.setInsuranceName(insuranceName);
					ins.setIssuer(issure);
					ins.setpMode(pMode);
					ins.setpName(pName);
					ins.setPremiumName(prName);
					ins.setPremium(premium);
					ins.setPterm(prTerm);
					ins.setsDate(sDate);
					ins.setSum(sum);
					ins.setTerm(term);
					ins.setPolicynumber(policynumber);
					insuranceRepository.save(ins);
				} 
			}
		} 
		return null;
	}
	public void delete(Long id) {
		insuranceRepository.delete(id);	
	}
	
}
