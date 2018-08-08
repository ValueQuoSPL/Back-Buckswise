package com.valuequo.buckswise.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.model.GetPaymentResponse;
import com.valuequo.buckswise.service.PaymentResponseService;

/**
 * PaymentResponse controller
 */
@RestController
@RequestMapping("/api/payment-response")
public class PaymentResponseResource {

    private final Logger log = LoggerFactory.getLogger(PaymentResponseResource.class);

    /**
    * POST paymentresponse
    */
    @Autowired
    private PaymentResponseService paymentResponseService;
    
    @PostMapping("/paymentresponse")
    public String paymentresponse(@RequestBody GetPaymentResponse getPaymentResponse) {
    	ResponseEntity<String> result = paymentResponseService.getpaymentresponse(getPaymentResponse);
        return result.getBody();
    }

}
