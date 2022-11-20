package com.hw.sol.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hw.sol.service.RewardsService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RewardsControllerTest {
	

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
	private RewardsService rewardsService;

    @Test
    public void testGetCustomerRewards() throws Exception {
        Mockito.when(rewardsService.generateCustomerRewards()).thenReturn(null);
        mockMvc.perform(get("/api/v1/customers/rewards")).andExpect(status().isOk());
    }
}
