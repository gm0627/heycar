package com.ganesh.heycar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ganesh.heycar.model.Dealer;
import com.ganesh.heycar.service.DealerService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DealerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	DealerService service;



	List<Dealer> mockDealerList = Arrays.asList(new Dealer(1, "MERC"), new Dealer(2, "VW"));

	String mockDealerJSON = "[\n" + "{\n" + "\"id\": \"1\",\n" + "\"name\": \"MERC\"\n" + "},\n" + "{\n"
			+ "\"id\": \"2\",\n" + "\"name\": \"VW\"\n" + "}" + "]\n";

	@Test
	public void uploadDealers() throws Exception {

		mockMvc.perform(post("/dealers").content(mockDealerJSON).contentType("application/json"))
				.andExpect(status().isOk());

	}

	@Test
	public void fetchAllDealers() throws Exception {

		mockMvc.perform(get(("/dealers"))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("MERC"));

	}

	@Test
	public void fetchDealerByID() throws Exception {

		mockMvc.perform(get(("/dealer/1")).contentType("application/json")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("MERC"));

	}

}
