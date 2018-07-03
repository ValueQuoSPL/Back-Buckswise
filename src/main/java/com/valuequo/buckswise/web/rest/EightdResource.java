package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.EightdService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.EightdDTO;
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
 * REST controller for managing Eightd.
 */
@RestController
@RequestMapping("/api")
public class EightdResource {

    private final Logger log = LoggerFactory.getLogger(EightdResource.class);

    private static final String ENTITY_NAME = "eightd";

    private final EightdService eightdService;

    public EightdResource(EightdService eightdService) {
        this.eightdService = eightdService;
    }

    /**
     * POST  /eightds : Create a new eightd.
     *
     * @param eightdDTO the eightdDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eightdDTO, or with status 400 (Bad Request) if the eightd has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/eightds")
    @Timed
    public ResponseEntity<EightdDTO> createEightd(@RequestBody EightdDTO eightdDTO) throws URISyntaxException {
        log.debug("REST request to save Eightd : {}", eightdDTO);
        if (eightdDTO.getId() != null) {
            throw new BadRequestAlertException("A new eightd cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EightdDTO result = eightdService.save(eightdDTO);
        return ResponseEntity.created(new URI("/api/eightds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /eightds : Updates an existing eightd.
     *
     * @param eightdDTO the eightdDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eightdDTO,
     * or with status 400 (Bad Request) if the eightdDTO is not valid,
     * or with status 500 (Internal Server Error) if the eightdDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/eightds")
    @Timed
    public ResponseEntity<EightdDTO> updateEightd(@RequestBody EightdDTO eightdDTO) throws URISyntaxException {
        log.debug("REST request to update Eightd : {}", eightdDTO);
        if (eightdDTO.getId() == null) {
            return createEightd(eightdDTO);
        }
        EightdDTO result = eightdService.save(eightdDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eightdDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /eightds : get all the eightds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eightds in body
     */
    @GetMapping("/eightds")
    @Timed
    public List<EightdDTO> getAllEightds() {
        log.debug("REST request to get all Eightds");
        return eightdService.findAll();
        }

    /**
     * GET  /eightds/:id : get the "id" eightd.
     *
     * @param id the id of the eightdDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eightdDTO, or with status 404 (Not Found)
     */
    @GetMapping("/eightds/{id}")
    @Timed
    public ResponseEntity<EightdDTO> getEightd(@PathVariable Long id) {
        log.debug("REST request to get Eightd : {}", id);
        EightdDTO eightdDTO = eightdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eightdDTO));
    }

    /**
     * DELETE  /eightds/:id : delete the "id" eightd.
     *
     * @param id the id of the eightdDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/eightds/{id})")
    @Timed
    public ResponseEntity<Void> deleteEightd(@PathVariable Long id) {
        log.debug("REST request to delete Eightd : {}", id);
        eightdService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
