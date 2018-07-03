package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseBackEndApp;

import com.valuequo.buckswise.domain.Eightd;
import com.valuequo.buckswise.repository.EightdRepository;
import com.valuequo.buckswise.service.EightdService;
import com.valuequo.buckswise.service.dto.EightdDTO;
import com.valuequo.buckswise.service.mapper.EightdMapper;
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
 * Test class for the EightdResource REST controller.
 *
 * @see EightdResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseBackEndApp.class)
public class EightdResourceIntTest {

    private static final Integer DEFAULT_MEDSELF = 1;
    private static final Integer UPDATED_MEDSELF = 2;

    private static final Integer DEFAULT_MEDPARENTS = 1;
    private static final Integer UPDATED_MEDPARENTS = 2;

    private static final Integer DEFAULT_HEALTHCHECK = 1;
    private static final Integer UPDATED_HEALTHCHECK = 2;

    @Autowired
    private EightdRepository eightdRepository;

    @Autowired
    private EightdMapper eightdMapper;

    @Autowired
    private EightdService eightdService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEightdMockMvc;

    private Eightd eightd;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EightdResource eightdResource = new EightdResource(eightdService);
        this.restEightdMockMvc = MockMvcBuilders.standaloneSetup(eightdResource)
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
    public static Eightd createEntity(EntityManager em) {
        Eightd eightd = new Eightd()
            .medself(DEFAULT_MEDSELF)
            .medparents(DEFAULT_MEDPARENTS)
            .healthcheck(DEFAULT_HEALTHCHECK);
        return eightd;
    }

    @Before
    public void initTest() {
        eightd = createEntity(em);
    }

    @Test
    @Transactional
    public void createEightd() throws Exception {
        int databaseSizeBeforeCreate = eightdRepository.findAll().size();

        // Create the Eightd
        EightdDTO eightdDTO = eightdMapper.toDto(eightd);
        restEightdMockMvc.perform(post("/api/eightds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightdDTO)))
            .andExpect(status().isCreated());

        // Validate the Eightd in the database
        List<Eightd> eightdList = eightdRepository.findAll();
        assertThat(eightdList).hasSize(databaseSizeBeforeCreate + 1);
        Eightd testEightd = eightdList.get(eightdList.size() - 1);
        assertThat(testEightd.getMedself()).isEqualTo(DEFAULT_MEDSELF);
        assertThat(testEightd.getMedparents()).isEqualTo(DEFAULT_MEDPARENTS);
        assertThat(testEightd.getHealthcheck()).isEqualTo(DEFAULT_HEALTHCHECK);
    }

    @Test
    @Transactional
    public void createEightdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eightdRepository.findAll().size();

        // Create the Eightd with an existing ID
        eightd.setId(1L);
        EightdDTO eightdDTO = eightdMapper.toDto(eightd);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEightdMockMvc.perform(post("/api/eightds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Eightd in the database
        List<Eightd> eightdList = eightdRepository.findAll();
        assertThat(eightdList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEightds() throws Exception {
        // Initialize the database
        eightdRepository.saveAndFlush(eightd);

        // Get all the eightdList
        restEightdMockMvc.perform(get("/api/eightds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eightd.getId().intValue())))
            .andExpect(jsonPath("$.[*].medself").value(hasItem(DEFAULT_MEDSELF)))
            .andExpect(jsonPath("$.[*].medparents").value(hasItem(DEFAULT_MEDPARENTS)))
            .andExpect(jsonPath("$.[*].healthcheck").value(hasItem(DEFAULT_HEALTHCHECK)));
    }

    @Test
    @Transactional
    public void getEightd() throws Exception {
        // Initialize the database
        eightdRepository.saveAndFlush(eightd);

        // Get the eightd
        restEightdMockMvc.perform(get("/api/eightds/{id}", eightd.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eightd.getId().intValue()))
            .andExpect(jsonPath("$.medself").value(DEFAULT_MEDSELF))
            .andExpect(jsonPath("$.medparents").value(DEFAULT_MEDPARENTS))
            .andExpect(jsonPath("$.healthcheck").value(DEFAULT_HEALTHCHECK));
    }

    @Test
    @Transactional
    public void getNonExistingEightd() throws Exception {
        // Get the eightd
        restEightdMockMvc.perform(get("/api/eightds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEightd() throws Exception {
        // Initialize the database
        eightdRepository.saveAndFlush(eightd);
        int databaseSizeBeforeUpdate = eightdRepository.findAll().size();

        // Update the eightd
        Eightd updatedEightd = eightdRepository.findOne(eightd.getId());
        // Disconnect from session so that the updates on updatedEightd are not directly saved in db
        em.detach(updatedEightd);
        updatedEightd
            .medself(UPDATED_MEDSELF)
            .medparents(UPDATED_MEDPARENTS)
            .healthcheck(UPDATED_HEALTHCHECK);
        EightdDTO eightdDTO = eightdMapper.toDto(updatedEightd);

        restEightdMockMvc.perform(put("/api/eightds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightdDTO)))
            .andExpect(status().isOk());

        // Validate the Eightd in the database
        List<Eightd> eightdList = eightdRepository.findAll();
        assertThat(eightdList).hasSize(databaseSizeBeforeUpdate);
        Eightd testEightd = eightdList.get(eightdList.size() - 1);
        assertThat(testEightd.getMedself()).isEqualTo(UPDATED_MEDSELF);
        assertThat(testEightd.getMedparents()).isEqualTo(UPDATED_MEDPARENTS);
        assertThat(testEightd.getHealthcheck()).isEqualTo(UPDATED_HEALTHCHECK);
    }

    @Test
    @Transactional
    public void updateNonExistingEightd() throws Exception {
        int databaseSizeBeforeUpdate = eightdRepository.findAll().size();

        // Create the Eightd
        EightdDTO eightdDTO = eightdMapper.toDto(eightd);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEightdMockMvc.perform(put("/api/eightds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightdDTO)))
            .andExpect(status().isCreated());

        // Validate the Eightd in the database
        List<Eightd> eightdList = eightdRepository.findAll();
        assertThat(eightdList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEightd() throws Exception {
        // Initialize the database
        eightdRepository.saveAndFlush(eightd);
        int databaseSizeBeforeDelete = eightdRepository.findAll().size();

        // Get the eightd
        restEightdMockMvc.perform(delete("/api/eightds/{id}", eightd.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Eightd> eightdList = eightdRepository.findAll();
        assertThat(eightdList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Eightd.class);
        Eightd eightd1 = new Eightd();
        eightd1.setId(1L);
        Eightd eightd2 = new Eightd();
        eightd2.setId(eightd1.getId());
        assertThat(eightd1).isEqualTo(eightd2);
        eightd2.setId(2L);
        assertThat(eightd1).isNotEqualTo(eightd2);
        eightd1.setId(null);
        assertThat(eightd1).isNotEqualTo(eightd2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EightdDTO.class);
        EightdDTO eightdDTO1 = new EightdDTO();
        eightdDTO1.setId(1L);
        EightdDTO eightdDTO2 = new EightdDTO();
        assertThat(eightdDTO1).isNotEqualTo(eightdDTO2);
        eightdDTO2.setId(eightdDTO1.getId());
        assertThat(eightdDTO1).isEqualTo(eightdDTO2);
        eightdDTO2.setId(2L);
        assertThat(eightdDTO1).isNotEqualTo(eightdDTO2);
        eightdDTO1.setId(null);
        assertThat(eightdDTO1).isNotEqualTo(eightdDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eightdMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eightdMapper.fromId(null)).isNull();
    }
}
