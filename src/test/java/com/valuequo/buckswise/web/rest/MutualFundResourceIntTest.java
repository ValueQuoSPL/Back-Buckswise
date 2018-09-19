package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.repository.MutualFundRepository;
import com.valuequo.buckswise.service.MutualFundService;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
import com.valuequo.buckswise.service.mapper.MutualFundMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.valuequo.buckswise.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MutualFundResource REST controller.
 *
 * @see MutualFundResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class MutualFundResourceIntTest {

    private static final String DEFAULT_FUND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PURCHASE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PURCHASE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NO_OF_UNITS = "AAAAAAAAAA";
    private static final String UPDATED_NO_OF_UNITS = "BBBBBBBBBB";

    private static final String DEFAULT_NAV = "AAAAAAAAAA";
    private static final String UPDATED_NAV = "BBBBBBBBBB";

    @Autowired
    private MutualFundRepository mutualFundRepository;

    @Autowired
    private MutualFundMapper mutualFundMapper;

    @Autowired
    private MutualFundService mutualFundService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMutualFundMockMvc;

    private MutualFund mutualFund;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MutualFundResource mutualFundResource = new MutualFundResource(mutualFundService);
        this.restMutualFundMockMvc = MockMvcBuilders.standaloneSetup(mutualFundResource)
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
    public static MutualFund createEntity(EntityManager em) {
        MutualFund mutualFund = new MutualFund()
            .fund_name(DEFAULT_FUND_NAME)
            .investor_name(DEFAULT_INVESTOR_NAME)
            .purchase_date(DEFAULT_PURCHASE_DATE)
            .no_of_units(DEFAULT_NO_OF_UNITS)
            .nav(DEFAULT_NAV);
        return mutualFund;
    }

    @Before
    public void initTest() {
        mutualFund = createEntity(em);
    }

    @Test
    @Transactional
    public void createMutualFund() throws Exception {
        int databaseSizeBeforeCreate = mutualFundRepository.findAll().size();

        // Create the MutualFund
        MutualFundDTO mutualFundDTO = mutualFundMapper.toDto(mutualFund);
        restMutualFundMockMvc.perform(post("/api/mutual-funds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mutualFundDTO)))
            .andExpect(status().isCreated());

        // Validate the MutualFund in the database
        List<MutualFund> mutualFundList = mutualFundRepository.findAll();
        assertThat(mutualFundList).hasSize(databaseSizeBeforeCreate + 1);
        MutualFund testMutualFund = mutualFundList.get(mutualFundList.size() - 1);
        assertThat(testMutualFund.getFund_name()).isEqualTo(DEFAULT_FUND_NAME);
        assertThat(testMutualFund.getInvestor_name()).isEqualTo(DEFAULT_INVESTOR_NAME);
        assertThat(testMutualFund.getPurchase_date()).isEqualTo(DEFAULT_PURCHASE_DATE);
        assertThat(testMutualFund.getNo_of_units()).isEqualTo(DEFAULT_NO_OF_UNITS);
        assertThat(testMutualFund.getNav()).isEqualTo(DEFAULT_NAV);
    }

    @Test
    @Transactional
    public void createMutualFundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mutualFundRepository.findAll().size();

        // Create the MutualFund with an existing ID
        mutualFund.setId(1L);
        MutualFundDTO mutualFundDTO = mutualFundMapper.toDto(mutualFund);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMutualFundMockMvc.perform(post("/api/mutual-funds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mutualFundDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MutualFund in the database
        List<MutualFund> mutualFundList = mutualFundRepository.findAll();
        assertThat(mutualFundList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMutualFunds() throws Exception {
        // Initialize the database
        mutualFundRepository.saveAndFlush(mutualFund);

        // Get all the mutualFundList
        restMutualFundMockMvc.perform(get("/api/mutual-funds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mutualFund.getId().intValue())))
            .andExpect(jsonPath("$.[*].fund_name").value(hasItem(DEFAULT_FUND_NAME.toString())))
            .andExpect(jsonPath("$.[*].investor_name").value(hasItem(DEFAULT_INVESTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].purchase_date").value(hasItem(DEFAULT_PURCHASE_DATE.toString())))
            .andExpect(jsonPath("$.[*].no_of_units").value(hasItem(DEFAULT_NO_OF_UNITS.toString())))
            .andExpect(jsonPath("$.[*].nav").value(hasItem(DEFAULT_NAV.toString())));
    }

    @Test
    @Transactional
    public void getMutualFund() throws Exception {
        // Initialize the database
        mutualFundRepository.saveAndFlush(mutualFund);

        // Get the mutualFund
        restMutualFundMockMvc.perform(get("/api/mutual-funds/{id}", mutualFund.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mutualFund.getId().intValue()))
            .andExpect(jsonPath("$.fund_name").value(DEFAULT_FUND_NAME.toString()))
            .andExpect(jsonPath("$.investor_name").value(DEFAULT_INVESTOR_NAME.toString()))
            .andExpect(jsonPath("$.purchase_date").value(DEFAULT_PURCHASE_DATE.toString()))
            .andExpect(jsonPath("$.no_of_units").value(DEFAULT_NO_OF_UNITS.toString()))
            .andExpect(jsonPath("$.nav").value(DEFAULT_NAV.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMutualFund() throws Exception {
        // Get the mutualFund
        restMutualFundMockMvc.perform(get("/api/mutual-funds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMutualFund() throws Exception {
        // Initialize the database
        mutualFundRepository.saveAndFlush(mutualFund);
        int databaseSizeBeforeUpdate = mutualFundRepository.findAll().size();

        // Update the mutualFund
        MutualFund updatedMutualFund = mutualFundRepository.findOne(mutualFund.getId());
        // Disconnect from session so that the updates on updatedMutualFund are not directly saved in db
        em.detach(updatedMutualFund);
        updatedMutualFund
            .fund_name(UPDATED_FUND_NAME)
            .investor_name(UPDATED_INVESTOR_NAME)
            .purchase_date(UPDATED_PURCHASE_DATE)
            .no_of_units(UPDATED_NO_OF_UNITS)
            .nav(UPDATED_NAV);
        MutualFundDTO mutualFundDTO = mutualFundMapper.toDto(updatedMutualFund);

        restMutualFundMockMvc.perform(put("/api/mutual-funds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mutualFundDTO)))
            .andExpect(status().isOk());

        // Validate the MutualFund in the database
        List<MutualFund> mutualFundList = mutualFundRepository.findAll();
        assertThat(mutualFundList).hasSize(databaseSizeBeforeUpdate);
        MutualFund testMutualFund = mutualFundList.get(mutualFundList.size() - 1);
        assertThat(testMutualFund.getFund_name()).isEqualTo(UPDATED_FUND_NAME);
        assertThat(testMutualFund.getInvestor_name()).isEqualTo(UPDATED_INVESTOR_NAME);
        assertThat(testMutualFund.getPurchase_date()).isEqualTo(UPDATED_PURCHASE_DATE);
        assertThat(testMutualFund.getNo_of_units()).isEqualTo(UPDATED_NO_OF_UNITS);
        assertThat(testMutualFund.getNav()).isEqualTo(UPDATED_NAV);
    }

    @Test
    @Transactional
    public void updateNonExistingMutualFund() throws Exception {
        int databaseSizeBeforeUpdate = mutualFundRepository.findAll().size();

        // Create the MutualFund
        MutualFundDTO mutualFundDTO = mutualFundMapper.toDto(mutualFund);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMutualFundMockMvc.perform(put("/api/mutual-funds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mutualFundDTO)))
            .andExpect(status().isCreated());

        // Validate the MutualFund in the database
        List<MutualFund> mutualFundList = mutualFundRepository.findAll();
        assertThat(mutualFundList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMutualFund() throws Exception {
        // Initialize the database
        mutualFundRepository.saveAndFlush(mutualFund);
        int databaseSizeBeforeDelete = mutualFundRepository.findAll().size();

        // Get the mutualFund
        restMutualFundMockMvc.perform(delete("/api/mutual-funds/{id}", mutualFund.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MutualFund> mutualFundList = mutualFundRepository.findAll();
        assertThat(mutualFundList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MutualFund.class);
        MutualFund mutualFund1 = new MutualFund();
        mutualFund1.setId(1L);
        MutualFund mutualFund2 = new MutualFund();
        mutualFund2.setId(mutualFund1.getId());
        assertThat(mutualFund1).isEqualTo(mutualFund2);
        mutualFund2.setId(2L);
        assertThat(mutualFund1).isNotEqualTo(mutualFund2);
        mutualFund1.setId(null);
        assertThat(mutualFund1).isNotEqualTo(mutualFund2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MutualFundDTO.class);
        MutualFundDTO mutualFundDTO1 = new MutualFundDTO();
        mutualFundDTO1.setId(1L);
        MutualFundDTO mutualFundDTO2 = new MutualFundDTO();
        assertThat(mutualFundDTO1).isNotEqualTo(mutualFundDTO2);
        mutualFundDTO2.setId(mutualFundDTO1.getId());
        assertThat(mutualFundDTO1).isEqualTo(mutualFundDTO2);
        mutualFundDTO2.setId(2L);
        assertThat(mutualFundDTO1).isNotEqualTo(mutualFundDTO2);
        mutualFundDTO1.setId(null);
        assertThat(mutualFundDTO1).isNotEqualTo(mutualFundDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mutualFundMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mutualFundMapper.fromId(null)).isNull();
    }
}
