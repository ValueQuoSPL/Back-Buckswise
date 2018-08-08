package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.GeneralInsurance;
import com.valuequo.buckswise.repository.GeneralInsuraanceRepository;

@Service
public class GeneralInsuranceService {

	@Autowired
	private GeneralInsuraanceRepository generalInsuraanceRepository;
	public GeneralInsurance save(int userid, String insureName, String policyName, String issuer, String policyDate,
			String policyNumber, String premiumName, String premium, String premiumTerm, String sum) {
		
		GeneralInsurance gi = new GeneralInsurance(userid, insureName, policyName, issuer, policyDate, policyNumber, premiumName, premium, premiumTerm, sum);
		gi.setUserid(userid);
		gi.setInsureName(insureName);
		gi.setPolicyName(policyName);
		gi.setIssuer(issuer);
		gi.setPolicyDate(policyDate);
		gi.setPolicyNumber(policyNumber);
		gi.setPremiumName(premiumName);
		gi.setPremium(premium);
		gi.setPremiumTerm(premiumTerm);
		gi.setSum(sum);
		generalInsuraanceRepository.save(gi);
		return null;
	}
	public List<GeneralInsurance> getDetail(int userid) {
		return generalInsuraanceRepository.findByUserid(userid);
	}

	
}
