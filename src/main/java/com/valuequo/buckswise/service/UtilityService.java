package com.valuequo.buckswise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Utility;
import com.valuequo.buckswise.repository.UtilityRepository;

@Service
public class UtilityService {

	@Autowired
	private UtilityRepository utilityRepository;
	
	public Utility save(String eName, String eValue) {
		
		Utility utility = new Utility(eName, eValue);
		utility.setName(eName);
		utility.setAmount(eValue);
		utilityRepository.save(utility);		
		return null;
		
	}
}
