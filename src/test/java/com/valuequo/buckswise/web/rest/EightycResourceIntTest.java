package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseBackEndApp;

import com.valuequo.buckswise.domain.Eightyc;
import com.valuequo.buckswise.repository.EightycRepository;
import com.valuequo.buckswise.service.EightycService;
import com.valuequo.buckswise.service.dto.EightycDTO;
import com.valuequo.buckswise.service.mapper.EightycMapper;
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
 * Test class for the EightycResource REST controller.
 *
 * @see EightycResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseBackEndApp.class)
public class EightycResourceIntTest {

    private static final Integer DEFAULT_FIXED = 1;
    private static final Integer UPDATED_FIXED = 2;

    private static final Integer DEFAULT_TUTION = 1;
    private static final Integer UPDATED_TUTION = 2;

    private static final Integer DEFAULT_NSC = 1;
    private static final Integer UPDATED_NSC = 2;

    private static final Integer DEFAULT_NSS = 1;
    private static final Integer UPDATED_NSS = 2;

    private static final Integer DEFAULT_POST = 1;
    private static final Integer UPDATED_POST = 2;

    private static final Integer DEFAULT_REINVEST = 1;
    private static final Integer UPDATED_REINVEST = 2;

    private static final Integer DEFAULT_LICPREMIUM = 1;
    private static final Integer UPDATED_LICPREMIUM = 2;

    private static final Integer DEFAULT_EQUITY = 1;
    private static final Integer UPDATED_EQUITY = 2;

    private static final Integer DEFAULT_PF = 1;
    private static final Integer UPDATED_PF = 2;

    private static final Integer DEFAULT_PPF = 1;
    private static final Integer UPDATED_PPF = 2;

    private static final Integer DEFAULT_OTHER = 1;
    private static final Integer UPDATED_OTHER = 2;

    private static final Integer DEFAULT_TUTIONFEE = 1;
    private static final Integer UPDATED_TUTIONFEE = 2;

    private static final Integer DEFAULT_ULIP = 1;
    private static final Integer UPDATED_ULIP = 2;

    @Autowired
    private EightycRepository eightycRepository;

    @Autowired
    private EightycMapper eightycMapper;

    @Autowired
    private EightycService eightycService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEightycMockMvc;

    private Eightyc eightyc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EightycResource eightycResource = new EightycResource(eightycService);
        this.restEightycMockMvc = MockMvcBuilders.standaloneSetup(eightycResource)
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
    public static Eightyc createEntity(EntityManager em) {
        Eightyc eightyc = new Eightyc()
            .fixed(DEFAULT_FIXED)
            .tution(DEFAULT_TUTION)
            .nsc(DEFAULT_NSC)
            .nss(DEFAULT_NSS)
            .post(DEFAULT_POST)
            .reinvest(DEFAULT_REINVEST)
            .licpremium(DEFAULT_LICPREMIUM)
            .equity(DEFAULT_EQUITY)
            .pf(DEFAULT_PF)
            .ppf(DEFAULT_PPF)
            .other(DEFAULT_OTHER)
            .tutionfee(DEFAULT_TUTIONFEE)
            .ulip(DEFAULT_ULIP);
        return eightyc;
    }

    @Before
    public void initTest() {
        eightyc = createEntity(em);
    }

