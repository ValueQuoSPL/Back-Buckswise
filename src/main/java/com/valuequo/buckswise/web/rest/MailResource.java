package com.valuequo.buckswise.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.model.MailVM;

/**
 * Mail controller
 */
@RestController
@RequestMapping("/api/mail")
public class MailResource {

    private final Logger log = LoggerFactory.getLogger(MailResource.class);

    /**
    * POST sendMail
    */
    @PostMapping("/send-mail")
    public String sendMail(@RequestBody MailVM mailVM) {
    	System.out.println(mailVM);
        return "sendMail";
    }

}
