package com.valuequo.buckswise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valuequo.buckswise.domain.Income;
import com.valuequo.buckswise.domain.SuccessandFailtransaction;
import com.valuequo.buckswise.repository.SuccessandFailRepository;


@Service
public class SuccessAndFailService {
	
	@Autowired
	private SuccessandFailRepository successandFailRepository;
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private SuccessandFailtransaction successandFailtransaction;
	@Autowired
	private MailService mailService;
	
	public SuccessandFailtransaction saveTransaction(Long mihpayid, String status, String txnid, String productinfo, String email,
			String amount, String addedon, String firstName) {
		Long uid = paymentService.getUserid();
		SuccessandFailtransaction successfail = new SuccessandFailtransaction(mihpayid, status, txnid, productinfo, email, amount, addedon, uid);
		successfail.setMihpayid(mihpayid);
		successfail.setStatus(status);
		successfail.setTxnid(txnid);
		successfail.setProductinfo(productinfo);
		successfail.setEmail(email);
		successfail.setAmount(amount);
		successfail.setAddedon(addedon);
		successfail.setUserid(uid);

		mailService.sendMailForWelcome(firstName, email);

		return successandFailRepository.save(successfail);
	}


	public List<SuccessandFailtransaction> getDetail(Long userid) {
		return successandFailRepository.findByUserid(userid);
	}

	public List<SuccessandFailtransaction> getAllDetail() {
		return successandFailRepository.findAll();
	}


	public void saveTrail(Long uid, String status, String productinfo) {
		// List<SuccessandFailtransaction> saveAndFial = successandFailRepository.findByUserid(uid);
		// for( SuccessandFailtransaction result : saveAndFial) {
		// 	if(result.getUserid() == uid) {
		// 	} else {
		// 		successandFailtransaction.setUserid(uid);
		// 		successandFailtransaction.setStatus(status);
		// 		successandFailtransaction.setProductinfo(productinfo);
		// 		successandFailRepository.save(successandFailtransaction);
		// 	}
		// }

		successandFailtransaction.setUserid(uid);
		successandFailtransaction.setStatus(status);
		successandFailtransaction.setProductinfo(productinfo);
		successandFailRepository.save(successandFailtransaction);

	}


	public void update(Long id, Long uid, String status) {
		SuccessandFailtransaction update = successandFailRepository.findById(id);
		update.setUserid(uid);
		update.setStatus(status);
		successandFailRepository.save(update);
	}

}
