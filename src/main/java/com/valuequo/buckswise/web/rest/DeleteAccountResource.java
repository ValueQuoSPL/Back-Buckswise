package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.service.DeleteAccountService;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DeleteAccount controller
 */
@RestController
@RequestMapping("/api/delete-account")
public class DeleteAccountResource {

    private final Logger log = LoggerFactory.getLogger(DeleteAccountResource.class);

    private static final String ENTITY_NAME = "deleteaccount";

    @Autowired
    private DeleteAccountService deleteAccountService;

    /**
    * POST deleteAccount
    */
    @PostMapping("/delete-account")
    public String deleteAccount() {
        return "deleteAccount";
    }

    @DeleteMapping("/delete-account/{id}")
    public String deleteAccount(@PathVariable Long id) {
        deleteAccountService.deleteAccount(id);
        return "deleteAccount";
    }

    // update the user account id in jhi_user
    @DeleteMapping("/update/{id}")
    public ResponseEntity<Void> updateId(@PathVariable Long id) {
        deleteAccountService.updateid(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
