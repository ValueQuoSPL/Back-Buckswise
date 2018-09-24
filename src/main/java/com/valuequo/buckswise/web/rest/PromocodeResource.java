package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.PromocodeService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.PromocodeDTO;
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
 * REST controller for managing Promocode.
 */
@RestController
@RequestMapping("/api")
public class PromocodeResource {

    private final Logger log = LoggerFactory.getLogger(PromocodeResource.class);

    private static final String ENTITY_NAME = "promocode";

    private final PromocodeService promocodeService;

    public PromocodeResource(PromocodeService promocodeService) {
        this.promocodeService = promocodeService;
    }

    /**
     * POST  /promocodes : Create a new promocode.
     *
     * @param promocodeDTO the promocodeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new promocodeDTO, or with status 400 (Bad Request) if the promocode has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/promocodes")
    @Timed
    public ResponseEntity<PromocodeDTO> createPromocode(@RequestBody PromocodeDTO promocodeDTO) throws URISyntaxException {
        log.debug("REST request to save Promocode : {}", promocodeDTO);
        if (promocodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new promocode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PromocodeDTO result = promocodeService.save(promocodeDTO);
        return ResponseEntity.created(new URI("/api/promocodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /promocodes : Updates an existing promocode.
     *
     * @param promocodeDTO the promocodeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated promocodeDTO,
     * or with status 400 (Bad Request) if the promocodeDTO is not valid,
     * or with status 500 (Internal Server Error) if the promocodeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/promocodes")
    @Timed
    public ResponseEntity<PromocodeDTO> updatePromocode(@RequestBody PromocodeDTO promocodeDTO) throws URISyntaxException {
        log.debug("REST request to update Promocode : {}", promocodeDTO);
        if (promocodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PromocodeDTO result = promocodeService.save(promocodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, promocodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /promocodes : get all the promocodes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of promocodes in body
     */
    @GetMapping("/promocodes")
    @Timed
    public List<PromocodeDTO> getAllPromocodes() {
        log.debug("REST request to get all Promocodes");
        return promocodeService.findAll();
    }

    /**
     * GET  /promocodes/:id : get the "id" promocode.
     *
     * @param id the id of the promocodeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the promocodeDTO, or with status 404 (Not Found)
     */
    // @GetMapping("/promocodes/{id}")
    // @Timed
    // public ResponseEntity<PromocodeDTO> getPromocode(@PathVariable Long id) {
    //     log.debug("REST request to get Promocode : {}", id);
    //     Optional<PromocodeDTO> promocodeDTO = promocodeService.findOne(id);
    //     return ResponseUtil.wrapOrNotFound(promocodeDTO);
    // }

    /**
     * DELETE  /promocodes/:id : delete the "id" promocode.
     *
     * @param id the id of the promocodeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    // @DeleteMapping("/promocodes/{id}")
    // @Timed
    // public ResponseEntity<Void> deletePromocode(@PathVariable Long id) {
    //     log.debug("REST request to delete Promocode : {}", id);
    //     promocodeService.delete(id);
    //     return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    // }
}
