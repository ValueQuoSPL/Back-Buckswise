package com.valuequo.buckswise.web.rest;
import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.domain.Stock;
import com.valuequo.buckswise.service.MutualFundService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
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
 * REST controller for managing Mutualfund.
 */
@RestController
@RequestMapping("/api")
public class MutualFundResource {

    private final Logger log = LoggerFactory.getLogger(MutualFundResource.class);

    private static final String ENTITY_NAME = "mutualfund";

    private final MutualFundService mutualfundService;

    public MutualFundResource(MutualFundService mutualfundService) {
        this.mutualfundService = mutualfundService;
    }

    /**
     * POST  /mutualfunds : Create a new mutualfund.
     *
     * @param mutualfundDTO the mutualfundDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mutualfundDTO, or with status 400 (Bad Request) if the mutualfund has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mutualfund")
    @Timed
    public ResponseEntity<MutualFundDTO> createMutualfund(@RequestBody MutualFundDTO mutualfundDTO) throws URISyntaxException {
        log.debug("REST request to save Mutualfund : {}", mutualfundDTO);
        if (mutualfundDTO.getId() != null) {
            throw new BadRequestAlertException("A new mutualfund cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MutualFundDTO result = mutualfundService.save(mutualfundDTO);
        return ResponseEntity.created(new URI("/api/mutualfunds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mutualfunds : Updates an existing mutualfund.
     *
     * @param mutualfundDTO the mutualfundDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mutualfundDTO,
     * or with status 400 (Bad Request) if the mutualfundDTO is not valid,
     * or with status 500 (Internal Server Error) if the mutualfundDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mutualfund")
    @Timed
    public ResponseEntity<MutualFundDTO> updateMutualfund(@RequestBody MutualFundDTO mutualfundDTO) throws URISyntaxException {
        log.debug("REST request to update Mutualfund : {}", mutualfundDTO);
        if (mutualfundDTO.getId() == null) {
            return createMutualfund(mutualfundDTO);
        }
        MutualFundDTO result = mutualfundService.save(mutualfundDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mutualfundDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mutualfunds : get all the mutualfunds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mutualfunds in body
     */
    @GetMapping("/mutualfund")
    @Timed
    public List<MutualFundDTO> getAllMutualfunds() {
        log.debug("REST request to get all Mutualfunds");
        return mutualfundService.findAll();
        }

    /**
     * GET  /mutualfunds/:id : get the "id" mutualfund.
     *
     * @param id the id of the mutualfundDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mutualfundDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mlfnd/{uid}")
    @Timed
    public List<MutualFund> getMutualfund(@PathVariable Long uid) {
    	return mutualfundService.getUserDetail(uid); 
    }
    @GetMapping("/mutualfund/{id}")
    @Timed
    public List<MutualFund> getMutualfundById(@PathVariable Long id) {
    	return mutualfundService.getUserDetailById(id); 
    }
    

    /**
     * DELETE  /mutualfunds/:id : delete the "id" mutualfund.
     *
     * @param id the id of the mutualfundDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mutualfund/{id}")
    @Timed
    public ResponseEntity<Void> deleteMutualfund(@PathVariable Long id) {
        log.debug("REST request to delete Mutualfund : {}", id);
        mutualfundService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
