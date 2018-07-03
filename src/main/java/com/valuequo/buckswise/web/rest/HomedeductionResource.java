package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.HomedeductionService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.HomedeductionDTO;
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
 * REST controller for managing Homededuction.
 */
@RestController
@RequestMapping("/api")
public class HomedeductionResource {

    private final Logger log = LoggerFactory.getLogger(HomedeductionResource.class);

    private static final String ENTITY_NAME = "homededuction";

    private final HomedeductionService homedeductionService;

    public HomedeductionResource(HomedeductionService homedeductionService) {
        this.homedeductionService = homedeductionService;
    }

    /**
     * POST  /homedeductions : Create a new homededuction.
     *
     * @param homedeductionDTO the homedeductionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new homedeductionDTO, or with status 400 (Bad Request) if the homededuction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/homedeductions")
    @Timed
    public ResponseEntity<HomedeductionDTO> createHomededuction(@RequestBody HomedeductionDTO homedeductionDTO) throws URISyntaxException {
        log.debug("REST request to save Homededuction : {}", homedeductionDTO);
        if (homedeductionDTO.getId() != null) {
            throw new BadRequestAlertException("A new homededuction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HomedeductionDTO result = homedeductionService.save(homedeductionDTO);
        return ResponseEntity.created(new URI("/api/homedeductions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /homedeductions : Updates an existing homededuction.
     *
     * @param homedeductionDTO the homedeductionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated homedeductionDTO,
     * or with status 400 (Bad Request) if the homedeductionDTO is not valid,
     * or with status 500 (Internal Server Error) if the homedeductionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/homedeductions")
    @Timed
    public ResponseEntity<HomedeductionDTO> updateHomededuction(@RequestBody HomedeductionDTO homedeductionDTO) throws URISyntaxException {
        log.debug("REST request to update Homededuction : {}", homedeductionDTO);
        if (homedeductionDTO.getId() == null) {
            return createHomededuction(homedeductionDTO);
        }
        HomedeductionDTO result = homedeductionService.save(homedeductionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, homedeductionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /homedeductions : get all the homedeductions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of homedeductions in body
     */
    @GetMapping("/homedeductions")
    @Timed
    public List<HomedeductionDTO> getAllHomedeductions() {
        log.debug("REST request to get all Homedeductions");
        return homedeductionService.findAll();
        }

    /**
     * GET  /homedeductions/:id : get the "id" homededuction.
     *
     * @param id the id of the homedeductionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the homedeductionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/homedeductions/{id}")
    @Timed
    public ResponseEntity<HomedeductionDTO> getHomededuction(@PathVariable Long id) {
        log.debug("REST request to get Homededuction : {}", id);
        HomedeductionDTO homedeductionDTO = homedeductionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(homedeductionDTO));
    }

    /**
     * DELETE  /homedeductions/:id : delete the "id" homededuction.
     *
     * @param id the id of the homedeductionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/homedeductions/{id}")
    @Timed
    public ResponseEntity<Void> deleteHomededuction(@PathVariable Long id) {
        log.debug("REST request to delete Homededuction : {}", id);
        homedeductionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
