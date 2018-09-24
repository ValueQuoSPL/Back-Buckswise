package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Chit;
import com.valuequo.buckswise.service.ChitService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.web.rest.util.PaginationUtil;
import com.valuequo.buckswise.service.dto.ChitDTO;
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
 * REST controller for managing Chit.
 */
@RestController
@RequestMapping("/api")
public class ChitResource {

    private final Logger log = LoggerFactory.getLogger(ChitResource.class);

    private static final String ENTITY_NAME = "chit";

    private final ChitService chitService;

    public ChitResource(ChitService chitService) {
        this.chitService = chitService;
    }

    /**
     * POST  /chits : Create a new chit.
     *
     * @param chitDTO the chitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chitDTO, or with status 400 (Bad Request) if the chit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/postchit")
    @Timed
    public ResponseEntity<ChitDTO> createChit(@RequestBody ChitDTO chitDTO) throws URISyntaxException {
        log.debug("REST request to save Chit : {}", chitDTO);
        if (chitDTO.getId() != null) {
            throw new BadRequestAlertException("A new chit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChitDTO result = chitService.save(chitDTO);
        return ResponseEntity.created(new URI("/api/chits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chits : Updates an existing chit.
     *
     * @param chitDTO the chitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chitDTO,
     * or with status 400 (Bad Request) if the chitDTO is not valid,
     * or with status 500 (Internal Server Error) if the chitDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/putchit")
    @Timed
    public ResponseEntity<ChitDTO> updateChit(@RequestBody ChitDTO chitDTO) throws URISyntaxException {
        log.debug("REST request to update Chit : {}", chitDTO);
        if (chitDTO.getId() == null) {
            return createChit(chitDTO);
        }
        ChitDTO result = chitService.save(chitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chits : get all the chits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of chits in body
     */
    @GetMapping("/chits")
    @Timed
    public ResponseEntity<List<ChitDTO>> getAllChits(Pageable pageable) {
        log.debug("REST request to get a page of Chits");
        Page<ChitDTO> page = chitService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/getchit/{userid}")
    @Timed
    public List<Chit> getChit1(@PathVariable Long userid){
    	return chitService.getAllChit(userid);
    } 
    /**
     * GET  /chits/:id : get the "id" chit.
     *
     * @param id the id of the chitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/getchitbyid/{id}")
    @Timed
    public ResponseEntity<ChitDTO> getChit(@PathVariable Long id) {
        log.debug("REST request to get Chit : {}", id);
        ChitDTO chitDTO = chitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(chitDTO));
    }

    /**
     * DELETE  /chits/:id : delete the "id" chit.
     *
     * @param id the id of the chitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/deletechit/{id}")
    @Timed
    public ResponseEntity<Void> deleteChit(@PathVariable Long id) {
        log.debug("REST request to delete Chit : {}", id);
        chitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
