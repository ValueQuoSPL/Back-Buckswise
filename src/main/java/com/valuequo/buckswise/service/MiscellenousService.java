package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Miscelleonous;
import com.valuequo.buckswise.repository.MiscellenousRepository;

@Service
public class MiscellenousService {

	@Autowired
	private MiscellenousRepository miscellenousRepository;
	
	public Miscelleonous save(String mName, String mValue, int userid) {
	
		Miscelleonous misc = new Miscelleonous(mValue, mValue, userid);
		misc.setUserid(userid);
		misc.setName(mName);
		misc.setAmount(mValue);
		miscellenousRepository.save(misc);
		return null;
	}

	public List<Miscelleonous> getDetail(int userid) {
		return miscellenousRepository.findByUserid(userid);
	}
}
