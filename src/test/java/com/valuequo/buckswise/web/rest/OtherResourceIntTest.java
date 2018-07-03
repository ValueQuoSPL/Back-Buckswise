package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseBackEndApp;

import com.valuequo.buckswise.domain.Other;
import com.valuequo.buckswise.repository.OtherRepository;
import com.valuequo.buckswise.service.OtherService;
import com.valuequo.buckswise.service.dto.OtherDTO;
import com.valuequo.buckswise.service.mapper.OtherMapper;
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
 * Test class for the OtherResource REST controller.
 *
 * @see OtherResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseBackEndApp.class)
public class OtherResourceIntTest {

    private static final Integer DEFAULT_HANDICAPPED = 1;
    private static final Integer UPDATED_HANDICAPPED = 2;

    private static final Integer DEFAULT_MEDICALTREAT = 1;
    private static final Integer UPDATED_MEDICALTREAT = 2;

    private static final Integer DEFAULT_SELFEDU = 1;
    private static final Integer UPDATED_SELFEDU = 2;

    private static final Integer DEFAULT_NPS = 1;
    private static final Integer UPDATED_NPS = 2;

    private static final Integer DEFAULT_RGESS = 1;
    private static final Integer UPDATED_RGESS = 2;

    private static final Integer DEFAULT_DONATION = 1;
    private static final Integer UPDATED_DONATION = 2;

    @Autowired
    private OtherRepository otherRepository;

    @Autowired
    private OtherMapper otherMapper;

    @Autowired
    private OtherService otherService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOtherMockMvc;

    private Other other;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OtherResource otherResource = new OtherResource(otherService);
        this.restOtherMockMvc = MockMvcBuilders.standaloneSetup(otherResource)
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
    public static Other createEntity(EntityManager em) {
        Other other = new Other()
            .handicapped(DEFAULT_HANDICAPPED)
            .medicaltreat(DEFAULT_MEDICALTREAT)
            .selfedu(DEFAULT_SELFEDU)
            .nps(DEFAULT_NPS)
            .rgess(DEFAULT_RGESS)
            .donation(DEFAULT_DONATION);
        return other;
    }

    @Before
    public void initTest() {
        other = createEntity(em);
    }

