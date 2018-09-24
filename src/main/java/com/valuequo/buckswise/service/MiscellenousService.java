package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Miscelleonous;
import com.valuequo.buckswise.repository.MiscellenousRepository;
import com.valuequo.buckswise.service.dto.MiscellenousDTO;

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

	public MiscellenousDTO update(String mName, String mValue, int userid, Long userid2) {
		if(userid == userid2) {
			MiscellenousDTO miscDTO = new MiscellenousDTO(userid, mName, mValue);
			miscDTO.setName(mName);
			miscDTO.setValue(mValue);
			miscDTO.setUserid(userid);
			
			String name = miscDTO.getName();
			
			List<Miscelleonous> misc = miscellenousRepository.findByName(name);
			for(Miscelleonous miscellenous: misc) {
				miscellenous.setAmount(miscDTO.getValue());
				miscellenousRepository.save(miscellenous);
			}
		} 
		return null;
	}

	public String delete(Long id) {
		miscellenousRepository.delete(id);
		return "Deleted";
	}
}
