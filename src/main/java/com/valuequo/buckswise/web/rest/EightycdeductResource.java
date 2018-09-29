package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.service.EightycdeductService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.EightycdeductDTO;
import com.valuequo.buckswise.service.dto.GrossdeductDTO;

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
 * REST controller for managing Eightycdeduct.
 */
@RestController
@RequestMapping("/api")
public class EightycdeductResource {

    private final Logger log = LoggerFactory.getLogger(EightycdeductResource.class);

    private static final String ENTITY_NAME = "eightycdeduct";

    private final EightycdeductService eightycdeductService;

    public EightycdeductResource(EightycdeductService eightycdeductService) {
        this.eightycdeductService = eightycdeductService;
    }

    /**
     * POST  /eightycdeducts : Create a new eightycdeduct.
     *
     * @param eightycdeductDTO the eightycdeductDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eightycdeductDTO, or with status 400 (Bad Request) if the eightycdeduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/eightycdeducts")
    @Timed
    public ResponseEntity<EightycdeductDTO> createEightycdeduct(@RequestBody EightycdeductDTO eightycdeductDTO) throws URISyntaxException {
        log.debug("REST request to save Eightycdeduct : {}", eightycdeductDTO);
        if (eightycdeductDTO.getId() != null) {
            throw new BadRequestAlertException("A new eightycdeduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EightycdeductDTO result = eightycdeductService.save(eightycdeductDTO);
        return ResponseEntity.created(new URI("/api/eightycdeducts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /eightycdeducts : Updates an existing eightycdeduct.
     *
     * @param eightycdeductDTO the eightycdeductDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eightycdeductDTO,
     * or with status 400 (Bad Request) if the eightycdeductDTO is not valid,
     * or with status 500 (Internal Server Error) if the eightycdeductDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/eightycdeducts")
    @Timed
    public ResponseEntity<EightycdeductDTO> updateEightycdeduct(@RequestBody EightycdeductDTO eightycdeductDTO) throws URISyntaxException {
        log.debug("REST request to update Eightycdeduct : {}", eightycdeductDTO);
        if (eightycdeductDTO.getId() == null) {
            return createEightycdeduct(eightycdeductDTO);
        }
        EightycdeductDTO result = eightycdeductService.save(eightycdeductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eightycdeductDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /eightycdeducts : get all the eightycdeducts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of eightycdeducts in body
     */
    @GetMapping("/eightycdeducts")
    @Timed
    public List<EightycdeductDTO> getAllEightycdeducts() {
        log.debug("REST request to get all Eightycdeducts");
        return eightycdeductService.findAll();
        }

    /**
     * GET  /eightycdeducts/:id : get the "id" eightycdeduct.
     *
     * @param id the id of the eightycdeductDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eightycdeductDTO, or with status 404 (Not Found)
     */
    @GetMapping("/eightycdeducts/{uid}")
    @Timed
    public ResponseEntity<List<EightycdeductDTO>> getEightycdeduct(@PathVariable int uid) {
    	System.out.println("fromEightyc" + uid);
        log.debug("REST request to get Eightycdeduct : {}", uid);
        List<EightycdeductDTO> eightycdeductDTO = eightycdeductService.findOne(uid);
        System.out.println(eightycdeductDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eightycdeductDTO));
    }
   
    /**
     * DELETE  /eightycdeducts/:id : delete the "id" eightycdeduct.
     *
     * @param id the id of the eightycdeductDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/eightycdeducts/{id}")
    @Timed
    public ResponseEntity<Void> deleteEightycdeduct(@PathVariable Long id) {
        log.debug("REST request to delete Eightycdeduct : {}", id);
        eightycdeductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
