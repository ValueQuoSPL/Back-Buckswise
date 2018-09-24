package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.AtlernateInvestment;
import com.valuequo.buckswise.service.AtlernateInvestmentService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.web.rest.util.PaginationUtil;
import com.valuequo.buckswise.service.dto.AtlernateInvestmentDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AtlernateInvestment.
 */
@RestController
@RequestMapping("/api")
public class AtlernateInvestmentResource {

    private final Logger log = LoggerFactory.getLogger(AtlernateInvestmentResource.class);

    private static final String ENTITY_NAME = "atlernateInvestment";

    private final AtlernateInvestmentService atlernateInvestmentService;

    public AtlernateInvestmentResource(AtlernateInvestmentService atlernateInvestmentService) {
        this.atlernateInvestmentService = atlernateInvestmentService;
    }

    /**
     * POST  /atlernate-investments : Create a new atlernateInvestment.
     *
     * @param atlernateInvestmentDTO the atlernateInvestmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new atlernateInvestmentDTO, or with status 400 (Bad Request) if the atlernateInvestment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atlernate-investments")
    @Timed
    public ResponseEntity<AtlernateInvestmentDTO> createAtlernateInvestment(@RequestBody AtlernateInvestmentDTO atlernateInvestmentDTO) throws URISyntaxException {
        log.debug("REST request to save AtlernateInvestment : {}", atlernateInvestmentDTO);
        if (atlernateInvestmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new atlernateInvestment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AtlernateInvestmentDTO result = atlernateInvestmentService.save(atlernateInvestmentDTO);
        return ResponseEntity.created(new URI("/api/atlernate-investments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /atlernate-investments : Updates an existing atlernateInvestment.
     *
     * @param atlernateInvestmentDTO the atlernateInvestmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated atlernateInvestmentDTO,
     * or with status 400 (Bad Request) if the atlernateInvestmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the atlernateInvestmentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/atlernateInvestments")
    @Timed
    public ResponseEntity<AtlernateInvestmentDTO> updateAtlernateInvestment(@RequestBody AtlernateInvestmentDTO atlernateInvestmentDTO) throws URISyntaxException {
        log.debug("REST request to update AtlernateInvestment : {}", atlernateInvestmentDTO);
        if (atlernateInvestmentDTO.getId() == null) {
            return createAtlernateInvestment(atlernateInvestmentDTO);
        }
        AtlernateInvestmentDTO result = atlernateInvestmentService.save(atlernateInvestmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, atlernateInvestmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /atlernate-investments : get all the atlernateInvestments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of atlernateInvestments in body
     */
    @GetMapping("/atlernate-investments")
    @Timed
    public ResponseEntity<List<AtlernateInvestmentDTO>> getAllAtlernateInvestments(Pageable pageable) {
        log.debug("REST request to get a page of AtlernateInvestments");
        Page<AtlernateInvestmentDTO> page = atlernateInvestmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/atlernate-investments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /atlernate-investments/:id : get the "id" atlernateInvestment.
     *
     * @param id the id of the atlernateInvestmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the atlernateInvestmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/atlernateInvest/{id}")
    @Timed
    public ResponseEntity<AtlernateInvestmentDTO> getAtlernateInvestment(@PathVariable Long id) {
        log.debug("REST request to get AtlernateInvestment : {}", id);
        AtlernateInvestmentDTO atlernateInvestmentDTO = atlernateInvestmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(atlernateInvestmentDTO));
    }
    
    @GetMapping("/atlernate/{userid}")
    @Timed
    public List<AtlernateInvestment> getInvestment(@PathVariable Long userid) {
    	return atlernateInvestmentService.getAInvestment(userid);
    }

    /**
     * DELETE  /atlernate-investments/:id : delete the "id" atlernateInvestment.
     *
     * @param id the id of the atlernateInvestmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atlerInvest/{id}")
    @Timed
    public ResponseEntity<Void> deleteAtlernateInvestment(@PathVariable Long id) {
        log.debug("REST request to delete AtlernateInvestment : {}", id);
        atlernateInvestmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
