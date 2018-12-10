package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.FutureOption;
import com.valuequo.buckswise.service.FutureOptionService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.web.rest.util.PaginationUtil;
import com.valuequo.buckswise.service.dto.FutureOptionDTO;
import io.github.jhipster.web.util.ResponseUtil;

import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing FutureOption.
 */
@RestController
@RequestMapping("/api")
public class FutureOptionResource {

    private final Logger log = LoggerFactory.getLogger(FutureOptionResource.class);

    private static final String ENTITY_NAME = "futureOption";

    private final FutureOptionService futureOptionService;

    public FutureOptionResource(FutureOptionService futureOptionService) {
        this.futureOptionService = futureOptionService;
    }

    /**
     * POST  /future-options : Create a new futureOption.
     *
     * @param futureOptionDTO the futureOptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new futureOptionDTO, or with status 400 (Bad Request) if the futureOption has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/future-options")
    @Timed
    public ResponseEntity<FutureOptionDTO> createFutureOption(@RequestBody FutureOptionDTO futureOptionDTO) throws URISyntaxException {
        log.debug("REST request to save FutureOption : {}", futureOptionDTO);
        if (futureOptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new futureOption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FutureOptionDTO result = futureOptionService.save(futureOptionDTO);
        return ResponseEntity.created(new URI("/api/future-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /future-options : Updates an existing futureOption.
     *
     * @param futureOptionDTO the futureOptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated futureOptionDTO,
     * or with status 400 (Bad Request) if the futureOptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the futureOptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/putfutureoptions")
    @Timed
    public ResponseEntity<FutureOptionDTO> updateFutureOption(@RequestBody FutureOptionDTO futureOptionDTO) throws URISyntaxException {
        log.debug("REST request to update FutureOption : {}", futureOptionDTO);
        if (futureOptionDTO.getId() == null) {
            return createFutureOption(futureOptionDTO);
        }
        FutureOptionDTO result = futureOptionService.save(futureOptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, futureOptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /future-options : get all the futureOptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of futureOptions in body
     */
    @GetMapping("/getfutureoptions")
    @Timed
    public ResponseEntity<List<FutureOptionDTO>> getAllFutureOptions(Pageable pageable) {
        log.debug("REST request to get a page of FutureOptions");
        Page<FutureOptionDTO> page = futureOptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/future-options");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /future-options/:id : get the "id" futureOption.
     *
     * @param id the id of the futureOptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the futureOptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/futureoptionsbyid/{id}")
    @Timed
    public ResponseEntity<FutureOptionDTO> getFutureOption(@PathVariable Long id) {
        log.debug("REST request to get FutureOption : {}", id);
        FutureOptionDTO futureOptionDTO = futureOptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(futureOptionDTO));
    }

    @GetMapping("/futureOptionbyuid/{userid}")
    @Timed
    public List<FutureOption> getfutureoption(@PathVariable Long userid) {
    	return futureOptionService.getfutureOption(userid);
    }

    /**
     * DELETE  /future-options/:id : delete the "id" futureOption.
     *
     * @param id the id of the futureOptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/deletefutureoptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteFutureOption(@PathVariable Long id) {
        log.debug("REST request to delete FutureOption : {}", id);
        futureOptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * Author - Pratik
     * @param availables
     * @return
     * @throws JSONException
     */
    @PutMapping("/availableFao")
    public String updateAvailable(@RequestBody Map<String, Object> availables) throws JSONException
    {
        JSONObject jObj = new JSONObject(availables);
        Long id = jObj.getLong("assetid");
        String avail = jObj.getString("available");
        futureOptionService.updateAvailable(id, avail);
        return null;
    }
}
