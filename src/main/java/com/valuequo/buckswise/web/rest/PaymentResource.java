package com.valuequo.buckswise.web.rest;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.model.PaymentVM;
import com.valuequo.buckswise.service.PaymentService;

/**
 * Payment controller
 * 
 */
@RestController
@RequestMapping("/api/payment")
public class PaymentResource {

    private final Logger log = LoggerFactory.getLogger(PaymentResource.class);
    
    @Autowired
    private PaymentService paymentService; 
    
    /**
    * POST payment
    */
    @PostMapping("/payment")
	public Map<String, String> payment(@Valid @RequestBody PaymentVM payment, Model model) {
		paymentService.createUser(payment);
		Map<String, String> result = paymentService.hashCalMethod(payment);
		return result;

	}
    
}
