package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Advisor;
import com.valuequo.buckswise.repository.AdvisorRepository;
import com.valuequo.buckswise.service.AdvisorService;
import com.valuequo.buckswise.service.dto.AdvisorDTO;
import com.valuequo.buckswise.service.mapper.AdvisorMapper;
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
 * Test class for the AdvisorResource REST controller.
 *
 * @see AdvisorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class AdvisorResourceIntTest {

    private static final Long DEFAULT_UID = 1L;
    private static final Long UPDATED_UID = 2L;

    private static final String DEFAULT_RECOTYPE = "AAAAAAAAAA";
    private static final String UPDATED_RECOTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RECO = "AAAAAAAAAA";
    private static final String UPDATED_RECO = "BBBBBBBBBB";

    private static final String DEFAULT_RECOBY = "AAAAAAAAAA";
    private static final String UPDATED_RECOBY = "BBBBBBBBBB";

    private static final String DEFAULT_RECODATE = "AAAAAAAAAA";
    private static final String UPDATED_RECODATE = "BBBBBBBBBB";

    private static final String DEFAULT_USERRESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_USERRESPONSE = "BBBBBBBBBB";

    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private AdvisorMapper advisorMapper;

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdvisorMockMvc;

    private Advisor advisor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdvisorResource advisorResource = new AdvisorResource(advisorService);
        this.restAdvisorMockMvc = MockMvcBuilders.standaloneSetup(advisorResource)
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
    public static Advisor createEntity(EntityManager em) {
        Advisor advisor = new Advisor()
            .uid(DEFAULT_UID)
            .recotype(DEFAULT_RECOTYPE)
            .reco(DEFAULT_RECO)
            .recoby(DEFAULT_RECOBY)
            .recodate(DEFAULT_RECODATE)
            .userresponse(DEFAULT_USERRESPONSE);
        return advisor;
    }

    @Before
    public void initTest() {
        advisor = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdvisor() throws Exception {
        int databaseSizeBeforeCreate = advisorRepository.findAll().size();

        // Create the Advisor
        AdvisorDTO advisorDTO = advisorMapper.toDto(advisor);
        restAdvisorMockMvc.perform(post("/api/advisors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advisorDTO)))
            .andExpect(status().isCreated());

        // Validate the Advisor in the database
        List<Advisor> advisorList = advisorRepository.findAll();
        assertThat(advisorList).hasSize(databaseSizeBeforeCreate + 1);
        Advisor testAdvisor = advisorList.get(advisorList.size() - 1);
        assertThat(testAdvisor.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testAdvisor.getRecotype()).isEqualTo(DEFAULT_RECOTYPE);
        assertThat(testAdvisor.getReco()).isEqualTo(DEFAULT_RECO);
        assertThat(testAdvisor.getRecoby()).isEqualTo(DEFAULT_RECOBY);
        assertThat(testAdvisor.getRecodate()).isEqualTo(DEFAULT_RECODATE);
        assertThat(testAdvisor.getUserresponse()).isEqualTo(DEFAULT_USERRESPONSE);
    }

    @Test
    @Transactional
    public void createAdvisorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = advisorRepository.findAll().size();

        // Create the Advisor with an existing ID
        advisor.setId(1L);
        AdvisorDTO advisorDTO = advisorMapper.toDto(advisor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdvisorMockMvc.perform(post("/api/advisors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advisorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Advisor in the database
        List<Advisor> advisorList = advisorRepository.findAll();
        assertThat(advisorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdvisors() throws Exception {
        // Initialize the database
        advisorRepository.saveAndFlush(advisor);

        // Get all the advisorList
        restAdvisorMockMvc.perform(get("/api/advisors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(advisor.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID.intValue())))
            .andExpect(jsonPath("$.[*].recotype").value(hasItem(DEFAULT_RECOTYPE.toString())))
            .andExpect(jsonPath("$.[*].reco").value(hasItem(DEFAULT_RECO.toString())))
            .andExpect(jsonPath("$.[*].recoby").value(hasItem(DEFAULT_RECOBY.toString())))
            .andExpect(jsonPath("$.[*].recodate").value(hasItem(DEFAULT_RECODATE.toString())))
            .andExpect(jsonPath("$.[*].userresponse").value(hasItem(DEFAULT_USERRESPONSE.toString())));
    }

    @Test
    @Transactional
    public void getAdvisor() throws Exception {
        // Initialize the database
        advisorRepository.saveAndFlush(advisor);

        // Get the advisor
        restAdvisorMockMvc.perform(get("/api/advisors/{id}", advisor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(advisor.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID.intValue()))
            .andExpect(jsonPath("$.recotype").value(DEFAULT_RECOTYPE.toString()))
            .andExpect(jsonPath("$.reco").value(DEFAULT_RECO.toString()))
            .andExpect(jsonPath("$.recoby").value(DEFAULT_RECOBY.toString()))
            .andExpect(jsonPath("$.recodate").value(DEFAULT_RECODATE.toString()))
            .andExpect(jsonPath("$.userresponse").value(DEFAULT_USERRESPONSE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdvisor() throws Exception {
        // Get the advisor
        restAdvisorMockMvc.perform(get("/api/advisors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdvisor() throws Exception {
        // Initialize the database
        advisorRepository.saveAndFlush(advisor);
        int databaseSizeBeforeUpdate = advisorRepository.findAll().size();

        // Update the advisor
        Advisor updatedAdvisor = advisorRepository.findOne(advisor.getId());
        // Disconnect from session so that the updates on updatedAdvisor are not directly saved in db
        em.detach(updatedAdvisor);
        updatedAdvisor
            .uid(UPDATED_UID)
            .recotype(UPDATED_RECOTYPE)
            .reco(UPDATED_RECO)
            .recoby(UPDATED_RECOBY)
            .recodate(UPDATED_RECODATE)
            .userresponse(UPDATED_USERRESPONSE);
        AdvisorDTO advisorDTO = advisorMapper.toDto(updatedAdvisor);

        restAdvisorMockMvc.perform(put("/api/advisors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advisorDTO)))
            .andExpect(status().isOk());

        // Validate the Advisor in the database
        List<Advisor> advisorList = advisorRepository.findAll();
        assertThat(advisorList).hasSize(databaseSizeBeforeUpdate);
        Advisor testAdvisor = advisorList.get(advisorList.size() - 1);
        assertThat(testAdvisor.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testAdvisor.getRecotype()).isEqualTo(UPDATED_RECOTYPE);
        assertThat(testAdvisor.getReco()).isEqualTo(UPDATED_RECO);
        assertThat(testAdvisor.getRecoby()).isEqualTo(UPDATED_RECOBY);
        assertThat(testAdvisor.getRecodate()).isEqualTo(UPDATED_RECODATE);
        assertThat(testAdvisor.getUserresponse()).isEqualTo(UPDATED_USERRESPONSE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdvisor() throws Exception {
        int databaseSizeBeforeUpdate = advisorRepository.findAll().size();

        // Create the Advisor
        AdvisorDTO advisorDTO = advisorMapper.toDto(advisor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdvisorMockMvc.perform(put("/api/advisors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advisorDTO)))
            .andExpect(status().isCreated());

        // Validate the Advisor in the database
        List<Advisor> advisorList = advisorRepository.findAll();
        assertThat(advisorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAdvisor() throws Exception {
        // Initialize the database
        advisorRepository.saveAndFlush(advisor);
        int databaseSizeBeforeDelete = advisorRepository.findAll().size();

        // Get the advisor
        restAdvisorMockMvc.perform(delete("/api/advisors/{id}", advisor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Advisor> advisorList = advisorRepository.findAll();
        assertThat(advisorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Advisor.class);
        Advisor advisor1 = new Advisor();
        advisor1.setId(1L);
        Advisor advisor2 = new Advisor();
        advisor2.setId(advisor1.getId());
        assertThat(advisor1).isEqualTo(advisor2);
        advisor2.setId(2L);
        assertThat(advisor1).isNotEqualTo(advisor2);
        advisor1.setId(null);
        assertThat(advisor1).isNotEqualTo(advisor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdvisorDTO.class);
        AdvisorDTO advisorDTO1 = new AdvisorDTO();
        advisorDTO1.setId(1L);
        AdvisorDTO advisorDTO2 = new AdvisorDTO();
        assertThat(advisorDTO1).isNotEqualTo(advisorDTO2);
        advisorDTO2.setId(advisorDTO1.getId());
        assertThat(advisorDTO1).isEqualTo(advisorDTO2);
        advisorDTO2.setId(2L);
        assertThat(advisorDTO1).isNotEqualTo(advisorDTO2);
        advisorDTO1.setId(null);
        assertThat(advisorDTO1).isNotEqualTo(advisorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(advisorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(advisorMapper.fromId(null)).isNull();
    }
}
