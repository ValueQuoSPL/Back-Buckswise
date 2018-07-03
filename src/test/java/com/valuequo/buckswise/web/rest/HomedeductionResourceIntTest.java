package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseBackEndApp;

import com.valuequo.buckswise.domain.Homededuction;
import com.valuequo.buckswise.repository.HomedeductionRepository;
import com.valuequo.buckswise.service.HomedeductionService;
import com.valuequo.buckswise.service.dto.HomedeductionDTO;
import com.valuequo.buckswise.service.mapper.HomedeductionMapper;
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
 * Test class for the HomedeductionResource REST controller.
 *
 * @see HomedeductionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseBackEndApp.class)
public class HomedeductionResourceIntTest {

    private static final Integer DEFAULT_HOMELOAN = 1;
    private static final Integer UPDATED_HOMELOAN = 2;

    private static final Integer DEFAULT_PRNCPALLOAN = 1;
    private static final Integer UPDATED_PRNCPALLOAN = 2;

    private static final Integer DEFAULT_RENTCLM = 1;
    private static final Integer UPDATED_RENTCLM = 2;

    private static final Integer DEFAULT_REMINTRST = 1;
    private static final Integer UPDATED_REMINTRST = 2;

    private static final Integer DEFAULT_RENTCLMGG = 1;
    private static final Integer UPDATED_RENTCLMGG = 2;

    @Autowired
    private HomedeductionRepository homedeductionRepository;

    @Autowired
    private HomedeductionMapper homedeductionMapper;

    @Autowired
    private HomedeductionService homedeductionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHomedeductionMockMvc;

    private Homededuction homededuction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HomedeductionResource homedeductionResource = new HomedeductionResource(homedeductionService);
        this.restHomedeductionMockMvc = MockMvcBuilders.standaloneSetup(homedeductionResource)
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
    public static Homededuction createEntity(EntityManager em) {
        Homededuction homededuction = new Homededuction()
            .homeloan(DEFAULT_HOMELOAN)
            .prncpalloan(DEFAULT_PRNCPALLOAN)
            .rentclm(DEFAULT_RENTCLM)
            .remintrst(DEFAULT_REMINTRST)
            .rentclmgg(DEFAULT_RENTCLMGG);
        return homededuction;
    }

    @Before
    public void initTest() {
        homededuction = createEntity(em);
    }

