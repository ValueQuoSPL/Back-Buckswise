package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseBackEndApp;

import com.valuequo.buckswise.domain.Gross;
import com.valuequo.buckswise.repository.GrossRepository;
import com.valuequo.buckswise.service.GrossService;
import com.valuequo.buckswise.service.dto.GrossDTO;
import com.valuequo.buckswise.service.mapper.GrossMapper;
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
 * Test class for the GrossResource REST controller.
 *
 * @see GrossResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseBackEndApp.class)
public class GrossResourceIntTest {

    private static final Integer DEFAULT_BSALARY = 1;
    private static final Integer UPDATED_BSALARY = 2;

    private static final Integer DEFAULT_DA = 1;
    private static final Integer UPDATED_DA = 2;

    private static final Integer DEFAULT_HRA = 1;
    private static final Integer UPDATED_HRA = 2;

    private static final Integer DEFAULT_CONVEYANCE = 1;
    private static final Integer UPDATED_CONVEYANCE = 2;

    private static final Integer DEFAULT_CHILDEDU = 1;
    private static final Integer UPDATED_CHILDEDU = 2;

    private static final Integer DEFAULT_MEDICAL = 1;
    private static final Integer UPDATED_MEDICAL = 2;

    private static final Integer DEFAULT_LTA = 1;
    private static final Integer UPDATED_LTA = 2;

    private static final Integer DEFAULT_OTHERALLOWN = 1;
    private static final Integer UPDATED_OTHERALLOWN = 2;

    private static final Integer DEFAULT_BONUS = 1;
    private static final Integer UPDATED_BONUS = 2;

    private static final Integer DEFAULT_RENTINCOME = 1;
    private static final Integer UPDATED_RENTINCOME = 2;

    private static final Integer DEFAULT_SAVING = 1;
    private static final Integer UPDATED_SAVING = 2;

    private static final Integer DEFAULT_BONDS = 1;
    private static final Integer UPDATED_BONDS = 2;

    private static final Integer DEFAULT_CONVEYANCEOTHER = 1;
    private static final Integer UPDATED_CONVEYANCEOTHER = 2;

    @Autowired
    private GrossRepository grossRepository;

    @Autowired
    private GrossMapper grossMapper;

    @Autowired
    private GrossService grossService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGrossMockMvc;

    private Gross gross;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrossResource grossResource = new GrossResource(grossService);
        this.restGrossMockMvc = MockMvcBuilders.standaloneSetup(grossResource)
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
    public static Gross createEntity(EntityManager em) {
        Gross gross = new Gross()
            .bsalary(DEFAULT_BSALARY)
            .da(DEFAULT_DA)
            .hra(DEFAULT_HRA)
            .conveyance(DEFAULT_CONVEYANCE)
            .childedu(DEFAULT_CHILDEDU)
            .medical(DEFAULT_MEDICAL)
            .lta(DEFAULT_LTA)
            .otherallown(DEFAULT_OTHERALLOWN)
            .bonus(DEFAULT_BONUS)
            .rentincome(DEFAULT_RENTINCOME)
            .saving(DEFAULT_SAVING)
            .bonds(DEFAULT_BONDS)
            .conveyanceother(DEFAULT_CONVEYANCEOTHER);
        return gross;
    }

    @Before
    public void initTest() {
        gross = createEntity(em);
    }

