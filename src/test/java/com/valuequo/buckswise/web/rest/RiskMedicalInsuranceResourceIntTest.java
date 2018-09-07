package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.RiskMedicalInsurance;
import com.valuequo.buckswise.repository.RiskMedicalInsuranceRepository;
import com.valuequo.buckswise.service.RiskMedicalInsuranceService;
import com.valuequo.buckswise.service.dto.RiskMedicalInsuranceDTO;
import com.valuequo.buckswise.service.mapper.RiskMedicalInsuranceMapper;
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
 * Test class for the RiskMedicalInsuranceResource REST controller.
 *
 * @see RiskMedicalInsuranceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class RiskMedicalInsuranceResourceIntTest {

    private static final String DEFAULT_FAMILY_MEMBERS = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_MEMBERS = "BBBBBBBBBB";

    private static final String DEFAULT_HOSP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_HOSP_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ROOM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ROOM_TYPE = "BBBBBBBBBB";

    @Autowired
    private RiskMedicalInsuranceRepository riskMedicalInsuranceRepository;

    @Autowired
    private RiskMedicalInsuranceMapper riskMedicalInsuranceMapper;

    @Autowired
    private RiskMedicalInsuranceService riskMedicalInsuranceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRiskMedicalInsuranceMockMvc;

    private RiskMedicalInsurance riskMedicalInsurance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RiskMedicalInsuranceResource riskMedicalInsuranceResource = new RiskMedicalInsuranceResource(riskMedicalInsuranceService);
        this.restRiskMedicalInsuranceMockMvc = MockMvcBuilders.standaloneSetup(riskMedicalInsuranceResource)
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
    public static RiskMedicalInsurance createEntity(EntityManager em) {
        RiskMedicalInsurance riskMedicalInsurance = new RiskMedicalInsurance()
            .family_members(DEFAULT_FAMILY_MEMBERS)
            .hosp_type(DEFAULT_HOSP_TYPE)
            .room_type(DEFAULT_ROOM_TYPE);
        return riskMedicalInsurance;
    }

    @Before
    public void initTest() {
        riskMedicalInsurance = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiskMedicalInsurance() throws Exception {
        int databaseSizeBeforeCreate = riskMedicalInsuranceRepository.findAll().size();

        // Create the RiskMedicalInsurance
        RiskMedicalInsuranceDTO riskMedicalInsuranceDTO = riskMedicalInsuranceMapper.toDto(riskMedicalInsurance);
        restRiskMedicalInsuranceMockMvc.perform(post("/api/risk-medical-insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskMedicalInsuranceDTO)))
            .andExpect(status().isCreated());

        // Validate the RiskMedicalInsurance in the database
        List<RiskMedicalInsurance> riskMedicalInsuranceList = riskMedicalInsuranceRepository.findAll();
        assertThat(riskMedicalInsuranceList).hasSize(databaseSizeBeforeCreate + 1);
        RiskMedicalInsurance testRiskMedicalInsurance = riskMedicalInsuranceList.get(riskMedicalInsuranceList.size() - 1);
        assertThat(testRiskMedicalInsurance.getFamily_members()).isEqualTo(DEFAULT_FAMILY_MEMBERS);
        assertThat(testRiskMedicalInsurance.getHosp_type()).isEqualTo(DEFAULT_HOSP_TYPE);
        assertThat(testRiskMedicalInsurance.getRoom_type()).isEqualTo(DEFAULT_ROOM_TYPE);
    }

    @Test
    @Transactional
    public void createRiskMedicalInsuranceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskMedicalInsuranceRepository.findAll().size();

        // Create the RiskMedicalInsurance with an existing ID
        riskMedicalInsurance.setId(1L);
        RiskMedicalInsuranceDTO riskMedicalInsuranceDTO = riskMedicalInsuranceMapper.toDto(riskMedicalInsurance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskMedicalInsuranceMockMvc.perform(post("/api/risk-medical-insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskMedicalInsuranceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RiskMedicalInsurance in the database
        List<RiskMedicalInsurance> riskMedicalInsuranceList = riskMedicalInsuranceRepository.findAll();
        assertThat(riskMedicalInsuranceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRiskMedicalInsurances() throws Exception {
        // Initialize the database
        riskMedicalInsuranceRepository.saveAndFlush(riskMedicalInsurance);

        // Get all the riskMedicalInsuranceList
        restRiskMedicalInsuranceMockMvc.perform(get("/api/risk-medical-insurances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskMedicalInsurance.getId().intValue())))
            .andExpect(jsonPath("$.[*].family_members").value(hasItem(DEFAULT_FAMILY_MEMBERS.toString())))
            .andExpect(jsonPath("$.[*].hosp_type").value(hasItem(DEFAULT_HOSP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].room_type").value(hasItem(DEFAULT_ROOM_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getRiskMedicalInsurance() throws Exception {
        // Initialize the database
        riskMedicalInsuranceRepository.saveAndFlush(riskMedicalInsurance);

        // Get the riskMedicalInsurance
        restRiskMedicalInsuranceMockMvc.perform(get("/api/risk-medical-insurances/{id}", riskMedicalInsurance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(riskMedicalInsurance.getId().intValue()))
            .andExpect(jsonPath("$.family_members").value(DEFAULT_FAMILY_MEMBERS.toString()))
            .andExpect(jsonPath("$.hosp_type").value(DEFAULT_HOSP_TYPE.toString()))
            .andExpect(jsonPath("$.room_type").value(DEFAULT_ROOM_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRiskMedicalInsurance() throws Exception {
        // Get the riskMedicalInsurance
        restRiskMedicalInsuranceMockMvc.perform(get("/api/risk-medical-insurances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiskMedicalInsurance() throws Exception {
        // Initialize the database
        riskMedicalInsuranceRepository.saveAndFlush(riskMedicalInsurance);
        int databaseSizeBeforeUpdate = riskMedicalInsuranceRepository.findAll().size();

        // Update the riskMedicalInsurance
        RiskMedicalInsurance updatedRiskMedicalInsurance = riskMedicalInsuranceRepository.findOne(riskMedicalInsurance.getId());
        // Disconnect from session so that the updates on updatedRiskMedicalInsurance are not directly saved in db
        em.detach(updatedRiskMedicalInsurance);
        updatedRiskMedicalInsurance
            .family_members(UPDATED_FAMILY_MEMBERS)
            .hosp_type(UPDATED_HOSP_TYPE)
            .room_type(UPDATED_ROOM_TYPE);
        RiskMedicalInsuranceDTO riskMedicalInsuranceDTO = riskMedicalInsuranceMapper.toDto(updatedRiskMedicalInsurance);

        restRiskMedicalInsuranceMockMvc.perform(put("/api/risk-medical-insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskMedicalInsuranceDTO)))
            .andExpect(status().isOk());

        // Validate the RiskMedicalInsurance in the database
        List<RiskMedicalInsurance> riskMedicalInsuranceList = riskMedicalInsuranceRepository.findAll();
        assertThat(riskMedicalInsuranceList).hasSize(databaseSizeBeforeUpdate);
        RiskMedicalInsurance testRiskMedicalInsurance = riskMedicalInsuranceList.get(riskMedicalInsuranceList.size() - 1);
        assertThat(testRiskMedicalInsurance.getFamily_members()).isEqualTo(UPDATED_FAMILY_MEMBERS);
        assertThat(testRiskMedicalInsurance.getHosp_type()).isEqualTo(UPDATED_HOSP_TYPE);
        assertThat(testRiskMedicalInsurance.getRoom_type()).isEqualTo(UPDATED_ROOM_TYPE);
        }

    @Test
    @Transactional
    public void updateNonExistingRiskMedicalInsurance() throws Exception {
        int databaseSizeBeforeUpdate = riskMedicalInsuranceRepository.findAll().size();

        // Create the RiskMedicalInsurance
        RiskMedicalInsuranceDTO riskMedicalInsuranceDTO = riskMedicalInsuranceMapper.toDto(riskMedicalInsurance);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRiskMedicalInsuranceMockMvc.perform(put("/api/risk-medical-insurances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(riskMedicalInsuranceDTO)))
            .andExpect(status().isCreated());

        // Validate the RiskMedicalInsurance in the database
        List<RiskMedicalInsurance> riskMedicalInsuranceList = riskMedicalInsuranceRepository.findAll();
        assertThat(riskMedicalInsuranceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRiskMedicalInsurance() throws Exception {
        // Initialize the database
        riskMedicalInsuranceRepository.saveAndFlush(riskMedicalInsurance);
        int databaseSizeBeforeDelete = riskMedicalInsuranceRepository.findAll().size();

        // Get the riskMedicalInsurance
        restRiskMedicalInsuranceMockMvc.perform(delete("/api/risk-medical-insurances/{id}", riskMedicalInsurance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RiskMedicalInsurance> riskMedicalInsuranceList = riskMedicalInsuranceRepository.findAll();
        assertThat(riskMedicalInsuranceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskMedicalInsurance.class);
        RiskMedicalInsurance riskMedicalInsurance1 = new RiskMedicalInsurance();
        riskMedicalInsurance1.setId(1L);
        RiskMedicalInsurance riskMedicalInsurance2 = new RiskMedicalInsurance();
        riskMedicalInsurance2.setId(riskMedicalInsurance1.getId());
        assertThat(riskMedicalInsurance1).isEqualTo(riskMedicalInsurance2);
        riskMedicalInsurance2.setId(2L);
        assertThat(riskMedicalInsurance1).isNotEqualTo(riskMedicalInsurance2);
        riskMedicalInsurance1.setId(null);
        assertThat(riskMedicalInsurance1).isNotEqualTo(riskMedicalInsurance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskMedicalInsuranceDTO.class);
        RiskMedicalInsuranceDTO riskMedicalInsuranceDTO1 = new RiskMedicalInsuranceDTO();
        riskMedicalInsuranceDTO1.setId(1L);
        RiskMedicalInsuranceDTO riskMedicalInsuranceDTO2 = new RiskMedicalInsuranceDTO();
        assertThat(riskMedicalInsuranceDTO1).isNotEqualTo(riskMedicalInsuranceDTO2);
        riskMedicalInsuranceDTO2.setId(riskMedicalInsuranceDTO1.getId());
        assertThat(riskMedicalInsuranceDTO1).isEqualTo(riskMedicalInsuranceDTO2);
        riskMedicalInsuranceDTO2.setId(2L);
        assertThat(riskMedicalInsuranceDTO1).isNotEqualTo(riskMedicalInsuranceDTO2);
        riskMedicalInsuranceDTO1.setId(null);
        assertThat(riskMedicalInsuranceDTO1).isNotEqualTo(riskMedicalInsuranceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(riskMedicalInsuranceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(riskMedicalInsuranceMapper.fromId(null)).isNull();
    }
}
