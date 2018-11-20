package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Familyprofile;
import com.valuequo.buckswise.domain.MyProfile;
import com.valuequo.buckswise.service.FamilyprofileService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.FamilyprofileDTO;
import com.valuequo.buckswise.service.dto.StockDTO;

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
 * REST controller for managing Familyprofile.
 */
@RestController
@RequestMapping("/api")
public class FamilyprofileResource {

    private final Logger log = LoggerFactory.getLogger(FamilyprofileResource.class);

    private static final String ENTITY_NAME = "familyprofile";

    private final FamilyprofileService familyprofileService;

    public FamilyprofileResource(FamilyprofileService familyprofileService) {
        this.familyprofileService = familyprofileService;
    }

    /**
     * POST  /familyprofiles : Create a new familyprofile.
     *
     * @param familyprofileDTO the familyprofileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new familyprofileDTO, or with status 400 (Bad Request) if the familyprofile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/familyprofiles")
    @Timed
    public ResponseEntity<FamilyprofileDTO> createFamilyprofile(@RequestBody FamilyprofileDTO familyprofileDTO) throws URISyntaxException {
        log.debug("REST request to save Familyprofile : {}", familyprofileDTO.getUid());
        if (familyprofileDTO.getId() != null) {
            throw new BadRequestAlertException("A new familyprofile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FamilyprofileDTO result = familyprofileService.save(familyprofileDTO);
        return ResponseEntity.created(new URI("/api/familyprofiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /familyprofiles : Updates an existing familyprofile.
     *
     * @param familyprofileDTO the familyprofileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated familyprofileDTO,
     * or with status 400 (Bad Request) if the familyprofileDTO is not valid,
     * or with status 500 (Internal Server Error) if the familyprofileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/familyprofile")
    @Timed
    public ResponseEntity<FamilyprofileDTO> updateFamilyprofile(@RequestBody FamilyprofileDTO familyprofileDTO) throws URISyntaxException {
        log.debug("REST request to update Familyprofile : {}", familyprofileDTO);
        if(familyprofileDTO.getUid() == null) {
            return createFamilyprofile(familyprofileDTO);
        }
        FamilyprofileDTO result = familyprofileService.update(familyprofileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, familyprofileDTO.getUid().toString()))
            .body(result);
    }

    /**
     * GET  /familyprofiles : get all the familyprofiles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of familyprofiles in body
     */    @GetMapping("/familyprofiles")
    @Timed
    public List<FamilyprofileDTO> getAllFamilyprofiles() {
        log.debug("REST request to get all Familyprofiles");
        return familyprofileService.findAll();
        }

    /**
     * GET  /familyprofiles/:id : get the "id" familyprofile.
     *
     * @param id the id of the familyprofileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the familyprofileDTO, or with status 404 (Not Found)
     */
//    @GetMapping("/familyprofiles/{uid}")
//    @Timed
//    public ResponseEntity<FamilyprofileDTO> getFamilyprofile(@PathVariable Long uid) {
//        log.debug("REST request to get Familyprofile : {}", uid);
//        FamilyprofileDTO familyprofileDTO = familyprofileService.findOne(uid);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(familyprofileDTO));
//    }
    @GetMapping("/familyprofiles/{uid}")
    @Timed
    public List<Familyprofile> getMyProfileById(@PathVariable Long uid)
    {
		return familyprofileService.getFamilyProfileById(uid);
    	
    }
    @GetMapping("/familypro/{id}")
    @Timed
  public ResponseEntity<FamilyprofileDTO> getFamilyprofile(@PathVariable Long id) {
      log.debug("REST request to get Familyprofile : {}", id);
      FamilyprofileDTO familyprofileDTO = familyprofileService.findOne(id);
      return ResponseUtil.wrapOrNotFound(Optional.ofNullable(familyprofileDTO));
  }
//    @GetMapping("/myprofile/{uid}")
//    @Timed
//    public List<MyProfile> getMyProfileById(@PathVariable int uid)
//    {
//		return myprofileService.getMyProfileById(uid);
//    	
//    }
    /**
     * DELETE  /familyprofiles/:id : delete the "id" familyprofile.
     *
     * @param id the id of the familyprofileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/familyprofiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteFamilyprofile(@PathVariable Long id) {
        log.debug("REST request to delete Familyprofile : {}", id);
        familyprofileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
