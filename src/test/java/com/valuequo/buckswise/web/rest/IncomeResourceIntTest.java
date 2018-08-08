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
 * Test class for the Income REST controller.
 *
 * @see IncomeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class IncomeResourceIntTest {

    private MockMvc restMockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        IncomeResource incomeResource = new IncomeResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(incomeResource)
            .build();
    }

    /**
    * Test income
    */
    @Test
    public void testIncome() throws Exception {
        restMockMvc.perform(post("/api/income/income"))
            .andExpect(status().isOk());
    }

}
