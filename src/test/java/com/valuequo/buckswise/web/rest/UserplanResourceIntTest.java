package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Userplan;
import com.valuequo.buckswise.repository.UserplanRepository;
import com.valuequo.buckswise.service.UserplanService;
import com.valuequo.buckswise.service.dto.UserplanDTO;
import com.valuequo.buckswise.service.mapper.UserplanMapper;
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
 * Test class for the UserplanResource REST controller.
 *
 * @see UserplanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class UserplanResourceIntTest {

    private static final Integer DEFAULT_UID = 1;
    private static final Integer UPDATED_UID = 2;

    private static final String DEFAULT_PROMOCODE = "AAAAAAAAAA";
    private static final String UPDATED_PROMOCODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPLY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPLY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DISCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PAID = "AAAAAAAAAA";
    private static final String UPDATED_PAID = "BBBBBBBBBB";

    private static final String DEFAULT_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_PLAN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EXPIRY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRY_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UserplanRepository userplanRepository;


    @Autowired
    private UserplanMapper userplanMapper;
    

    @Autowired
    private UserplanService userplanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserplanMockMvc;

    private Userplan userplan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserplanResource userplanResource = new UserplanResource(userplanService);
        this.restUserplanMockMvc = MockMvcBuilders.standaloneSetup(userplanResource)
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
    public static Userplan createEntity(EntityManager em) {
        Userplan userplan = new Userplan()
            .uid(DEFAULT_UID)
            .promocode(DEFAULT_PROMOCODE)
            .applyDate(DEFAULT_APPLY_DATE)
            .discount(DEFAULT_DISCOUNT)
            .paid(DEFAULT_PAID)
            .plan(DEFAULT_PLAN)
            .expiryDate(DEFAULT_EXPIRY_DATE);
        return userplan;
    }

    @Before
    public void initTest() {
        userplan = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserplan() throws Exception {
        int databaseSizeBeforeCreate = userplanRepository.findAll().size();

        // Create the Userplan
        UserplanDTO userplanDTO = userplanMapper.toDto(userplan);
        restUserplanMockMvc.perform(post("/api/userplans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userplanDTO)))
            .andExpect(status().isCreated());

        // Validate the Userplan in the database
        List<Userplan> userplanList = userplanRepository.findAll();
        assertThat(userplanList).hasSize(databaseSizeBeforeCreate + 1);
        Userplan testUserplan = userplanList.get(userplanList.size() - 1);
        assertThat(testUserplan.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testUserplan.getPromocode()).isEqualTo(DEFAULT_PROMOCODE);
        assertThat(testUserplan.getApplyDate()).isEqualTo(DEFAULT_APPLY_DATE);
        assertThat(testUserplan.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testUserplan.getPaid()).isEqualTo(DEFAULT_PAID);
        assertThat(testUserplan.getPlan()).isEqualTo(DEFAULT_PLAN);
        assertThat(testUserplan.getExpiryDate()).isEqualTo(DEFAULT_EXPIRY_DATE);
    }

    @Test
    @Transactional
    public void createUserplanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userplanRepository.findAll().size();

        // Create the Userplan with an existing ID
        userplan.setId(1L);
        UserplanDTO userplanDTO = userplanMapper.toDto(userplan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserplanMockMvc.perform(post("/api/userplans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userplanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userplan in the database
        List<Userplan> userplanList = userplanRepository.findAll();
        assertThat(userplanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserplans() throws Exception {
        // Initialize the database
        userplanRepository.saveAndFlush(userplan);

        // Get all the userplanList
        restUserplanMockMvc.perform(get("/api/userplans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userplan.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].promocode").value(hasItem(DEFAULT_PROMOCODE.toString())))
            .andExpect(jsonPath("$.[*].applyDate").value(hasItem(DEFAULT_APPLY_DATE.toString())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.toString())))
            .andExpect(jsonPath("$.[*].paid").value(hasItem(DEFAULT_PAID.toString())))
            .andExpect(jsonPath("$.[*].plan").value(hasItem(DEFAULT_PLAN.toString())))
            .andExpect(jsonPath("$.[*].expiryDate").value(hasItem(DEFAULT_EXPIRY_DATE.toString())));
    }
    

    // @Test
    // @Transactional
    // public void getUserplan() throws Exception {
    //     // Initialize the database
    //     userplanRepository.saveAndFlush(userplan);

    //     // Get the userplan
    //     restUserplanMockMvc.perform(get("/api/userplans/{id}", userplan.getId()))
    //         .andExpect(status().isOk())
    //         .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    //         .andExpect(jsonPath("$.id").value(userplan.getId().intValue()))
    //         .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
    //         .andExpect(jsonPath("$.promocode").value(DEFAULT_PROMOCODE.toString()))
    //         .andExpect(jsonPath("$.applyDate").value(DEFAULT_APPLY_DATE.toString()))
    //         .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.toString()))
    //         .andExpect(jsonPath("$.paid").value(DEFAULT_PAID.toString()))
    //         .andExpect(jsonPath("$.plan").value(DEFAULT_PLAN.toString()))
    //         .andExpect(jsonPath("$.expiryDate").value(DEFAULT_EXPIRY_DATE.toString()));
    // }
    @Test
    @Transactional
    public void getNonExistingUserplan() throws Exception {
        // Get the userplan
        restUserplanMockMvc.perform(get("/api/userplans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserplan() throws Exception {
        // Initialize the database
        userplanRepository.saveAndFlush(userplan);

        int databaseSizeBeforeUpdate = userplanRepository.findAll().size();

         // Update the userplan
        // Userplan updatedUserplan = userplanRepository.findById(userplan.getId()).get();
        // // Disconnect from session so that the updates on updatedUserplan are not directly saved in db
        // em.detach(updatedUserplan);
        // updatedUserplan
        //     .uid(UPDATED_UID)
        //     .promocode(UPDATED_PROMOCODE)
        //     .applyDate(UPDATED_APPLY_DATE)
        //     .discount(UPDATED_DISCOUNT)
        //     .paid(UPDATED_PAID)
        //     .plan(UPDATED_PLAN)
        //     .expiryDate(UPDATED_EXPIRY_DATE);
        // UserplanDTO userplanDTO = userplanMapper.toDto(updatedUserplan);

        // restUserplanMockMvc.perform(put("/api/userplans")
        //     .contentType(TestUtil.APPLICATION_JSON_UTF8)
        //     .content(TestUtil.convertObjectToJsonBytes(userplanDTO)))
        //     .andExpect(status().isOk());

        // Validate the Userplan in the database
        List<Userplan> userplanList = userplanRepository.findAll();
        assertThat(userplanList).hasSize(databaseSizeBeforeUpdate);
        Userplan testUserplan = userplanList.get(userplanList.size() - 1);
        assertThat(testUserplan.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testUserplan.getPromocode()).isEqualTo(UPDATED_PROMOCODE);
        assertThat(testUserplan.getApplyDate()).isEqualTo(UPDATED_APPLY_DATE);
        assertThat(testUserplan.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testUserplan.getPaid()).isEqualTo(UPDATED_PAID);
        assertThat(testUserplan.getPlan()).isEqualTo(UPDATED_PLAN);
        assertThat(testUserplan.getExpiryDate()).isEqualTo(UPDATED_EXPIRY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserplan() throws Exception {
        int databaseSizeBeforeUpdate = userplanRepository.findAll().size();

        // Create the Userplan
        UserplanDTO userplanDTO = userplanMapper.toDto(userplan);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserplanMockMvc.perform(put("/api/userplans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userplanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userplan in the database
        List<Userplan> userplanList = userplanRepository.findAll();
        assertThat(userplanList).hasSize(databaseSizeBeforeUpdate);
    }

    // @Test
    // @Transactional
    // public void deleteUserplan() throws Exception {
    //     // Initialize the database
    //     userplanRepository.saveAndFlush(userplan);

    //     int databaseSizeBeforeDelete = userplanRepository.findAll().size();

    //     // Get the userplan
    //     restUserplanMockMvc.perform(delete("/api/userplans/{id}", userplan.getId())
    //         .accept(TestUtil.APPLICATION_JSON_UTF8))
    //         .andExpect(status().isOk());

    //     // Validate the database is empty
    //     List<Userplan> userplanList = userplanRepository.findAll();
    //     assertThat(userplanList).hasSize(databaseSizeBeforeDelete - 1);
    // }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Userplan.class);
        Userplan userplan1 = new Userplan();
        userplan1.setId(1L);
        Userplan userplan2 = new Userplan();
        userplan2.setId(userplan1.getId());
        assertThat(userplan1).isEqualTo(userplan2);
        userplan2.setId(2L);
        assertThat(userplan1).isNotEqualTo(userplan2);
        userplan1.setId(null);
        assertThat(userplan1).isNotEqualTo(userplan2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserplanDTO.class);
        UserplanDTO userplanDTO1 = new UserplanDTO();
        userplanDTO1.setId(1L);
        UserplanDTO userplanDTO2 = new UserplanDTO();
        assertThat(userplanDTO1).isNotEqualTo(userplanDTO2);
        userplanDTO2.setId(userplanDTO1.getId());
        assertThat(userplanDTO1).isEqualTo(userplanDTO2);
        userplanDTO2.setId(2L);
        assertThat(userplanDTO1).isNotEqualTo(userplanDTO2);
        userplanDTO1.setId(null);
        assertThat(userplanDTO1).isNotEqualTo(userplanDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userplanMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userplanMapper.fromId(null)).isNull();
    }
}
