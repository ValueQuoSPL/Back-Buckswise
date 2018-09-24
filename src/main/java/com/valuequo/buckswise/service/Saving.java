package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valuequo.buckswise.domain.SavingScheme;
import com.valuequo.buckswise.repository.SavingSchemeRepository;
import com.valuequo.buckswise.service.dto.SavingDTO;
import com.valuequo.buckswise.service.dto.SavingSchemeDTO;

import afu.org.checkerframework.checker.units.qual.Time;

@Service
public class Saving {
	
	@Autowired
	private SavingSchemeRepository savingSchemeRepository;
	
	public SavingScheme create(SavingDTO savingDTO) {
		SavingScheme save = new SavingScheme();
		save.setUid(savingDTO.getUserId());
		save.setId(savingDTO.getId());
    	save.setNum(savingDTO.getNum());
    	save.setOrganisation_name(savingDTO.getOrganisation_name());
    	save.setAmount_invested(savingDTO.getAmount_invested());
    	save.setDividend_type(savingDTO.getDividend_type());
    	save.setInvestor_name(savingDTO.getInvestor_name());
    	save.setRate_of_interest(savingDTO.getRate_of_interest());
    	save.setTenure(savingDTO.getTenure());
    	save.setStart_date(savingDTO.getStart_date());
    	save.setEnd_date(savingDTO.getEnd_date());
    	save.setFund_value(savingDTO.getFund_value());
    	save.setAs_of_date(savingDTO.getAs_of_date());
    	save.setNotes(savingDTO.getNotes());
    	save.setType(savingDTO.getType());
    	System.out.println( "under service" + save);
        savingSchemeRepository.save(save);
		return save;	
	}
	
	public List<SavingScheme> getAllUser(){
		return savingSchemeRepository.findAll(); 
		
	}
	
	@Transactional(readOnly = true)
	public List<SavingScheme> findusers(Long uid){
		return savingSchemeRepository.findByUid(uid);	
	}
	
	public List<SavingScheme> update(SavingDTO savingDTO) {
		return null;
	}

	public List<SavingScheme> updateData(SavingDTO savingDTO) {
		Long id = savingDTO.getId();
		List<SavingScheme> update = savingSchemeRepository.findByid(id);
		for(SavingScheme up: update) {
			up.setNum(savingDTO.getNum());
			up.setOrganisation_name(savingDTO.getOrganisation_name());
			up.setAmount_invested(savingDTO.getAmount_invested());
			up.setDividend_type(savingDTO.getDividend_type());
			up.setInvestor_name(savingDTO.getInvestor_name());
			up.setRate_of_interest(savingDTO.getRate_of_interest());
			up.setTenure(savingDTO.getTenure());
			up.setStart_date(savingDTO.getStart_date());
			up.setEnd_date(savingDTO.getEnd_date());
			up.setFund_value(savingDTO.getFund_value());
			up.setAs_of_date(savingDTO.getAs_of_date());
			up.setNotes(savingDTO.getNotes());
			up.setType(savingDTO.getType());
			savingSchemeRepository.save(up);
		}
		return null;
	}
	public void delete(Long id) {
//        log.debug("Request to delete Stock : {}", id);
        savingSchemeRepository.delete(id);
    }



}
