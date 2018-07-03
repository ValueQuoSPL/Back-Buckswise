package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.GrossService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.GrossDTO;
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
 * REST controller for managing Gross.
 */
@RestController
@RequestMapping("/api")
public class GrossResource {

    private final Logger log = LoggerFactory.getLogger(GrossResource.class);

    private static final String ENTITY_NAME = "gross";

    private final GrossService grossService;

    public GrossResource(GrossService grossService) {
        this.grossService = grossService;
    }

    /**
     * POST  /grosses : Create a new gross.
     *
     * @param grossDTO the grossDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new grossDTO, or with status 400 (Bad Request) if the gross has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/grosses")
    @Timed
    public ResponseEntity<GrossDTO> createGross(@RequestBody GrossDTO grossDTO) throws URISyntaxException {
        log.debug("REST request to save Gross : {}", grossDTO);
        if (grossDTO.getId() != null) {
            throw new BadRequestAlertException("A new gross cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrossDTO result = grossService.save(grossDTO);
        return ResponseEntity.created(new URI("/api/grosses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /grosses : Updates an existing gross.
     *
     * @param grossDTO the grossDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated grossDTO,
     * or with status 400 (Bad Request) if the grossDTO is not valid,
     * or with status 500 (Internal Server Error) if the grossDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/grosses")
    @Timed
    public ResponseEntity<GrossDTO> updateGross(@RequestBody GrossDTO grossDTO) throws URISyntaxException {
        log.debug("REST request to update Gross : {}", grossDTO);
        if (grossDTO.getId() == null) {
            return createGross(grossDTO);
        }
        GrossDTO result = grossService.save(grossDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, grossDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /grosses : get all the grosses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of grosses in body
     */
    @GetMapping("/grosses")
    @Timed
    public List<GrossDTO> getAllGrosses() {
        log.debug("REST request to get all Grosses");
        return grossService.findAll();
        }

    /**
     * GET  /grosses/:id : get the "id" gross.
     *
     * @param id the id of the grossDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the grossDTO, or with status 404 (Not Found)
     */
    @GetMapping("/grosses/{id}")
    @Timed
    public ResponseEntity<GrossDTO> getGross(@PathVariable Long id) {
        log.debug("REST request to get Gross : {}", id);
        GrossDTO grossDTO = grossService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(grossDTO));
    }

    /**
     * DELETE  /grosses/:id : delete the "id" gross.
     *
     * @param id the id of the grossDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/grosses/{id}")
    @Timed
    public ResponseEntity<Void> deleteGross(@PathVariable Long id) {
        log.debug("REST request to delete Gross : {}", id);
        grossService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
