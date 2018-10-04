package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Assetmapping;
import com.valuequo.buckswise.domain.Chit;
import com.valuequo.buckswise.service.AssetmappingService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.AssetmappingDTO;
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
 * REST controller for managing Assetmapping.
 */
@RestController
@RequestMapping("/api")
public class AssetmappingResource {

    private final Logger log = LoggerFactory.getLogger(AssetmappingResource.class);

    private static final String ENTITY_NAME = "assetmapping";

    private final AssetmappingService assetmappingService;

    public AssetmappingResource(AssetmappingService assetmappingService) {
        this.assetmappingService = assetmappingService;
    }

    /**
     * POST  /assetmappings : Create a new assetmapping.
     *
     * @param assetmappingDTO the assetmappingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetmappingDTO, or with status 400 (Bad Request) if the assetmapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/assetmappings")
    @Timed
    public ResponseEntity<AssetmappingDTO> createAssetmapping(@RequestBody AssetmappingDTO assetmappingDTO) throws URISyntaxException {
        log.debug("REST request to save Assetmapping : {}", assetmappingDTO);
        if (assetmappingDTO.getId() != null) {
            throw new BadRequestAlertException("A new assetmapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetmappingDTO result = assetmappingService.save(assetmappingDTO);
        return ResponseEntity.created(new URI("/api/assetmappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /assetmappings : Updates an existing assetmapping.
     *
     * @param assetmappingDTO the assetmappingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assetmappingDTO,
     * or with status 400 (Bad Request) if the assetmappingDTO is not valid,
     * or with status 500 (Internal Server Error) if the assetmappingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/assetmappings")
    @Timed
    public ResponseEntity<AssetmappingDTO> updateAssetmapping(@RequestBody AssetmappingDTO assetmappingDTO) throws URISyntaxException {
        log.debug("REST request to update Assetmapping : {}", assetmappingDTO);
        if (assetmappingDTO.getId() == null) {
            return createAssetmapping(assetmappingDTO);
        }
        AssetmappingDTO result = assetmappingService.save(assetmappingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assetmappingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /assetmappings : get all the assetmappings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of assetmappings in body
     */
    @GetMapping("/assetmappings")
    @Timed
    public List<AssetmappingDTO> getAllAssetmappings() {
        log.debug("REST request to get all Assetmappings");
        return assetmappingService.findAll();
        }

    /**
     * GET  /assetmappings/:id : get the "id" assetmapping.
     *
     * @param id the id of the assetmappingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assetmappingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/assetmappings/{id}")
    @Timed
    public ResponseEntity<AssetmappingDTO> getAssetmapping(@PathVariable Long id) {
        log.debug("REST request to get Assetmapping : {}", id);
        AssetmappingDTO assetmappingDTO = assetmappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(assetmappingDTO));
    }
//    @GetMapping("/assetmappings/{uid}")
//    @Timed
//    public ResponseEntity<AssetmappingDTO> getAssetmappinguid(@PathVariable Long uid) {
//        log.debug("REST request to get Assetmapping : {}", uid);
//        AssetmappingDTO assetmappingDTO = assetmappingService.findOneuid(uid);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(assetmappingDTO));
//    }
//    

    @GetMapping("/assetmappingbyuid/{uid}")
    @Timed
    public List<Assetmapping> getChit1(@PathVariable Long uid){
    	return assetmappingService.getAssetByUid(uid);
    } 


    /**
     * DELETE  /assetmappings/:id : delete the "id" assetmapping.
     *
     * @param id the id of the assetmappingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delete/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssetmapping(@PathVariable Long id) {
        System.out.println("delete asset mapping" + id);
        log.debug("REST request to delete Assetmapping : {}", id);
        assetmappingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
