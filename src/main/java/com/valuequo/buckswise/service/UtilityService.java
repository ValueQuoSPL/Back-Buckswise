package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Utility;
import com.valuequo.buckswise.repository.UtilityRepository;

@Service
public class UtilityService {

	@Autowired
	private UtilityRepository utilityRepository;
	
	public Utility save(String eName, String eValue, int userid) {
		
		Utility utility = new Utility(eName, eValue, userid);
		utility.setName(eName);
		utility.setAmount(eValue);
		utility.setUserid(userid);
		utilityRepository.save(utility);		
		return null;
		
	}

	public List<Utility> getDetail(int userid) {
		return utilityRepository.findByUserid(userid);
	}
}
