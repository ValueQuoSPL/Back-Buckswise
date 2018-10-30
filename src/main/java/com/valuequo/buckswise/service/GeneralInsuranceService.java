package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Credit;
import com.valuequo.buckswise.domain.GeneralInsurance;
import com.valuequo.buckswise.repository.GeneralInsuraanceRepository;

@Service
public class GeneralInsuranceService {

	@Autowired
	private GeneralInsuraanceRepository generalInsuraanceRepository;
	public GeneralInsurance save(int userid, String insureName, String policyName, String issuer, String policyDate,
			String policyNumber, String premiumName, String premium, String premiumTerm, String sum, String proposer) {
		
		GeneralInsurance gi = new GeneralInsurance(userid, insureName, policyName, issuer, policyDate, policyNumber, premiumName, premium, premiumTerm, sum, proposer);
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
		gi.setProposer(proposer);
		generalInsuraanceRepository.save(gi);
		return null;
	}
	public List<GeneralInsurance> getDetail(int userid) {
		return generalInsuraanceRepository.findByUserid(userid);
	}
	public GeneralInsurance update(int userid, String insureName, String policyName, String issuer, String policyDate,
			String policyNumber, String premiumName, String premium, String premiumTerm, String sum, String proposer, Long id, Long uid) {
		if(userid == uid) {
			List<GeneralInsurance> general = generalInsuraanceRepository.findById(id);
			for(GeneralInsurance g: general) {
				Long tableId = g.getId();
				if(tableId == id) {
					g.setInsureName(insureName);
					g.setIssuer(issuer);
					g.setPolicyDate(policyDate);
					g.setPolicyName(policyName);
					g.setPolicyNumber(policyNumber);
					g.setPremium(premium);
					g.setPremiumName(premiumName);
					g.setPremiumTerm(premiumTerm);
					g.setSum(sum);
					g.setProposer(proposer);
				   generalInsuraanceRepository.save(g);
				}
			}		
		}
		return null;
	}
	public void delete(Long id) {
		generalInsuraanceRepository.delete(id);
	}

	
}
