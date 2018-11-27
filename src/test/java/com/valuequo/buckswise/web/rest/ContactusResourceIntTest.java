package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Contactus;
import com.valuequo.buckswise.repository.ContactusRepository;
import com.valuequo.buckswise.service.ContactusService;
import com.valuequo.buckswise.service.MailService;
import com.valuequo.buckswise.service.dto.ContactusDTO;
import com.valuequo.buckswise.service.mapper.ContactusMapper;
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
 * Test class for the ContactusResource REST controller.
 *
 * @see ContactusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class ContactusResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE =  "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MSG = "AAAAAAAAAA";
    private static final String UPDATED_MSG = "BBBBBBBBBB";

    @Autowired
    private ContactusRepository contactusRepository;

    @Autowired
    private ContactusMapper contactusMapper;

    @Autowired
    private ContactusService contactusService;

    @Autowired
    private MailService mailService;
    
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContactusMockMvc;

    private Contactus contactus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactusResource contactusResource = new ContactusResource(contactusService, mailService);
        this.restContactusMockMvc = MockMvcBuilders.standaloneSetup(contactusResource)
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
    public static Contactus createEntity(EntityManager em) {
        Contactus contactus = new Contactus()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .msg(DEFAULT_MSG);
        return contactus;
    }

    @Before
    public void initTest() {
        contactus = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactus() throws Exception {
        int databaseSizeBeforeCreate = contactusRepository.findAll().size();

        // Create the Contactus
        ContactusDTO contactusDTO = contactusMapper.toDto(contactus);
        restContactusMockMvc.perform(post("/api/contactuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactusDTO)))
            .andExpect(status().isCreated());

        // Validate the Contactus in the database
        List<Contactus> contactusList = contactusRepository.findAll();
        assertThat(contactusList).hasSize(databaseSizeBeforeCreate + 1);
        Contactus testContactus = contactusList.get(contactusList.size() - 1);
        assertThat(testContactus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testContactus.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testContactus.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testContactus.getMsg()).isEqualTo(DEFAULT_MSG);
    }

    @Test
    @Transactional
    public void createContactusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactusRepository.findAll().size();

        // Create the Contactus with an existing ID
        contactus.setId(1L);
        ContactusDTO contactusDTO = contactusMapper.toDto(contactus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactusMockMvc.perform(post("/api/contactuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contactus in the database
        List<Contactus> contactusList = contactusRepository.findAll();
        assertThat(contactusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContactuses() throws Exception {
        // Initialize the database
        contactusRepository.saveAndFlush(contactus);

        // Get all the contactusList
        restContactusMockMvc.perform(get("/api/contactuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].msg").value(hasItem(DEFAULT_MSG.toString())));
    }

    @Test
    @Transactional
    public void getContactus() throws Exception {
        // Initialize the database
        contactusRepository.saveAndFlush(contactus);

        // Get the contactus
        restContactusMockMvc.perform(get("/api/contactuses/{id}", contactus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contactus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.msg").value(DEFAULT_MSG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContactus() throws Exception {
        // Get the contactus
        restContactusMockMvc.perform(get("/api/contactuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactus() throws Exception {
        // Initialize the database
        contactusRepository.saveAndFlush(contactus);
        int databaseSizeBeforeUpdate = contactusRepository.findAll().size();

        // Update the contactus
        Contactus updatedContactus = contactusRepository.findOne(contactus.getId());
        // Disconnect from session so that the updates on updatedContactus are not directly saved in db
        em.detach(updatedContactus);
        updatedContactus
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .msg(UPDATED_MSG);
        ContactusDTO contactusDTO = contactusMapper.toDto(updatedContactus);

        restContactusMockMvc.perform(put("/api/contactuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactusDTO)))
            .andExpect(status().isOk());

        // Validate the Contactus in the database
        List<Contactus> contactusList = contactusRepository.findAll();
        assertThat(contactusList).hasSize(databaseSizeBeforeUpdate);
        Contactus testContactus = contactusList.get(contactusList.size() - 1);
        assertThat(testContactus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testContactus.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testContactus.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testContactus.getMsg()).isEqualTo(UPDATED_MSG);
    }

    @Test
    @Transactional
    public void updateNonExistingContactus() throws Exception {
        int databaseSizeBeforeUpdate = contactusRepository.findAll().size();

        // Create the Contactus
        ContactusDTO contactusDTO = contactusMapper.toDto(contactus);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContactusMockMvc.perform(put("/api/contactuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactusDTO)))
            .andExpect(status().isCreated());

        // Validate the Contactus in the database
        List<Contactus> contactusList = contactusRepository.findAll();
        assertThat(contactusList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContactus() throws Exception {
        // Initialize the database
        contactusRepository.saveAndFlush(contactus);
        int databaseSizeBeforeDelete = contactusRepository.findAll().size();

        // Get the contactus
        restContactusMockMvc.perform(delete("/api/contactuses/{id}", contactus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contactus> contactusList = contactusRepository.findAll();
        assertThat(contactusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contactus.class);
        Contactus contactus1 = new Contactus();
        contactus1.setId(1L);
        Contactus contactus2 = new Contactus();
        contactus2.setId(contactus1.getId());
        assertThat(contactus1).isEqualTo(contactus2);
        contactus2.setId(2L);
        assertThat(contactus1).isNotEqualTo(contactus2);
        contactus1.setId(null);
        assertThat(contactus1).isNotEqualTo(contactus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactusDTO.class);
        ContactusDTO contactusDTO1 = new ContactusDTO();
        contactusDTO1.setId(1L);
        ContactusDTO contactusDTO2 = new ContactusDTO();
        assertThat(contactusDTO1).isNotEqualTo(contactusDTO2);
        contactusDTO2.setId(contactusDTO1.getId());
        assertThat(contactusDTO1).isEqualTo(contactusDTO2);
        contactusDTO2.setId(2L);
        assertThat(contactusDTO1).isNotEqualTo(contactusDTO2);
        contactusDTO1.setId(null);
        assertThat(contactusDTO1).isNotEqualTo(contactusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contactusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contactusMapper.fromId(null)).isNull();
    }
}
