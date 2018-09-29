package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.OtherdeductionService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.EightycdeductDTO;
import com.valuequo.buckswise.service.dto.OtherdeductionDTO;
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
 * REST controller for managing Otherdeduction.
 */
@RestController
@RequestMapping("/api")
public class OtherdeductionResource {

    private final Logger log = LoggerFactory.getLogger(OtherdeductionResource.class);

    private static final String ENTITY_NAME = "otherdeduction";

    private final OtherdeductionService otherdeductionService;

    public OtherdeductionResource(OtherdeductionService otherdeductionService) {
        this.otherdeductionService = otherdeductionService;
    }

    /**
     * POST  /otherdeductions : Create a new otherdeduction.
     *
     * @param otherdeductionDTO the otherdeductionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new otherdeductionDTO, or with status 400 (Bad Request) if the otherdeduction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/otherdeductions")
    @Timed
    public ResponseEntity<OtherdeductionDTO> createOtherdeduction(@RequestBody OtherdeductionDTO otherdeductionDTO) throws URISyntaxException {
        log.debug("REST request to save Otherdeduction : {}", otherdeductionDTO);
        if (otherdeductionDTO.getId() != null) {
            throw new BadRequestAlertException("A new otherdeduction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OtherdeductionDTO result = otherdeductionService.save(otherdeductionDTO);
        return ResponseEntity.created(new URI("/api/otherdeductions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /otherdeductions : Updates an existing otherdeduction.
     *
     * @param otherdeductionDTO the otherdeductionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated otherdeductionDTO,
     * or with status 400 (Bad Request) if the otherdeductionDTO is not valid,
     * or with status 500 (Internal Server Error) if the otherdeductionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/otherdeductions")
    @Timed
    public ResponseEntity<OtherdeductionDTO> updateOtherdeduction(@RequestBody OtherdeductionDTO otherdeductionDTO) throws URISyntaxException {
        log.debug("REST request to update Otherdeduction : {}", otherdeductionDTO);
        if (otherdeductionDTO.getId() == null) {
            return createOtherdeduction(otherdeductionDTO);
        }
        OtherdeductionDTO result = otherdeductionService.save(otherdeductionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, otherdeductionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /otherdeductions : get all the otherdeductions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of otherdeductions in body
     */
    @GetMapping("/otherdeductions")
    @Timed
    public List<OtherdeductionDTO> getAllOtherdeductions() {
        log.debug("REST request to get all Otherdeductions");
        return otherdeductionService.findAll();
        }

    /**
     * GET  /otherdeductions/:id : get the "id" otherdeduction.
     *
     * @param id the id of the otherdeductionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the otherdeductionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/otherdeductions/{uid}")
    @Timed
    public ResponseEntity<List<OtherdeductionDTO>> getOtherdeduction(@PathVariable int uid) {
    	System.out.println("fromOther" + uid);
        log.debug("REST request to get Otherdeduction : {}", uid);
        List<OtherdeductionDTO> otherdeductionDTO = otherdeductionService.findOne(uid);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(otherdeductionDTO));
    }
   

    /**
     * DELETE  /otherdeductions/:id : delete the "id" otherdeduction.
     *
     * @param id the id of the otherdeductionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/otherdeductions/{id}")
    @Timed
    public ResponseEntity<Void> deleteOtherdeduction(@PathVariable Long id) {
        log.debug("REST request to delete Otherdeduction : {}", id);
        otherdeductionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
