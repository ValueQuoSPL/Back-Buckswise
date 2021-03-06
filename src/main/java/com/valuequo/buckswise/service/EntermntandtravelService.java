package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Entermntandtravel;
import com.valuequo.buckswise.repository.Entermntandtravelrepository;
import com.valuequo.buckswise.service.dto.EntandTravelDTO;

@Service
public class EntermntandtravelService {

	@Autowired
	private Entermntandtravelrepository entermntandtravelrepository;
	
	public void save(String entName, String entValue, Long userid) {
		
		Entermntandtravel ent = new Entermntandtravel(entName, entValue, userid);
		ent.setUserid(userid);
		ent.setName(entName);
		ent.setAmount(entValue);
		System.out.println("service data" + ent);
		entermntandtravelrepository.save(ent);
		
	}

	public List<Entermntandtravel> getDetail(Long userid) {
		return entermntandtravelrepository.findByUserid(userid);
	}

	public EntandTravelDTO update(String entName, String entValue, Long userid) {
			EntandTravelDTO etDTO = new EntandTravelDTO(userid, entName, entValue);
			etDTO.setName(entName);
			etDTO.setUserid(userid);
			etDTO.setValue(entValue);
			
			String name = etDTO.getName();
			
			List<Entermntandtravel> entTravel = entermntandtravelrepository.findByName(name, userid);
			for(Entermntandtravel et: entTravel) {
				et.setAmount(etDTO.getValue());
				entermntandtravelrepository.save(et);
			}
		return null;
	}

	public void delete(Long id) {
		entermntandtravelrepository.delete(id);
	}
}
