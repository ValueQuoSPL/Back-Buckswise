package com.valuequo.buckswise.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.SavingScheme;
import com.valuequo.buckswise.service.Saving;
import com.valuequo.buckswise.service.SavingSchemeService;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
import com.valuequo.buckswise.service.dto.SavingDTO;
import com.valuequo.buckswise.service.dto.SavingSchemeDTO;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;

import afu.org.checkerframework.checker.units.qual.Time;

/**
 * SavingScheme controller
 */
@RestController
@RequestMapping("/api")
public class SavingSchemeResource {

    private static final String ENTITY_NAME = null;

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
    
    @GetMapping("/getsaving/{uid}")
    @Time
    public List<SavingScheme> getUsers(@PathVariable Long uid) {
    	return saving.findusers(uid);
    }
    @GetMapping("/getsavingSchemebyid/{id}")
    @Time
    public List<SavingScheme> getUsersbyid(@PathVariable Long id) {
    	return saving.findusers(id);
    }
    
    @PutMapping("/saving")
    @Time
    public SavingScheme updateSaving(@Valid @RequestBody SavingDTO savingDTO) {
//    	System.out.println("update controller " + savingDTO);
//    	return null;
    	 log.debug("REST request to update Mutualfund : {}", savingDTO.getId());
         if (savingDTO.getId() == null) {
              return saving.create(savingDTO);
              
         }
         SavingScheme result = saving.create(savingDTO);
         return result;
//         return ResponseEntity.ok()
//             .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, savingDTO.getId().toString()))
//             .body(result);
     }
    
    @PutMapping("/savingUpdate")
    public List<SavingScheme> updateSave(@RequestBody SavingDTO savingDTO) {
    	return saving.updateData(savingDTO);
    }
    @DeleteMapping("/savingdelete/{id}")
    @Timed
    public ResponseEntity<Void> deleteSaving(@PathVariable Long id) {
        log.debug("REST request to delete Stock : {}", id);
        saving.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    	
    }
