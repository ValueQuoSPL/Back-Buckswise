package com.valuequo.buckswise.web.rest;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.model.RefundIDVM;
import com.valuequo.buckswise.model.RefundPaymentIDVM;
import com.valuequo.buckswise.model.RefundPaymentVM;
import com.valuequo.buckswise.service.MerchantTxnStatusService;
import com.valuequo.buckswise.service.RefundIdService;
import com.valuequo.buckswise.service.RefundPaymentIDService;

/**
 * RefundPayment controller
 */
@RestController
@RequestMapping("/api/refund-payment")
public class RefundPaymentResource {

    private final Logger log = LoggerFactory.getLogger(RefundPaymentResource.class);
    
    @Autowired
    private MerchantTxnStatusService merchantTxnService;
    
    @Autowired
    private RefundPaymentIDService refundpaymentIDService;
    
    @Autowired
    private RefundIdService refundIdService;
    /**
    * POST refundPayment
    */
    @PostMapping("/refund-payment")
    public String refundPayment(@RequestBody RefundPaymentVM refundPaymentVM) {
    	ResponseEntity<String> result = merchantTxnService.callmerchanttxn(refundPaymentVM);
		return result.getBody();
    }
    
    @PostMapping("/refund-paymentID")
    public String refundPaymentID(@RequestBody RefundPaymentIDVM refundPaymentIDVM) {
    	ResponseEntity<String> result = refundpaymentIDService.refundPaymentID(refundPaymentIDVM);
		return result.getBody();  	
    }
    
    @PostMapping("/refund-refundID")
    public String refundID(@RequestBody RefundIDVM refundIDVM) {
		ResponseEntity<String> result = refundIdService.refundId(refundIDVM);
    	return result.getBody();
    }
    
}