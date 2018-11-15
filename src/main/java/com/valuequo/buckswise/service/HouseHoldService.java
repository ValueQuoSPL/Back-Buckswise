package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Household;
import com.valuequo.buckswise.repository.HouseholdRepository;
import com.valuequo.buckswise.service.dto.HouseholdDTO;

@Service
public class HouseHoldService {

	@Autowired
	private HouseholdRepository householdRepository;
	
	public Household save(String hName, String hValue, Long userid) {
		Household house = new Household(hName, hValue, userid);
		house.setName(hName);
		house.setAmount(hValue);
		house.setUserid(userid);
		System.out.println("save data is" + house);
		householdRepository.save(house);
		return null;
		
	}

	public List<Household> getDetail(Long userid) {
		return householdRepository.findByUserid(userid);
	}

	public HouseholdDTO update(String hName, String hValue, Long userid) {
			HouseholdDTO householdDTO = new HouseholdDTO(userid, hName, hValue);
			householdDTO.setName(hName);
			householdDTO.setValue(hValue);
			householdDTO.setUserid(userid);
			
			String name = householdDTO.getName();
			
			List<Household> huosehold = householdRepository.findByName(name, userid);
			for(Household house: huosehold) {
				house.setAmount(householdDTO.getValue());
				householdRepository.save(house);
			}
		return null;
	}

	public void delete(Long id) {
		householdRepository.delete(id);
	}
}
