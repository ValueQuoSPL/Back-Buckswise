package com.valuequo.buckswise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.valuequo.buckswise.model.RefundPaymentIDVM;

@Component
public class RefundPaymentIDService implements CommandLineRunner{
	
	@Autowired
	private RefundPaymentIDVM refundPaymentIDVM;
	
	public ResponseEntity<String> refundPaymentID(RefundPaymentIDVM refundPaymentIDVM){
		
		String merchantKey = "RIPDEbRM";
		String _paymentId = refundPaymentIDVM.getPaymentID();
		final String uri = "https://www.payumoney.com/sandbox/treasury/ext/merchant/getRefundDetailsByPayment?merchantKey="+merchantKey+"&paymentId="+_paymentId;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "+tqgv1tcw3G8XctkwpUWhXDTxI0PzkIf988qDd1SxoE=");
		headers.set("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> output = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		System.out.println(output.getBody());
		return output;	
	}

	@Override
	public void run(String... arg0) throws Exception {
		refundPaymentID(refundPaymentIDVM);
	}
}