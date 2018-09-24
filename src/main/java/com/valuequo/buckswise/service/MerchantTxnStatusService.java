package com.valuequo.buckswise.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.valuequo.buckswise.model.PaymentcheckMerchantTxnStatusVM;
import com.valuequo.buckswise.model.RefundPaymentVM;

@Component
public class MerchantTxnStatusService implements CommandLineRunner{

	@Autowired
	private RefundPaymentVM refundPaymentVM;
	
	public ResponseEntity<String> callmerchanttxn(RefundPaymentVM refundPaymentVM) {
		String merchantKey = "RIPDEbRM";
		String _payemntId = refundPaymentVM.get_paymentId();
		String _refundAmount = refundPaymentVM.get_refundAmount();
		
		final String uri = "https://www.payumoney.com/sandbox/treasury/merchant/refundPayment?paymentId="+_payemntId+"&refundAmount="+_refundAmount+"&merchantKey="+merchantKey;
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "+tqgv1tcw3G8XctkwpUWhXDTxI0PzkIf988qDd1SxoE=");
		headers.set("Content-Type", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> output = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		
		return output;

	}

	@Override
	public void run(String... arg0) throws Exception {
		callmerchanttxn(refundPaymentVM);
	}

}
