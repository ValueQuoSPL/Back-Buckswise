package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Chit;
import com.valuequo.buckswise.repository.ChitRepository;
import com.valuequo.buckswise.service.ChitService;
import com.valuequo.buckswise.service.dto.ChitDTO;
import com.valuequo.buckswise.service.mapper.ChitMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.valuequo.buckswise.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ChitResource REST controller.
 *
 * @see ChitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class ChitResourceIntTest {

    private static final String DEFAULT_CHIT_HOLDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHIT_HOLDER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHIT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHIT_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHIT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHIT_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CHIT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CHIT_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_TENURE = "AAAAAAAAAA";
    private static final String UPDATED_TENURE = "BBBBBBBBBB";

    private static final Long DEFAULT_USERID = 1L;
    private static final Long UPDATED_USERID = 2L;

    @Autowired
    private ChitRepository chitRepository;

    @Autowired
    private ChitMapper chitMapper;

    @Autowired
    private ChitService chitService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChitMockMvc;

    private Chit chit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChitResource chitResource = new ChitResource(chitService);
        this.restChitMockMvc = MockMvcBuilders.standaloneSetup(chitResource)
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
    public static Chit createEntity(EntityManager em) {
        Chit chit = new Chit()
            .chit_holder_name(DEFAULT_CHIT_HOLDER_NAME)
            .chit_name(DEFAULT_CHIT_NAME)
            .chit_start_date(DEFAULT_CHIT_START_DATE)
            .chit_value(DEFAULT_CHIT_VALUE)
            .current_value(DEFAULT_CURRENT_VALUE)
            .notes(DEFAULT_NOTES)
            .tenure(DEFAULT_TENURE)
            .userid(DEFAULT_USERID);
        return chit;
    }

    @Before
    public void initTest() {
        chit = createEntity(em);
    }

    @Test
    @Transactional
    public void createChit() throws Exception {
        int databaseSizeBeforeCreate = chitRepository.findAll().size();

        // Create the Chit
        ChitDTO chitDTO = chitMapper.toDto(chit);
        restChitMockMvc.perform(post("/api/postchit")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chitDTO)))
            .andExpect(status().isCreated());

        // Validate the Chit in the database
        List<Chit> chitList = chitRepository.findAll();
        assertThat(chitList).hasSize(databaseSizeBeforeCreate + 1);
        Chit testChit = chitList.get(chitList.size() - 1);
        assertThat(testChit.getChit_holder_name()).isEqualTo(DEFAULT_CHIT_HOLDER_NAME);
        assertThat(testChit.getChit_name()).isEqualTo(DEFAULT_CHIT_NAME);
        assertThat(testChit.getChit_start_date()).isEqualTo(DEFAULT_CHIT_START_DATE);
        assertThat(testChit.getChit_value()).isEqualTo(DEFAULT_CHIT_VALUE);
        assertThat(testChit.getCurrent_value()).isEqualTo(DEFAULT_CURRENT_VALUE);
        assertThat(testChit.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testChit.getTenure()).isEqualTo(DEFAULT_TENURE);
        assertThat(testChit.getUserid()).isEqualTo(DEFAULT_USERID);
    }

    @Test
    @Transactional
    public void createChitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chitRepository.findAll().size();

        // Create the Chit with an existing ID
        chit.setId(1L);
        ChitDTO chitDTO = chitMapper.toDto(chit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChitMockMvc.perform(post("/api/postchit")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chit in the database
        List<Chit> chitList = chitRepository.findAll();
        assertThat(chitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllChits() throws Exception {
        // Initialize the database
        chitRepository.saveAndFlush(chit);

        // Get all the chitList
        restChitMockMvc.perform(get("/api/chits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chit.getId().intValue())))
            .andExpect(jsonPath("$.[*].chit_holder_name").value(hasItem(DEFAULT_CHIT_HOLDER_NAME.toString())))
            .andExpect(jsonPath("$.[*].chit_name").value(hasItem(DEFAULT_CHIT_NAME.toString())))
            .andExpect(jsonPath("$.[*].chit_start_date").value(hasItem(DEFAULT_CHIT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].chit_value").value(hasItem(DEFAULT_CHIT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].current_value").value(hasItem(DEFAULT_CURRENT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].tenure").value(hasItem(DEFAULT_TENURE.toString())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.intValue())));
    }

    @Test
    @Transactional
    public void getChit() throws Exception {
        // Initialize the database
        chitRepository.saveAndFlush(chit);

        // Get the chit
        restChitMockMvc.perform(get("/api/getchitbyid/{id}", chit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chit.getId().intValue()))
            .andExpect(jsonPath("$.chit_holder_name").value(DEFAULT_CHIT_HOLDER_NAME.toString()))
            .andExpect(jsonPath("$.chit_name").value(DEFAULT_CHIT_NAME.toString()))
            .andExpect(jsonPath("$.chit_start_date").value(DEFAULT_CHIT_START_DATE.toString()))
            .andExpect(jsonPath("$.chit_value").value(DEFAULT_CHIT_VALUE.toString()))
            .andExpect(jsonPath("$.current_value").value(DEFAULT_CURRENT_VALUE.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.tenure").value(DEFAULT_TENURE.toString()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingChit() throws Exception {
        // Get the chit
        restChitMockMvc.perform(get("/api/chits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChit() throws Exception {
        // Initialize the database
        chitRepository.saveAndFlush(chit);
        int databaseSizeBeforeUpdate = chitRepository.findAll().size();

        // Update the chit
        Chit updatedChit = chitRepository.findOne(chit.getId());
        // Disconnect from session so that the updates on updatedChit are not directly saved in db
        em.detach(updatedChit);
        updatedChit
            .chit_holder_name(UPDATED_CHIT_HOLDER_NAME)
            .chit_name(UPDATED_CHIT_NAME)
            .chit_start_date(UPDATED_CHIT_START_DATE)
            .chit_value(UPDATED_CHIT_VALUE)
            .current_value(UPDATED_CURRENT_VALUE)
            .notes(UPDATED_NOTES)
            .tenure(UPDATED_TENURE)
            .userid(UPDATED_USERID);
        ChitDTO chitDTO = chitMapper.toDto(updatedChit);

        restChitMockMvc.perform(put("/api/putchit")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chitDTO)))
            .andExpect(status().isOk());

        // Validate the Chit in the database
        List<Chit> chitList = chitRepository.findAll();
        assertThat(chitList).hasSize(databaseSizeBeforeUpdate);
        Chit testChit = chitList.get(chitList.size() - 1);
        assertThat(testChit.getChit_holder_name()).isEqualTo(UPDATED_CHIT_HOLDER_NAME);
        assertThat(testChit.getChit_name()).isEqualTo(UPDATED_CHIT_NAME);
        assertThat(testChit.getChit_start_date()).isEqualTo(UPDATED_CHIT_START_DATE);
        assertThat(testChit.getChit_value()).isEqualTo(UPDATED_CHIT_VALUE);
        assertThat(testChit.getCurrent_value()).isEqualTo(UPDATED_CURRENT_VALUE);
        assertThat(testChit.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testChit.getTenure()).isEqualTo(UPDATED_TENURE);
        assertThat(testChit.getUserid()).isEqualTo(UPDATED_USERID);
    }

    @Test
    @Transactional
    public void updateNonExistingChit() throws Exception {
        int databaseSizeBeforeUpdate = chitRepository.findAll().size();

        // Create the Chit
        ChitDTO chitDTO = chitMapper.toDto(chit);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restChitMockMvc.perform(put("/api/putchit")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chitDTO)))
            .andExpect(status().isCreated());

        // Validate the Chit in the database
        List<Chit> chitList = chitRepository.findAll();
        assertThat(chitList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteChit() throws Exception {
        // Initialize the database
        chitRepository.saveAndFlush(chit);
        int databaseSizeBeforeDelete = chitRepository.findAll().size();

        // Get the chit
        restChitMockMvc.perform(delete("/api/deletechit/{id}", chit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Chit> chitList = chitRepository.findAll();
        assertThat(chitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chit.class);
        Chit chit1 = new Chit();
        chit1.setId(1L);
        Chit chit2 = new Chit();
        chit2.setId(chit1.getId());
        assertThat(chit1).isEqualTo(chit2);
        chit2.setId(2L);
        assertThat(chit1).isNotEqualTo(chit2);
        chit1.setId(null);
        assertThat(chit1).isNotEqualTo(chit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChitDTO.class);
        ChitDTO chitDTO1 = new ChitDTO();
        chitDTO1.setId(1L);
        ChitDTO chitDTO2 = new ChitDTO();
        assertThat(chitDTO1).isNotEqualTo(chitDTO2);
        chitDTO2.setId(chitDTO1.getId());
        assertThat(chitDTO1).isEqualTo(chitDTO2);
        chitDTO2.setId(2L);
        assertThat(chitDTO1).isNotEqualTo(chitDTO2);
        chitDTO1.setId(null);
        assertThat(chitDTO1).isNotEqualTo(chitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(chitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(chitMapper.fromId(null)).isNull();
    }
}
