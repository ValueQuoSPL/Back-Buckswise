package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.EightycService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.EightycDTO;
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
 * REST controller for managing Eightyc.
 */
@RestController
@RequestMapping("/api")
public class EightycResource {

    private final Logger log = LoggerFactory.getLogger(EightycResource.class);

    private static final String ENTITY_NAME = "eightyc";

    private final EightycService eightycService;

    public EightycResource(EightycService eightycService) {
        this.eightycService = eightycService;
    }

    /**
     * POST  /eightycs : Create a new eightyc.
     *
     * @param eightycDTO the eightycDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eightycDTO, or with status 400 (Bad Request) if the eightyc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/eightycs")
    @Timed
    public ResponseEntity<EightycDTO> createEightyc(@RequestBody EightycDTO eightycDTO) throws URISyntaxException {
        log.debug("REST request to save Eightyc : {}", eightycDTO);
        if (eightycDTO.getId() != null) {
            throw new BadRequestAlertException("A new eightyc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EightycDTO result = eightycService.save(eightycDTO);
        return ResponseEntity.created(new URI("/api/eightycs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /eightycs : Updates an existing eightyc.
     *
     * @param eightycDTO the eightycDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eightycDTO,
     * or with status 400 (Bad Request) if the eightycDTO is not valid,
     * or with status 500 (Internal Server Error) if the eightycDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/eightycs")
    @Timed
    public ResponseEntity<EightycDTO> updateEightyc(@RequestBody EightycDTO eightycDTO) throws URISyntaxException {
        log.debug("REST request to update Eightyc : {}", eightycDTO);
        if (eightycDTO.getId() == null) {
            return createEightyc(eightycDTO);
        }
        EightycDTO result = eightycService.save(eightycDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eightycDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /eightycs : get all the eightycs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eightycs in body
     */
    @GetMapping("/eightycs")
    @Timed
    public List<EightycDTO> getAllEightycs() {
        log.debug("REST request to get all Eightycs");
        return eightycService.findAll();
        }

    /**
     * GET  /eightycs/:id : get the "id" eightyc.
     *
     * @param id the id of the eightycDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eightycDTO, or with status 404 (Not Found)
     */
    @GetMapping("/eightycs/{id}")
    @Timed
    public ResponseEntity<EightycDTO> getEightyc(@PathVariable Long id) {
        log.debug("REST request to get Eightyc : {}", id);
        EightycDTO eightycDTO = eightycService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eightycDTO));
    }

    /**
     * DELETE  /eightycs/:id : delete the "id" eightyc.
     *
     * @param id the id of the eightycDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/eightycs/{id}")
    @Timed
    public ResponseEntity<Void> deleteEightyc(@PathVariable Long id) {
        log.debug("REST request to delete Eightyc : {}", id);
        eightycService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
