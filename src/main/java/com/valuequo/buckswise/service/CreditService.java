package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Credit;
import com.valuequo.buckswise.repository.CreditRepository;

@Service
public class CreditService {
	
	@Autowired
	private CreditRepository creditRepository;

	public Credit save(int userid, String bank, String balance, String type, String roi) {

		Credit credit = new Credit(userid, bank, balance, type, roi);
		credit.setUserid(userid);
		credit.setBalance(balance);
		credit.setBank(bank);
		credit.setType(type);
		credit.setRoi(roi);
		creditRepository.save(credit);
		return null;
	}

	public List<Credit> getDetail(int userid) {
		return creditRepository.findByUserid(userid);
		
	}
}
