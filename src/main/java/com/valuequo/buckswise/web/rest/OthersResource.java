package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.OthersService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.OthersDTO;
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
 * REST controller for managing Others.
 */
@RestController
@RequestMapping("/api")
public class OthersResource {

    private final Logger log = LoggerFactory.getLogger(OthersResource.class);

    private static final String ENTITY_NAME = "others";

    private final OthersService othersService;

    public OthersResource(OthersService othersService) {
        this.othersService = othersService;
    }

    /**
     * POST  /others : Create a new others.
     *
     * @param othersDTO the othersDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new othersDTO, or with status 400 (Bad Request) if the others has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/others")
    @Timed
    public ResponseEntity<OthersDTO> createOthers(@RequestBody OthersDTO othersDTO) throws URISyntaxException {
        log.debug("REST request to save Others : {}", othersDTO);
        if (othersDTO.getId() != null) {
            throw new BadRequestAlertException("A new others cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OthersDTO result = othersService.save(othersDTO);
        return ResponseEntity.created(new URI("/api/others/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /others : Updates an existing others.
     *
     * @param othersDTO the othersDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated othersDTO,
     * or with status 400 (Bad Request) if the othersDTO is not valid,
     * or with status 500 (Internal Server Error) if the othersDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/others")
    @Timed
    public ResponseEntity<OthersDTO> updateOthers(@RequestBody OthersDTO othersDTO) throws URISyntaxException {
        log.debug("REST request to update Others : {}", othersDTO);
        if (othersDTO.getId() == null) {
            return createOthers(othersDTO);
        }
        OthersDTO result = othersService.save(othersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, othersDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /others : get all the others.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of others in body
     */
    @GetMapping("/others")
    @Timed
    public List<OthersDTO> getAllOthers() {
        log.debug("REST request to get all Others");
        return othersService.findAll();
        }

    /**
     * GET  /others/:id : get the "id" others.
     *
     * @param id the id of the othersDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the othersDTO, or with status 404 (Not Found)
     */
    @GetMapping("/others/{uid}")
    @Timed
    public ResponseEntity<OthersDTO> getOthers(@PathVariable Long uid) {
        log.debug("REST request to get Others : {}", uid);
        OthersDTO othersDTO = othersService.findOne(uid);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(othersDTO));
    }

    /**
     * DELETE  /others/:id : delete the "id" others.
     *
     * @param id the id of the othersDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/others/{id}")
    @Timed
    public ResponseEntity<Void> deleteOthers(@PathVariable Long id) {
        log.debug("REST request to delete Others : {}", id);
        othersService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