    @Test
    @Transactional
    public void createHomededuction() throws Exception {
        int databaseSizeBeforeCreate = homedeductionRepository.findAll().size();

        // Create the Homededuction
        HomedeductionDTO homedeductionDTO = homedeductionMapper.toDto(homededuction);
        restHomedeductionMockMvc.perform(post("/api/homedeductions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(homedeductionDTO)))
            .andExpect(status().isCreated());

        // Validate the Homededuction in the database
        List<Homededuction> homedeductionList = homedeductionRepository.findAll();
        assertThat(homedeductionList).hasSize(databaseSizeBeforeCreate + 1);
        Homededuction testHomededuction = homedeductionList.get(homedeductionList.size() - 1);
        assertThat(testHomededuction.getHomeloan()).isEqualTo(DEFAULT_HOMELOAN);
        assertThat(testHomededuction.getPrncpalloan()).isEqualTo(DEFAULT_PRNCPALLOAN);
        assertThat(testHomededuction.getRentclm()).isEqualTo(DEFAULT_RENTCLM);
        assertThat(testHomededuction.getRemintrst()).isEqualTo(DEFAULT_REMINTRST);
        assertThat(testHomededuction.getRentclmgg()).isEqualTo(DEFAULT_RENTCLMGG);
    }

    @Test
    @Transactional
    public void createHomedeductionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = homedeductionRepository.findAll().size();

        // Create the Homededuction with an existing ID
        homededuction.setId(1L);
        HomedeductionDTO homedeductionDTO = homedeductionMapper.toDto(homededuction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHomedeductionMockMvc.perform(post("/api/homedeductions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(homedeductionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Homededuction in the database
        List<Homededuction> homedeductionList = homedeductionRepository.findAll();
        assertThat(homedeductionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHomedeductions() throws Exception {
        // Initialize the database
        homedeductionRepository.saveAndFlush(homededuction);

        // Get all the homedeductionList
        restHomedeductionMockMvc.perform(get("/api/homedeductions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(homededuction.getId().intValue())))
            .andExpect(jsonPath("$.[*].homeloan").value(hasItem(DEFAULT_HOMELOAN)))
            .andExpect(jsonPath("$.[*].prncpalloan").value(hasItem(DEFAULT_PRNCPALLOAN)))
            .andExpect(jsonPath("$.[*].rentclm").value(hasItem(DEFAULT_RENTCLM)))
            .andExpect(jsonPath("$.[*].remintrst").value(hasItem(DEFAULT_REMINTRST)))
            .andExpect(jsonPath("$.[*].rentclmgg").value(hasItem(DEFAULT_RENTCLMGG)));
    }

    @Test
    @Transactional
    public void getHomededuction() throws Exception {
        // Initialize the database
        homedeductionRepository.saveAndFlush(homededuction);

        // Get the homededuction
        restHomedeductionMockMvc.perform(get("/api/homedeductions/{id}", homededuction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(homededuction.getId().intValue()))
            .andExpect(jsonPath("$.homeloan").value(DEFAULT_HOMELOAN))
            .andExpect(jsonPath("$.prncpalloan").value(DEFAULT_PRNCPALLOAN))
            .andExpect(jsonPath("$.rentclm").value(DEFAULT_RENTCLM))
            .andExpect(jsonPath("$.remintrst").value(DEFAULT_REMINTRST))
            .andExpect(jsonPath("$.rentclmgg").value(DEFAULT_RENTCLMGG));
    }

    @Test
    @Transactional
    public void getNonExistingHomededuction() throws Exception {
        // Get the homededuction
        restHomedeductionMockMvc.perform(get("/api/homedeductions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHomededuction() throws Exception {
        // Initialize the database
        homedeductionRepository.saveAndFlush(homededuction);
        int databaseSizeBeforeUpdate = homedeductionRepository.findAll().size();

        // Update the homededuction
        Homededuction updatedHomededuction = homedeductionRepository.findOne(homededuction.getId());
        // Disconnect from session so that the updates on updatedHomededuction are not directly saved in db
        em.detach(updatedHomededuction);
        updatedHomededuction
            .homeloan(UPDATED_HOMELOAN)
            .prncpalloan(UPDATED_PRNCPALLOAN)
            .rentclm(UPDATED_RENTCLM)
            .remintrst(UPDATED_REMINTRST)
            .rentclmgg(UPDATED_RENTCLMGG);
        HomedeductionDTO homedeductionDTO = homedeductionMapper.toDto(updatedHomededuction);

        restHomedeductionMockMvc.perform(put("/api/homedeductions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(homedeductionDTO)))
            .andExpect(status().isOk());

        // Validate the Homededuction in the database
        List<Homededuction> homedeductionList = homedeductionRepository.findAll();
        assertThat(homedeductionList).hasSize(databaseSizeBeforeUpdate);
        Homededuction testHomededuction = homedeductionList.get(homedeductionList.size() - 1);
        assertThat(testHomededuction.getHomeloan()).isEqualTo(UPDATED_HOMELOAN);
        assertThat(testHomededuction.getPrncpalloan()).isEqualTo(UPDATED_PRNCPALLOAN);
        assertThat(testHomededuction.getRentclm()).isEqualTo(UPDATED_RENTCLM);
        assertThat(testHomededuction.getRemintrst()).isEqualTo(UPDATED_REMINTRST);
        assertThat(testHomededuction.getRentclmgg()).isEqualTo(UPDATED_RENTCLMGG);
    }

    @Test
    @Transactional
    public void updateNonExistingHomededuction() throws Exception {
        int databaseSizeBeforeUpdate = homedeductionRepository.findAll().size();

        // Create the Homededuction
        HomedeductionDTO homedeductionDTO = homedeductionMapper.toDto(homededuction);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHomedeductionMockMvc.perform(put("/api/homedeductions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(homedeductionDTO)))
            .andExpect(status().isCreated());

        // Validate the Homededuction in the database
        List<Homededuction> homedeductionList = homedeductionRepository.findAll();
        assertThat(homedeductionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteHomededuction() throws Exception {
        // Initialize the database
        homedeductionRepository.saveAndFlush(homededuction);
        int databaseSizeBeforeDelete = homedeductionRepository.findAll().size();

        // Get the homededuction
        restHomedeductionMockMvc.perform(delete("/api/homedeductions/{id}", homededuction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Homededuction> homedeductionList = homedeductionRepository.findAll();
        assertThat(homedeductionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Homededuction.class);
        Homededuction homededuction1 = new Homededuction();
        homededuction1.setId(1L);
        Homededuction homededuction2 = new Homededuction();
        homededuction2.setId(homededuction1.getId());
        assertThat(homededuction1).isEqualTo(homededuction2);
        homededuction2.setId(2L);
        assertThat(homededuction1).isNotEqualTo(homededuction2);
        homededuction1.setId(null);
        assertThat(homededuction1).isNotEqualTo(homededuction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HomedeductionDTO.class);
        HomedeductionDTO homedeductionDTO1 = new HomedeductionDTO();
        homedeductionDTO1.setId(1L);
        HomedeductionDTO homedeductionDTO2 = new HomedeductionDTO();
        assertThat(homedeductionDTO1).isNotEqualTo(homedeductionDTO2);
        homedeductionDTO2.setId(homedeductionDTO1.getId());
        assertThat(homedeductionDTO1).isEqualTo(homedeductionDTO2);
        homedeductionDTO2.setId(2L);
        assertThat(homedeductionDTO1).isNotEqualTo(homedeductionDTO2);
        homedeductionDTO1.setId(null);
        assertThat(homedeductionDTO1).isNotEqualTo(homedeductionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(homedeductionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(homedeductionMapper.fromId(null)).isNull();
    }
}
