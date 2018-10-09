package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Assetmapping;
import com.valuequo.buckswise.repository.AssetmappingRepository;
import com.valuequo.buckswise.service.AssetmappingService;
import com.valuequo.buckswise.service.dto.AssetmappingDTO;
import com.valuequo.buckswise.service.mapper.AssetmappingMapper;
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
 * Test class for the AssetmappingResource REST controller.
 *
 * @see AssetmappingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class AssetmappingResourceIntTest {

    private static final Long DEFAULT_UID = 1l;
    private static final Long UPDATED_UID = 2l;

    private static final Integer DEFAULT_GOALID = 1;
    private static final Integer UPDATED_GOALID = 2;

    private static final String DEFAULT_ASSETNAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSETNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ASSETID = 1;
    private static final Integer UPDATED_ASSETID = 2;

    @Autowired
    private AssetmappingRepository assetmappingRepository;

    @Autowired
    private AssetmappingMapper assetmappingMapper;

    @Autowired
    private AssetmappingService assetmappingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAssetmappingMockMvc;

    private Assetmapping assetmapping;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssetmappingResource assetmappingResource = new AssetmappingResource(assetmappingService);
        this.restAssetmappingMockMvc = MockMvcBuilders.standaloneSetup(assetmappingResource)
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
    public static Assetmapping createEntity(EntityManager em) {
        Assetmapping assetmapping = new Assetmapping()
            .uid(DEFAULT_UID)
            .goalid(DEFAULT_GOALID)
            .assetname(DEFAULT_ASSETNAME)
            .assetid(DEFAULT_ASSETID);
        return assetmapping;
    }

    @Before
    public void initTest() {
        assetmapping = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssetmapping() throws Exception {
        int databaseSizeBeforeCreate = assetmappingRepository.findAll().size();

        // Create the Assetmapping
        AssetmappingDTO assetmappingDTO = assetmappingMapper.toDto(assetmapping);
        restAssetmappingMockMvc.perform(post("/api/assetmappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetmappingDTO)))
            .andExpect(status().isCreated());

        // Validate the Assetmapping in the database
        List<Assetmapping> assetmappingList = assetmappingRepository.findAll();
        assertThat(assetmappingList).hasSize(databaseSizeBeforeCreate + 1);
        Assetmapping testAssetmapping = assetmappingList.get(assetmappingList.size() - 1);
        assertThat(testAssetmapping.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testAssetmapping.getGoalid()).isEqualTo(DEFAULT_GOALID);
        assertThat(testAssetmapping.getAssetname()).isEqualTo(DEFAULT_ASSETNAME);
        assertThat(testAssetmapping.getAssetid()).isEqualTo(DEFAULT_ASSETID);
    }

    @Test
    @Transactional
    public void createAssetmappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assetmappingRepository.findAll().size();

        // Create the Assetmapping with an existing ID
        assetmapping.setId(1L);
        AssetmappingDTO assetmappingDTO = assetmappingMapper.toDto(assetmapping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssetmappingMockMvc.perform(post("/api/assetmappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetmappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Assetmapping in the database
        List<Assetmapping> assetmappingList = assetmappingRepository.findAll();
        assertThat(assetmappingList).hasSize(databaseSizeBeforeCreate);
    }

//    @Test
//    @Transactional
//    public void getAllAssetmappings() throws Exception {
//        // Initialize the database
//        assetmappingRepository.saveAndFlush(assetmapping);
//
//        // Get all the assetmappingList
//        restAssetmappingMockMvc.perform(get("/api/assetmappings?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(assetmapping.getId().intValue())))
//            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
//            .andExpect(jsonPath("$.[*].goalid").value(hasItem(DEFAULT_GOALID)))
//            .andExpect(jsonPath("$.[*].assetname").value(hasItem(DEFAULT_ASSETNAME.toString())))
//            .andExpect(jsonPath("$.[*].assetid").value(hasItem(DEFAULT_ASSETID)));
//    }

    @Test
    @Transactional
    public void getAssetmapping() throws Exception {
        // Initialize the database
        assetmappingRepository.saveAndFlush(assetmapping);

        // Get the assetmapping
        restAssetmappingMockMvc.perform(get("/api/assetmappings/{id}", assetmapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(assetmapping.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.goalid").value(DEFAULT_GOALID))
            .andExpect(jsonPath("$.assetname").value(DEFAULT_ASSETNAME.toString()))
            .andExpect(jsonPath("$.assetid").value(DEFAULT_ASSETID));
    }

    @Test
    @Transactional
    public void getNonExistingAssetmapping() throws Exception {
        // Get the assetmapping
        restAssetmappingMockMvc.perform(get("/api/assetmappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssetmapping() throws Exception {
        // Initialize the database
        assetmappingRepository.saveAndFlush(assetmapping);
        int databaseSizeBeforeUpdate = assetmappingRepository.findAll().size();

        // Update the assetmapping
        Assetmapping updatedAssetmapping = assetmappingRepository.findOne(assetmapping.getId());
        // Disconnect from session so that the updates on updatedAssetmapping are not directly saved in db
        em.detach(updatedAssetmapping);
        updatedAssetmapping
            .uid(UPDATED_UID)
            .goalid(UPDATED_GOALID)
            .assetname(UPDATED_ASSETNAME)
            .assetid(UPDATED_ASSETID);
        AssetmappingDTO assetmappingDTO = assetmappingMapper.toDto(updatedAssetmapping);

        restAssetmappingMockMvc.perform(put("/api/assetmappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetmappingDTO)))
            .andExpect(status().isOk());

        // Validate the Assetmapping in the database
        List<Assetmapping> assetmappingList = assetmappingRepository.findAll();
        assertThat(assetmappingList).hasSize(databaseSizeBeforeUpdate);
        Assetmapping testAssetmapping = assetmappingList.get(assetmappingList.size() - 1);
        assertThat(testAssetmapping.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testAssetmapping.getGoalid()).isEqualTo(UPDATED_GOALID);
        assertThat(testAssetmapping.getAssetname()).isEqualTo(UPDATED_ASSETNAME);
        assertThat(testAssetmapping.getAssetid()).isEqualTo(UPDATED_ASSETID);
    }

    @Test
    @Transactional
    public void updateNonExistingAssetmapping() throws Exception {
        int databaseSizeBeforeUpdate = assetmappingRepository.findAll().size();

        // Create the Assetmapping
        AssetmappingDTO assetmappingDTO = assetmappingMapper.toDto(assetmapping);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAssetmappingMockMvc.perform(put("/api/assetmappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assetmappingDTO)))
            .andExpect(status().isCreated());

        // Validate the Assetmapping in the database
        List<Assetmapping> assetmappingList = assetmappingRepository.findAll();
        assertThat(assetmappingList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAssetmapping() throws Exception {
        // Initialize the database
        assetmappingRepository.saveAndFlush(assetmapping);
        int databaseSizeBeforeDelete = assetmappingRepository.findAll().size();

        // Get the assetmapping
        restAssetmappingMockMvc.perform(delete("/api/delete/{id}", assetmapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Assetmapping> assetmappingList = assetmappingRepository.findAll();
        assertThat(assetmappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Assetmapping.class);
        Assetmapping assetmapping1 = new Assetmapping();
        assetmapping1.setId(1L);
        Assetmapping assetmapping2 = new Assetmapping();
        assetmapping2.setId(assetmapping1.getId());
        assertThat(assetmapping1).isEqualTo(assetmapping2);
        assetmapping2.setId(2L);
        assertThat(assetmapping1).isNotEqualTo(assetmapping2);
        assetmapping1.setId(null);
        assertThat(assetmapping1).isNotEqualTo(assetmapping2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssetmappingDTO.class);
        AssetmappingDTO assetmappingDTO1 = new AssetmappingDTO();
        assetmappingDTO1.setId(1L);
        AssetmappingDTO assetmappingDTO2 = new AssetmappingDTO();
        assertThat(assetmappingDTO1).isNotEqualTo(assetmappingDTO2);
        assetmappingDTO2.setId(assetmappingDTO1.getId());
        assertThat(assetmappingDTO1).isEqualTo(assetmappingDTO2);
        assetmappingDTO2.setId(2L);
        assertThat(assetmappingDTO1).isNotEqualTo(assetmappingDTO2);
        assetmappingDTO1.setId(null);
        assertThat(assetmappingDTO1).isNotEqualTo(assetmappingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(assetmappingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(assetmappingMapper.fromId(null)).isNull();
    }
}
