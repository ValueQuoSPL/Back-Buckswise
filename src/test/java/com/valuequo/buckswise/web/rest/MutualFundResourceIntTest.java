package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;
import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.repository.MutualFundRepository;
import com.valuequo.buckswise.service.MutualFundService;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
import com.valuequo.buckswise.service.mapper.MutualFundMapper;
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
 * Test class for the MutualfundResource REST controller.
 *
 * @see MutualfundResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class MutualFundResourceIntTest {

//    private static final Integer DEFAULT_USERID = 1;
//    private static final Integer UPDATED_USERID = 2;

    private static final String DEFAULT_MFSCHEME = "AAAAAAAAAA";
    private static final String UPDATED_MFSCHEME = "BBBBBBBBBB";

    private static final String DEFAULT_FOLIONUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FOLIONUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_HOLDINGDAYS = "AAAAAAAAAA";
    private static final String UPDATED_HOLDINGDAYS = "BBBBBBBBBB";

    private static final String DEFAULT_PURCHESPRICE = "AAAAAAAAAA";
    private static final String UPDATED_PURCHESPRICE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENTVALUE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENTVALUE = "BBBBBBBBBB";

    private static final String DEFAULT_GAINLOSS = "AAAAAAAAAA";
    private static final String UPDATED_GAINLOSS = "BBBBBBBBBB";

    private static final String DEFAULT_ABSOLUTERETURN = "AAAAAAAAAA";
    private static final String UPDATED_ABSOLUTERETURN = "BBBBBBBBBB";

    private static final String DEFAULT_CAGR = "AAAAAAAAAA";
    private static final String UPDATED_CAGR = "BBBBBBBBBB";

    @Autowired
    private MutualFundRepository mutualfundRepository;

    @Autowired
    private MutualFundMapper mutualfundMapper;

    @Autowired
    private MutualFundService mutualfundService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMutualfundMockMvc;

    private MutualFund mutualfund;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MutualFundResource mutualfundResource = new MutualFundResource(mutualfundService);
        this.restMutualfundMockMvc = MockMvcBuilders.standaloneSetup(mutualfundResource)
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
    public static MutualFund createEntity(EntityManager em) {
    	MutualFund mutualfund = new MutualFund()
//            .userid(DEFAULT_USERID)
            .mfscheme(DEFAULT_MFSCHEME)
            .folionumber(DEFAULT_FOLIONUMBER)
            .holdingdays(DEFAULT_HOLDINGDAYS)
            .purchesprice(DEFAULT_PURCHESPRICE)
            .currentvalue(DEFAULT_CURRENTVALUE)
            .gainloss(DEFAULT_GAINLOSS)
            .absolutereturn(DEFAULT_ABSOLUTERETURN)
            .cagr(DEFAULT_CAGR);
        return mutualfund;
    }

    @Before
    public void initTest() {
        mutualfund = createEntity(em);
    }

    @Test
    @Transactional
    public void createMutualfund() throws Exception {
        int databaseSizeBeforeCreate = mutualfundRepository.findAll().size();

        // Create the Mutualfund
        MutualFundDTO mutualfundDTO = mutualfundMapper.toDto(mutualfund);
        restMutualfundMockMvc.perform(post("/api/mutualfunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mutualfundDTO)))
            .andExpect(status().isCreated());

        // Validate the Mutualfund in the database
        List<MutualFund> mutualfundList = mutualfundRepository.findAll();
        assertThat(mutualfundList).hasSize(databaseSizeBeforeCreate + 1);
        MutualFund testMutualfund = mutualfundList.get(mutualfundList.size() - 1);
//        assertThat(testMutualfund.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testMutualfund.getMfscheme()).isEqualTo(DEFAULT_MFSCHEME);
        assertThat(testMutualfund.getFolionumber()).isEqualTo(DEFAULT_FOLIONUMBER);
        assertThat(testMutualfund.getHoldingdays()).isEqualTo(DEFAULT_HOLDINGDAYS);
        assertThat(testMutualfund.getPurchesprice()).isEqualTo(DEFAULT_PURCHESPRICE);
        assertThat(testMutualfund.getCurrentvalue()).isEqualTo(DEFAULT_CURRENTVALUE);
        assertThat(testMutualfund.getGainloss()).isEqualTo(DEFAULT_GAINLOSS);
        assertThat(testMutualfund.getAbsolutereturn()).isEqualTo(DEFAULT_ABSOLUTERETURN);
        assertThat(testMutualfund.getCagr()).isEqualTo(DEFAULT_CAGR);
    }

    @Test
    @Transactional
    public void createMutualfundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mutualfundRepository.findAll().size();

        // Create the Mutualfund with an existing ID
        mutualfund.setId(1L);
        MutualFundDTO mutualfundDTO = mutualfundMapper.toDto(mutualfund);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMutualfundMockMvc.perform(post("/api/mutualfunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mutualfundDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mutualfund in the database
        List<MutualFund> mutualfundList = mutualfundRepository.findAll();
        assertThat(mutualfundList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMutualfunds() throws Exception {
        // Initialize the database
        mutualfundRepository.saveAndFlush(mutualfund);

        // Get all the mutualfundList
        restMutualfundMockMvc.perform(get("/api/mutualfunds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mutualfund.getId().intValue())))
//            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].mfscheme").value(hasItem(DEFAULT_MFSCHEME.toString())))
            .andExpect(jsonPath("$.[*].folionumber").value(hasItem(DEFAULT_FOLIONUMBER.toString())))
            .andExpect(jsonPath("$.[*].holdingdays").value(hasItem(DEFAULT_HOLDINGDAYS.toString())))
            .andExpect(jsonPath("$.[*].purchesprice").value(hasItem(DEFAULT_PURCHESPRICE.toString())))
            .andExpect(jsonPath("$.[*].currentvalue").value(hasItem(DEFAULT_CURRENTVALUE.toString())))
            .andExpect(jsonPath("$.[*].gainloss").value(hasItem(DEFAULT_GAINLOSS.toString())))
            .andExpect(jsonPath("$.[*].absolutereturn").value(hasItem(DEFAULT_ABSOLUTERETURN.toString())))
            .andExpect(jsonPath("$.[*].cagr").value(hasItem(DEFAULT_CAGR.toString())));
    }

    @Test
    @Transactional
    public void getMutualfund() throws Exception {
        // Initialize the database
        mutualfundRepository.saveAndFlush(mutualfund);

        // Get the mutualfund
        restMutualfundMockMvc.perform(get("/api/mutualfunds/{id}", mutualfund.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mutualfund.getId().intValue()))
//            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.mfscheme").value(DEFAULT_MFSCHEME.toString()))
            .andExpect(jsonPath("$.folionumber").value(DEFAULT_FOLIONUMBER.toString()))
            .andExpect(jsonPath("$.holdingdays").value(DEFAULT_HOLDINGDAYS.toString()))
            .andExpect(jsonPath("$.purchesprice").value(DEFAULT_PURCHESPRICE.toString()))
            .andExpect(jsonPath("$.currentvalue").value(DEFAULT_CURRENTVALUE.toString()))
            .andExpect(jsonPath("$.gainloss").value(DEFAULT_GAINLOSS.toString()))
            .andExpect(jsonPath("$.absolutereturn").value(DEFAULT_ABSOLUTERETURN.toString()))
            .andExpect(jsonPath("$.cagr").value(DEFAULT_CAGR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMutualfund() throws Exception {
        // Get the mutualfund
        restMutualfundMockMvc.perform(get("/api/mutualfunds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMutualfund() throws Exception {
        // Initialize the database
        mutualfundRepository.saveAndFlush(mutualfund);
        int databaseSizeBeforeUpdate = mutualfundRepository.findAll().size();

        // Update the mutualfund
        MutualFund updatedMutualfund = mutualfundRepository.findOne(mutualfund.getId());
        // Disconnect from session so that the updates on updatedMutualfund are not directly saved in db
        em.detach(updatedMutualfund);
        updatedMutualfund
//            .userid(UPDATED_USERID)
            .mfscheme(UPDATED_MFSCHEME)
            .folionumber(UPDATED_FOLIONUMBER)
            .holdingdays(UPDATED_HOLDINGDAYS)
            .purchesprice(UPDATED_PURCHESPRICE)
            .currentvalue(UPDATED_CURRENTVALUE)
            .gainloss(UPDATED_GAINLOSS)
            .absolutereturn(UPDATED_ABSOLUTERETURN)
            .cagr(UPDATED_CAGR);
        MutualFundDTO mutualfundDTO = mutualfundMapper.toDto(updatedMutualfund);

        restMutualfundMockMvc.perform(put("/api/mutualfunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mutualfundDTO)))
            .andExpect(status().isOk());

        // Validate the Mutualfund in the database
        List<MutualFund> mutualfundList = mutualfundRepository.findAll();
        assertThat(mutualfundList).hasSize(databaseSizeBeforeUpdate);
        MutualFund testMutualfund = mutualfundList.get(mutualfundList.size() - 1);
//        assertThat(testMutualfund.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testMutualfund.getMfscheme()).isEqualTo(UPDATED_MFSCHEME);
        assertThat(testMutualfund.getFolionumber()).isEqualTo(UPDATED_FOLIONUMBER);
        assertThat(testMutualfund.getHoldingdays()).isEqualTo(UPDATED_HOLDINGDAYS);
        assertThat(testMutualfund.getPurchesprice()).isEqualTo(UPDATED_PURCHESPRICE);
        assertThat(testMutualfund.getCurrentvalue()).isEqualTo(UPDATED_CURRENTVALUE);
        assertThat(testMutualfund.getGainloss()).isEqualTo(UPDATED_GAINLOSS);
        assertThat(testMutualfund.getAbsolutereturn()).isEqualTo(UPDATED_ABSOLUTERETURN);
        assertThat(testMutualfund.getCagr()).isEqualTo(UPDATED_CAGR);
    }

    @Test
    @Transactional
    public void updateNonExistingMutualfund() throws Exception {
        int databaseSizeBeforeUpdate = mutualfundRepository.findAll().size();

        // Create the Mutualfund
        MutualFundDTO mutualfundDTO = mutualfundMapper.toDto(mutualfund);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMutualfundMockMvc.perform(put("/api/mutualfunds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mutualfundDTO)))
            .andExpect(status().isCreated());

        // Validate the Mutualfund in the database
        List<MutualFund> mutualfundList = mutualfundRepository.findAll();
        assertThat(mutualfundList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMutualfund() throws Exception {
        // Initialize the database
        mutualfundRepository.saveAndFlush(mutualfund);
        int databaseSizeBeforeDelete = mutualfundRepository.findAll().size();

        // Get the mutualfund
        restMutualfundMockMvc.perform(delete("/api/mutualfunds/{id}", mutualfund.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MutualFund> mutualfundList = mutualfundRepository.findAll();
        assertThat(mutualfundList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MutualFund.class);
        MutualFund mutualfund1 = new MutualFund();
        mutualfund1.setId(1L);
        MutualFund mutualfund2 = new MutualFund();
        mutualfund2.setId(mutualfund1.getId());
        assertThat(mutualfund1).isEqualTo(mutualfund2);
        mutualfund2.setId(2L);
        assertThat(mutualfund1).isNotEqualTo(mutualfund2);
        mutualfund1.setId(null);
        assertThat(mutualfund1).isNotEqualTo(mutualfund2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MutualFundDTO.class);
        MutualFundDTO mutualfundDTO1 = new MutualFundDTO();
        mutualfundDTO1.setId(1L);
        MutualFundDTO mutualfundDTO2 = new MutualFundDTO();
        assertThat(mutualfundDTO1).isNotEqualTo(mutualfundDTO2);
        mutualfundDTO2.setId(mutualfundDTO1.getId());
        assertThat(mutualfundDTO1).isEqualTo(mutualfundDTO2);
        mutualfundDTO2.setId(2L);
        assertThat(mutualfundDTO1).isNotEqualTo(mutualfundDTO2);
        mutualfundDTO1.setId(null);
        assertThat(mutualfundDTO1).isNotEqualTo(mutualfundDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mutualfundMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mutualfundMapper.fromId(null)).isNull();
    }
}
