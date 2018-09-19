package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Others;
import com.valuequo.buckswise.repository.OthersRepository;
import com.valuequo.buckswise.service.OthersService;
import com.valuequo.buckswise.service.dto.OthersDTO;
import com.valuequo.buckswise.service.mapper.OthersMapper;
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
 * Test class for the OthersResource REST controller.
 *
 * @see OthersResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class OthersResourceIntTest {

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
    private OthersRepository othersRepository;

    @Autowired
    private OthersMapper othersMapper;

    @Autowired
    private OthersService othersService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOthersMockMvc;

    private Others others;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OthersResource othersResource = new OthersResource(othersService);
        this.restOthersMockMvc = MockMvcBuilders.standaloneSetup(othersResource)
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
    public static Others createEntity(EntityManager em) {
        Others others = new Others()
            .uid(DEFAULT_UID)
            .handicapped(DEFAULT_HANDICAPPED)
            .medicaltreat(DEFAULT_MEDICALTREAT)
            .selfedu(DEFAULT_SELFEDU)
            .nps(DEFAULT_NPS)
            .rgess(DEFAULT_RGESS)
            .donation(DEFAULT_DONATION);
        return others;
    }

    @Before
    public void initTest() {
        others = createEntity(em);
    }

    @Test
    @Transactional
    public void createOthers() throws Exception {
        int databaseSizeBeforeCreate = othersRepository.findAll().size();

        // Create the Others
        OthersDTO othersDTO = othersMapper.toDto(others);
        restOthersMockMvc.perform(post("/api/others")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(othersDTO)))
            .andExpect(status().isCreated());

        // Validate the Others in the database
        List<Others> othersList = othersRepository.findAll();
        assertThat(othersList).hasSize(databaseSizeBeforeCreate + 1);
        Others testOthers = othersList.get(othersList.size() - 1);
        assertThat(testOthers.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testOthers.getHandicapped()).isEqualTo(DEFAULT_HANDICAPPED);
        assertThat(testOthers.getMedicaltreat()).isEqualTo(DEFAULT_MEDICALTREAT);
        assertThat(testOthers.getSelfedu()).isEqualTo(DEFAULT_SELFEDU);
        assertThat(testOthers.getNps()).isEqualTo(DEFAULT_NPS);
        assertThat(testOthers.getRgess()).isEqualTo(DEFAULT_RGESS);
        assertThat(testOthers.getDonation()).isEqualTo(DEFAULT_DONATION);
    }

    @Test
    @Transactional
    public void createOthersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = othersRepository.findAll().size();

        // Create the Others with an existing ID
        others.setId(1L);
        OthersDTO othersDTO = othersMapper.toDto(others);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOthersMockMvc.perform(post("/api/others")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(othersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Others in the database
        List<Others> othersList = othersRepository.findAll();
        assertThat(othersList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOthers() throws Exception {
        // Initialize the database
        othersRepository.saveAndFlush(others);

        // Get all the othersList
        restOthersMockMvc.perform(get("/api/others?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(others.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].handicapped").value(hasItem(DEFAULT_HANDICAPPED.toString())))
            .andExpect(jsonPath("$.[*].medicaltreat").value(hasItem(DEFAULT_MEDICALTREAT.toString())))
            .andExpect(jsonPath("$.[*].selfedu").value(hasItem(DEFAULT_SELFEDU.toString())))
            .andExpect(jsonPath("$.[*].nps").value(hasItem(DEFAULT_NPS.toString())))
            .andExpect(jsonPath("$.[*].rgess").value(hasItem(DEFAULT_RGESS.toString())))
            .andExpect(jsonPath("$.[*].donation").value(hasItem(DEFAULT_DONATION.toString())));
    }

    @Test
    @Transactional
    public void getOthers() throws Exception {
        // Initialize the database
        othersRepository.saveAndFlush(others);

        // Get the others
        restOthersMockMvc.perform(get("/api/others/{id}", others.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(others.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.handicapped").value(DEFAULT_HANDICAPPED.toString()))
            .andExpect(jsonPath("$.medicaltreat").value(DEFAULT_MEDICALTREAT.toString()))
            .andExpect(jsonPath("$.selfedu").value(DEFAULT_SELFEDU.toString()))
            .andExpect(jsonPath("$.nps").value(DEFAULT_NPS.toString()))
            .andExpect(jsonPath("$.rgess").value(DEFAULT_RGESS.toString()))
            .andExpect(jsonPath("$.donation").value(DEFAULT_DONATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOthers() throws Exception {
        // Get the others
        restOthersMockMvc.perform(get("/api/others/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOthers() throws Exception {
        // Initialize the database
        othersRepository.saveAndFlush(others);
        int databaseSizeBeforeUpdate = othersRepository.findAll().size();

        // Update the others
        Others updatedOthers = othersRepository.findOne(others.getId());
        // Disconnect from session so that the updates on updatedOthers are not directly saved in db
        em.detach(updatedOthers);
        updatedOthers
            .uid(UPDATED_UID)
            .handicapped(UPDATED_HANDICAPPED)
            .medicaltreat(UPDATED_MEDICALTREAT)
            .selfedu(UPDATED_SELFEDU)
            .nps(UPDATED_NPS)
            .rgess(UPDATED_RGESS)
            .donation(UPDATED_DONATION);
        OthersDTO othersDTO = othersMapper.toDto(updatedOthers);

        restOthersMockMvc.perform(put("/api/others")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(othersDTO)))
            .andExpect(status().isOk());

        // Validate the Others in the database
        List<Others> othersList = othersRepository.findAll();
        assertThat(othersList).hasSize(databaseSizeBeforeUpdate);
        Others testOthers = othersList.get(othersList.size() - 1);
        assertThat(testOthers.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testOthers.getHandicapped()).isEqualTo(UPDATED_HANDICAPPED);
        assertThat(testOthers.getMedicaltreat()).isEqualTo(UPDATED_MEDICALTREAT);
        assertThat(testOthers.getSelfedu()).isEqualTo(UPDATED_SELFEDU);
        assertThat(testOthers.getNps()).isEqualTo(UPDATED_NPS);
        assertThat(testOthers.getRgess()).isEqualTo(UPDATED_RGESS);
        assertThat(testOthers.getDonation()).isEqualTo(UPDATED_DONATION);
    }

    @Test
    @Transactional
    public void updateNonExistingOthers() throws Exception {
        int databaseSizeBeforeUpdate = othersRepository.findAll().size();

        // Create the Others
        OthersDTO othersDTO = othersMapper.toDto(others);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOthersMockMvc.perform(put("/api/others")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(othersDTO)))
            .andExpect(status().isCreated());

        // Validate the Others in the database
        List<Others> othersList = othersRepository.findAll();
        assertThat(othersList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOthers() throws Exception {
        // Initialize the database
        othersRepository.saveAndFlush(others);
        int databaseSizeBeforeDelete = othersRepository.findAll().size();

        // Get the others
        restOthersMockMvc.perform(delete("/api/others/{id}", others.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Others> othersList = othersRepository.findAll();
        assertThat(othersList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Others.class);
        Others others1 = new Others();
        others1.setId(1L);
        Others others2 = new Others();
        others2.setId(others1.getId());
        assertThat(others1).isEqualTo(others2);
        others2.setId(2L);
        assertThat(others1).isNotEqualTo(others2);
        others1.setId(null);
        assertThat(others1).isNotEqualTo(others2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OthersDTO.class);
        OthersDTO othersDTO1 = new OthersDTO();
        othersDTO1.setId(1L);
        OthersDTO othersDTO2 = new OthersDTO();
        assertThat(othersDTO1).isNotEqualTo(othersDTO2);
        othersDTO2.setId(othersDTO1.getId());
        assertThat(othersDTO1).isEqualTo(othersDTO2);
        othersDTO2.setId(2L);
        assertThat(othersDTO1).isNotEqualTo(othersDTO2);
        othersDTO1.setId(null);
        assertThat(othersDTO1).isNotEqualTo(othersDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(othersMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(othersMapper.fromId(null)).isNull();
    }
}
