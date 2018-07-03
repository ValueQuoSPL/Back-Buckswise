package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.OtherService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.OtherDTO;
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
 * REST controller for managing Other.
 */
@RestController
@RequestMapping("/api")
public class OtherResource {

    private final Logger log = LoggerFactory.getLogger(OtherResource.class);

    private static final String ENTITY_NAME = "other";

    private final OtherService otherService;

    public OtherResource(OtherService otherService) {
        this.otherService = otherService;
    }

    /**
     * POST  /others : Create a new other.
     *
     * @param otherDTO the otherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new otherDTO, or with status 400 (Bad Request) if the other has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/others")
    @Timed
    public ResponseEntity<OtherDTO> createOther(@RequestBody OtherDTO otherDTO) throws URISyntaxException {
        log.debug("REST request to save Other : {}", otherDTO);
        if (otherDTO.getId() != null) {
            throw new BadRequestAlertException("A new other cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OtherDTO result = otherService.save(otherDTO);
        return ResponseEntity.created(new URI("/api/others/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /others : Updates an existing other.
     *
     * @param otherDTO the otherDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated otherDTO,
     * or with status 400 (Bad Request) if the otherDTO is not valid,
     * or with status 500 (Internal Server Error) if the otherDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/others")
    @Timed
    public ResponseEntity<OtherDTO> updateOther(@RequestBody OtherDTO otherDTO) throws URISyntaxException {
        log.debug("REST request to update Other : {}", otherDTO);
        if (otherDTO.getId() == null) {
            return createOther(otherDTO);
        }
        OtherDTO result = otherService.save(otherDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, otherDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /others : get all the others.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of others in body
     */
    @GetMapping("/others")
    @Timed
    public List<OtherDTO> getAllOthers() {
        log.debug("REST request to get all Others");
        return otherService.findAll();
        }

    /**
     * GET  /others/:id : get the "id" other.
     *
     * @param id the id of the otherDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the otherDTO, or with status 404 (Not Found)
     */
    @GetMapping("/others/{id}")
    @Timed
    public ResponseEntity<OtherDTO> getOther(@PathVariable Long id) {
        log.debug("REST request to get Other : {}", id);
        OtherDTO otherDTO = otherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(otherDTO));
    }

    /**
     * DELETE  /others/:id : delete the "id" other.
     *
     * @param id the id of the otherDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/others/{id}")
    @Timed
    public ResponseEntity<Void> deleteOther(@PathVariable Long id) {
        log.debug("REST request to delete Other : {}", id);
        otherService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
