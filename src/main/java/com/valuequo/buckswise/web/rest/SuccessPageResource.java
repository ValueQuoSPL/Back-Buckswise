package com.valuequo.buckswise.web.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.valuequo.buckswise.domain.Income;
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

	@GetMapping("/getsuccess")
	public List<SuccessandFailtransaction> getSuccess() {
		return successAndFailService.getDetail();
	}

	@PostMapping("/success")
	public RedirectView defaultAction(@RequestParam String mihpayid, @RequestParam String status,
			@RequestParam String txnid, @RequestParam String productinfo, @RequestParam String email,
			@RequestParam String amount, @RequestParam String addedon, RedirectAttributes attributes) {

		successAndFailService.saveTransaction(mihpayid, status, txnid, productinfo, email, amount, addedon);
		return new RedirectView("http://192.168.0.104:9000/#/success");
	}
}
