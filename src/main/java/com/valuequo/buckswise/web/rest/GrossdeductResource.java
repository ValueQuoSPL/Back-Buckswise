package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.GrossdeductService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.GrossdeductDTO;
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
 * REST controller for managing Grossdeduct.
 */
@RestController
@RequestMapping("/api")
public class GrossdeductResource {

    private final Logger log = LoggerFactory.getLogger(GrossdeductResource.class);

    private static final String ENTITY_NAME = "grossdeduct";

    private final GrossdeductService grossdeductService;

    public GrossdeductResource(GrossdeductService grossdeductService) {
        this.grossdeductService = grossdeductService;
    }

    /**
     * POST  /grossdeducts : Create a new grossdeduct.
     *
     * @param grossdeductDTO the grossdeductDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new grossdeductDTO, or with status 400 (Bad Request) if the grossdeduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/grossdeducts")
    @Timed
    public ResponseEntity<GrossdeductDTO> createGrossdeduct(@RequestBody GrossdeductDTO grossdeductDTO) throws URISyntaxException {
        log.debug("REST request to save Grossdeduct : {}", grossdeductDTO);
        if (grossdeductDTO.getId() != null) {
            throw new BadRequestAlertException("A new grossdeduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrossdeductDTO result = grossdeductService.save(grossdeductDTO);
        return ResponseEntity.created(new URI("/api/grossdeducts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /grossdeducts : Updates an existing grossdeduct.
     *
     * @param grossdeductDTO the grossdeductDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated grossdeductDTO,
     * or with status 400 (Bad Request) if the grossdeductDTO is not valid,
     * or with status 500 (Internal Server Error) if the grossdeductDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/grossdeducts")
    @Timed
    public ResponseEntity<GrossdeductDTO> updateGrossdeduct(@RequestBody GrossdeductDTO grossdeductDTO) throws URISyntaxException {
        log.debug("REST request to update Grossdeduct : {}", grossdeductDTO);
        if (grossdeductDTO.getId() == null) {
            return createGrossdeduct(grossdeductDTO);
        }
        GrossdeductDTO result = grossdeductService.save(grossdeductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, grossdeductDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /grossdeducts : get all the grossdeducts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of grossdeducts in body
     */
    @GetMapping("/grossdeducts")
    @Timed
    public List<GrossdeductDTO> getAllGrossdeducts() {
        log.debug("REST request to get all Grossdeducts");
        return grossdeductService.findAll();
        }

    /**
     * GET  /grossdeducts/:id : get the "id" grossdeduct.
     *
     * @param id the id of the grossdeductDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the grossdeductDTO, or with status 404 (Not Found)
     */
    @GetMapping("/grossdeducts/{uid}")
    @Timed
    public ResponseEntity<GrossdeductDTO> getGrossdeduct(@PathVariable Long uid) {
        log.debug("REST request to get Grossdeduct : {}", uid);
        GrossdeductDTO grossdeductDTO = grossdeductService.findOne(uid);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(grossdeductDTO));
    }

    /**
     * DELETE  /grossdeducts/:id : delete the "id" grossdeduct.
     *
     * @param id the id of the grossdeductDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/grossdeducts/{id}")
    @Timed
    public ResponseEntity<Void> deleteGrossdeduct(@PathVariable Long id) {
        log.debug("REST request to delete Grossdeduct : {}", id);
        grossdeductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
