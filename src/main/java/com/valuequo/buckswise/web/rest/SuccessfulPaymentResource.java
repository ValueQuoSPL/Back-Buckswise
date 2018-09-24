package com.valuequo.buckswise.web.rest;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.model.Sucessful;

import io.swagger.models.Model;

/**
 * SuccessfulPayment controller
 */
@RestController
//@RequestMapping("/api/successful-payment" )
public class SuccessfulPaymentResource {

    private final Logger log = LoggerFactory.getLogger(SuccessfulPaymentResource.class);

    /**
    * POST sucessfulPayment
    */
//    @PostMapping("/sucessful-payment")
    @RequestMapping(path="/api/successful-payment" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> sucessfulPayment(@RequestBody Sucessful sucessful) {
    	System.out.println(sucessful);
        return null;
    }

}
