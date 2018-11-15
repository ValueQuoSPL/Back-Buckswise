package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Utility;
import com.valuequo.buckswise.repository.UtilityRepository;
import com.valuequo.buckswise.service.dto.UtilityDTO;

@Service
public class UtilityService {

	@Autowired
	private UtilityRepository utilityRepository;
	
	public Utility save(String eName, String eValue, Long userid) {
		
		Utility utility = new Utility(eName, eValue, userid);
		utility.setName(eName);
		utility.setAmount(eValue);
		utility.setUserid(userid);
		utilityRepository.save(utility);		
		return null;
		
	}

	public List<Utility> getDetail(Long userid) {
		return utilityRepository.findByUserid(userid);
	}

	public UtilityDTO update(String eName, String eValue, Long userid) {
		
			UtilityDTO utilityDTO = new UtilityDTO(eName, eValue, userid);
			utilityDTO.setName(eName);
			utilityDTO.setValue(eValue);
			utilityDTO.setUserid(userid);
			
			String name = utilityDTO.getName();
			System.out.println("upadatename"+name);
			List<Utility> utility =  utilityRepository.findByName(name, userid);
			for(Utility ut: utility) {
				ut.setAmount(utilityDTO.getValue());
				utilityRepository.save(ut);
			}
		return null;
	}

	public void delete(Long id) {
		utilityRepository.delete(id);
	}
}
