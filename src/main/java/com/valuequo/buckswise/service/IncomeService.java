package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Income;
import com.valuequo.buckswise.repository.IncomeRepository;

@Service
public class IncomeService {
	
	@Autowired
	private IncomeRepository incomeRepository;

	public Income save(String uName, String uValue, int userid) {
		
		System.out.println("under service " + uName);
		Income income = new Income(uName, uValue, userid);
		income.setName(uName);
		income.setAmount(uValue);
		income.setUserid(userid);
		System.out.println("under service " + income);
		incomeRepository.save(income);
		return null;
	}

	public List<Income> getDetail(int userid) {
		return incomeRepository.findByUserid(userid);
	}
}
