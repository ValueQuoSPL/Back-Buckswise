package com.valuequo.buckswise.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.valuequo.buckswise.domain.SuccessandFailtransaction;
import com.valuequo.buckswise.service.SuccessAndFailService;

/**
 * SuccessPage controller
 */
@RestController
@RequestMapping("/api")
public class SuccessPageResource {

	private final Logger log = LoggerFactory.getLogger(SuccessPageResource.class);

	/**
	 * GET defaultAction
	 */
	
	@Autowired
	private SuccessAndFailService successAndFailService;

	@GetMapping("/getsuccess/{userid}")
	public List<SuccessandFailtransaction> getSuccess(@PathVariable Long userid) {
		return successAndFailService.getDetail(userid);
	}
	
	@GetMapping("/getAllSuccess")
	public List<SuccessandFailtransaction> getAllSuccess() {
		return successAndFailService.getAllDetail();
	}

	@PostMapping("/success")
	public RedirectView  defaultAction(@RequestParam Long mihpayid, @RequestParam String status,
			@RequestParam String txnid, @RequestParam String productinfo, @RequestParam String email,
			@RequestParam String amount, @RequestParam String addedon, @RequestParam String firstname) {
		successAndFailService.saveTransaction(mihpayid, status, txnid, productinfo, email, amount, addedon, firstname);
		return new RedirectView("https://www.buckswise.com/#/success");
	}

	// storing trail transaction data
	@PostMapping("/trail/{uid}/{status}/{productinfo}")
	public void trail(@PathVariable("uid") Long uid, @PathVariable("status") String status, @PathVariable("productinfo") String productinfo) {
		successAndFailService.saveTrail(uid, status, productinfo);
	}

	@PostMapping("/fail")
	public RedirectView fail() {
		return new RedirectView("https://www.buckswise.com/#/fail");
	}
}
