package com.valuequo.buckswise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Credit;
import com.valuequo.buckswise.repository.CreditRepository;
import com.valuequo.buckswise.service.dto.CreditDTO;

@Service
public class CreditService {
	
	@Autowired
	private CreditRepository creditRepository;

	public Credit save(int userid, String bank, String balance, String type, String roi, String lt, String pay, String usage) {

		Credit credit = new Credit(userid, bank, balance, type, roi, lt, pay, usage);
		credit.setUserid(userid);
		credit.setBalance(balance);
		credit.setBank(bank);
		credit.setType(type);
		credit.setRoi(roi);
		credit.setLt(lt);
		credit.setPay(pay);
		credit.setUsage(usage);
		creditRepository.save(credit);
		return null;
	}

	public List<Credit> getDetail(int userid) {
		return creditRepository.findByUserid(userid);
		
	}

	public Credit update(int userid, String bank, String balance, String type, String roi, String lt, String pay,
			String usage, Long uid, Long id) {
			List<Credit> Id  = creditRepository.findById(id);
			for(Credit credit: Id) {
				Long tableId = credit.getId();
				if(tableId == id) {
					credit.setBalance(balance);
					credit.setBank(bank);
					credit.setLt(lt);
					credit.setPay(pay);
					credit.setType(type);
					credit.setUsage(usage);
					credit.setRoi(roi);
					creditRepository.save(credit);
				}
			}
		return null;
	}

	public String delete(Long id) {
		creditRepository.delete(id);;
		return "Deleteted!";
	}
}