    @Test
    @Transactional
    public void createEightyc() throws Exception {
        int databaseSizeBeforeCreate = eightycRepository.findAll().size();

        // Create the Eightyc
        EightycDTO eightycDTO = eightycMapper.toDto(eightyc);
        restEightycMockMvc.perform(post("/api/eightycs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightycDTO)))
            .andExpect(status().isCreated());

        // Validate the Eightyc in the database
        List<Eightyc> eightycList = eightycRepository.findAll();
        assertThat(eightycList).hasSize(databaseSizeBeforeCreate + 1);
        Eightyc testEightyc = eightycList.get(eightycList.size() - 1);
        assertThat(testEightyc.getFixed()).isEqualTo(DEFAULT_FIXED);
        assertThat(testEightyc.getTution()).isEqualTo(DEFAULT_TUTION);
        assertThat(testEightyc.getNsc()).isEqualTo(DEFAULT_NSC);
        assertThat(testEightyc.getNss()).isEqualTo(DEFAULT_NSS);
        assertThat(testEightyc.getPost()).isEqualTo(DEFAULT_POST);
        assertThat(testEightyc.getReinvest()).isEqualTo(DEFAULT_REINVEST);
        assertThat(testEightyc.getLicpremium()).isEqualTo(DEFAULT_LICPREMIUM);
        assertThat(testEightyc.getEquity()).isEqualTo(DEFAULT_EQUITY);
        assertThat(testEightyc.getPf()).isEqualTo(DEFAULT_PF);
        assertThat(testEightyc.getPpf()).isEqualTo(DEFAULT_PPF);
        assertThat(testEightyc.getOther()).isEqualTo(DEFAULT_OTHER);
        assertThat(testEightyc.getTutionfee()).isEqualTo(DEFAULT_TUTIONFEE);
        assertThat(testEightyc.getUlip()).isEqualTo(DEFAULT_ULIP);
    }

    @Test
    @Transactional
    public void createEightycWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eightycRepository.findAll().size();

        // Create the Eightyc with an existing ID
        eightyc.setId(1L);
        EightycDTO eightycDTO = eightycMapper.toDto(eightyc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEightycMockMvc.perform(post("/api/eightycs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightycDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Eightyc in the database
        List<Eightyc> eightycList = eightycRepository.findAll();
        assertThat(eightycList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEightycs() throws Exception {
        // Initialize the database
        eightycRepository.saveAndFlush(eightyc);

        // Get all the eightycList
        restEightycMockMvc.perform(get("/api/eightycs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eightyc.getId().intValue())))
            .andExpect(jsonPath("$.[*].fixed").value(hasItem(DEFAULT_FIXED)))
            .andExpect(jsonPath("$.[*].tution").value(hasItem(DEFAULT_TUTION)))
            .andExpect(jsonPath("$.[*].nsc").value(hasItem(DEFAULT_NSC)))
            .andExpect(jsonPath("$.[*].nss").value(hasItem(DEFAULT_NSS)))
            .andExpect(jsonPath("$.[*].post").value(hasItem(DEFAULT_POST)))
            .andExpect(jsonPath("$.[*].reinvest").value(hasItem(DEFAULT_REINVEST)))
            .andExpect(jsonPath("$.[*].licpremium").value(hasItem(DEFAULT_LICPREMIUM)))
            .andExpect(jsonPath("$.[*].equity").value(hasItem(DEFAULT_EQUITY)))
            .andExpect(jsonPath("$.[*].pf").value(hasItem(DEFAULT_PF)))
            .andExpect(jsonPath("$.[*].ppf").value(hasItem(DEFAULT_PPF)))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER)))
            .andExpect(jsonPath("$.[*].tutionfee").value(hasItem(DEFAULT_TUTIONFEE)))
            .andExpect(jsonPath("$.[*].ulip").value(hasItem(DEFAULT_ULIP)));
    }

    @Test
    @Transactional
    public void getEightyc() throws Exception {
        // Initialize the database
        eightycRepository.saveAndFlush(eightyc);

        // Get the eightyc
        restEightycMockMvc.perform(get("/api/eightycs/{id}", eightyc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eightyc.getId().intValue()))
            .andExpect(jsonPath("$.fixed").value(DEFAULT_FIXED))
            .andExpect(jsonPath("$.tution").value(DEFAULT_TUTION))
            .andExpect(jsonPath("$.nsc").value(DEFAULT_NSC))
            .andExpect(jsonPath("$.nss").value(DEFAULT_NSS))
            .andExpect(jsonPath("$.post").value(DEFAULT_POST))
            .andExpect(jsonPath("$.reinvest").value(DEFAULT_REINVEST))
            .andExpect(jsonPath("$.licpremium").value(DEFAULT_LICPREMIUM))
            .andExpect(jsonPath("$.equity").value(DEFAULT_EQUITY))
            .andExpect(jsonPath("$.pf").value(DEFAULT_PF))
            .andExpect(jsonPath("$.ppf").value(DEFAULT_PPF))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER))
            .andExpect(jsonPath("$.tutionfee").value(DEFAULT_TUTIONFEE))
            .andExpect(jsonPath("$.ulip").value(DEFAULT_ULIP));
    }

    @Test
    @Transactional
    public void getNonExistingEightyc() throws Exception {
        // Get the eightyc
        restEightycMockMvc.perform(get("/api/eightycs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEightyc() throws Exception {
        // Initialize the database
        eightycRepository.saveAndFlush(eightyc);
        int databaseSizeBeforeUpdate = eightycRepository.findAll().size();

        // Update the eightyc
        Eightyc updatedEightyc = eightycRepository.findOne(eightyc.getId());
        // Disconnect from session so that the updates on updatedEightyc are not directly saved in db
        em.detach(updatedEightyc);
        updatedEightyc
            .fixed(UPDATED_FIXED)
            .tution(UPDATED_TUTION)
            .nsc(UPDATED_NSC)
            .nss(UPDATED_NSS)
            .post(UPDATED_POST)
            .reinvest(UPDATED_REINVEST)
            .licpremium(UPDATED_LICPREMIUM)
            .equity(UPDATED_EQUITY)
            .pf(UPDATED_PF)
            .ppf(UPDATED_PPF)
            .other(UPDATED_OTHER)
            .tutionfee(UPDATED_TUTIONFEE)
            .ulip(UPDATED_ULIP);
        EightycDTO eightycDTO = eightycMapper.toDto(updatedEightyc);

        restEightycMockMvc.perform(put("/api/eightycs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightycDTO)))
            .andExpect(status().isOk());

        // Validate the Eightyc in the database
        List<Eightyc> eightycList = eightycRepository.findAll();
        assertThat(eightycList).hasSize(databaseSizeBeforeUpdate);
        Eightyc testEightyc = eightycList.get(eightycList.size() - 1);
        assertThat(testEightyc.getFixed()).isEqualTo(UPDATED_FIXED);
        assertThat(testEightyc.getTution()).isEqualTo(UPDATED_TUTION);
        assertThat(testEightyc.getNsc()).isEqualTo(UPDATED_NSC);
        assertThat(testEightyc.getNss()).isEqualTo(UPDATED_NSS);
        assertThat(testEightyc.getPost()).isEqualTo(UPDATED_POST);
        assertThat(testEightyc.getReinvest()).isEqualTo(UPDATED_REINVEST);
        assertThat(testEightyc.getLicpremium()).isEqualTo(UPDATED_LICPREMIUM);
        assertThat(testEightyc.getEquity()).isEqualTo(UPDATED_EQUITY);
        assertThat(testEightyc.getPf()).isEqualTo(UPDATED_PF);
        assertThat(testEightyc.getPpf()).isEqualTo(UPDATED_PPF);
        assertThat(testEightyc.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testEightyc.getTutionfee()).isEqualTo(UPDATED_TUTIONFEE);
        assertThat(testEightyc.getUlip()).isEqualTo(UPDATED_ULIP);
    }

    @Test
    @Transactional
    public void updateNonExistingEightyc() throws Exception {
        int databaseSizeBeforeUpdate = eightycRepository.findAll().size();

        // Create the Eightyc
        EightycDTO eightycDTO = eightycMapper.toDto(eightyc);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEightycMockMvc.perform(put("/api/eightycs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightycDTO)))
            .andExpect(status().isCreated());

        // Validate the Eightyc in the database
        List<Eightyc> eightycList = eightycRepository.findAll();
        assertThat(eightycList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEightyc() throws Exception {
        // Initialize the database
        eightycRepository.saveAndFlush(eightyc);
        int databaseSizeBeforeDelete = eightycRepository.findAll().size();

        // Get the eightyc
        restEightycMockMvc.perform(delete("/api/eightycs/{id}", eightyc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Eightyc> eightycList = eightycRepository.findAll();
        assertThat(eightycList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Eightyc.class);
        Eightyc eightyc1 = new Eightyc();
        eightyc1.setId(1L);
        Eightyc eightyc2 = new Eightyc();
        eightyc2.setId(eightyc1.getId());
        assertThat(eightyc1).isEqualTo(eightyc2);
        eightyc2.setId(2L);
        assertThat(eightyc1).isNotEqualTo(eightyc2);
        eightyc1.setId(null);
        assertThat(eightyc1).isNotEqualTo(eightyc2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EightycDTO.class);
        EightycDTO eightycDTO1 = new EightycDTO();
        eightycDTO1.setId(1L);
        EightycDTO eightycDTO2 = new EightycDTO();
        assertThat(eightycDTO1).isNotEqualTo(eightycDTO2);
        eightycDTO2.setId(eightycDTO1.getId());
        assertThat(eightycDTO1).isEqualTo(eightycDTO2);
        eightycDTO2.setId(2L);
        assertThat(eightycDTO1).isNotEqualTo(eightycDTO2);
        eightycDTO1.setId(null);
        assertThat(eightycDTO1).isNotEqualTo(eightycDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eightycMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eightycMapper.fromId(null)).isNull();
    }
}
