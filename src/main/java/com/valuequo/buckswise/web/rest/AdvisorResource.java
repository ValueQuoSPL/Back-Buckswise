package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Advisor;
import com.valuequo.buckswise.service.AdvisorService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.AdvisorDTO;
import io.github.jhipster.web.util.ResponseUtil;

import org.springframework.boot.configurationprocessor.json.JSONObject;

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
 * REST controller for managing Advisor.
 */
@RestController
@RequestMapping("/api")
public class AdvisorResource {

    private final Logger log = LoggerFactory.getLogger(AdvisorResource.class);

    private static final String ENTITY_NAME = "advisor";

    private final AdvisorService advisorService;

    public AdvisorResource(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    /**
     * POST  /advisors : Create a new advisor.
     *
     * @param advisorDTO the advisorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new advisorDTO, or with status 400 (Bad Request) if the advisor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/advisors")
    @Timed
    public ResponseEntity<AdvisorDTO> createAdvisor(@RequestBody Map<String, Object> data) throws JSONException {
        Long uid;
		Long aid;
        String recotype;
        String recoby;
        String recodate;
        String reco;

        log.debug("REST request to save Advisor : {}", data);
        // for(Map.Entry<String, Object> entry: data.entrySet()) {
            JSONObject jObj = new JSONObject(data);
            System.out.println(jObj);
            uid = jObj.getLong("uid");
			aid = jObj.getLong("aid");
            recotype = jObj.getString("recotype");
            recoby = jObj.getString("recoby");
            recodate = jObj.getString("recodate");
			reco = jObj.getString("reco");
            /* JSONArray ja_data = jObj.getJSONArray("reco");
            System.out.println(ja_data.length());
            for (int i =0; i<ja_data.length(); i++) {
                JSONObject jObj1 = ja_data.getJSONObject(i);
                reco = jObj1.get("reco").toString();
                System.out.println("reco" + reco);
                
            } */
			advisorService.saveRecommendation(uid, aid,recotype, recoby, recodate, reco);
        // }
        return null;
    }

    /**
     * PUT /advisors : Updates an existing advisor.
     *
     * @param advisorDTO the advisorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         advisorDTO, or with status 400 (Bad Request) if the advisorDTO is not
     *         valid, or with status 500 (Internal Server Error) if the advisorDTO
     *         couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws JSONException
     */
    @PutMapping("/advisors")
    @Timed
    public ResponseEntity<AdvisorDTO> updateAdvisor(@RequestBody AdvisorDTO advisorDTO)
            throws URISyntaxException, JSONException {
        log.debug("REST request to update Advisor : {}", advisorDTO);
        if (advisorDTO.getId() == null) {
            // return createAdvisor(advisorDTO);
            return createAdvisor((Map<String, Object>) advisorDTO);
        }
        AdvisorDTO result = advisorService.save(advisorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, advisorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /advisors : get all the advisors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of advisors in body
     */
    @GetMapping("/advisors")
    @Timed
    public List<AdvisorDTO> getAllAdvisors() {
        log.debug("REST request to get all Advisors");
        return advisorService.findAll();
        }

    /**
     * GET  /advisors/:id : get the "id" advisor.
     *
     * @param id the id of the advisorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the advisorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/advisors/{id}")
    @Timed
    public ResponseEntity<AdvisorDTO> getAdvisor(@PathVariable Long id) {
        log.debug("REST request to get Advisor : {}", id);
        AdvisorDTO advisorDTO = advisorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(advisorDTO));
    }

    @GetMapping("/advisor/{aid}")
    @Timed
    public List<Advisor> getAdvisorAid(@PathVariable Long aid) {
        return advisorService.findByAid(aid);
    }

    /**
     * DELETE  /advisors/:id : delete the "id" advisor.
     *
     * @param id the id of the advisorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/advisors/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdvisor(@PathVariable Long id) {
        log.debug("REST request to delete Advisor : {}", id);
        advisorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
