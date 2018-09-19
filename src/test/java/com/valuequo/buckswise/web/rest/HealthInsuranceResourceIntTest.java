package com.valuequo.buckswise.web.rest;

import com.valuequo.buckswise.BuckswiseApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the HealthInsurance REST controller.
 *
 * @see HealthInsuranceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class HealthInsuranceResourceIntTest {

    private MockMvc restMockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        HealthInsuranceResource healthInsuranceResource = new HealthInsuranceResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(healthInsuranceResource)
            .build();
    }

    /**
    * Test health
    */
    @Test
    public void testHealth() throws Exception {
        restMockMvc.perform(post("/api/health-insurance/health"))
            .andExpect(status().isOk());
    }

}
