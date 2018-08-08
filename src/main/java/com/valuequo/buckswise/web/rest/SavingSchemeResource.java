package com.valuequo.buckswise.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.domain.SavingScheme;
import com.valuequo.buckswise.service.Saving;
import com.valuequo.buckswise.service.SavingSchemeService;
import com.valuequo.buckswise.service.dto.SavingDTO;
import com.valuequo.buckswise.service.dto.SavingSchemeDTO;

import afu.org.checkerframework.checker.units.qual.Time;

/**
 * SavingScheme controller
 */
@RestController
@RequestMapping("/api/saving-scheme")
public class SavingSchemeResource {

    private final Logger log = LoggerFactory.getLogger(SavingSchemeResource.class);

    /**
    * POST savingscheme
    */
    @Autowired
    private SavingSchemeDTO savingSchemeDTO;
    
    @Autowired
    private Saving saving;
    
    @PostMapping("/savingscheme")
    public SavingScheme savingscheme(@Valid @RequestBody SavingDTO savingDTO) {
    	System.out.println("under resource");
    	saving.create(savingDTO);
        return null;
    }
    
    @GetMapping("/getSavingScheme")
    public List<SavingScheme> getAllUsers() {
    	return saving.getAllUser();    	
    }
    
    @GetMapping("/getsavingScheme/{uid}")
    @Time
    public List<SavingScheme> getUsers(@PathVariable Long uid) {
    	return saving.findusers(uid);
    }
    
    @PutMapping("/saving")
    @Time
    public SavingScheme updateSaving(@Valid @RequestBody SavingDTO savingDTO) {
    	System.out.println("update controller " + savingDTO);
    	return null;
    	
    }
}
