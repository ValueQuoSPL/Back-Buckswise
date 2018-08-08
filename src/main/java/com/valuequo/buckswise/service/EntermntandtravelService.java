package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Entermntandtravel;
import com.valuequo.buckswise.repository.Entermntandtravelrepository;

@Service
public class EntermntandtravelService {

	@Autowired
	private Entermntandtravelrepository entermntandtravelrepository;
	
	public void save(String entName, String entValue, int userid) {
		
		Entermntandtravel ent = new Entermntandtravel(entName, entValue, userid);
		ent.setUserid(userid);
		ent.setName(entName);
		ent.setAmount(entValue);
		System.out.println("service data" + ent);
		entermntandtravelrepository.save(ent);
		
	}

	public List<Entermntandtravel> getDetail(int userid) {
		return entermntandtravelrepository.findByUserid(userid);
	}
}
