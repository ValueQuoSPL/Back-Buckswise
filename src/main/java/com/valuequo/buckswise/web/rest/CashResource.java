package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.hazelcast.com.eclipsesource.json.JsonObject;
import com.valuequo.buckswise.domain.Cash;
import com.valuequo.buckswise.service.CashService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.CashDTO;
import io.github.jhipster.web.util.ResponseUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Cash.
 */
@RestController
@RequestMapping("/api")
public class CashResource {

    private final Logger log = LoggerFactory.getLogger(CashResource.class);

    private static final String ENTITY_NAME = "cash";

    private final CashService cashService;

    public CashResource(CashService cashService) {
        this.cashService = cashService;
    }

    /**
     * POST  /cash : Create a new cash.
     *
     * @param cashDTO the cashDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cashDTO, or with status 400 (Bad Request) if the cash has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/postcash")
    @Timed
    public ResponseEntity<CashDTO> createCash(@RequestBody CashDTO cashDTO) throws URISyntaxException {
        log.debug("REST request to save Cash : {}", cashDTO);
        if (cashDTO.getId() != null) {
            throw new BadRequestAlertException("A new cash cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CashDTO result = cashService.save(cashDTO);
        return ResponseEntity.created(new URI("/api/cash/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cash : Updates an existing cash.
     *
     * @param cashDTO the cashDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cashDTO,
     * or with status 400 (Bad Request) if the cashDTO is not valid,
     * or with status 500 (Internal Server Error) if the cashDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/putcash")
    @Timed
    public ResponseEntity<CashDTO> updateCash(@RequestBody CashDTO cashDTO) throws URISyntaxException {
        log.debug("REST request to update Cash : {}", cashDTO);
        if (cashDTO.getId() == null) {
            return createCash(cashDTO);
        }
        CashDTO result = cashService.save(cashDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cashDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cash : get all the cash.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cash in body
     */
//    @GetMapping("/cash")
//    @Timed
//    public List<CashDTO> getAllCash() {
//        log.debug("REST request to get all Cash");
//        return cashService.findAll();
//        }
    @GetMapping("/cashuid/{userid}")
    @Timed
    public List<Cash> getAllCash(@PathVariable Long userid){
    	return cashService.getCash(userid);
    }
    /**
     * GET  /cash/:id : get the "id" cash.
     *
     * @param id the id of the cashDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cashDTO, or with status 404 (Not Found)
     */
    /**
     * GET  /cash/:id : get the "id" cash.
     *
     * @param id the id of the cashDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cashDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cash/{id}")
    @Timed
    public ResponseEntity<CashDTO> getCash(@PathVariable Long id) {
        log.debug("REST request to get Cash : {}", id);
        CashDTO cashDTO = cashService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cashDTO));
    }

    /**
     * DELETE  /cash/:id : delete the "id" cash.
     *
     * @param id the id of the cashDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/deletecash/{id}")
    @Timed
    public ResponseEntity<Void> deleteCash(@PathVariable Long id) {
        log.debug("REST request to delete Cash : {}", id);
        cashService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/availableCash")
    public String updateAvailable(@RequestBody Map<String, Object> available) throws JSONException {
        JSONObject jObj = new JSONObject(available);
        Long id = jObj.getLong("assetid");
        String avail = jObj.getString("available");
        cashService.updateAvailable(id, avail);
        return null;
    }


}
