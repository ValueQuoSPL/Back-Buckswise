package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.Stock;
import com.valuequo.buckswise.service.StockService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.web.rest.util.PaginationUtil;
import com.valuequo.buckswise.service.dto.StockDTO;
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
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Stock.
 */
@RestController
@RequestMapping("/api")
public class StockResource {

    private final Logger log = LoggerFactory.getLogger(StockResource.class);

    private static final String ENTITY_NAME = "stock";

    private final StockService stockService;

    public StockResource(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * POST  /stocks : Create a new stock.
     *
     * @param stockDTO the stockDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stockDTO, or with status 400 (Bad Request) if the stock has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poststocks")
    @Timed
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO) throws URISyntaxException {
        log.debug("REST request to save Stock : {}", stockDTO);
        if (stockDTO.getId() != null) {
            throw new BadRequestAlertException("A new stock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.created(new URI("/api/stocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stocks : Updates an existing stock.
     *
     * @param stockDTO the stockDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stockDTO,
     * or with status 400 (Bad Request) if the stockDTO is not valid,
     * or with status 500 (Internal Server Error) if the stockDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/putstocks")
    @Timed
    public ResponseEntity<StockDTO> updateStock(@RequestBody StockDTO stockDTO) throws URISyntaxException {
        log.debug("REST request to update Stock : {}", stockDTO);
        if (stockDTO.getId() == null) {
            return createStock(stockDTO);
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/available")
    public String updateAvailable(@RequestBody Map<String, Object> available)
    {
        System.out.println("available" + available);
        return null;
    }

    /**
     * GET  /stocks : get all the stocks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stocks in body
     */
    @GetMapping("/getstocks")
    @Timed
    public ResponseEntity<List<StockDTO>> getAllStocks(Pageable pageable) {
        log.debug("REST request to get a page of Stocks");
        Page<StockDTO> page = stockService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stocks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/getbyuidstock/{userid}")
    @Timed
    public List<Stock> getuserId(@PathVariable Long userid) {
    	return stockService.getUser(userid);
    }

    /**
     * GET  /stocks/:id : get the "id" stock.
     *
     * @param id the id of the stockDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockDTO, or with status 404 (Not Found)
     */
    @GetMapping("/getbyidstocks/{id}")
    @Timed
    public ResponseEntity<StockDTO> getStock(@PathVariable Long id) {
        log.debug("REST request to get Stock : {}", id);
        StockDTO stockDTO = stockService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockDTO));
    }

    /**
     * DELETE  /stocks/:id : delete the "id" stock.
     *
     * @param id the id of the stockDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/deletestocks/{id}")
    @Timed
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        log.debug("REST request to delete Stock : {}", id);
        stockService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
