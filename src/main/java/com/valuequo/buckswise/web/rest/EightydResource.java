package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.EightydService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.EightydDTO;
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
 * REST controller for managing Eightyd.
 */
@RestController
@RequestMapping("/api")
public class EightydResource {

    private final Logger log = LoggerFactory.getLogger(EightydResource.class);

    private static final String ENTITY_NAME = "eightyd";

    private final EightydService eightydService;

    public EightydResource(EightydService eightydService) {
        this.eightydService = eightydService;
    }

    /**
     * POST  /eightyds : Create a new eightyd.
     *
     * @param eightydDTO the eightydDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eightydDTO, or with status 400 (Bad Request) if the eightyd has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/eightyds")
    @Timed
    public ResponseEntity<EightydDTO> createEightyd(@RequestBody EightydDTO eightydDTO) throws URISyntaxException {
        log.debug("REST request to save Eightyd : {}", eightydDTO);
        if (eightydDTO.getId() != null) {
            throw new BadRequestAlertException("A new eightyd cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EightydDTO result = eightydService.save(eightydDTO);
        return ResponseEntity.created(new URI("/api/eightyds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /eightyds : Updates an existing eightyd.
     *
     * @param eightydDTO the eightydDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eightydDTO,
     * or with status 400 (Bad Request) if the eightydDTO is not valid,
     * or with status 500 (Internal Server Error) if the eightydDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/eightyds")
    @Timed
    public ResponseEntity<EightydDTO> updateEightyd(@RequestBody EightydDTO eightydDTO) throws URISyntaxException {
        log.debug("REST request to update Eightyd : {}", eightydDTO);
        if (eightydDTO.getId() == null) {
            return createEightyd(eightydDTO);
        }
        EightydDTO result = eightydService.save(eightydDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eightydDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /eightyds : get all the eightyds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eightyds in body
     */
    @GetMapping("/eightyds")
    @Timed
    public List<EightydDTO> getAllEightyds() {
        log.debug("REST request to get all Eightyds");
        return eightydService.findAll();
        }

    /**
     * GET  /eightyds/:id : get the "id" eightyd.
     *
     * @param id the id of the eightydDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eightydDTO, or with status 404 (Not Found)
     */
    @GetMapping("/eightyds/{uid}")
    @Timed
    public ResponseEntity<EightydDTO> getEightyd(@PathVariable Long uid) {
        log.debug("REST request to get Eightyd : {}", uid);
        EightydDTO eightydDTO = eightydService.findOne(uid);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eightydDTO));
    }

    /**
     * DELETE  /eightyds/:id : delete the "id" eightyd.
     *
     * @param id the id of the eightydDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/eightyds/{id}")
    @Timed
    public ResponseEntity<Void> deleteEightyd(@PathVariable Long id) {
        log.debug("REST request to delete Eightyd : {}", id);
        eightydService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
