package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.RiskLifeInsurance;
import com.valuequo.buckswise.service.RiskLifeInsuranceService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.web.rest.util.PaginationUtil;
import com.valuequo.buckswise.service.dto.RiskLifeInsuranceDTO;
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
 * REST controller for managing RiskLifeInsurance.
 */
@RestController
@RequestMapping("/api")
public class RiskLifeInsuranceResource {

    private final Logger log = LoggerFactory.getLogger(RiskLifeInsuranceResource.class);

    private static final String ENTITY_NAME = "riskLifeInsurance";

    private final RiskLifeInsuranceService riskLifeInsuranceService;

    public RiskLifeInsuranceResource(RiskLifeInsuranceService riskLifeInsuranceService) {
        this.riskLifeInsuranceService = riskLifeInsuranceService;
    }

    /**
     * POST  /risk-life-insurances : Create a new riskLifeInsurance.
     *
     * @param riskLifeInsuranceDTO the riskLifeInsuranceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new riskLifeInsuranceDTO, or with status 400 (Bad Request) if the riskLifeInsurance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/risk-life-insurances")
    @Timed
    public ResponseEntity<RiskLifeInsuranceDTO> createRiskLifeInsurance(@RequestBody RiskLifeInsuranceDTO riskLifeInsuranceDTO) throws URISyntaxException {
        log.debug("REST request to save RiskLifeInsurance : {}", riskLifeInsuranceDTO);
        if (riskLifeInsuranceDTO.getId() != null) {
            throw new BadRequestAlertException("A new riskLifeInsurance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskLifeInsuranceDTO result = riskLifeInsuranceService.save(riskLifeInsuranceDTO);
        return ResponseEntity.created(new URI("/api/risk-life-insurances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /risk-life-insurances : Updates an existing riskLifeInsurance.
     *
     * @param riskLifeInsuranceDTO the riskLifeInsuranceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated riskLifeInsuranceDTO,
     * or with status 400 (Bad Request) if the riskLifeInsuranceDTO is not valid,
     * or with status 500 (Internal Server Error) if the riskLifeInsuranceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/risk-life-insurances")
    @Timed
    public ResponseEntity<RiskLifeInsuranceDTO> updateRiskLifeInsurance(@RequestBody RiskLifeInsuranceDTO riskLifeInsuranceDTO) throws URISyntaxException {
        log.debug("REST request to update RiskLifeInsurance : {}", riskLifeInsuranceDTO);
        if (riskLifeInsuranceDTO.getId() == null) {
            return createRiskLifeInsurance(riskLifeInsuranceDTO);
        }
        RiskLifeInsuranceDTO result = riskLifeInsuranceService.save(riskLifeInsuranceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, riskLifeInsuranceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /risk-life-insurances : get all the riskLifeInsurances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of riskLifeInsurances in body
     */
    @GetMapping("/risk-life-insurances")
    @Timed
    public ResponseEntity<List<RiskLifeInsuranceDTO>> getAllRiskLifeInsurances(Pageable pageable) {
        log.debug("REST request to get a page of RiskLifeInsurances");
        Page<RiskLifeInsuranceDTO> page = riskLifeInsuranceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/risk-life-insurances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /risk-life-insurances/:id : get the "id" riskLifeInsurance.
     *
     * @param id the id of the riskLifeInsuranceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the riskLifeInsuranceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/risk-life-insurances/{id}")
    @Timed
    public ResponseEntity<RiskLifeInsuranceDTO> getRiskLifeInsurance(@PathVariable Long id) {
        log.debug("REST request to get RiskLifeInsurance : {}", id);
        RiskLifeInsuranceDTO riskLifeInsuranceDTO = riskLifeInsuranceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(riskLifeInsuranceDTO));
    }
    
//    get user detail by userId.
    @GetMapping("/getRiskLifeInsurance/{userid}")
    @Timed
    public List<RiskLifeInsurance> getDetail(@PathVariable Long userid) {
    	return riskLifeInsuranceService.details(userid);
    }

    /**
     * DELETE  /risk-life-insurances/:id : delete the "id" riskLifeInsurance.
     *
     * @param id the id of the riskLifeInsuranceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/risk-life-insurances/{id}")
    @Timed
    public ResponseEntity<Void> deleteRiskLifeInsurance(@PathVariable Long id) {
        log.debug("REST request to delete RiskLifeInsurance : {}", id);
        riskLifeInsuranceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
