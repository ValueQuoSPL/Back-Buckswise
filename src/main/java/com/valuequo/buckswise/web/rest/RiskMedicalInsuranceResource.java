package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.RiskMedicalInsurance;
import com.valuequo.buckswise.service.RiskMedicalInsuranceService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.web.rest.util.PaginationUtil;
import com.valuequo.buckswise.service.dto.RiskMedicalInsuranceDTO;
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
 * REST controller for managing RiskMedicalInsurance.
 */
@RestController
@RequestMapping("/api")
public class RiskMedicalInsuranceResource {

    private final Logger log = LoggerFactory.getLogger(RiskMedicalInsuranceResource.class);

    private static final String ENTITY_NAME = "riskMedicalInsurance";

    private final RiskMedicalInsuranceService riskMedicalInsuranceService;

    public RiskMedicalInsuranceResource(RiskMedicalInsuranceService riskMedicalInsuranceService) {
        this.riskMedicalInsuranceService = riskMedicalInsuranceService;
    }

    /**
     * POST  /risk-medical-insurances : Create a new riskMedicalInsurance.
     *
     * @param riskMedicalInsuranceDTO the riskMedicalInsuranceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new riskMedicalInsuranceDTO, or with status 400 (Bad Request) if the riskMedicalInsurance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/risk-medical-insurances")
    @Timed
    public ResponseEntity<RiskMedicalInsuranceDTO> createRiskMedicalInsurance(@RequestBody RiskMedicalInsuranceDTO riskMedicalInsuranceDTO) throws URISyntaxException {
        log.debug("REST request to save RiskMedicalInsurance : {}", riskMedicalInsuranceDTO);
        if (riskMedicalInsuranceDTO.getId() != null) {
            throw new BadRequestAlertException("A new riskMedicalInsurance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskMedicalInsuranceDTO result = riskMedicalInsuranceService.save(riskMedicalInsuranceDTO);
        return ResponseEntity.created(new URI("/api/risk-medical-insurances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /risk-medical-insurances : Updates an existing riskMedicalInsurance.
     *
     * @param riskMedicalInsuranceDTO the riskMedicalInsuranceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated riskMedicalInsuranceDTO,
     * or with status 400 (Bad Request) if the riskMedicalInsuranceDTO is not valid,
     * or with status 500 (Internal Server Error) if the riskMedicalInsuranceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/risk-medical-insurances")
    @Timed
    public ResponseEntity<RiskMedicalInsuranceDTO> updateRiskMedicalInsurance(@RequestBody RiskMedicalInsuranceDTO riskMedicalInsuranceDTO) throws URISyntaxException {
        log.debug("REST request to update RiskMedicalInsurance : {}", riskMedicalInsuranceDTO);
        if (riskMedicalInsuranceDTO.getId() == null) {
            return createRiskMedicalInsurance(riskMedicalInsuranceDTO);
        }
        RiskMedicalInsuranceDTO result = riskMedicalInsuranceService.save(riskMedicalInsuranceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, riskMedicalInsuranceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /risk-medical-insurances : get all the riskMedicalInsurances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of riskMedicalInsurances in body
     */
    @GetMapping("/risk-medical-insurances")
    @Timed
    public ResponseEntity<List<RiskMedicalInsuranceDTO>> getAllRiskMedicalInsurances(Pageable pageable) {
        log.debug("REST request to get a page of RiskMedicalInsurances");
        Page<RiskMedicalInsuranceDTO> page = riskMedicalInsuranceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/risk-medical-insurances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /risk-medical-insurances/:id : get the "id" riskMedicalInsurance.
     *
     * @param id the id of the riskMedicalInsuranceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the riskMedicalInsuranceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/risk-medical-insurances/{id}")
    @Timed
    public ResponseEntity<RiskMedicalInsuranceDTO> getRiskMedicalInsurance(@PathVariable Long id) {
        log.debug("REST request to get RiskMedicalInsurance : {}", id);
        RiskMedicalInsuranceDTO riskMedicalInsuranceDTO = riskMedicalInsuranceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(riskMedicalInsuranceDTO));
    }

    @GetMapping("/getRiskMedicalInsurance/{userid}")
    @Timed
    public List<RiskMedicalInsurance> getData(@PathVariable Long userid) {
    	return riskMedicalInsuranceService.getdata(userid);
    }
    
    
    /**
     * DELETE  /risk-medical-insurances/:id : delete the "id" riskMedicalInsurance.
     *
     * @param id the id of the riskMedicalInsuranceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/risk-medical-insurances/{id}")
    @Timed
    public ResponseEntity<Void> deleteRiskMedicalInsurance(@PathVariable Long id) {
        log.debug("REST request to delete RiskMedicalInsurance : {}", id);
        riskMedicalInsuranceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
