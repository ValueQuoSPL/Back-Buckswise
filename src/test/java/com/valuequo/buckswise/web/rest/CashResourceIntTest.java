package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Cash;
import com.valuequo.buckswise.repository.CashRepository;
import com.valuequo.buckswise.service.CashService;
import com.valuequo.buckswise.service.dto.CashDTO;
import com.valuequo.buckswise.service.mapper.CashMapper;
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
 * Test class for the CashResource REST controller.
 *
 * @see CashResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class CashResourceIntTest {

    private static final String DEFAULT_BANKNAME = "AAAAAAAAAA";
    private static final String UPDATED_BANKNAME = "BBBBBBBBBB";

    private static final String DEFAULT_INTRESTRATE = "AAAAAAAAAA";
    private static final String UPDATED_INTRESTRATE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOOUNTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOOUNTTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTNAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_HANDLOANNAME = "AAAAAAAAAA";
    private static final String UPDATED_HANDLOANNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CASHSOURCE = "AAAAAAAAAA";
    private static final String UPDATED_CASHSOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final Long DEFAULT_USERID = 1L;
    private static final Long UPDATED_USERID = 2L;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRAFIELD = "AAAAAAAAAA";
    private static final String UPDATED_EXTRAFIELD = "BBBBBBBBBB";

    @Autowired
    private CashRepository cashRepository;

    @Autowired
    private CashMapper cashMapper;

    @Autowired
    private CashService cashService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCashMockMvc;

    private Cash cash;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CashResource cashResource = new CashResource(cashService);
        this.restCashMockMvc = MockMvcBuilders.standaloneSetup(cashResource)
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
    public static Cash createEntity(EntityManager em) {
        Cash cash = new Cash()
            .bankname(DEFAULT_BANKNAME)
            .intrestrate(DEFAULT_INTRESTRATE)
            .accoounttype(DEFAULT_ACCOOUNTTYPE)
            .accountname(DEFAULT_ACCOUNTNAME)
            .handloanname(DEFAULT_HANDLOANNAME)
            .cashsource(DEFAULT_CASHSOURCE)
            .amount(DEFAULT_AMOUNT)
            .userid(DEFAULT_USERID)
            .notes(DEFAULT_NOTES)
            .extrafield(DEFAULT_EXTRAFIELD);
        return cash;
    }

    @Before
    public void initTest() {
        cash = createEntity(em);
    }

    @Test
    @Transactional
    public void createCash() throws Exception {
        int databaseSizeBeforeCreate = cashRepository.findAll().size();

        // Create the Cash
        CashDTO cashDTO = cashMapper.toDto(cash);
        restCashMockMvc.perform(post("/api/cash")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cashDTO)))
            .andExpect(status().isCreated());

        // Validate the Cash in the database
        List<Cash> cashList = cashRepository.findAll();
        assertThat(cashList).hasSize(databaseSizeBeforeCreate + 1);
        Cash testCash = cashList.get(cashList.size() - 1);
        assertThat(testCash.getBankname()).isEqualTo(DEFAULT_BANKNAME);
        assertThat(testCash.getIntrestrate()).isEqualTo(DEFAULT_INTRESTRATE);
        assertThat(testCash.getAccoounttype()).isEqualTo(DEFAULT_ACCOOUNTTYPE);
        assertThat(testCash.getAccountname()).isEqualTo(DEFAULT_ACCOUNTNAME);
        assertThat(testCash.getHandloanname()).isEqualTo(DEFAULT_HANDLOANNAME);
        assertThat(testCash.getCashsource()).isEqualTo(DEFAULT_CASHSOURCE);
        assertThat(testCash.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCash.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testCash.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testCash.getExtrafield()).isEqualTo(DEFAULT_EXTRAFIELD);
    }

    @Test
    @Transactional
    public void createCashWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cashRepository.findAll().size();

        // Create the Cash with an existing ID
        cash.setId(1L);
        CashDTO cashDTO = cashMapper.toDto(cash);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCashMockMvc.perform(post("/api/cash")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cashDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cash in the database
        List<Cash> cashList = cashRepository.findAll();
        assertThat(cashList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCash() throws Exception {
        // Initialize the database
        cashRepository.saveAndFlush(cash);

        // Get all the cashList
        restCashMockMvc.perform(get("/api/cash?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cash.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankname").value(hasItem(DEFAULT_BANKNAME.toString())))
            .andExpect(jsonPath("$.[*].intrestrate").value(hasItem(DEFAULT_INTRESTRATE.toString())))
            .andExpect(jsonPath("$.[*].accoounttype").value(hasItem(DEFAULT_ACCOOUNTTYPE.toString())))
            .andExpect(jsonPath("$.[*].accountname").value(hasItem(DEFAULT_ACCOUNTNAME.toString())))
            .andExpect(jsonPath("$.[*].handloanname").value(hasItem(DEFAULT_HANDLOANNAME.toString())))
            .andExpect(jsonPath("$.[*].cashsource").value(hasItem(DEFAULT_CASHSOURCE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.intValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].extrafield").value(hasItem(DEFAULT_EXTRAFIELD.toString())));
    }

    @Test
    @Transactional
    public void getCash() throws Exception {
        // Initialize the database
        cashRepository.saveAndFlush(cash);

        // Get the cash
        restCashMockMvc.perform(get("/api/cash/{id}", cash.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cash.getId().intValue()))
            .andExpect(jsonPath("$.bankname").value(DEFAULT_BANKNAME.toString()))
            .andExpect(jsonPath("$.intrestrate").value(DEFAULT_INTRESTRATE.toString()))
            .andExpect(jsonPath("$.accoounttype").value(DEFAULT_ACCOOUNTTYPE.toString()))
            .andExpect(jsonPath("$.accountname").value(DEFAULT_ACCOUNTNAME.toString()))
            .andExpect(jsonPath("$.handloanname").value(DEFAULT_HANDLOANNAME.toString()))
            .andExpect(jsonPath("$.cashsource").value(DEFAULT_CASHSOURCE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.toString()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.intValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.extrafield").value(DEFAULT_EXTRAFIELD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCash() throws Exception {
        // Get the cash
        restCashMockMvc.perform(get("/api/cash/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCash() throws Exception {
        // Initialize the database
        cashRepository.saveAndFlush(cash);
        int databaseSizeBeforeUpdate = cashRepository.findAll().size();

        // Update the cash
        Cash updatedCash = cashRepository.findOne(cash.getId());
        // Disconnect from session so that the updates on updatedCash are not directly saved in db
        em.detach(updatedCash);
        updatedCash
            .bankname(UPDATED_BANKNAME)
            .intrestrate(UPDATED_INTRESTRATE)
            .accoounttype(UPDATED_ACCOOUNTTYPE)
            .accountname(UPDATED_ACCOUNTNAME)
            .handloanname(UPDATED_HANDLOANNAME)
            .cashsource(UPDATED_CASHSOURCE)
            .amount(UPDATED_AMOUNT)
            .userid(UPDATED_USERID)
            .notes(UPDATED_NOTES)
            .extrafield(UPDATED_EXTRAFIELD);
        CashDTO cashDTO = cashMapper.toDto(updatedCash);

        restCashMockMvc.perform(put("/api/cash")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cashDTO)))
            .andExpect(status().isOk());

        // Validate the Cash in the database
        List<Cash> cashList = cashRepository.findAll();
        assertThat(cashList).hasSize(databaseSizeBeforeUpdate);
        Cash testCash = cashList.get(cashList.size() - 1);
        assertThat(testCash.getBankname()).isEqualTo(UPDATED_BANKNAME);
        assertThat(testCash.getIntrestrate()).isEqualTo(UPDATED_INTRESTRATE);
        assertThat(testCash.getAccoounttype()).isEqualTo(UPDATED_ACCOOUNTTYPE);
        assertThat(testCash.getAccountname()).isEqualTo(UPDATED_ACCOUNTNAME);
        assertThat(testCash.getHandloanname()).isEqualTo(UPDATED_HANDLOANNAME);
        assertThat(testCash.getCashsource()).isEqualTo(UPDATED_CASHSOURCE);
        assertThat(testCash.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCash.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testCash.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testCash.getExtrafield()).isEqualTo(UPDATED_EXTRAFIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingCash() throws Exception {
        int databaseSizeBeforeUpdate = cashRepository.findAll().size();

        // Create the Cash
        CashDTO cashDTO = cashMapper.toDto(cash);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCashMockMvc.perform(put("/api/cash")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cashDTO)))
            .andExpect(status().isCreated());

        // Validate the Cash in the database
        List<Cash> cashList = cashRepository.findAll();
        assertThat(cashList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCash() throws Exception {
        // Initialize the database
        cashRepository.saveAndFlush(cash);
        int databaseSizeBeforeDelete = cashRepository.findAll().size();

        // Get the cash
        restCashMockMvc.perform(delete("/api/cash/{id}", cash.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cash> cashList = cashRepository.findAll();
        assertThat(cashList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cash.class);
        Cash cash1 = new Cash();
        cash1.setId(1L);
        Cash cash2 = new Cash();
        cash2.setId(cash1.getId());
        assertThat(cash1).isEqualTo(cash2);
        cash2.setId(2L);
        assertThat(cash1).isNotEqualTo(cash2);
        cash1.setId(null);
        assertThat(cash1).isNotEqualTo(cash2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CashDTO.class);
        CashDTO cashDTO1 = new CashDTO();
        cashDTO1.setId(1L);
        CashDTO cashDTO2 = new CashDTO();
        assertThat(cashDTO1).isNotEqualTo(cashDTO2);
        cashDTO2.setId(cashDTO1.getId());
        assertThat(cashDTO1).isEqualTo(cashDTO2);
        cashDTO2.setId(2L);
        assertThat(cashDTO1).isNotEqualTo(cashDTO2);
        cashDTO1.setId(null);
        assertThat(cashDTO1).isNotEqualTo(cashDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cashMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cashMapper.fromId(null)).isNull();
    }
}
