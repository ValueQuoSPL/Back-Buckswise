package com.valuequo.buckswise.web.rest;
import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.ContactusService;
import com.valuequo.buckswise.service.MailService;
import com.valuequo.buckswise.service.UserService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.errors.EmailNotFoundException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.ContactusDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Contactus.
 */
@RestController
@RequestMapping("/api")
public class ContactusResource {

    private final Logger log = LoggerFactory.getLogger(ContactusResource.class);

    private static final String ENTITY_NAME = "contactus";

    private final ContactusService contactusService;
    
    private final MailService mailService;

    public ContactusResource(ContactusService contactusService,MailService mailService) {
        this.contactusService = contactusService;
        this.mailService = mailService;
    }
    /**
     * POST  /contactuses : Create a new contactus.
     *
     * @param contactusDTO the contactusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contactusDTO, or with status 400 (Bad Request) if the contactus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contactuses")
    @Timed
    public ResponseEntity<ContactusDTO> createContactus(@RequestBody ContactusDTO contactusDTO) throws URISyntaxException {
        log.debug("REST request to save Contactus : {}", contactusDTO);
        if (contactusDTO.getId() != null) {
            throw new BadRequestAlertException("A new contactus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactusDTO result = contactusService.save(contactusDTO);
//       String to = result.getEmail();       
//       mailService.sendEmail(to, "admin@localhost", "Thank you for your Intrest we will shortly contact you", "admin@valuequo.com",false, false);
       mailService.sendEmailContact(result);
        log.debug("REST request to save Contactus : {}", result);
        return ResponseEntity.created(new URI("/api/contactuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contactuses : Updates an existing contactus.
     *
     * @param contactusDTO the contactusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contactusDTO,
     * or with status 400 (Bad Request) if the contactusDTO is not valid,
     * or with status 500 (Internal Server Error) if the contactusDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contactuses")
    @Timed
    public ResponseEntity<ContactusDTO> updateContactus(@RequestBody ContactusDTO contactusDTO) throws URISyntaxException {
        log.debug("REST request to update Contactus : {}", contactusDTO);
        if (contactusDTO.getId() == null) {
            return createContactus(contactusDTO);
        }
        ContactusDTO result = contactusService.save(contactusDTO);
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contactusDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contactuses : get all the contactuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contactuses in body
     */
    @GetMapping("/contactuses")
    @Timed
    public List<ContactusDTO> getAllContactuses() {
        log.debug("REST request to get all Contactuses");
        return contactusService.findAll();
        }

    /**
     * GET  /contactuses/:id : get the "id" contactus.
     *
     * @param id the id of the contactusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contactusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contactuses/{id}")
    @Timed
    public ResponseEntity<ContactusDTO> getContactus(@PathVariable Long id) {
        log.debug("REST request to get Contactus : {}", id);
        ContactusDTO contactusDTO = contactusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contactusDTO));
    }

    /**
     * DELETE  /contactuses/:id : delete the "id" contactus.
     *
     * @param id the id of the contactusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contactuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteContactus(@PathVariable Long id) {
        log.debug("REST request to delete Contactus : {}", id);
        contactusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
