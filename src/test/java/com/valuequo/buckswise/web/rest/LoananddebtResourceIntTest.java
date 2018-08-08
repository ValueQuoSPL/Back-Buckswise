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
 * Test class for the Loananddebt REST controller.
 *
 * @see LoananddebtResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuckswiseApp.class)
public class LoananddebtResourceIntTest {

    private MockMvc restMockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        LoananddebtResource loananddebtResource = new LoananddebtResource();
        restMockMvc = MockMvcBuilders
            .standaloneSetup(loananddebtResource)
            .build();
    }

    /**
    * Test loanDebt
    */
    @Test
    public void testLoanDebt() throws Exception {
        restMockMvc.perform(post("/api/loananddebt/loan-debt"))
            .andExpect(status().isOk());
    }

}
