package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Grossdeduct;
import com.valuequo.buckswise.repository.GrossdeductRepository;
import com.valuequo.buckswise.service.GrossdeductService;
import com.valuequo.buckswise.service.dto.GrossdeductDTO;
import com.valuequo.buckswise.service.mapper.GrossdeductMapper;
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
 * Test class for the GrossdeductResource REST controller.
 *
 * @see GrossdeductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class GrossdeductResourceIntTest {

    private static final Integer DEFAULT_UID = 1;
    private static final Integer UPDATED_UID = 2;

    private static final String DEFAULT_BSALARY = "AAAAAAAAAA";
    private static final String UPDATED_BSALARY = "BBBBBBBBBB";

    private static final String DEFAULT_DA = "AAAAAAAAAA";
    private static final String UPDATED_DA = "BBBBBBBBBB";

    private static final String DEFAULT_HRA = "AAAAAAAAAA";
    private static final String UPDATED_HRA = "BBBBBBBBBB";

    private static final String DEFAULT_CONVEYANCE = "AAAAAAAAAA";
    private static final String UPDATED_CONVEYANCE = "BBBBBBBBBB";

    private static final String DEFAULT_CHILDEDU = "AAAAAAAAAA";
    private static final String UPDATED_CHILDEDU = "BBBBBBBBBB";

    private static final String DEFAULT_MEDICAL = "AAAAAAAAAA";
    private static final String UPDATED_MEDICAL = "BBBBBBBBBB";

    private static final String DEFAULT_LTA = "AAAAAAAAAA";
    private static final String UPDATED_LTA = "BBBBBBBBBB";

    private static final String DEFAULT_OTHERALLOWN = "AAAAAAAAAA";
    private static final String UPDATED_OTHERALLOWN = "BBBBBBBBBB";

    private static final String DEFAULT_BONUS = "AAAAAAAAAA";
    private static final String UPDATED_BONUS = "BBBBBBBBBB";

    private static final String DEFAULT_RENTINCOME = "AAAAAAAAAA";
    private static final String UPDATED_RENTINCOME = "BBBBBBBBBB";

    private static final String DEFAULT_SAVING = "AAAAAAAAAA";
    private static final String UPDATED_SAVING = "BBBBBBBBBB";

    private static final String DEFAULT_BONDS = "AAAAAAAAAA";
    private static final String UPDATED_BONDS = "BBBBBBBBBB";

    private static final String DEFAULT_CONVEYANCEOTHER = "AAAAAAAAAA";
    private static final String UPDATED_CONVEYANCEOTHER = "BBBBBBBBBB";

    @Autowired
    private GrossdeductRepository grossdeductRepository;

    @Autowired
    private GrossdeductMapper grossdeductMapper;

    @Autowired
    private GrossdeductService grossdeductService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGrossdeductMockMvc;

    private Grossdeduct grossdeduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrossdeductResource grossdeductResource = new GrossdeductResource(grossdeductService);
        this.restGrossdeductMockMvc = MockMvcBuilders.standaloneSetup(grossdeductResource)
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
    public static Grossdeduct createEntity(EntityManager em) {
        Grossdeduct grossdeduct = new Grossdeduct()
            .uid(DEFAULT_UID)
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
        return grossdeduct;
    }

    @Before
    public void initTest() {
        grossdeduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrossdeduct() throws Exception {
        int databaseSizeBeforeCreate = grossdeductRepository.findAll().size();

        // Create the Grossdeduct
        GrossdeductDTO grossdeductDTO = grossdeductMapper.toDto(grossdeduct);
        restGrossdeductMockMvc.perform(post("/api/grossdeducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grossdeductDTO)))
            .andExpect(status().isCreated());

        // Validate the Grossdeduct in the database
        List<Grossdeduct> grossdeductList = grossdeductRepository.findAll();
        assertThat(grossdeductList).hasSize(databaseSizeBeforeCreate + 1);
        Grossdeduct testGrossdeduct = grossdeductList.get(grossdeductList.size() - 1);
        assertThat(testGrossdeduct.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testGrossdeduct.getBsalary()).isEqualTo(DEFAULT_BSALARY);
        assertThat(testGrossdeduct.getDa()).isEqualTo(DEFAULT_DA);
        assertThat(testGrossdeduct.getHra()).isEqualTo(DEFAULT_HRA);
        assertThat(testGrossdeduct.getConveyance()).isEqualTo(DEFAULT_CONVEYANCE);
        assertThat(testGrossdeduct.getChildedu()).isEqualTo(DEFAULT_CHILDEDU);
        assertThat(testGrossdeduct.getMedical()).isEqualTo(DEFAULT_MEDICAL);
        assertThat(testGrossdeduct.getLta()).isEqualTo(DEFAULT_LTA);
        assertThat(testGrossdeduct.getOtherallown()).isEqualTo(DEFAULT_OTHERALLOWN);
        assertThat(testGrossdeduct.getBonus()).isEqualTo(DEFAULT_BONUS);
        assertThat(testGrossdeduct.getRentincome()).isEqualTo(DEFAULT_RENTINCOME);
        assertThat(testGrossdeduct.getSaving()).isEqualTo(DEFAULT_SAVING);
        assertThat(testGrossdeduct.getBonds()).isEqualTo(DEFAULT_BONDS);
        assertThat(testGrossdeduct.getConveyanceother()).isEqualTo(DEFAULT_CONVEYANCEOTHER);
    }

    @Test
    @Transactional
    public void createGrossdeductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grossdeductRepository.findAll().size();

        // Create the Grossdeduct with an existing ID
        grossdeduct.setId(1L);
        GrossdeductDTO grossdeductDTO = grossdeductMapper.toDto(grossdeduct);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrossdeductMockMvc.perform(post("/api/grossdeducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grossdeductDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Grossdeduct in the database
        List<Grossdeduct> grossdeductList = grossdeductRepository.findAll();
        assertThat(grossdeductList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGrossdeducts() throws Exception {
        // Initialize the database
        grossdeductRepository.saveAndFlush(grossdeduct);

        // Get all the grossdeductList
        restGrossdeductMockMvc.perform(get("/api/grossdeducts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grossdeduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].bsalary").value(hasItem(DEFAULT_BSALARY.toString())))
            .andExpect(jsonPath("$.[*].da").value(hasItem(DEFAULT_DA.toString())))
            .andExpect(jsonPath("$.[*].hra").value(hasItem(DEFAULT_HRA.toString())))
            .andExpect(jsonPath("$.[*].conveyance").value(hasItem(DEFAULT_CONVEYANCE.toString())))
            .andExpect(jsonPath("$.[*].childedu").value(hasItem(DEFAULT_CHILDEDU.toString())))
            .andExpect(jsonPath("$.[*].medical").value(hasItem(DEFAULT_MEDICAL.toString())))
            .andExpect(jsonPath("$.[*].lta").value(hasItem(DEFAULT_LTA.toString())))
            .andExpect(jsonPath("$.[*].otherallown").value(hasItem(DEFAULT_OTHERALLOWN.toString())))
            .andExpect(jsonPath("$.[*].bonus").value(hasItem(DEFAULT_BONUS.toString())))
            .andExpect(jsonPath("$.[*].rentincome").value(hasItem(DEFAULT_RENTINCOME.toString())))
            .andExpect(jsonPath("$.[*].saving").value(hasItem(DEFAULT_SAVING.toString())))
            .andExpect(jsonPath("$.[*].bonds").value(hasItem(DEFAULT_BONDS.toString())))
            .andExpect(jsonPath("$.[*].conveyanceother").value(hasItem(DEFAULT_CONVEYANCEOTHER.toString())));
    }

    @Test
    @Transactional
    public void getGrossdeduct() throws Exception {
        // Initialize the database
        grossdeductRepository.saveAndFlush(grossdeduct);

        // Get the grossdeduct
        restGrossdeductMockMvc.perform(get("/api/grossdeducts/{id}", grossdeduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grossdeduct.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.bsalary").value(DEFAULT_BSALARY.toString()))
            .andExpect(jsonPath("$.da").value(DEFAULT_DA.toString()))
            .andExpect(jsonPath("$.hra").value(DEFAULT_HRA.toString()))
            .andExpect(jsonPath("$.conveyance").value(DEFAULT_CONVEYANCE.toString()))
            .andExpect(jsonPath("$.childedu").value(DEFAULT_CHILDEDU.toString()))
            .andExpect(jsonPath("$.medical").value(DEFAULT_MEDICAL.toString()))
            .andExpect(jsonPath("$.lta").value(DEFAULT_LTA.toString()))
            .andExpect(jsonPath("$.otherallown").value(DEFAULT_OTHERALLOWN.toString()))
            .andExpect(jsonPath("$.bonus").value(DEFAULT_BONUS.toString()))
            .andExpect(jsonPath("$.rentincome").value(DEFAULT_RENTINCOME.toString()))
            .andExpect(jsonPath("$.saving").value(DEFAULT_SAVING.toString()))
            .andExpect(jsonPath("$.bonds").value(DEFAULT_BONDS.toString()))
            .andExpect(jsonPath("$.conveyanceother").value(DEFAULT_CONVEYANCEOTHER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGrossdeduct() throws Exception {
        // Get the grossdeduct
        restGrossdeductMockMvc.perform(get("/api/grossdeducts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrossdeduct() throws Exception {
        // Initialize the database
        grossdeductRepository.saveAndFlush(grossdeduct);
        int databaseSizeBeforeUpdate = grossdeductRepository.findAll().size();

        // Update the grossdeduct
        Grossdeduct updatedGrossdeduct = grossdeductRepository.findOne(grossdeduct.getId());
        // Disconnect from session so that the updates on updatedGrossdeduct are not directly saved in db
        em.detach(updatedGrossdeduct);
        updatedGrossdeduct
            .uid(UPDATED_UID)
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
        GrossdeductDTO grossdeductDTO = grossdeductMapper.toDto(updatedGrossdeduct);

        restGrossdeductMockMvc.perform(put("/api/grossdeducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grossdeductDTO)))
            .andExpect(status().isOk());

        // Validate the Grossdeduct in the database
        List<Grossdeduct> grossdeductList = grossdeductRepository.findAll();
        assertThat(grossdeductList).hasSize(databaseSizeBeforeUpdate);
        Grossdeduct testGrossdeduct = grossdeductList.get(grossdeductList.size() - 1);
        assertThat(testGrossdeduct.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testGrossdeduct.getBsalary()).isEqualTo(UPDATED_BSALARY);
        assertThat(testGrossdeduct.getDa()).isEqualTo(UPDATED_DA);
        assertThat(testGrossdeduct.getHra()).isEqualTo(UPDATED_HRA);
        assertThat(testGrossdeduct.getConveyance()).isEqualTo(UPDATED_CONVEYANCE);
        assertThat(testGrossdeduct.getChildedu()).isEqualTo(UPDATED_CHILDEDU);
        assertThat(testGrossdeduct.getMedical()).isEqualTo(UPDATED_MEDICAL);
        assertThat(testGrossdeduct.getLta()).isEqualTo(UPDATED_LTA);
        assertThat(testGrossdeduct.getOtherallown()).isEqualTo(UPDATED_OTHERALLOWN);
        assertThat(testGrossdeduct.getBonus()).isEqualTo(UPDATED_BONUS);
        assertThat(testGrossdeduct.getRentincome()).isEqualTo(UPDATED_RENTINCOME);
        assertThat(testGrossdeduct.getSaving()).isEqualTo(UPDATED_SAVING);
        assertThat(testGrossdeduct.getBonds()).isEqualTo(UPDATED_BONDS);
        assertThat(testGrossdeduct.getConveyanceother()).isEqualTo(UPDATED_CONVEYANCEOTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingGrossdeduct() throws Exception {
        int databaseSizeBeforeUpdate = grossdeductRepository.findAll().size();

        // Create the Grossdeduct
        GrossdeductDTO grossdeductDTO = grossdeductMapper.toDto(grossdeduct);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGrossdeductMockMvc.perform(put("/api/grossdeducts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grossdeductDTO)))
            .andExpect(status().isCreated());

        // Validate the Grossdeduct in the database
        List<Grossdeduct> grossdeductList = grossdeductRepository.findAll();
        assertThat(grossdeductList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGrossdeduct() throws Exception {
        // Initialize the database
        grossdeductRepository.saveAndFlush(grossdeduct);
        int databaseSizeBeforeDelete = grossdeductRepository.findAll().size();

        // Get the grossdeduct
        restGrossdeductMockMvc.perform(delete("/api/grossdeducts/{id}", grossdeduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Grossdeduct> grossdeductList = grossdeductRepository.findAll();
        assertThat(grossdeductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Grossdeduct.class);
        Grossdeduct grossdeduct1 = new Grossdeduct();
        grossdeduct1.setId(1L);
        Grossdeduct grossdeduct2 = new Grossdeduct();
        grossdeduct2.setId(grossdeduct1.getId());
        assertThat(grossdeduct1).isEqualTo(grossdeduct2);
        grossdeduct2.setId(2L);
        assertThat(grossdeduct1).isNotEqualTo(grossdeduct2);
        grossdeduct1.setId(null);
        assertThat(grossdeduct1).isNotEqualTo(grossdeduct2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrossdeductDTO.class);
        GrossdeductDTO grossdeductDTO1 = new GrossdeductDTO();
        grossdeductDTO1.setId(1L);
        GrossdeductDTO grossdeductDTO2 = new GrossdeductDTO();
        assertThat(grossdeductDTO1).isNotEqualTo(grossdeductDTO2);
        grossdeductDTO2.setId(grossdeductDTO1.getId());
        assertThat(grossdeductDTO1).isEqualTo(grossdeductDTO2);
        grossdeductDTO2.setId(2L);
        assertThat(grossdeductDTO1).isNotEqualTo(grossdeductDTO2);
        grossdeductDTO1.setId(null);
        assertThat(grossdeductDTO1).isNotEqualTo(grossdeductDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(grossdeductMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(grossdeductMapper.fromId(null)).isNull();
    }
}
