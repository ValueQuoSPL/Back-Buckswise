package com.valuequo.buckswise.service;

import java.awt.print.Pageable;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;


import com.valuequo.buckswise.domain.Payment;
import com.valuequo.buckswise.model.PaymentVM;
import com.valuequo.buckswise.repository.PaymentRepository;
import com.valuequo.buckswise.service.mapper.PaymentMapper;

import ch.qos.logback.core.net.SyslogOutputStream;



@Service
@Transactional
public class PaymentService { 
	
	@Autowired
	private PaymentVM payment;
	private PaymentRepository paymentRepository;
	
	private Integer error;
	
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

    public boolean empty(String s) {
        if (s == null || s.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public Payment createUser(PaymentVM payment) {
    	System.out.println(" under service create "+payment);
    	Payment payment1 = new Payment();
    	payment1.setAmount(payment.getAmount());
    	payment1.setFirstName(payment.getFirstName());
    	payment1.seteMail(payment.geteMail());
    	payment1.setPhone(payment.getPhone());
    	payment1.setProductInfo(payment.getProductInfo());
    	payment1.setsUrl(payment.getsUrl());
    	payment1.setfUrl(payment.getfUrl());
    	System.out.println("create payment " + payment1);
    	paymentRepository.save(payment1);
		return payment1;
    	
    }
    
    
    public String hashCal(String type, String str) {
        byte[] hashseq = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException nsae) {
        }
        return hexString.toString();
    }

    
    public Map<String, String> hashCalMethod(PaymentVM payment) {
     //   response.setContentType("text/html;charset=UTF-8");
    	
		String key = "RIPDEbRM";
        String salt = "UTDGSbczvf";
        String action1 = "";
        String base_url = "https://sandboxsecure.payu.in";
        error = 0;
        String hashString = "";
        String Amount= payment.getAmount();
        String fName = payment.getFirstName();
        String eMail = payment.geteMail();
        String phone = payment.getPhone();
        String productInfo = payment.getProductInfo();
        String sUrl =  payment.getsUrl();
        String fUrl =  payment.getfUrl();
        String serviceProvider = "valuequo";
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String> urlParams = new HashMap<String, String>();
             params.put("key",key ); 
            params.put("amount", Amount );
            System.out.println("params : " + params.get("amount"));
            params.put("firstname", fName); 
            params.put("email",eMail );
            params.put("phone",phone );
            params.put("productinfo",productInfo );
            params.put("surl",sUrl );
            params.put("furl",fUrl );
            params.put("service_provider", serviceProvider);
        String txnid = "";
        if (empty(params.get("txnid"))) {
        	
            Random rand = new Random();
            String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
            txnid = rndm;
            params.remove("txnid");
            params.put("txnid", txnid);
            txnid = hashCal("SHA-256", rndm).substring(0, 20);
        } else {
            txnid = params.get("txnid");
        }
        
        String txn = "abcd";
        String hash = "";
        String otherPostParamSeq = "phone|surl|furl|lastname|curl|address1|address2|city|state|country|zipcode|pg";
        String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
     
        if (empty(params.get("hash")) && params.size() > 0) {
        	
            if (empty(params.get("key")) || empty(txnid) || empty(params.get("amount")) || empty(params.get("firstname")) || empty(params.get("email")) || empty(params.get("phone")) || empty(params.get("productinfo")) || empty(params.get("surl")) || empty(params.get("furl")) || empty(params.get("service_provider"))) {
            	
                error = 1;
               	
            } else {
               String[] hashVarSeq = hashSequence.split("\\|");
                for (String part : hashVarSeq) {
                   if (part.equals("txnid")) {
                        hashString = hashString + txnid;
                         urlParams.put("txnid", txnid);
                    } else {
                        hashString = (empty(params.get(part))) ? hashString.concat("") : hashString.concat(params.get(part).trim());
                        urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
                    }
                    hashString = hashString.concat("|");
                   
                }
                hashString = hashString.concat(salt);
                 hash = hashCal("SHA-512", hashString);
                System.out.println(hash);
                action1 = base_url.concat("/_payment");
                String[] otherPostParamVarSeq = otherPostParamSeq.split("\\|");
                for (String part : otherPostParamVarSeq) {
                    urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
                }

            }
        } else if (!empty(params.get("hash"))) {
            hash = params.get("hash");
            action1 = base_url.concat("/_payment");
        }
   
        urlParams.put("hash", hash);
        urlParams.put("action", action1);
        urlParams.put("hashString", hashString);

        return urlParams;
    }

    public static void trustSelfSignedSSL() {
        try {
            final SSLContext ctx = SSLContext.getInstance(
                    "TLS");
            final X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(final X509Certificate[] xcs, final String string) throws CertificateException {
// do nothing
                }

                @Override
                public void checkServerTrusted(final X509Certificate[] xcs, final String string) throws CertificateException {
// do nothing
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLContext.setDefault(ctx);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

}