    @Test
    @Transactional
    public void createOther() throws Exception {
        int databaseSizeBeforeCreate = otherRepository.findAll().size();

        // Create the Other
        OtherDTO otherDTO = otherMapper.toDto(other);
        restOtherMockMvc.perform(post("/api/others")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otherDTO)))
            .andExpect(status().isCreated());

        // Validate the Other in the database
        List<Other> otherList = otherRepository.findAll();
        assertThat(otherList).hasSize(databaseSizeBeforeCreate + 1);
        Other testOther = otherList.get(otherList.size() - 1);
        assertThat(testOther.getHandicapped()).isEqualTo(DEFAULT_HANDICAPPED);
        assertThat(testOther.getMedicaltreat()).isEqualTo(DEFAULT_MEDICALTREAT);
        assertThat(testOther.getSelfedu()).isEqualTo(DEFAULT_SELFEDU);
        assertThat(testOther.getNps()).isEqualTo(DEFAULT_NPS);
        assertThat(testOther.getRgess()).isEqualTo(DEFAULT_RGESS);
        assertThat(testOther.getDonation()).isEqualTo(DEFAULT_DONATION);
    }

    @Test
    @Transactional
    public void createOtherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = otherRepository.findAll().size();

        // Create the Other with an existing ID
        other.setId(1L);
        OtherDTO otherDTO = otherMapper.toDto(other);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOtherMockMvc.perform(post("/api/others")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Other in the database
        List<Other> otherList = otherRepository.findAll();
        assertThat(otherList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOthers() throws Exception {
        // Initialize the database
        otherRepository.saveAndFlush(other);

        // Get all the otherList
        restOtherMockMvc.perform(get("/api/others?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(other.getId().intValue())))
            .andExpect(jsonPath("$.[*].handicapped").value(hasItem(DEFAULT_HANDICAPPED)))
            .andExpect(jsonPath("$.[*].medicaltreat").value(hasItem(DEFAULT_MEDICALTREAT)))
            .andExpect(jsonPath("$.[*].selfedu").value(hasItem(DEFAULT_SELFEDU)))
            .andExpect(jsonPath("$.[*].nps").value(hasItem(DEFAULT_NPS)))
            .andExpect(jsonPath("$.[*].rgess").value(hasItem(DEFAULT_RGESS)))
            .andExpect(jsonPath("$.[*].donation").value(hasItem(DEFAULT_DONATION)));
    }

    @Test
    @Transactional
    public void getOther() throws Exception {
        // Initialize the database
        otherRepository.saveAndFlush(other);

        // Get the other
        restOtherMockMvc.perform(get("/api/others/{id}", other.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(other.getId().intValue()))
            .andExpect(jsonPath("$.handicapped").value(DEFAULT_HANDICAPPED))
            .andExpect(jsonPath("$.medicaltreat").value(DEFAULT_MEDICALTREAT))
            .andExpect(jsonPath("$.selfedu").value(DEFAULT_SELFEDU))
            .andExpect(jsonPath("$.nps").value(DEFAULT_NPS))
            .andExpect(jsonPath("$.rgess").value(DEFAULT_RGESS))
            .andExpect(jsonPath("$.donation").value(DEFAULT_DONATION));
    }

    @Test
    @Transactional
    public void getNonExistingOther() throws Exception {
        // Get the other
        restOtherMockMvc.perform(get("/api/others/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOther() throws Exception {
        // Initialize the database
        otherRepository.saveAndFlush(other);
        int databaseSizeBeforeUpdate = otherRepository.findAll().size();

        // Update the other
        Other updatedOther = otherRepository.findOne(other.getId());
        // Disconnect from session so that the updates on updatedOther are not directly saved in db
        em.detach(updatedOther);
        updatedOther
            .handicapped(UPDATED_HANDICAPPED)
            .medicaltreat(UPDATED_MEDICALTREAT)
            .selfedu(UPDATED_SELFEDU)
            .nps(UPDATED_NPS)
            .rgess(UPDATED_RGESS)
            .donation(UPDATED_DONATION);
        OtherDTO otherDTO = otherMapper.toDto(updatedOther);

        restOtherMockMvc.perform(put("/api/others")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otherDTO)))
            .andExpect(status().isOk());

        // Validate the Other in the database
        List<Other> otherList = otherRepository.findAll();
        assertThat(otherList).hasSize(databaseSizeBeforeUpdate);
        Other testOther = otherList.get(otherList.size() - 1);
        assertThat(testOther.getHandicapped()).isEqualTo(UPDATED_HANDICAPPED);
        assertThat(testOther.getMedicaltreat()).isEqualTo(UPDATED_MEDICALTREAT);
        assertThat(testOther.getSelfedu()).isEqualTo(UPDATED_SELFEDU);
        assertThat(testOther.getNps()).isEqualTo(UPDATED_NPS);
        assertThat(testOther.getRgess()).isEqualTo(UPDATED_RGESS);
        assertThat(testOther.getDonation()).isEqualTo(UPDATED_DONATION);
    }

    @Test
    @Transactional
    public void updateNonExistingOther() throws Exception {
        int databaseSizeBeforeUpdate = otherRepository.findAll().size();

        // Create the Other
        OtherDTO otherDTO = otherMapper.toDto(other);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOtherMockMvc.perform(put("/api/others")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otherDTO)))
            .andExpect(status().isCreated());

        // Validate the Other in the database
        List<Other> otherList = otherRepository.findAll();
        assertThat(otherList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOther() throws Exception {
        // Initialize the database
        otherRepository.saveAndFlush(other);
        int databaseSizeBeforeDelete = otherRepository.findAll().size();

        // Get the other
        restOtherMockMvc.perform(delete("/api/others/{id}", other.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Other> otherList = otherRepository.findAll();
        assertThat(otherList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Other.class);
        Other other1 = new Other();
        other1.setId(1L);
        Other other2 = new Other();
        other2.setId(other1.getId());
        assertThat(other1).isEqualTo(other2);
        other2.setId(2L);
        assertThat(other1).isNotEqualTo(other2);
        other1.setId(null);
        assertThat(other1).isNotEqualTo(other2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OtherDTO.class);
        OtherDTO otherDTO1 = new OtherDTO();
        otherDTO1.setId(1L);
        OtherDTO otherDTO2 = new OtherDTO();
        assertThat(otherDTO1).isNotEqualTo(otherDTO2);
        otherDTO2.setId(otherDTO1.getId());
        assertThat(otherDTO1).isEqualTo(otherDTO2);
        otherDTO2.setId(2L);
        assertThat(otherDTO1).isNotEqualTo(otherDTO2);
        otherDTO1.setId(null);
        assertThat(otherDTO1).isNotEqualTo(otherDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(otherMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(otherMapper.fromId(null)).isNull();
    }
}
