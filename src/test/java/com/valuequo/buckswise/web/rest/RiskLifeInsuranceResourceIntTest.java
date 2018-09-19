package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.RiskLifeInsurance;
import com.valuequo.buckswise.repository.RiskLifeInsuranceRepository;
import com.valuequo.buckswise.service.RiskLifeInsuranceService;
import com.valuequo.buckswise.service.dto.RiskLifeInsuranceDTO;
import com.valuequo.buckswise.service.mapper.RiskLifeInsuranceMapper;
import com.valuequo.buckswise.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.valuequo.buckswise.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RiskLifeInsuranceResource REST controller.
 *
 * @see RiskLifeInsuranceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class RiskLifeInsuranceResourceIntTest {

    private static final String DEFAULT_EXPENSE_COVER = "AAAAAAAAAA";
    private static final String UPDATED_EXPENSE_COVER = "BBBBBBBBBB";

    private static final String DEFAULT_RISK_COVERAGE = "AAAAAAAAAA";
    private static final String UPDATED_RISK_COVERAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL = "BBBBBBBBBB";

    @Autowired
    private RiskLifeInsuranceRepository riskLifeInsuranceRepository;

    @Autowired
    private RiskLifeInsuranceMapper riskLifeInsuranceMapper;

    @Autowired
    private RiskLifeInsuranceService riskLifeInsuranceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRiskLifeInsuranceMockMvc;

    private RiskLifeInsurance riskLifeInsurance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RiskLifeInsuranceResource riskLifeInsuranceResource = new RiskLifeInsuranceResource(riskLifeInsuranceService);
        this.restRiskLifeInsuranceMockMvc = MockMvcBuilders.standaloneSetup(riskLifeInsuranceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskLifeInsurance createEntity(EntityManager em) {
        RiskLifeInsurance riskLifeInsurance = new RiskLifeInsurance()
            .expense_cover(DEFAULT_EXPENSE_COVER)
            .risk_coverage(DEFAULT_RISK_COVERAGE)
            .total(DEFAULT_TOTAL);
        return riskLifeInsurance;
    }

    @Before
    public void initTest() {
        riskLifeInsurance = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiskLifeInsurance() throws Exception {
        int databaseSizeBeforeCreate = riskLifeInsuranceRepository.findAll().size();

        // Create the RiskLifeInsurance
        RiskLifeInsuranceDTO riskLifeInsuranceDTO = riskLifeInsuranceMapper.toDto(riskLifeInsurance);
        restRiskLifeInsuranceMockMvc.perform(post("/api/risk-life-insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskLifeInsuranceDTO)))
            .andExpect(status().isCreated());

        // Validate the RiskLifeInsurance in the database
        List<RiskLifeInsurance> riskLifeInsuranceList = riskLifeInsuranceRepository.findAll();
        assertThat(riskLifeInsuranceList).hasSize(databaseSizeBeforeCreate + 1);
        RiskLifeInsurance testRiskLifeInsurance = riskLifeInsuranceList.get(riskLifeInsuranceList.size() - 1);
        assertThat(testRiskLifeInsurance.getExpense_cover()).isEqualTo(DEFAULT_EXPENSE_COVER);
        assertThat(testRiskLifeInsurance.getRisk_coverage()).isEqualTo(DEFAULT_RISK_COVERAGE);
        assertThat(testRiskLifeInsurance.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    public void createRiskLifeInsuranceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskLifeInsuranceRepository.findAll().size();

        // Create the RiskLifeInsurance with an existing ID
        riskLifeInsurance.setId(1L);
        RiskLifeInsuranceDTO riskLifeInsuranceDTO = riskLifeInsuranceMapper.toDto(riskLifeInsurance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskLifeInsuranceMockMvc.perform(post("/api/risk-life-insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskLifeInsuranceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RiskLifeInsurance in the database
        List<RiskLifeInsurance> riskLifeInsuranceList = riskLifeInsuranceRepository.findAll();
        assertThat(riskLifeInsuranceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRiskLifeInsurances() throws Exception {
        // Initialize the database
        riskLifeInsuranceRepository.saveAndFlush(riskLifeInsurance);

        // Get all the riskLifeInsuranceList
        restRiskLifeInsuranceMockMvc.perform(get("/api/risk-life-insurances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskLifeInsurance.getId().intValue())))
            .andExpect(jsonPath("$.[*].expense_cover").value(hasItem(DEFAULT_EXPENSE_COVER.toString())))
            .andExpect(jsonPath("$.[*].risk_coverage").value(hasItem(DEFAULT_RISK_COVERAGE.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.toString())));
    }

    @Test
    @Transactional
    public void getRiskLifeInsurance() throws Exception {
        // Initialize the database
        riskLifeInsuranceRepository.saveAndFlush(riskLifeInsurance);

        // Get the riskLifeInsurance
        restRiskLifeInsuranceMockMvc.perform(get("/api/risk-life-insurances/{id}", riskLifeInsurance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(riskLifeInsurance.getId().intValue()))
            .andExpect(jsonPath("$.expense_cover").value(DEFAULT_EXPENSE_COVER.toString()))
            .andExpect(jsonPath("$.risk_coverage").value(DEFAULT_RISK_COVERAGE.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRiskLifeInsurance() throws Exception {
        // Get the riskLifeInsurance
        restRiskLifeInsuranceMockMvc.perform(get("/api/risk-life-insurances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiskLifeInsurance() throws Exception {
        // Initialize the database
        riskLifeInsuranceRepository.saveAndFlush(riskLifeInsurance);
        int databaseSizeBeforeUpdate = riskLifeInsuranceRepository.findAll().size();

        // Update the riskLifeInsurance
        RiskLifeInsurance updatedRiskLifeInsurance = riskLifeInsuranceRepository.findOne(riskLifeInsurance.getId());
        // Disconnect from session so that the updates on updatedRiskLifeInsurance are not directly saved in db
        em.detach(updatedRiskLifeInsurance);
        updatedRiskLifeInsurance
            .expense_cover(UPDATED_EXPENSE_COVER)
            .risk_coverage(UPDATED_RISK_COVERAGE)
            .total(UPDATED_TOTAL);
        RiskLifeInsuranceDTO riskLifeInsuranceDTO = riskLifeInsuranceMapper.toDto(updatedRiskLifeInsurance);

        restRiskLifeInsuranceMockMvc.perform(put("/api/risk-life-insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskLifeInsuranceDTO)))
            .andExpect(status().isOk());

        // Validate the RiskLifeInsurance in the database
        List<RiskLifeInsurance> riskLifeInsuranceList = riskLifeInsuranceRepository.findAll();
        assertThat(riskLifeInsuranceList).hasSize(databaseSizeBeforeUpdate);
        RiskLifeInsurance testRiskLifeInsurance = riskLifeInsuranceList.get(riskLifeInsuranceList.size() - 1);
        assertThat(testRiskLifeInsurance.getExpense_cover()).isEqualTo(UPDATED_EXPENSE_COVER);
        assertThat(testRiskLifeInsurance.getRisk_coverage()).isEqualTo(UPDATED_RISK_COVERAGE);
        assertThat(testRiskLifeInsurance.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingRiskLifeInsurance() throws Exception {
        int databaseSizeBeforeUpdate = riskLifeInsuranceRepository.findAll().size();

        // Create the RiskLifeInsurance
        RiskLifeInsuranceDTO riskLifeInsuranceDTO = riskLifeInsuranceMapper.toDto(riskLifeInsurance);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRiskLifeInsuranceMockMvc.perform(put("/api/risk-life-insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskLifeInsuranceDTO)))
            .andExpect(status().isCreated());

        // Validate the RiskLifeInsurance in the database
        List<RiskLifeInsurance> riskLifeInsuranceList = riskLifeInsuranceRepository.findAll();
        assertThat(riskLifeInsuranceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRiskLifeInsurance() throws Exception {
        // Initialize the database
        riskLifeInsuranceRepository.saveAndFlush(riskLifeInsurance);
        int databaseSizeBeforeDelete = riskLifeInsuranceRepository.findAll().size();

        // Get the riskLifeInsurance
        restRiskLifeInsuranceMockMvc.perform(delete("/api/risk-life-insurances/{id}", riskLifeInsurance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RiskLifeInsurance> riskLifeInsuranceList = riskLifeInsuranceRepository.findAll();
        assertThat(riskLifeInsuranceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskLifeInsurance.class);
        RiskLifeInsurance riskLifeInsurance1 = new RiskLifeInsurance();
        riskLifeInsurance1.setId(1L);
        RiskLifeInsurance riskLifeInsurance2 = new RiskLifeInsurance();
        riskLifeInsurance2.setId(riskLifeInsurance1.getId());
        assertThat(riskLifeInsurance1).isEqualTo(riskLifeInsurance2);
        riskLifeInsurance2.setId(2L);
        assertThat(riskLifeInsurance1).isNotEqualTo(riskLifeInsurance2);
        riskLifeInsurance1.setId(null);
        assertThat(riskLifeInsurance1).isNotEqualTo(riskLifeInsurance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskLifeInsuranceDTO.class);
        RiskLifeInsuranceDTO riskLifeInsuranceDTO1 = new RiskLifeInsuranceDTO();
        riskLifeInsuranceDTO1.setId(1L);
        RiskLifeInsuranceDTO riskLifeInsuranceDTO2 = new RiskLifeInsuranceDTO();
        assertThat(riskLifeInsuranceDTO1).isNotEqualTo(riskLifeInsuranceDTO2);
        riskLifeInsuranceDTO2.setId(riskLifeInsuranceDTO1.getId());
        assertThat(riskLifeInsuranceDTO1).isEqualTo(riskLifeInsuranceDTO2);
        riskLifeInsuranceDTO2.setId(2L);
        assertThat(riskLifeInsuranceDTO1).isNotEqualTo(riskLifeInsuranceDTO2);
        riskLifeInsuranceDTO1.setId(null);
        assertThat(riskLifeInsuranceDTO1).isNotEqualTo(riskLifeInsuranceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(riskLifeInsuranceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(riskLifeInsuranceMapper.fromId(null)).isNull();
    }
}
