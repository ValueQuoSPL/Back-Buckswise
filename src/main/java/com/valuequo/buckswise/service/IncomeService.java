package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Income;
import com.valuequo.buckswise.repository.IncomeRepository;
import com.valuequo.buckswise.service.dto.IncomeDTO;

@Service
public class IncomeService {
	
	@Autowired
	private IncomeRepository incomeRepository;

	public Income save(String uName, String uValue, int userid) {
		Income income = new Income(uName, uValue, userid);
		income.setName(uName);
		income.setAmount(uValue);
		income.setUserid(userid);
		incomeRepository.save(income);
		return null;
	}

	public List<Income> getDetail(int userid) {
		return incomeRepository.findByUserid(userid);
	}
	
	public IncomeDTO update(String uName, String uValue, int userid, Long usersid) {
		if(userid == usersid) {
		IncomeDTO incomeDTO = new IncomeDTO(uName, uValue, userid);
			incomeDTO.setName(uName);
			incomeDTO.setValue(uValue);
			incomeDTO.setUserid(userid);
			
			String name = incomeDTO.getName();	
			List<Income> incomes = incomeRepository.findByName(name);
			for(Income income: incomes) {
				income.setAmount(incomeDTO.getValue());
				incomeRepository.save(income);				
			}
	 }
		return null;
	}

	public String deleteData(Long id) {
		incomeRepository.delete(id);
		return null;
	}
}