    @Test
    @Transactional
    public void createGross() throws Exception {
        int databaseSizeBeforeCreate = grossRepository.findAll().size();

        // Create the Gross
        GrossDTO grossDTO = grossMapper.toDto(gross);
        restGrossMockMvc.perform(post("/api/grosses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grossDTO)))
            .andExpect(status().isCreated());

        // Validate the Gross in the database
        List<Gross> grossList = grossRepository.findAll();
        assertThat(grossList).hasSize(databaseSizeBeforeCreate + 1);
        Gross testGross = grossList.get(grossList.size() - 1);
        assertThat(testGross.getBsalary()).isEqualTo(DEFAULT_BSALARY);
        assertThat(testGross.getDa()).isEqualTo(DEFAULT_DA);
        assertThat(testGross.getHra()).isEqualTo(DEFAULT_HRA);
        assertThat(testGross.getConveyance()).isEqualTo(DEFAULT_CONVEYANCE);
        assertThat(testGross.getChildedu()).isEqualTo(DEFAULT_CHILDEDU);
        assertThat(testGross.getMedical()).isEqualTo(DEFAULT_MEDICAL);
        assertThat(testGross.getLta()).isEqualTo(DEFAULT_LTA);
        assertThat(testGross.getOtherallown()).isEqualTo(DEFAULT_OTHERALLOWN);
        assertThat(testGross.getBonus()).isEqualTo(DEFAULT_BONUS);
        assertThat(testGross.getRentincome()).isEqualTo(DEFAULT_RENTINCOME);
        assertThat(testGross.getSaving()).isEqualTo(DEFAULT_SAVING);
        assertThat(testGross.getBonds()).isEqualTo(DEFAULT_BONDS);
        assertThat(testGross.getConveyanceother()).isEqualTo(DEFAULT_CONVEYANCEOTHER);
    }

    @Test
    @Transactional
    public void createGrossWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grossRepository.findAll().size();

        // Create the Gross with an existing ID
        gross.setId(1L);
        GrossDTO grossDTO = grossMapper.toDto(gross);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrossMockMvc.perform(post("/api/grosses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grossDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Gross in the database
        List<Gross> grossList = grossRepository.findAll();
        assertThat(grossList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGrosses() throws Exception {
        // Initialize the database
        grossRepository.saveAndFlush(gross);

        // Get all the grossList
        restGrossMockMvc.perform(get("/api/grosses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gross.getId().intValue())))
            .andExpect(jsonPath("$.[*].bsalary").value(hasItem(DEFAULT_BSALARY)))
            .andExpect(jsonPath("$.[*].da").value(hasItem(DEFAULT_DA)))
            .andExpect(jsonPath("$.[*].hra").value(hasItem(DEFAULT_HRA)))
            .andExpect(jsonPath("$.[*].conveyance").value(hasItem(DEFAULT_CONVEYANCE)))
            .andExpect(jsonPath("$.[*].childedu").value(hasItem(DEFAULT_CHILDEDU)))
            .andExpect(jsonPath("$.[*].medical").value(hasItem(DEFAULT_MEDICAL)))
            .andExpect(jsonPath("$.[*].lta").value(hasItem(DEFAULT_LTA)))
            .andExpect(jsonPath("$.[*].otherallown").value(hasItem(DEFAULT_OTHERALLOWN)))
            .andExpect(jsonPath("$.[*].bonus").value(hasItem(DEFAULT_BONUS)))
            .andExpect(jsonPath("$.[*].rentincome").value(hasItem(DEFAULT_RENTINCOME)))
            .andExpect(jsonPath("$.[*].saving").value(hasItem(DEFAULT_SAVING)))
            .andExpect(jsonPath("$.[*].bonds").value(hasItem(DEFAULT_BONDS)))
            .andExpect(jsonPath("$.[*].conveyanceother").value(hasItem(DEFAULT_CONVEYANCEOTHER)));
    }

    @Test
    @Transactional
    public void getGross() throws Exception {
        // Initialize the database
        grossRepository.saveAndFlush(gross);

        // Get the gross
        restGrossMockMvc.perform(get("/api/grosses/{id}", gross.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gross.getId().intValue()))
            .andExpect(jsonPath("$.bsalary").value(DEFAULT_BSALARY))
            .andExpect(jsonPath("$.da").value(DEFAULT_DA))
            .andExpect(jsonPath("$.hra").value(DEFAULT_HRA))
            .andExpect(jsonPath("$.conveyance").value(DEFAULT_CONVEYANCE))
            .andExpect(jsonPath("$.childedu").value(DEFAULT_CHILDEDU))
            .andExpect(jsonPath("$.medical").value(DEFAULT_MEDICAL))
            .andExpect(jsonPath("$.lta").value(DEFAULT_LTA))
            .andExpect(jsonPath("$.otherallown").value(DEFAULT_OTHERALLOWN))
            .andExpect(jsonPath("$.bonus").value(DEFAULT_BONUS))
            .andExpect(jsonPath("$.rentincome").value(DEFAULT_RENTINCOME))
            .andExpect(jsonPath("$.saving").value(DEFAULT_SAVING))
            .andExpect(jsonPath("$.bonds").value(DEFAULT_BONDS))
            .andExpect(jsonPath("$.conveyanceother").value(DEFAULT_CONVEYANCEOTHER));
    }

    @Test
    @Transactional
    public void getNonExistingGross() throws Exception {
        // Get the gross
        restGrossMockMvc.perform(get("/api/grosses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGross() throws Exception {
        // Initialize the database
        grossRepository.saveAndFlush(gross);
        int databaseSizeBeforeUpdate = grossRepository.findAll().size();

        // Update the gross
        Gross updatedGross = grossRepository.findOne(gross.getId());
        // Disconnect from session so that the updates on updatedGross are not directly saved in db
        em.detach(updatedGross);
        updatedGross
            .bsalary(UPDATED_BSALARY)
            .da(UPDATED_DA)
            .hra(UPDATED_HRA)
            .conveyance(UPDATED_CONVEYANCE)
            .childedu(UPDATED_CHILDEDU)
            .medical(UPDATED_MEDICAL)
            .lta(UPDATED_LTA)
            .otherallown(UPDATED_OTHERALLOWN)
            .bonus(UPDATED_BONUS)
            .rentincome(UPDATED_RENTINCOME)
            .saving(UPDATED_SAVING)
            .bonds(UPDATED_BONDS)
            .conveyanceother(UPDATED_CONVEYANCEOTHER);
        GrossDTO grossDTO = grossMapper.toDto(updatedGross);

        restGrossMockMvc.perform(put("/api/grosses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grossDTO)))
            .andExpect(status().isOk());

        // Validate the Gross in the database
        List<Gross> grossList = grossRepository.findAll();
        assertThat(grossList).hasSize(databaseSizeBeforeUpdate);
        Gross testGross = grossList.get(grossList.size() - 1);
        assertThat(testGross.getBsalary()).isEqualTo(UPDATED_BSALARY);
        assertThat(testGross.getDa()).isEqualTo(UPDATED_DA);
        assertThat(testGross.getHra()).isEqualTo(UPDATED_HRA);
        assertThat(testGross.getConveyance()).isEqualTo(UPDATED_CONVEYANCE);
        assertThat(testGross.getChildedu()).isEqualTo(UPDATED_CHILDEDU);
        assertThat(testGross.getMedical()).isEqualTo(UPDATED_MEDICAL);
        assertThat(testGross.getLta()).isEqualTo(UPDATED_LTA);
        assertThat(testGross.getOtherallown()).isEqualTo(UPDATED_OTHERALLOWN);
        assertThat(testGross.getBonus()).isEqualTo(UPDATED_BONUS);
        assertThat(testGross.getRentincome()).isEqualTo(UPDATED_RENTINCOME);
        assertThat(testGross.getSaving()).isEqualTo(UPDATED_SAVING);
        assertThat(testGross.getBonds()).isEqualTo(UPDATED_BONDS);
        assertThat(testGross.getConveyanceother()).isEqualTo(UPDATED_CONVEYANCEOTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingGross() throws Exception {
        int databaseSizeBeforeUpdate = grossRepository.findAll().size();

        // Create the Gross
        GrossDTO grossDTO = grossMapper.toDto(gross);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGrossMockMvc.perform(put("/api/grosses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grossDTO)))
            .andExpect(status().isCreated());

        // Validate the Gross in the database
        List<Gross> grossList = grossRepository.findAll();
        assertThat(grossList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGross() throws Exception {
        // Initialize the database
        grossRepository.saveAndFlush(gross);
        int databaseSizeBeforeDelete = grossRepository.findAll().size();

        // Get the gross
        restGrossMockMvc.perform(delete("/api/grosses/{id}", gross.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Gross> grossList = grossRepository.findAll();
        assertThat(grossList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gross.class);
        Gross gross1 = new Gross();
        gross1.setId(1L);
        Gross gross2 = new Gross();
        gross2.setId(gross1.getId());
        assertThat(gross1).isEqualTo(gross2);
        gross2.setId(2L);
        assertThat(gross1).isNotEqualTo(gross2);
        gross1.setId(null);
        assertThat(gross1).isNotEqualTo(gross2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrossDTO.class);
        GrossDTO grossDTO1 = new GrossDTO();
        grossDTO1.setId(1L);
        GrossDTO grossDTO2 = new GrossDTO();
        assertThat(grossDTO1).isNotEqualTo(grossDTO2);
        grossDTO2.setId(grossDTO1.getId());
        assertThat(grossDTO1).isEqualTo(grossDTO2);
        grossDTO2.setId(2L);
        assertThat(grossDTO1).isNotEqualTo(grossDTO2);
        grossDTO1.setId(null);
        assertThat(grossDTO1).isNotEqualTo(grossDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(grossMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(grossMapper.fromId(null)).isNull();
    }
}
