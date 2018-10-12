package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.AtlernateInvestment;
import com.valuequo.buckswise.repository.AtlernateInvestmentRepository;
import com.valuequo.buckswise.service.AtlernateInvestmentService;
import com.valuequo.buckswise.service.dto.AtlernateInvestmentDTO;
import com.valuequo.buckswise.service.mapper.AtlernateInvestmentMapper;
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
 * Test class for the AtlernateInvestmentResource REST controller.
 *
 * @see AtlernateInvestmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class AtlernateInvestmentResourceIntTest {

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_FUND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_P_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_P_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AMOUNT_INVESTED = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_INVESTED = "BBBBBBBBBB";

    private static final String DEFAULT_MARKET_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_MARKET_VALUE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AS_OF_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AS_OF_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    @Autowired
    private AtlernateInvestmentRepository atlernateInvestmentRepository;

    @Autowired
    private AtlernateInvestmentMapper atlernateInvestmentMapper;

    @Autowired
    private AtlernateInvestmentService atlernateInvestmentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAtlernateInvestmentMockMvc;

    private AtlernateInvestment atlernateInvestment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AtlernateInvestmentResource atlernateInvestmentResource = new AtlernateInvestmentResource(atlernateInvestmentService);
        this.restAtlernateInvestmentMockMvc = MockMvcBuilders.standaloneSetup(atlernateInvestmentResource)
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
    public static AtlernateInvestment createEntity(EntityManager em) {
        AtlernateInvestment atlernateInvestment = new AtlernateInvestment()
            .num(DEFAULT_NUM)
            .fund_name(DEFAULT_FUND_NAME)
            .investor_name(DEFAULT_INVESTOR_NAME)
            .p_date(DEFAULT_P_DATE)
            .amount_invested(DEFAULT_AMOUNT_INVESTED)
            .market_value(DEFAULT_MARKET_VALUE)
            .as_of_date(DEFAULT_AS_OF_DATE)
            .notes(DEFAULT_NOTES)
            .type(DEFAULT_TYPE)
            .userId(DEFAULT_USER_ID);
        return atlernateInvestment;
    }

    @Before
    public void initTest() {
        atlernateInvestment = createEntity(em);
    }

    @Test
    @Transactional
    public void createAtlernateInvestment() throws Exception {
        int databaseSizeBeforeCreate = atlernateInvestmentRepository.findAll().size();

        // Create the AtlernateInvestment
        AtlernateInvestmentDTO atlernateInvestmentDTO = atlernateInvestmentMapper.toDto(atlernateInvestment);
        restAtlernateInvestmentMockMvc.perform(post("/api/atlernate-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atlernateInvestmentDTO)))
            .andExpect(status().isCreated());

        // Validate the AtlernateInvestment in the database
        List<AtlernateInvestment> atlernateInvestmentList = atlernateInvestmentRepository.findAll();
        assertThat(atlernateInvestmentList).hasSize(databaseSizeBeforeCreate + 1);
        AtlernateInvestment testAtlernateInvestment = atlernateInvestmentList.get(atlernateInvestmentList.size() - 1);
        assertThat(testAtlernateInvestment.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testAtlernateInvestment.getFund_name()).isEqualTo(DEFAULT_FUND_NAME);
        assertThat(testAtlernateInvestment.getInvestor_name()).isEqualTo(DEFAULT_INVESTOR_NAME);
        assertThat(testAtlernateInvestment.getp_date()).isEqualTo(DEFAULT_P_DATE);
        assertThat(testAtlernateInvestment.getAmount_invested()).isEqualTo(DEFAULT_AMOUNT_INVESTED);
        assertThat(testAtlernateInvestment.getMarket_value()).isEqualTo(DEFAULT_MARKET_VALUE);
        assertThat(testAtlernateInvestment.getAs_of_date()).isEqualTo(DEFAULT_AS_OF_DATE);
        assertThat(testAtlernateInvestment.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testAtlernateInvestment.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAtlernateInvestment.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createAtlernateInvestmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = atlernateInvestmentRepository.findAll().size();

        // Create the AtlernateInvestment with an existing ID
        atlernateInvestment.setId(1L);
        AtlernateInvestmentDTO atlernateInvestmentDTO = atlernateInvestmentMapper.toDto(atlernateInvestment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAtlernateInvestmentMockMvc.perform(post("/api/atlernate-investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atlernateInvestmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AtlernateInvestment in the database
        List<AtlernateInvestment> atlernateInvestmentList = atlernateInvestmentRepository.findAll();
        assertThat(atlernateInvestmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAtlernateInvestments() throws Exception {
        // Initialize the database
        atlernateInvestmentRepository.saveAndFlush(atlernateInvestment);

        // Get all the atlernateInvestmentList
        restAtlernateInvestmentMockMvc.perform(get("/api/atlernate-investments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(atlernateInvestment.getId().intValue())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
            .andExpect(jsonPath("$.[*].fund_name").value(hasItem(DEFAULT_FUND_NAME.toString())))
            .andExpect(jsonPath("$.[*].investor_name").value(hasItem(DEFAULT_INVESTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].p_date").value(hasItem(DEFAULT_P_DATE.toString())))
            .andExpect(jsonPath("$.[*].amount_invested").value(hasItem(DEFAULT_AMOUNT_INVESTED.toString())))
            .andExpect(jsonPath("$.[*].market_value").value(hasItem(DEFAULT_MARKET_VALUE.toString())))
            .andExpect(jsonPath("$.[*].as_of_date").value(hasItem(DEFAULT_AS_OF_DATE.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())));
    }

    @Test
    @Transactional
    public void getAtlernateInvestment() throws Exception {
        // Initialize the database
        atlernateInvestmentRepository.saveAndFlush(atlernateInvestment);

        // Get the atlernateInvestment
        restAtlernateInvestmentMockMvc.perform(get("/api/atlernateInvest/{id}", atlernateInvestment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(atlernateInvestment.getId().intValue()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
            .andExpect(jsonPath("$.fund_name").value(DEFAULT_FUND_NAME.toString()))
            .andExpect(jsonPath("$.investor_name").value(DEFAULT_INVESTOR_NAME.toString()))
            .andExpect(jsonPath("$.p_date").value(DEFAULT_P_DATE.toString()))
            .andExpect(jsonPath("$.amount_invested").value(DEFAULT_AMOUNT_INVESTED.toString()))
            .andExpect(jsonPath("$.market_value").value(DEFAULT_MARKET_VALUE.toString()))
            .andExpect(jsonPath("$.as_of_date").value(DEFAULT_AS_OF_DATE.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAtlernateInvestment() throws Exception {
        // Get the atlernateInvestment
        restAtlernateInvestmentMockMvc.perform(get("/api/atlernate-investments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAtlernateInvestment() throws Exception {
        // Initialize the database
        atlernateInvestmentRepository.saveAndFlush(atlernateInvestment);
        int databaseSizeBeforeUpdate = atlernateInvestmentRepository.findAll().size();

        // Update the atlernateInvestment
        AtlernateInvestment updatedAtlernateInvestment = atlernateInvestmentRepository.findOne(atlernateInvestment.getId());
        // Disconnect from session so that the updates on updatedAtlernateInvestment are not directly saved in db
        em.detach(updatedAtlernateInvestment);
        updatedAtlernateInvestment
            .num(UPDATED_NUM)
            .fund_name(UPDATED_FUND_NAME)
            .investor_name(UPDATED_INVESTOR_NAME)
            .p_date(UPDATED_P_DATE)
            .amount_invested(UPDATED_AMOUNT_INVESTED)
            .market_value(UPDATED_MARKET_VALUE)
            .as_of_date(UPDATED_AS_OF_DATE)
            .notes(UPDATED_NOTES)
            .type(UPDATED_TYPE)
            .userId(UPDATED_USER_ID);
        AtlernateInvestmentDTO atlernateInvestmentDTO = atlernateInvestmentMapper.toDto(updatedAtlernateInvestment);

        restAtlernateInvestmentMockMvc.perform(put("/api/atlernateInvestments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atlernateInvestmentDTO)))
            .andExpect(status().isOk());

        // Validate the AtlernateInvestment in the database
        List<AtlernateInvestment> atlernateInvestmentList = atlernateInvestmentRepository.findAll();
        assertThat(atlernateInvestmentList).hasSize(databaseSizeBeforeUpdate);
        AtlernateInvestment testAtlernateInvestment = atlernateInvestmentList.get(atlernateInvestmentList.size() - 1);
        assertThat(testAtlernateInvestment.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testAtlernateInvestment.getFund_name()).isEqualTo(UPDATED_FUND_NAME);
        assertThat(testAtlernateInvestment.getInvestor_name()).isEqualTo(UPDATED_INVESTOR_NAME);
        assertThat(testAtlernateInvestment.getp_date()).isEqualTo(UPDATED_P_DATE);
        assertThat(testAtlernateInvestment.getAmount_invested()).isEqualTo(UPDATED_AMOUNT_INVESTED);
        assertThat(testAtlernateInvestment.getMarket_value()).isEqualTo(UPDATED_MARKET_VALUE);
        assertThat(testAtlernateInvestment.getAs_of_date()).isEqualTo(UPDATED_AS_OF_DATE);
        assertThat(testAtlernateInvestment.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testAtlernateInvestment.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAtlernateInvestment.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAtlernateInvestment() throws Exception {
        int databaseSizeBeforeUpdate = atlernateInvestmentRepository.findAll().size();

        // Create the AtlernateInvestment
        AtlernateInvestmentDTO atlernateInvestmentDTO = atlernateInvestmentMapper.toDto(atlernateInvestment);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAtlernateInvestmentMockMvc.perform(put("/api/atlernateInvestments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atlernateInvestmentDTO)))
            .andExpect(status().isCreated());

        // Validate the AtlernateInvestment in the database
        List<AtlernateInvestment> atlernateInvestmentList = atlernateInvestmentRepository.findAll();
        assertThat(atlernateInvestmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAtlernateInvestment() throws Exception {
        // Initialize the database
        atlernateInvestmentRepository.saveAndFlush(atlernateInvestment);
        int databaseSizeBeforeDelete = atlernateInvestmentRepository.findAll().size();

        // Get the atlernateInvestment
        restAtlernateInvestmentMockMvc.perform(delete("/api/atlerInvest/{id}", atlernateInvestment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AtlernateInvestment> atlernateInvestmentList = atlernateInvestmentRepository.findAll();
        assertThat(atlernateInvestmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AtlernateInvestment.class);
        AtlernateInvestment atlernateInvestment1 = new AtlernateInvestment();
        atlernateInvestment1.setId(1L);
        AtlernateInvestment atlernateInvestment2 = new AtlernateInvestment();
        atlernateInvestment2.setId(atlernateInvestment1.getId());
        assertThat(atlernateInvestment1).isEqualTo(atlernateInvestment2);
        atlernateInvestment2.setId(2L);
        assertThat(atlernateInvestment1).isNotEqualTo(atlernateInvestment2);
        atlernateInvestment1.setId(null);
        assertThat(atlernateInvestment1).isNotEqualTo(atlernateInvestment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AtlernateInvestmentDTO.class);
        AtlernateInvestmentDTO atlernateInvestmentDTO1 = new AtlernateInvestmentDTO();
        atlernateInvestmentDTO1.setId(1L);
        AtlernateInvestmentDTO atlernateInvestmentDTO2 = new AtlernateInvestmentDTO();
        assertThat(atlernateInvestmentDTO1).isNotEqualTo(atlernateInvestmentDTO2);
        atlernateInvestmentDTO2.setId(atlernateInvestmentDTO1.getId());
        assertThat(atlernateInvestmentDTO1).isEqualTo(atlernateInvestmentDTO2);
        atlernateInvestmentDTO2.setId(2L);
        assertThat(atlernateInvestmentDTO1).isNotEqualTo(atlernateInvestmentDTO2);
        atlernateInvestmentDTO1.setId(null);
        assertThat(atlernateInvestmentDTO1).isNotEqualTo(atlernateInvestmentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(atlernateInvestmentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(atlernateInvestmentMapper.fromId(null)).isNull();
    }
}
