package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.FutureOption;
import com.valuequo.buckswise.repository.FutureOptionRepository;
import com.valuequo.buckswise.service.FutureOptionService;
import com.valuequo.buckswise.service.dto.FutureOptionDTO;
import com.valuequo.buckswise.service.mapper.FutureOptionMapper;
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
 * Test class for the FutureOptionResource REST controller.
 *
 * @see FutureOptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class FutureOptionResourceIntTest {

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final Long DEFAULT_USERID = 1L;
    private static final Long UPDATED_USERID = 2L;

    private static final String DEFAULT_INVESTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACT_M_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_M_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACT_P_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_P_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_NO_OF_CONTRACTS = "AAAAAAAAAA";
    private static final String UPDATED_NO_OF_CONTRACTS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AS_OF_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AS_OF_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_P_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_P_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    @Autowired
    private FutureOptionRepository futureOptionRepository;

    @Autowired
    private FutureOptionMapper futureOptionMapper;

    @Autowired
    private FutureOptionService futureOptionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFutureOptionMockMvc;

    private FutureOption futureOption;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FutureOptionResource futureOptionResource = new FutureOptionResource(futureOptionService);
        this.restFutureOptionMockMvc = MockMvcBuilders.standaloneSetup(futureOptionResource)
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
    public static FutureOption createEntity(EntityManager em) {
        FutureOption futureOption = new FutureOption()
            .num(DEFAULT_NUM)
            .userid(DEFAULT_USERID)
            .investor_name(DEFAULT_INVESTOR_NAME)
            .investment_type(DEFAULT_INVESTMENT_TYPE)
            .asset_type(DEFAULT_ASSET_TYPE)
            .asset_name(DEFAULT_ASSET_NAME)
            .contract_m_value(DEFAULT_CONTRACT_M_VALUE)
            .contract_p_value(DEFAULT_CONTRACT_P_VALUE)
            .no_of_contracts(DEFAULT_NO_OF_CONTRACTS)
            .as_of_date(DEFAULT_AS_OF_DATE)
            .p_date(DEFAULT_P_DATE)
            .notes(DEFAULT_NOTES);
        return futureOption;
    }

    @Before
    public void initTest() {
        futureOption = createEntity(em);
    }

    @Test
    @Transactional
    public void createFutureOption() throws Exception {
        int databaseSizeBeforeCreate = futureOptionRepository.findAll().size();

        // Create the FutureOption
        FutureOptionDTO futureOptionDTO = futureOptionMapper.toDto(futureOption);
        restFutureOptionMockMvc.perform(post("/api/future-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(futureOptionDTO)))
            .andExpect(status().isCreated());

        // Validate the FutureOption in the database
        List<FutureOption> futureOptionList = futureOptionRepository.findAll();
        assertThat(futureOptionList).hasSize(databaseSizeBeforeCreate + 1);
        FutureOption testFutureOption = futureOptionList.get(futureOptionList.size() - 1);
        assertThat(testFutureOption.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testFutureOption.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testFutureOption.getInvestor_name()).isEqualTo(DEFAULT_INVESTOR_NAME);
        assertThat(testFutureOption.getInvestment_type()).isEqualTo(DEFAULT_INVESTMENT_TYPE);
        assertThat(testFutureOption.getAsset_type()).isEqualTo(DEFAULT_ASSET_TYPE);
        assertThat(testFutureOption.getAsset_name()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testFutureOption.getContract_m_value()).isEqualTo(DEFAULT_CONTRACT_M_VALUE);
        assertThat(testFutureOption.getContract_p_value()).isEqualTo(DEFAULT_CONTRACT_P_VALUE);
        assertThat(testFutureOption.getNo_of_contracts()).isEqualTo(DEFAULT_NO_OF_CONTRACTS);
        assertThat(testFutureOption.getAs_of_date()).isEqualTo(DEFAULT_AS_OF_DATE);
        assertThat(testFutureOption.getp_date()).isEqualTo(DEFAULT_P_DATE);
        assertThat(testFutureOption.getNotes()).isEqualTo(DEFAULT_NOTES);
    }

    @Test
    @Transactional
    public void createFutureOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = futureOptionRepository.findAll().size();

        // Create the FutureOption with an existing ID
        futureOption.setId(1L);
        FutureOptionDTO futureOptionDTO = futureOptionMapper.toDto(futureOption);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFutureOptionMockMvc.perform(post("/api/future-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(futureOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FutureOption in the database
        List<FutureOption> futureOptionList = futureOptionRepository.findAll();
        assertThat(futureOptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFutureOptions() throws Exception {
        // Initialize the database
        futureOptionRepository.saveAndFlush(futureOption);

        // Get all the futureOptionList
        restFutureOptionMockMvc.perform(get("/api/getfutureoptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(futureOption.getId().intValue())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.intValue())))
            .andExpect(jsonPath("$.[*].investor_name").value(hasItem(DEFAULT_INVESTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].investment_type").value(hasItem(DEFAULT_INVESTMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].asset_type").value(hasItem(DEFAULT_ASSET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].asset_name").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].contract_m_value").value(hasItem(DEFAULT_CONTRACT_M_VALUE.toString())))
            .andExpect(jsonPath("$.[*].contract_p_value").value(hasItem(DEFAULT_CONTRACT_P_VALUE.toString())))
            .andExpect(jsonPath("$.[*].no_of_contracts").value(hasItem(DEFAULT_NO_OF_CONTRACTS.toString())))
            .andExpect(jsonPath("$.[*].as_of_date").value(hasItem(DEFAULT_AS_OF_DATE.toString())))
            .andExpect(jsonPath("$.[*].p_date").value(hasItem(DEFAULT_P_DATE.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }

    @Test
    @Transactional
    public void getFutureOption() throws Exception {
        // Initialize the database
        futureOptionRepository.saveAndFlush(futureOption);

        // Get the futureOption
        restFutureOptionMockMvc.perform(get("/api/futureoptionsbyid/{id}", futureOption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(futureOption.getId().intValue()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.intValue()))
            .andExpect(jsonPath("$.investor_name").value(DEFAULT_INVESTOR_NAME.toString()))
            .andExpect(jsonPath("$.investment_type").value(DEFAULT_INVESTMENT_TYPE.toString()))
            .andExpect(jsonPath("$.asset_type").value(DEFAULT_ASSET_TYPE.toString()))
            .andExpect(jsonPath("$.asset_name").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.contract_m_value").value(DEFAULT_CONTRACT_M_VALUE.toString()))
            .andExpect(jsonPath("$.contract_p_value").value(DEFAULT_CONTRACT_P_VALUE.toString()))
            .andExpect(jsonPath("$.no_of_contracts").value(DEFAULT_NO_OF_CONTRACTS.toString()))
            .andExpect(jsonPath("$.as_of_date").value(DEFAULT_AS_OF_DATE.toString()))
            .andExpect(jsonPath("$.p_date").value(DEFAULT_P_DATE.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFutureOption() throws Exception {
        // Get the futureOption
        restFutureOptionMockMvc.perform(get("/api/future-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFutureOption() throws Exception {
        // Initialize the database
        futureOptionRepository.saveAndFlush(futureOption);
        int databaseSizeBeforeUpdate = futureOptionRepository.findAll().size();

        // Update the futureOption
        FutureOption updatedFutureOption = futureOptionRepository.findOne(futureOption.getId());
        // Disconnect from session so that the updates on updatedFutureOption are not directly saved in db
        em.detach(updatedFutureOption);
        updatedFutureOption
            .num(UPDATED_NUM)
            .userid(UPDATED_USERID)
            .investor_name(UPDATED_INVESTOR_NAME)
            .investment_type(UPDATED_INVESTMENT_TYPE)
            .asset_type(UPDATED_ASSET_TYPE)
            .asset_name(UPDATED_ASSET_NAME)
            .contract_m_value(UPDATED_CONTRACT_M_VALUE)
            .contract_p_value(UPDATED_CONTRACT_P_VALUE)
            .no_of_contracts(UPDATED_NO_OF_CONTRACTS)
            .as_of_date(UPDATED_AS_OF_DATE)
            .p_date(UPDATED_P_DATE)
            .notes(UPDATED_NOTES);
        FutureOptionDTO futureOptionDTO = futureOptionMapper.toDto(updatedFutureOption);

        restFutureOptionMockMvc.perform(put("/api/putfutureoptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(futureOptionDTO)))
            .andExpect(status().isOk());

        // Validate the FutureOption in the database
        List<FutureOption> futureOptionList = futureOptionRepository.findAll();
        assertThat(futureOptionList).hasSize(databaseSizeBeforeUpdate);
        FutureOption testFutureOption = futureOptionList.get(futureOptionList.size() - 1);
        assertThat(testFutureOption.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testFutureOption.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testFutureOption.getInvestor_name()).isEqualTo(UPDATED_INVESTOR_NAME);
        assertThat(testFutureOption.getInvestment_type()).isEqualTo(UPDATED_INVESTMENT_TYPE);
        assertThat(testFutureOption.getAsset_type()).isEqualTo(UPDATED_ASSET_TYPE);
        assertThat(testFutureOption.getAsset_name()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testFutureOption.getContract_m_value()).isEqualTo(UPDATED_CONTRACT_M_VALUE);
        assertThat(testFutureOption.getContract_p_value()).isEqualTo(UPDATED_CONTRACT_P_VALUE);
        assertThat(testFutureOption.getNo_of_contracts()).isEqualTo(UPDATED_NO_OF_CONTRACTS);
        assertThat(testFutureOption.getAs_of_date()).isEqualTo(UPDATED_AS_OF_DATE);
        assertThat(testFutureOption.getp_date()).isEqualTo(UPDATED_P_DATE);
        assertThat(testFutureOption.getNotes()).isEqualTo(UPDATED_NOTES);
    }

    @Test
    @Transactional
    public void updateNonExistingFutureOption() throws Exception {
        int databaseSizeBeforeUpdate = futureOptionRepository.findAll().size();

        // Create the FutureOption
        FutureOptionDTO futureOptionDTO = futureOptionMapper.toDto(futureOption);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFutureOptionMockMvc.perform(put("/api/putfutureoptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(futureOptionDTO)))
            .andExpect(status().isCreated());

        // Validate the FutureOption in the database
        List<FutureOption> futureOptionList = futureOptionRepository.findAll();
        assertThat(futureOptionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFutureOption() throws Exception {
        // Initialize the database
        futureOptionRepository.saveAndFlush(futureOption);
        int databaseSizeBeforeDelete = futureOptionRepository.findAll().size();

        // Get the futureOption
        restFutureOptionMockMvc.perform(delete("/api/deletefutureoptions/{id}", futureOption.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FutureOption> futureOptionList = futureOptionRepository.findAll();
        assertThat(futureOptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FutureOption.class);
        FutureOption futureOption1 = new FutureOption();
        futureOption1.setId(1L);
        FutureOption futureOption2 = new FutureOption();
        futureOption2.setId(futureOption1.getId());
        assertThat(futureOption1).isEqualTo(futureOption2);
        futureOption2.setId(2L);
        assertThat(futureOption1).isNotEqualTo(futureOption2);
        futureOption1.setId(null);
        assertThat(futureOption1).isNotEqualTo(futureOption2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FutureOptionDTO.class);
        FutureOptionDTO futureOptionDTO1 = new FutureOptionDTO();
        futureOptionDTO1.setId(1L);
        FutureOptionDTO futureOptionDTO2 = new FutureOptionDTO();
        assertThat(futureOptionDTO1).isNotEqualTo(futureOptionDTO2);
        futureOptionDTO2.setId(futureOptionDTO1.getId());
        assertThat(futureOptionDTO1).isEqualTo(futureOptionDTO2);
        futureOptionDTO2.setId(2L);
        assertThat(futureOptionDTO1).isNotEqualTo(futureOptionDTO2);
        futureOptionDTO1.setId(null);
        assertThat(futureOptionDTO1).isNotEqualTo(futureOptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(futureOptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(futureOptionMapper.fromId(null)).isNull();
    }
}
