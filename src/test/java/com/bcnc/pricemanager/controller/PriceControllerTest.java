package com.bcnc.pricemanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test1() throws Exception {
	    mockMvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
	            .param("applicationDate", "2020-06-14T10:00:00"))
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	void test2() throws Exception {
	    mockMvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
	            .param("applicationDate", "2020-06-14T16:00:00"))
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.price").value(25.45));
	}

	@Test
	void test3() throws Exception {
	    mockMvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
	            .param("applicationDate", "2020-06-14T21:00:00"))
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.price").value(35.50));
	}

	@Test
	void test4() throws Exception {
	    mockMvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
	            .param("applicationDate", "2020-06-15T10:00:00"))
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.price").value(30.50));
	}

	@Test
	void test5() throws Exception {
	    mockMvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
	            .param("applicationDate", "2020-06-16T21:00:00"))
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.price").value(38.95));
	}

}
