package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Eightycdeduct;
import com.valuequo.buckswise.repository.EightycdeductRepository;
import com.valuequo.buckswise.service.EightycdeductService;
import com.valuequo.buckswise.service.dto.EightycdeductDTO;
import com.valuequo.buckswise.service.mapper.EightycdeductMapper;
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
 * Test class for the EightycdeductResource REST controller.
 *
 * @see EightycdeductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class EightycdeductResourceIntTest {

    private static final Integer DEFAULT_UID = 1;
    private static final Integer UPDATED_UID = 2;

    private static final String DEFAULT_FIXED = "AAAAAAAAAA";
    private static final String UPDATED_FIXED = "BBBBBBBBBB";

    private static final String DEFAULT_TUTION = "AAAAAAAAAA";
    private static final String UPDATED_TUTION = "BBBBBBBBBB";

    private static final String DEFAULT_NSC = "AAAAAAAAAA";
    private static final String UPDATED_NSC = "BBBBBBBBBB";

    private static final String DEFAULT_NSS = "AAAAAAAAAA";
    private static final String UPDATED_NSS = "BBBBBBBBBB";

    private static final String DEFAULT_REINVEST = "AAAAAAAAAA";
    private static final String UPDATED_REINVEST = "BBBBBBBBBB";

    private static final String DEFAULT_LICPREMIUM = "AAAAAAAAAA";
    private static final String UPDATED_LICPREMIUM = "BBBBBBBBBB";

    private static final String DEFAULT_EQUITY = "AAAAAAAAAA";
    private static final String UPDATED_EQUITY = "BBBBBBBBBB";

    private static final String DEFAULT_PF = "AAAAAAAAAA";
    private static final String UPDATED_PF = "BBBBBBBBBB";

    private static final String DEFAULT_PPF = "AAAAAAAAAA";
    private static final String UPDATED_PPF = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_TUTIONFEE = "AAAAAAAAAA";
    private static final String UPDATED_TUTIONFEE = "BBBBBBBBBB";

    private static final String DEFAULT_ULIP = "AAAAAAAAAA";
    private static final String UPDATED_ULIP = "BBBBBBBBBB";

    private static final String DEFAULT_POST = "AAAAAAAAAA";
    private static final String UPDATED_POST = "BBBBBBBBBB";

    @Autowired
    private EightycdeductRepository eightycdeductRepository;

    @Autowired
    private EightycdeductMapper eightycdeductMapper;

    @Autowired
    private EightycdeductService eightycdeductService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEightycdeductMockMvc;

    private Eightycdeduct eightycdeduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EightycdeductResource eightycdeductResource = new EightycdeductResource(eightycdeductService);
        this.restEightycdeductMockMvc = MockMvcBuilders.standaloneSetup(eightycdeductResource)
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
    public static Eightycdeduct createEntity(EntityManager em) {
        Eightycdeduct eightycdeduct = new Eightycdeduct()
            .uid(DEFAULT_UID)
            .fixed(DEFAULT_FIXED)
            .tution(DEFAULT_TUTION)
            .nsc(DEFAULT_NSC)
            .nss(DEFAULT_NSS)
            .reinvest(DEFAULT_REINVEST)
            .licpremium(DEFAULT_LICPREMIUM)
            .equity(DEFAULT_EQUITY)
            .pf(DEFAULT_PF)
            .ppf(DEFAULT_PPF)
            .other(DEFAULT_OTHER)
            .ulip(DEFAULT_ULIP)
            .post(DEFAULT_POST);
        return eightycdeduct;
    }

    @Before
    public void initTest() {
        eightycdeduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createEightycdeduct() throws Exception {
        int databaseSizeBeforeCreate = eightycdeductRepository.findAll().size();

        // Create the Eightycdeduct
        EightycdeductDTO eightycdeductDTO = eightycdeductMapper.toDto(eightycdeduct);
        restEightycdeductMockMvc.perform(post("/api/eightycdeducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightycdeductDTO)))
            .andExpect(status().isCreated());

        // Validate the Eightycdeduct in the database
        List<Eightycdeduct> eightycdeductList = eightycdeductRepository.findAll();
        assertThat(eightycdeductList).hasSize(databaseSizeBeforeCreate + 1);
        Eightycdeduct testEightycdeduct = eightycdeductList.get(eightycdeductList.size() - 1);
        assertThat(testEightycdeduct.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testEightycdeduct.getFixed()).isEqualTo(DEFAULT_FIXED);
        assertThat(testEightycdeduct.getTution()).isEqualTo(DEFAULT_TUTION);
        assertThat(testEightycdeduct.getNsc()).isEqualTo(DEFAULT_NSC);
        assertThat(testEightycdeduct.getNss()).isEqualTo(DEFAULT_NSS);
        assertThat(testEightycdeduct.getReinvest()).isEqualTo(DEFAULT_REINVEST);
        assertThat(testEightycdeduct.getLicpremium()).isEqualTo(DEFAULT_LICPREMIUM);
        assertThat(testEightycdeduct.getEquity()).isEqualTo(DEFAULT_EQUITY);
        assertThat(testEightycdeduct.getPf()).isEqualTo(DEFAULT_PF);
        assertThat(testEightycdeduct.getPpf()).isEqualTo(DEFAULT_PPF);
        assertThat(testEightycdeduct.getOther()).isEqualTo(DEFAULT_OTHER);
        assertThat(testEightycdeduct.getUlip()).isEqualTo(DEFAULT_ULIP);
        assertThat(testEightycdeduct.getPost()).isEqualTo(DEFAULT_POST);
    }

    @Test
    @Transactional
    public void createEightycdeductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eightycdeductRepository.findAll().size();

        // Create the Eightycdeduct with an existing ID
        eightycdeduct.setId(1L);
        EightycdeductDTO eightycdeductDTO = eightycdeductMapper.toDto(eightycdeduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEightycdeductMockMvc.perform(post("/api/eightycdeducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightycdeductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Eightycdeduct in the database
        List<Eightycdeduct> eightycdeductList = eightycdeductRepository.findAll();
        assertThat(eightycdeductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEightycdeducts() throws Exception {
        // Initialize the database
        eightycdeductRepository.saveAndFlush(eightycdeduct);

        // Get all the eightycdeductList
        restEightycdeductMockMvc.perform(get("/api/eightycdeducts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eightycdeduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].fixed").value(hasItem(DEFAULT_FIXED.toString())))
            .andExpect(jsonPath("$.[*].tution").value(hasItem(DEFAULT_TUTION.toString())))
            .andExpect(jsonPath("$.[*].nsc").value(hasItem(DEFAULT_NSC.toString())))
            .andExpect(jsonPath("$.[*].nss").value(hasItem(DEFAULT_NSS.toString())))
            .andExpect(jsonPath("$.[*].reinvest").value(hasItem(DEFAULT_REINVEST.toString())))
            .andExpect(jsonPath("$.[*].licpremium").value(hasItem(DEFAULT_LICPREMIUM.toString())))
            .andExpect(jsonPath("$.[*].equity").value(hasItem(DEFAULT_EQUITY.toString())))
            .andExpect(jsonPath("$.[*].pf").value(hasItem(DEFAULT_PF.toString())))
            .andExpect(jsonPath("$.[*].ppf").value(hasItem(DEFAULT_PPF.toString())))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())))
            // .andExpect(jsonPath("$.[*].tutionfee").value(hasItem(DEFAULT_TUTIONFEE.toString())))
            .andExpect(jsonPath("$.[*].ulip").value(hasItem(DEFAULT_ULIP.toString())))
            .andExpect(jsonPath("$.[*].post").value(hasItem(DEFAULT_POST.toString())));
    }

    // @Test
    // @Transactional
    // public void getEightycdeduct() throws Exception {
    //     // Initialize the database
    //     eightycdeductRepository.saveAndFlush(eightycdeduct);

    //     // Get the eightycdeduct
    //     restEightycdeductMockMvc.perform(get("/api/eightycdeducts/{id}", eightycdeduct.getId()))
    //         .andExpect(status().isOk())
    //         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    //         .andExpect(jsonPath("$.id").value(eightycdeduct.getId().intValue()))
    //         .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
    //         .andExpect(jsonPath("$.fixed").value(DEFAULT_FIXED.toString()))
    //         .andExpect(jsonPath("$.tution").value(DEFAULT_TUTION.toString()))
    //         .andExpect(jsonPath("$.nsc").value(DEFAULT_NSC.toString()))
    //         .andExpect(jsonPath("$.nss").value(DEFAULT_NSS.toString()))
    //         .andExpect(jsonPath("$.reinvest").value(DEFAULT_REINVEST.toString()))
    //         .andExpect(jsonPath("$.licpremium").value(DEFAULT_LICPREMIUM.toString()))
    //         .andExpect(jsonPath("$.equity").value(DEFAULT_EQUITY.toString()))
    //         .andExpect(jsonPath("$.pf").value(DEFAULT_PF.toString()))
    //         .andExpect(jsonPath("$.ppf").value(DEFAULT_PPF.toString()))
    //         .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()))
    //         .andExpect(jsonPath("$.tutionfee").value(DEFAULT_TUTIONFEE.toString()))
    //         .andExpect(jsonPath("$.ulip").value(DEFAULT_ULIP.toString()))
    //         .andExpect(jsonPath("$.post").value(DEFAULT_POST.toString()));
    // }

    // @Test
    // @Transactional
    // public void getNonExistingEightycdeduct() throws Exception {
    //     // Get the eightycdeduct
    //     restEightycdeductMockMvc.perform(get("/api/eightycdeducts/{id}", Long.MAX_VALUE))
    //         .andExpect(status().isNotFound());
    // }

    @Test
    @Transactional
    public void updateEightycdeduct() throws Exception {
        // Initialize the database
        eightycdeductRepository.saveAndFlush(eightycdeduct);
        int databaseSizeBeforeUpdate = eightycdeductRepository.findAll().size();

        // Update the eightycdeduct
        Eightycdeduct updatedEightycdeduct = eightycdeductRepository.findOne(eightycdeduct.getId());
        // Disconnect from session so that the updates on updatedEightycdeduct are not directly saved in db
        em.detach(updatedEightycdeduct);
        updatedEightycdeduct
            .uid(UPDATED_UID)
            .fixed(UPDATED_FIXED)
            .tution(UPDATED_TUTION)
            .nsc(UPDATED_NSC)
            .nss(UPDATED_NSS)
            .reinvest(UPDATED_REINVEST)
            .licpremium(UPDATED_LICPREMIUM)
            .equity(UPDATED_EQUITY)
            .pf(UPDATED_PF)
            .ppf(UPDATED_PPF)
            .other(UPDATED_OTHER)
            .ulip(UPDATED_ULIP)
            .post(UPDATED_POST);
        EightycdeductDTO eightycdeductDTO = eightycdeductMapper.toDto(updatedEightycdeduct);

        restEightycdeductMockMvc.perform(put("/api/eightycdeducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightycdeductDTO)))
            .andExpect(status().isOk());

        // Validate the Eightycdeduct in the database
        List<Eightycdeduct> eightycdeductList = eightycdeductRepository.findAll();
        assertThat(eightycdeductList).hasSize(databaseSizeBeforeUpdate);
        Eightycdeduct testEightycdeduct = eightycdeductList.get(eightycdeductList.size() - 1);
        assertThat(testEightycdeduct.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testEightycdeduct.getFixed()).isEqualTo(UPDATED_FIXED);
        assertThat(testEightycdeduct.getTution()).isEqualTo(UPDATED_TUTION);
        assertThat(testEightycdeduct.getNsc()).isEqualTo(UPDATED_NSC);
        assertThat(testEightycdeduct.getNss()).isEqualTo(UPDATED_NSS);
        assertThat(testEightycdeduct.getReinvest()).isEqualTo(UPDATED_REINVEST);
        assertThat(testEightycdeduct.getLicpremium()).isEqualTo(UPDATED_LICPREMIUM);
        assertThat(testEightycdeduct.getEquity()).isEqualTo(UPDATED_EQUITY);
        assertThat(testEightycdeduct.getPf()).isEqualTo(UPDATED_PF);
        assertThat(testEightycdeduct.getPpf()).isEqualTo(UPDATED_PPF);
        assertThat(testEightycdeduct.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testEightycdeduct.getUlip()).isEqualTo(UPDATED_ULIP);
        assertThat(testEightycdeduct.getPost()).isEqualTo(UPDATED_POST);
    }

    @Test
    @Transactional
    public void updateNonExistingEightycdeduct() throws Exception {
        int databaseSizeBeforeUpdate = eightycdeductRepository.findAll().size();

        // Create the Eightycdeduct
        EightycdeductDTO eightycdeductDTO = eightycdeductMapper.toDto(eightycdeduct);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEightycdeductMockMvc.perform(put("/api/eightycdeducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightycdeductDTO)))
            .andExpect(status().isCreated());

        // Validate the Eightycdeduct in the database
        List<Eightycdeduct> eightycdeductList = eightycdeductRepository.findAll();
        assertThat(eightycdeductList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEightycdeduct() throws Exception {
        // Initialize the database
        eightycdeductRepository.saveAndFlush(eightycdeduct);
        int databaseSizeBeforeDelete = eightycdeductRepository.findAll().size();

        // Get the eightycdeduct
        restEightycdeductMockMvc.perform(delete("/api/eightycdeducts/{id}", eightycdeduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Eightycdeduct> eightycdeductList = eightycdeductRepository.findAll();
        assertThat(eightycdeductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Eightycdeduct.class);
        Eightycdeduct eightycdeduct1 = new Eightycdeduct();
        eightycdeduct1.setId(1L);
        Eightycdeduct eightycdeduct2 = new Eightycdeduct();
        eightycdeduct2.setId(eightycdeduct1.getId());
        assertThat(eightycdeduct1).isEqualTo(eightycdeduct2);
        eightycdeduct2.setId(2L);
        assertThat(eightycdeduct1).isNotEqualTo(eightycdeduct2);
        eightycdeduct1.setId(null);
        assertThat(eightycdeduct1).isNotEqualTo(eightycdeduct2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EightycdeductDTO.class);
        EightycdeductDTO eightycdeductDTO1 = new EightycdeductDTO();
        eightycdeductDTO1.setId(1L);
        EightycdeductDTO eightycdeductDTO2 = new EightycdeductDTO();
        assertThat(eightycdeductDTO1).isNotEqualTo(eightycdeductDTO2);
        eightycdeductDTO2.setId(eightycdeductDTO1.getId());
        assertThat(eightycdeductDTO1).isEqualTo(eightycdeductDTO2);
        eightycdeductDTO2.setId(2L);
        assertThat(eightycdeductDTO1).isNotEqualTo(eightycdeductDTO2);
        eightycdeductDTO1.setId(null);
        assertThat(eightycdeductDTO1).isNotEqualTo(eightycdeductDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eightycdeductMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eightycdeductMapper.fromId(null)).isNull();
    }
}
