package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Promocode;
import com.valuequo.buckswise.repository.PromocodeRepository;
import com.valuequo.buckswise.service.PromocodeService;
import com.valuequo.buckswise.service.dto.PromocodeDTO;
import com.valuequo.buckswise.service.mapper.PromocodeMapper;
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
 * Test class for the PromocodeResource REST controller.
 *
 * @see PromocodeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class PromocodeResourceIntTest {

    private static final String DEFAULT_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_PLAN = "BBBBBBBBBB";

    private static final String DEFAULT_PROMOCODE = "AAAAAAAAAA";
    private static final String UPDATED_PROMOCODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EXPIRY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DISCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNT = "BBBBBBBBBB";

    @Autowired
    private PromocodeRepository promocodeRepository;


    @Autowired
    private PromocodeMapper promocodeMapper;
    

    @Autowired
    private PromocodeService promocodeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPromocodeMockMvc;

    private Promocode promocode;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PromocodeResource promocodeResource = new PromocodeResource(promocodeService);
        this.restPromocodeMockMvc = MockMvcBuilders.standaloneSetup(promocodeResource)
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
    public static Promocode createEntity(EntityManager em) {
        Promocode promocode = new Promocode()
            .plan(DEFAULT_PLAN)
            .promocode(DEFAULT_PROMOCODE)
            .expiryDate(DEFAULT_EXPIRY_DATE)
            .discount(DEFAULT_DISCOUNT);
        return promocode;
    }

    @Before
    public void initTest() {
        promocode = createEntity(em);
    }

    @Test
    @Transactional
    public void createPromocode() throws Exception {
        int databaseSizeBeforeCreate = promocodeRepository.findAll().size();

        // Create the Promocode
        PromocodeDTO promocodeDTO = promocodeMapper.toDto(promocode);
        restPromocodeMockMvc.perform(post("/api/promocodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(promocodeDTO)))
            .andExpect(status().isCreated());

        // Validate the Promocode in the database
        List<Promocode> promocodeList = promocodeRepository.findAll();
        assertThat(promocodeList).hasSize(databaseSizeBeforeCreate + 1);
        Promocode testPromocode = promocodeList.get(promocodeList.size() - 1);
        assertThat(testPromocode.getPlan()).isEqualTo(DEFAULT_PLAN);
        assertThat(testPromocode.getPromocode()).isEqualTo(DEFAULT_PROMOCODE);
        assertThat(testPromocode.getExpiryDate()).isEqualTo(DEFAULT_EXPIRY_DATE);
        assertThat(testPromocode.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
    }

    @Test
    @Transactional
    public void createPromocodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = promocodeRepository.findAll().size();

        // Create the Promocode with an existing ID
        promocode.setId(1L);
        PromocodeDTO promocodeDTO = promocodeMapper.toDto(promocode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromocodeMockMvc.perform(post("/api/promocodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(promocodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Promocode in the database
        List<Promocode> promocodeList = promocodeRepository.findAll();
        assertThat(promocodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPromocodes() throws Exception {
        // Initialize the database
        promocodeRepository.saveAndFlush(promocode);

        // Get all the promocodeList
        restPromocodeMockMvc.perform(get("/api/promocodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promocode.getId().intValue())))
            .andExpect(jsonPath("$.[*].plan").value(hasItem(DEFAULT_PLAN.toString())))
            .andExpect(jsonPath("$.[*].promocode").value(hasItem(DEFAULT_PROMOCODE.toString())))
            .andExpect(jsonPath("$.[*].expiryDate").value(hasItem(DEFAULT_EXPIRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.toString())));
    }
    

    @Test
    @Transactional
    public void getPromocode() throws Exception {
        // Initialize the database
        promocodeRepository.saveAndFlush(promocode);

        // Get the promocode
        restPromocodeMockMvc.perform(get("/api/promocodes/{id}", promocode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(promocode.getId().intValue()))
            .andExpect(jsonPath("$.plan").value(DEFAULT_PLAN.toString()))
            .andExpect(jsonPath("$.promocode").value(DEFAULT_PROMOCODE.toString()))
            .andExpect(jsonPath("$.expiryDate").value(DEFAULT_EXPIRY_DATE.toString()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPromocode() throws Exception {
        // Get the promocode
        restPromocodeMockMvc.perform(get("/api/promocodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePromocode() throws Exception {
        // Initialize the database
        promocodeRepository.saveAndFlush(promocode);

        int databaseSizeBeforeUpdate = promocodeRepository.findAll().size();

        // Update the promocode
        Promocode updatedPromocode = promocodeRepository.findById(promocode.getId()).get();
        // Disconnect from session so that the updates on updatedPromocode are not directly saved in db
        em.detach(updatedPromocode);
        updatedPromocode
            .plan(UPDATED_PLAN)
            .promocode(UPDATED_PROMOCODE)
            .expiryDate(UPDATED_EXPIRY_DATE)
            .discount(UPDATED_DISCOUNT);
        PromocodeDTO promocodeDTO = promocodeMapper.toDto(updatedPromocode);

        restPromocodeMockMvc.perform(put("/api/promocodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(promocodeDTO)))
            .andExpect(status().isOk());

        // Validate the Promocode in the database
        List<Promocode> promocodeList = promocodeRepository.findAll();
        assertThat(promocodeList).hasSize(databaseSizeBeforeUpdate);
        Promocode testPromocode = promocodeList.get(promocodeList.size() - 1);
        assertThat(testPromocode.getPlan()).isEqualTo(UPDATED_PLAN);
        assertThat(testPromocode.getPromocode()).isEqualTo(UPDATED_PROMOCODE);
        assertThat(testPromocode.getExpiryDate()).isEqualTo(UPDATED_EXPIRY_DATE);
        assertThat(testPromocode.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingPromocode() throws Exception {
        int databaseSizeBeforeUpdate = promocodeRepository.findAll().size();

        // Create the Promocode
        PromocodeDTO promocodeDTO = promocodeMapper.toDto(promocode);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPromocodeMockMvc.perform(put("/api/promocodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(promocodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Promocode in the database
        List<Promocode> promocodeList = promocodeRepository.findAll();
        assertThat(promocodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePromocode() throws Exception {
        // Initialize the database
        promocodeRepository.saveAndFlush(promocode);

        int databaseSizeBeforeDelete = promocodeRepository.findAll().size();

        // Get the promocode
        restPromocodeMockMvc.perform(delete("/api/promocodes/{id}", promocode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Promocode> promocodeList = promocodeRepository.findAll();
        assertThat(promocodeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Promocode.class);
        Promocode promocode1 = new Promocode();
        promocode1.setId(1L);
        Promocode promocode2 = new Promocode();
        promocode2.setId(promocode1.getId());
        assertThat(promocode1).isEqualTo(promocode2);
        promocode2.setId(2L);
        assertThat(promocode1).isNotEqualTo(promocode2);
        promocode1.setId(null);
        assertThat(promocode1).isNotEqualTo(promocode2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PromocodeDTO.class);
        PromocodeDTO promocodeDTO1 = new PromocodeDTO();
        promocodeDTO1.setId(1L);
        PromocodeDTO promocodeDTO2 = new PromocodeDTO();
        assertThat(promocodeDTO1).isNotEqualTo(promocodeDTO2);
        promocodeDTO2.setId(promocodeDTO1.getId());
        assertThat(promocodeDTO1).isEqualTo(promocodeDTO2);
        promocodeDTO2.setId(2L);
        assertThat(promocodeDTO1).isNotEqualTo(promocodeDTO2);
        promocodeDTO1.setId(null);
        assertThat(promocodeDTO1).isNotEqualTo(promocodeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(promocodeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(promocodeMapper.fromId(null)).isNull();
    }
}
