package com.valuequo.buckswise.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.valuequo.buckswise.model.GetPaymentResponse;

@Service
public class PaymentResponseService {
	
	public ResponseEntity<String> getpaymentresponse(GetPaymentResponse getPaymentResponse){
		
		String merchantKey = "RIPDEbRM";
		String merchantTxnId = getPaymentResponse.getMerchantTxnId();
		final String uri = "https://www.payumoney.com/sandbox/payment/op/getPaymentResponse?merchantKey="+ merchantKey +"&merchantTransactionIds="+merchantTxnId;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "+tqgv1tcw3G8XctkwpUWhXDTxI0PzkIf988qDd1SxoE=");
		headers.set("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> output = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		
		return output;
		
	}

}
