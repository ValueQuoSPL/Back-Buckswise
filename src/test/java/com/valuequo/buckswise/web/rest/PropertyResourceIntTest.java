package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;

import com.valuequo.buckswise.domain.Property;
import com.valuequo.buckswise.repository.PropertyRepository;
import com.valuequo.buckswise.service.PropertyService;
import com.valuequo.buckswise.service.dto.PropertyDTO;
import com.valuequo.buckswise.service.mapper.PropertyMapper;
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
 * Test class for the PropertyResource REST controller.
 *
 * @see PropertyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class PropertyResourceIntTest {

    private static final String DEFAULT_PROP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROP_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PROP_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_PROP_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_M_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_M_VALUE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AS_OF_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AS_OF_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final Long DEFAULT_USERID = 1L;
    private static final Long UPDATED_USERID = 2L;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPropertyMockMvc;

    private Property property;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropertyResource propertyResource = new PropertyResource(propertyService);
        this.restPropertyMockMvc = MockMvcBuilders.standaloneSetup(propertyResource)
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
    public static Property createEntity(EntityManager em) {
        Property property = new Property()
            .prop_name(DEFAULT_PROP_NAME)
            .prop_type(DEFAULT_PROP_TYPE)
            .prop_details(DEFAULT_PROP_DETAILS)
            .current_m_value(DEFAULT_CURRENT_M_VALUE)
            .as_of_date(DEFAULT_AS_OF_DATE)
            .notes(DEFAULT_NOTES)
            .userid(DEFAULT_USERID);
        return property;
    }

    @Before
    public void initTest() {
        property = createEntity(em);
    }

    @Test
    @Transactional
    public void createProperty() throws Exception {
        int databaseSizeBeforeCreate = propertyRepository.findAll().size();

        // Create the Property
        PropertyDTO propertyDTO = propertyMapper.toDto(property);
        restPropertyMockMvc.perform(post("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isCreated());

        // Validate the Property in the database
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeCreate + 1);
        Property testProperty = propertyList.get(propertyList.size() - 1);
        assertThat(testProperty.getProp_name()).isEqualTo(DEFAULT_PROP_NAME);
        assertThat(testProperty.getProp_type()).isEqualTo(DEFAULT_PROP_TYPE);
        assertThat(testProperty.getProp_details()).isEqualTo(DEFAULT_PROP_DETAILS);
        assertThat(testProperty.getCurrent_m_value()).isEqualTo(DEFAULT_CURRENT_M_VALUE);
        assertThat(testProperty.getAs_of_date()).isEqualTo(DEFAULT_AS_OF_DATE);
        assertThat(testProperty.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testProperty.getUserid()).isEqualTo(DEFAULT_USERID);
    }

    @Test
    @Transactional
    public void createPropertyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propertyRepository.findAll().size();

        // Create the Property with an existing ID
        property.setId(1L);
        PropertyDTO propertyDTO = propertyMapper.toDto(property);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropertyMockMvc.perform(post("/api/properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Property in the database
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProperties() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get all the propertyList
        restPropertyMockMvc.perform(get("/api/properties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(property.getId().intValue())))
            .andExpect(jsonPath("$.[*].prop_name").value(hasItem(DEFAULT_PROP_NAME.toString())))
            .andExpect(jsonPath("$.[*].prop_type").value(hasItem(DEFAULT_PROP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].prop_details").value(hasItem(DEFAULT_PROP_DETAILS.toString())))
            .andExpect(jsonPath("$.[*].current_m_value").value(hasItem(DEFAULT_CURRENT_M_VALUE.toString())))
            .andExpect(jsonPath("$.[*].as_of_date").value(hasItem(DEFAULT_AS_OF_DATE.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.intValue())));
    }

    @Test
    @Transactional
    public void getProperty() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);

        // Get the property
        restPropertyMockMvc.perform(get("/api/propertiesbyid/{id}", property.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(property.getId().intValue()))
            .andExpect(jsonPath("$.prop_name").value(DEFAULT_PROP_NAME.toString()))
            .andExpect(jsonPath("$.prop_type").value(DEFAULT_PROP_TYPE.toString()))
            .andExpect(jsonPath("$.prop_details").value(DEFAULT_PROP_DETAILS.toString()))
            .andExpect(jsonPath("$.current_m_value").value(DEFAULT_CURRENT_M_VALUE.toString()))
            .andExpect(jsonPath("$.as_of_date").value(DEFAULT_AS_OF_DATE.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProperty() throws Exception {
        // Get the property
        restPropertyMockMvc.perform(get("/api/properties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProperty() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);
        int databaseSizeBeforeUpdate = propertyRepository.findAll().size();

        // Update the property
        Property updatedProperty = propertyRepository.findOne(property.getId());
        // Disconnect from session so that the updates on updatedProperty are not directly saved in db
        em.detach(updatedProperty);
        updatedProperty
            .prop_name(UPDATED_PROP_NAME)
            .prop_type(UPDATED_PROP_TYPE)
            .prop_details(UPDATED_PROP_DETAILS)
            .current_m_value(UPDATED_CURRENT_M_VALUE)
            .as_of_date(UPDATED_AS_OF_DATE)
            .notes(UPDATED_NOTES)
            .userid(UPDATED_USERID);
        PropertyDTO propertyDTO = propertyMapper.toDto(updatedProperty);

        restPropertyMockMvc.perform(put("/api/putproperties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isOk());

        // Validate the Property in the database
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeUpdate);
        Property testProperty = propertyList.get(propertyList.size() - 1);
        assertThat(testProperty.getProp_name()).isEqualTo(UPDATED_PROP_NAME);
        assertThat(testProperty.getProp_type()).isEqualTo(UPDATED_PROP_TYPE);
        assertThat(testProperty.getProp_details()).isEqualTo(UPDATED_PROP_DETAILS);
        assertThat(testProperty.getCurrent_m_value()).isEqualTo(UPDATED_CURRENT_M_VALUE);
        assertThat(testProperty.getAs_of_date()).isEqualTo(UPDATED_AS_OF_DATE);
        assertThat(testProperty.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testProperty.getUserid()).isEqualTo(UPDATED_USERID);
    }

    @Test
    @Transactional
    public void updateNonExistingProperty() throws Exception {
        int databaseSizeBeforeUpdate = propertyRepository.findAll().size();

        // Create the Property
        PropertyDTO propertyDTO = propertyMapper.toDto(property);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPropertyMockMvc.perform(put("/api/putproperties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propertyDTO)))
            .andExpect(status().isCreated());

        // Validate the Property in the database
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProperty() throws Exception {
        // Initialize the database
        propertyRepository.saveAndFlush(property);
        int databaseSizeBeforeDelete = propertyRepository.findAll().size();

        // Get the property
        restPropertyMockMvc.perform(delete("/api/deleteproperties/{id}", property.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Property> propertyList = propertyRepository.findAll();
        assertThat(propertyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Property.class);
        Property property1 = new Property();
        property1.setId(1L);
        Property property2 = new Property();
        property2.setId(property1.getId());
        assertThat(property1).isEqualTo(property2);
        property2.setId(2L);
        assertThat(property1).isNotEqualTo(property2);
        property1.setId(null);
        assertThat(property1).isNotEqualTo(property2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PropertyDTO.class);
        PropertyDTO propertyDTO1 = new PropertyDTO();
        propertyDTO1.setId(1L);
        PropertyDTO propertyDTO2 = new PropertyDTO();
        assertThat(propertyDTO1).isNotEqualTo(propertyDTO2);
        propertyDTO2.setId(propertyDTO1.getId());
        assertThat(propertyDTO1).isEqualTo(propertyDTO2);
        propertyDTO2.setId(2L);
        assertThat(propertyDTO1).isNotEqualTo(propertyDTO2);
        propertyDTO1.setId(null);
        assertThat(propertyDTO1).isNotEqualTo(propertyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(propertyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(propertyMapper.fromId(null)).isNull();
    }
}
