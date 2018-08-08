package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Household;
import com.valuequo.buckswise.repository.HouseholdRepository;

@Service
public class HouseHoldService {

	@Autowired
	private HouseholdRepository householdRepository;
	
	public Household save(String hName, String hValue, int userid) {
		Household house = new Household(hName, hValue, userid);
		house.setName(hName);
		house.setAmount(hValue);
		house.setUserid(userid);
		System.out.println("save data is" + house);
		householdRepository.save(house);
		return null;
		
	}

	public List<Household> getDetail(int userid) {
		return householdRepository.findByUserid(userid);
	}

	
}
