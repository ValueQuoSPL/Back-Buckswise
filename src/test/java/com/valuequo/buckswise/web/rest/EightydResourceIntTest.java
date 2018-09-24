package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Eightyd;
import com.valuequo.buckswise.repository.EightydRepository;
import com.valuequo.buckswise.service.EightydService;
import com.valuequo.buckswise.service.dto.EightydDTO;
import com.valuequo.buckswise.service.mapper.EightydMapper;
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
 * Test class for the EightydResource REST controller.
 *
 * @see EightydResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class EightydResourceIntTest {

    private static final Integer DEFAULT_UID = 1;
    private static final Integer UPDATED_UID = 2;

    private static final String DEFAULT_MEDSELF = "AAAAAAAAAA";
    private static final String UPDATED_MEDSELF = "BBBBBBBBBB";

    private static final String DEFAULT_MEDPARENTS = "AAAAAAAAAA";
    private static final String UPDATED_MEDPARENTS = "BBBBBBBBBB";

    private static final String DEFAULT_HEALTHCHECK = "AAAAAAAAAA";
    private static final String UPDATED_HEALTHCHECK = "BBBBBBBBBB";

    @Autowired
    private EightydRepository eightydRepository;

    @Autowired
    private EightydMapper eightydMapper;

    @Autowired
    private EightydService eightydService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEightydMockMvc;

    private Eightyd eightyd;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EightydResource eightydResource = new EightydResource(eightydService);
        this.restEightydMockMvc = MockMvcBuilders.standaloneSetup(eightydResource)
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
    public static Eightyd createEntity(EntityManager em) {
        Eightyd eightyd = new Eightyd()
            .uid(DEFAULT_UID)
            .medself(DEFAULT_MEDSELF)
            .medparents(DEFAULT_MEDPARENTS)
            .healthcheck(DEFAULT_HEALTHCHECK);
        return eightyd;
    }

    @Before
    public void initTest() {
        eightyd = createEntity(em);
    }

    @Test
    @Transactional
    public void createEightyd() throws Exception {
        int databaseSizeBeforeCreate = eightydRepository.findAll().size();

        // Create the Eightyd
        EightydDTO eightydDTO = eightydMapper.toDto(eightyd);
        restEightydMockMvc.perform(post("/api/eightyds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightydDTO)))
            .andExpect(status().isCreated());

        // Validate the Eightyd in the database
        List<Eightyd> eightydList = eightydRepository.findAll();
        assertThat(eightydList).hasSize(databaseSizeBeforeCreate + 1);
        Eightyd testEightyd = eightydList.get(eightydList.size() - 1);
        assertThat(testEightyd.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testEightyd.getMedself()).isEqualTo(DEFAULT_MEDSELF);
        assertThat(testEightyd.getMedparents()).isEqualTo(DEFAULT_MEDPARENTS);
        assertThat(testEightyd.getHealthcheck()).isEqualTo(DEFAULT_HEALTHCHECK);
    }

    @Test
    @Transactional
    public void createEightydWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eightydRepository.findAll().size();

        // Create the Eightyd with an existing ID
        eightyd.setId(1L);
        EightydDTO eightydDTO = eightydMapper.toDto(eightyd);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEightydMockMvc.perform(post("/api/eightyds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightydDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Eightyd in the database
        List<Eightyd> eightydList = eightydRepository.findAll();
        assertThat(eightydList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEightyds() throws Exception {
        // Initialize the database
        eightydRepository.saveAndFlush(eightyd);

        // Get all the eightydList
        restEightydMockMvc.perform(get("/api/eightyds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eightyd.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].medself").value(hasItem(DEFAULT_MEDSELF.toString())))
            .andExpect(jsonPath("$.[*].medparents").value(hasItem(DEFAULT_MEDPARENTS.toString())))
            .andExpect(jsonPath("$.[*].healthcheck").value(hasItem(DEFAULT_HEALTHCHECK.toString())));
    }

    @Test
    @Transactional
    public void getEightyd() throws Exception {
        // Initialize the database
        eightydRepository.saveAndFlush(eightyd);

        // Get the eightyd
        restEightydMockMvc.perform(get("/api/eightyds/{id}", eightyd.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eightyd.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.medself").value(DEFAULT_MEDSELF.toString()))
            .andExpect(jsonPath("$.medparents").value(DEFAULT_MEDPARENTS.toString()))
            .andExpect(jsonPath("$.healthcheck").value(DEFAULT_HEALTHCHECK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEightyd() throws Exception {
        // Get the eightyd
        restEightydMockMvc.perform(get("/api/eightyds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEightyd() throws Exception {
        // Initialize the database
        eightydRepository.saveAndFlush(eightyd);
        int databaseSizeBeforeUpdate = eightydRepository.findAll().size();

        // Update the eightyd
        Eightyd updatedEightyd = eightydRepository.findOne(eightyd.getId());
        // Disconnect from session so that the updates on updatedEightyd are not directly saved in db
        em.detach(updatedEightyd);
        updatedEightyd
            .uid(UPDATED_UID)
            .medself(UPDATED_MEDSELF)
            .medparents(UPDATED_MEDPARENTS)
            .healthcheck(UPDATED_HEALTHCHECK);
        EightydDTO eightydDTO = eightydMapper.toDto(updatedEightyd);

        restEightydMockMvc.perform(put("/api/eightyds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightydDTO)))
            .andExpect(status().isOk());

        // Validate the Eightyd in the database
        List<Eightyd> eightydList = eightydRepository.findAll();
        assertThat(eightydList).hasSize(databaseSizeBeforeUpdate);
        Eightyd testEightyd = eightydList.get(eightydList.size() - 1);
        assertThat(testEightyd.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testEightyd.getMedself()).isEqualTo(UPDATED_MEDSELF);
        assertThat(testEightyd.getMedparents()).isEqualTo(UPDATED_MEDPARENTS);
        assertThat(testEightyd.getHealthcheck()).isEqualTo(UPDATED_HEALTHCHECK);
    }

    @Test
    @Transactional
    public void updateNonExistingEightyd() throws Exception {
        int databaseSizeBeforeUpdate = eightydRepository.findAll().size();

        // Create the Eightyd
        EightydDTO eightydDTO = eightydMapper.toDto(eightyd);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEightydMockMvc.perform(put("/api/eightyds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eightydDTO)))
            .andExpect(status().isCreated());

        // Validate the Eightyd in the database
        List<Eightyd> eightydList = eightydRepository.findAll();
        assertThat(eightydList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEightyd() throws Exception {
        // Initialize the database
        eightydRepository.saveAndFlush(eightyd);
        int databaseSizeBeforeDelete = eightydRepository.findAll().size();

        // Get the eightyd
        restEightydMockMvc.perform(delete("/api/eightyds/{id}", eightyd.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Eightyd> eightydList = eightydRepository.findAll();
        assertThat(eightydList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Eightyd.class);
        Eightyd eightyd1 = new Eightyd();
        eightyd1.setId(1L);
        Eightyd eightyd2 = new Eightyd();
        eightyd2.setId(eightyd1.getId());
        assertThat(eightyd1).isEqualTo(eightyd2);
        eightyd2.setId(2L);
        assertThat(eightyd1).isNotEqualTo(eightyd2);
        eightyd1.setId(null);
        assertThat(eightyd1).isNotEqualTo(eightyd2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EightydDTO.class);
        EightydDTO eightydDTO1 = new EightydDTO();
        eightydDTO1.setId(1L);
        EightydDTO eightydDTO2 = new EightydDTO();
        assertThat(eightydDTO1).isNotEqualTo(eightydDTO2);
        eightydDTO2.setId(eightydDTO1.getId());
        assertThat(eightydDTO1).isEqualTo(eightydDTO2);
        eightydDTO2.setId(2L);
        assertThat(eightydDTO1).isNotEqualTo(eightydDTO2);
        eightydDTO1.setId(null);
        assertThat(eightydDTO1).isNotEqualTo(eightydDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eightydMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eightydMapper.fromId(null)).isNull();
    }
}
