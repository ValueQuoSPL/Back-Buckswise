package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Property;
import com.valuequo.buckswise.service.PropertyService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.web.rest.util.PaginationUtil;
import com.valuequo.buckswise.service.dto.PropertyDTO;
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
 * REST controller for managing Property.
 */
@RestController
@RequestMapping("/api")
public class PropertyResource {

    private final Logger log = LoggerFactory.getLogger(PropertyResource.class);

    private static final String ENTITY_NAME = "property";

    private final PropertyService propertyService;

    public PropertyResource(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * POST  /properties : Create a new property.
     *
     * @param propertyDTO the propertyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new propertyDTO, or with status 400 (Bad Request) if the property has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/properties")
    @Timed
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) throws URISyntaxException {
        log.debug("REST request to save Property : {}", propertyDTO);
        if (propertyDTO.getId() != null) {
            throw new BadRequestAlertException("A new property cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PropertyDTO result = propertyService.save(propertyDTO);
        return ResponseEntity.created(new URI("/api/properties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /properties : Updates an existing property.
     *
     * @param propertyDTO the propertyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated propertyDTO,
     * or with status 400 (Bad Request) if the propertyDTO is not valid,
     * or with status 500 (Internal Server Error) if the propertyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/putproperties")
    @Timed
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO) throws URISyntaxException {
        log.debug("REST request to update Property : {}", propertyDTO);
        if (propertyDTO.getId() == null) {
            return createProperty(propertyDTO);
        }
        PropertyDTO result = propertyService.save(propertyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, propertyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /properties : get all the properties.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of properties in body
     */
    @GetMapping("/properties")
    @Timed
    public ResponseEntity<List<PropertyDTO>> getAllProperties(Pageable pageable) {
        log.debug("REST request to get a page of Properties");
        Page<PropertyDTO> page = propertyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/properties");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/property/{userid}")
    @Timed
    public List<Property> getProperties(@PathVariable Long userid){
    	return propertyService.getPropertie(userid);
    }
    /**
     * GET  /properties/:id : get the "id" property.
     *
     * @param id the id of the propertyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the propertyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/propertiesbyid/{id}")
    @Timed
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable Long id) {
        log.debug("REST request to get Property : {}", id);
        PropertyDTO propertyDTO = propertyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(propertyDTO));
    }

    /**
     * DELETE  /properties/:id : delete the "id" property.
     *
     * @param id the id of the propertyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/deleteproperties/{id}")
    @Timed
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        log.debug("REST request to delete Property : {}", id);
        propertyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/availableProperty")
    public String updateAvailable(@RequestBody Map<String, Object> available) throws JSONException{
        JSONObject jObj =  new JSONObject(available);
        System.out.println(available);
        Long id = jObj.getLong("assetid");
        String avail = jObj.getString("available");
        propertyService.updateAvailable(id, avail);
        return null;
    }
}
