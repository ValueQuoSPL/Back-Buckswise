package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Otherdeduction;
import com.valuequo.buckswise.repository.OtherdeductionRepository;
import com.valuequo.buckswise.service.OtherdeductionService;
import com.valuequo.buckswise.service.dto.OtherdeductionDTO;
import com.valuequo.buckswise.service.mapper.OtherdeductionMapper;
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
 * Test class for the OtherdeductionResource REST controller.
 *
 * @see OtherdeductionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class OtherdeductionResourceIntTest {

    private static final Integer DEFAULT_UID = 1;
    private static final Integer UPDATED_UID = 2;

    private static final String DEFAULT_HANDICAPPED = "AAAAAAAAAA";
    private static final String UPDATED_HANDICAPPED = "BBBBBBBBBB";

    private static final String DEFAULT_MEDICALTREAT = "AAAAAAAAAA";
    private static final String UPDATED_MEDICALTREAT = "BBBBBBBBBB";

    private static final String DEFAULT_SELFEDU = "AAAAAAAAAA";
    private static final String UPDATED_SELFEDU = "BBBBBBBBBB";

    private static final String DEFAULT_NPS = "AAAAAAAAAA";
    private static final String UPDATED_NPS = "BBBBBBBBBB";

    private static final String DEFAULT_RGESS = "AAAAAAAAAA";
    private static final String UPDATED_RGESS = "BBBBBBBBBB";

    private static final String DEFAULT_DONATION = "AAAAAAAAAA";
    private static final String UPDATED_DONATION = "BBBBBBBBBB";

    @Autowired
    private OtherdeductionRepository otherdeductionRepository;

    @Autowired
    private OtherdeductionMapper otherdeductionMapper;

    @Autowired
    private OtherdeductionService otherdeductionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOtherdeductionMockMvc;

    private Otherdeduction otherdeduction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OtherdeductionResource otherdeductionResource = new OtherdeductionResource(otherdeductionService);
        this.restOtherdeductionMockMvc = MockMvcBuilders.standaloneSetup(otherdeductionResource)
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
    public static Otherdeduction createEntity(EntityManager em) {
        Otherdeduction otherdeduction = new Otherdeduction()
            .uid(DEFAULT_UID)
            .handicapped(DEFAULT_HANDICAPPED)
            .medicaltreat(DEFAULT_MEDICALTREAT)
            .selfedu(DEFAULT_SELFEDU)
            .nps(DEFAULT_NPS)
            .rgess(DEFAULT_RGESS)
            .donation(DEFAULT_DONATION);
        return otherdeduction;
    }

    @Before
    public void initTest() {
        otherdeduction = createEntity(em);
    }

    @Test
    @Transactional
    public void createOtherdeduction() throws Exception {
        int databaseSizeBeforeCreate = otherdeductionRepository.findAll().size();

        // Create the Otherdeduction
        OtherdeductionDTO otherdeductionDTO = otherdeductionMapper.toDto(otherdeduction);
        restOtherdeductionMockMvc.perform(post("/api/otherdeductions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otherdeductionDTO)))
            .andExpect(status().isCreated());

        // Validate the Otherdeduction in the database
        List<Otherdeduction> otherdeductionList = otherdeductionRepository.findAll();
        assertThat(otherdeductionList).hasSize(databaseSizeBeforeCreate + 1);
        Otherdeduction testOtherdeduction = otherdeductionList.get(otherdeductionList.size() - 1);
        assertThat(testOtherdeduction.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testOtherdeduction.getHandicapped()).isEqualTo(DEFAULT_HANDICAPPED);
        assertThat(testOtherdeduction.getMedicaltreat()).isEqualTo(DEFAULT_MEDICALTREAT);
        assertThat(testOtherdeduction.getSelfedu()).isEqualTo(DEFAULT_SELFEDU);
        assertThat(testOtherdeduction.getNps()).isEqualTo(DEFAULT_NPS);
        assertThat(testOtherdeduction.getRgess()).isEqualTo(DEFAULT_RGESS);
        assertThat(testOtherdeduction.getDonation()).isEqualTo(DEFAULT_DONATION);
    }

    @Test
    @Transactional
    public void createOtherdeductionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = otherdeductionRepository.findAll().size();

        // Create the Otherdeduction with an existing ID
        otherdeduction.setId(1L);
        OtherdeductionDTO otherdeductionDTO = otherdeductionMapper.toDto(otherdeduction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOtherdeductionMockMvc.perform(post("/api/otherdeductions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otherdeductionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Otherdeduction in the database
        List<Otherdeduction> otherdeductionList = otherdeductionRepository.findAll();
        assertThat(otherdeductionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOtherdeductions() throws Exception {
        // Initialize the database
        otherdeductionRepository.saveAndFlush(otherdeduction);

        // Get all the otherdeductionList
        restOtherdeductionMockMvc.perform(get("/api/otherdeductions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(otherdeduction.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].handicapped").value(hasItem(DEFAULT_HANDICAPPED.toString())))
            .andExpect(jsonPath("$.[*].medicaltreat").value(hasItem(DEFAULT_MEDICALTREAT.toString())))
            .andExpect(jsonPath("$.[*].selfedu").value(hasItem(DEFAULT_SELFEDU.toString())))
            .andExpect(jsonPath("$.[*].nps").value(hasItem(DEFAULT_NPS.toString())))
            .andExpect(jsonPath("$.[*].rgess").value(hasItem(DEFAULT_RGESS.toString())))
            .andExpect(jsonPath("$.[*].donation").value(hasItem(DEFAULT_DONATION.toString())));
    }

    // @Test
    // @Transactional
    // public void getOtherdeduction() throws Exception {
    //     // Initialize the database
    //     otherdeductionRepository.saveAndFlush(otherdeduction);

    //     // Get the otherdeduction
    //     restOtherdeductionMockMvc.perform(get("/api/otherdeductions/{id}", otherdeduction.getId()))
    //         .andExpect(status().isOk())
    //         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    //         .andExpect(jsonPath("$.id").value(otherdeduction.getId().intValue()))
    //         .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
    //         .andExpect(jsonPath("$.handicapped").value(DEFAULT_HANDICAPPED.toString()))
    //         .andExpect(jsonPath("$.medicaltreat").value(DEFAULT_MEDICALTREAT.toString()))
    //         .andExpect(jsonPath("$.selfedu").value(DEFAULT_SELFEDU.toString()))
    //         .andExpect(jsonPath("$.nps").value(DEFAULT_NPS.toString()))
    //         .andExpect(jsonPath("$.rgess").value(DEFAULT_RGESS.toString()))
    //         .andExpect(jsonPath("$.donation").value(DEFAULT_DONATION.toString()));
    // }

    // @Test
    // @Transactional
    // public void getNonExistingOtherdeduction() throws Exception {
    //     // Get the otherdeduction
    //     restOtherdeductionMockMvc.perform(get("/api/otherdeductions/{id}", Long.MAX_VALUE))
    //         .andExpect(status().isNotFound());
    // }

    @Test
    @Transactional
    public void updateOtherdeduction() throws Exception {
        // Initialize the database
        otherdeductionRepository.saveAndFlush(otherdeduction);
        int databaseSizeBeforeUpdate = otherdeductionRepository.findAll().size();

        // Update the otherdeduction
        Otherdeduction updatedOtherdeduction = otherdeductionRepository.findOne(otherdeduction.getId());
        // Disconnect from session so that the updates on updatedOtherdeduction are not directly saved in db
        em.detach(updatedOtherdeduction);
        updatedOtherdeduction
            .uid(UPDATED_UID)
            .handicapped(UPDATED_HANDICAPPED)
            .medicaltreat(UPDATED_MEDICALTREAT)
            .selfedu(UPDATED_SELFEDU)
            .nps(UPDATED_NPS)
            .rgess(UPDATED_RGESS)
            .donation(UPDATED_DONATION);
        OtherdeductionDTO otherdeductionDTO = otherdeductionMapper.toDto(updatedOtherdeduction);

        restOtherdeductionMockMvc.perform(put("/api/otherdeductions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otherdeductionDTO)))
            .andExpect(status().isOk());

        // Validate the Otherdeduction in the database
        List<Otherdeduction> otherdeductionList = otherdeductionRepository.findAll();
        assertThat(otherdeductionList).hasSize(databaseSizeBeforeUpdate);
        Otherdeduction testOtherdeduction = otherdeductionList.get(otherdeductionList.size() - 1);
        assertThat(testOtherdeduction.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testOtherdeduction.getHandicapped()).isEqualTo(UPDATED_HANDICAPPED);
        assertThat(testOtherdeduction.getMedicaltreat()).isEqualTo(UPDATED_MEDICALTREAT);
        assertThat(testOtherdeduction.getSelfedu()).isEqualTo(UPDATED_SELFEDU);
        assertThat(testOtherdeduction.getNps()).isEqualTo(UPDATED_NPS);
        assertThat(testOtherdeduction.getRgess()).isEqualTo(UPDATED_RGESS);
        assertThat(testOtherdeduction.getDonation()).isEqualTo(UPDATED_DONATION);
    }

    @Test
    @Transactional
    public void updateNonExistingOtherdeduction() throws Exception {
        int databaseSizeBeforeUpdate = otherdeductionRepository.findAll().size();

        // Create the Otherdeduction
        OtherdeductionDTO otherdeductionDTO = otherdeductionMapper.toDto(otherdeduction);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOtherdeductionMockMvc.perform(put("/api/otherdeductions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otherdeductionDTO)))
            .andExpect(status().isCreated());

        // Validate the Otherdeduction in the database
        List<Otherdeduction> otherdeductionList = otherdeductionRepository.findAll();
        assertThat(otherdeductionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOtherdeduction() throws Exception {
        // Initialize the database
        otherdeductionRepository.saveAndFlush(otherdeduction);
        int databaseSizeBeforeDelete = otherdeductionRepository.findAll().size();

        // Get the otherdeduction
        restOtherdeductionMockMvc.perform(delete("/api/otherdeductions/{id}", otherdeduction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Otherdeduction> otherdeductionList = otherdeductionRepository.findAll();
        assertThat(otherdeductionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Otherdeduction.class);
        Otherdeduction otherdeduction1 = new Otherdeduction();
        otherdeduction1.setId(1L);
        Otherdeduction otherdeduction2 = new Otherdeduction();
        otherdeduction2.setId(otherdeduction1.getId());
        assertThat(otherdeduction1).isEqualTo(otherdeduction2);
        otherdeduction2.setId(2L);
        assertThat(otherdeduction1).isNotEqualTo(otherdeduction2);
        otherdeduction1.setId(null);
        assertThat(otherdeduction1).isNotEqualTo(otherdeduction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OtherdeductionDTO.class);
        OtherdeductionDTO otherdeductionDTO1 = new OtherdeductionDTO();
        otherdeductionDTO1.setId(1L);
        OtherdeductionDTO otherdeductionDTO2 = new OtherdeductionDTO();
        assertThat(otherdeductionDTO1).isNotEqualTo(otherdeductionDTO2);
        otherdeductionDTO2.setId(otherdeductionDTO1.getId());
        assertThat(otherdeductionDTO1).isEqualTo(otherdeductionDTO2);
        otherdeductionDTO2.setId(2L);
        assertThat(otherdeductionDTO1).isNotEqualTo(otherdeductionDTO2);
        otherdeductionDTO1.setId(null);
        assertThat(otherdeductionDTO1).isNotEqualTo(otherdeductionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(otherdeductionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(otherdeductionMapper.fromId(null)).isNull();
    }
}
