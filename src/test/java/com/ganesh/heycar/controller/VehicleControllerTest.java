package com.ganesh.heycar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.ganesh.heycar.service.VehicleService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class VehicleControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	VehicleService service;

	@Autowired
	private WebApplicationContext webApplicationContext;

	String mockVehicleJSON = "[\n" + "{\n" + "\"code\": \"v\",\n" + "\"make\": \"Volkswagen\",\n"
			+ "\"model\": \"polo GT\",\n" + "\"kW\": 300,\n" + "\"year\": 2021,\n" + "\"color\": \"black\",\n"
			+ "\"price\": 2000\n" + "},\n" + "{\n" + "\"code\": \"m\",\n" + "\"make\": \"Mercedes\",\n"
			+ "\"model\": \"gclass\",\n" + "\"kW\": 1000,\n" + "\"year\": 2025,\n" + "\"color\": \"mattblack\",\n"
			+ "\"price\": 9999\n" + "}\n" + "]\n";

	@Test
	public void uploadCSVFileTest() throws Exception {

		MockMultipartFile file = new MockMultipartFile("test.csv", "test.csv", "text/csv",
				this.getClass().getResourceAsStream("/test.csv"));

		mockMvc.perform(
				MockMvcRequestBuilders.multipart("/upload_csv/1").file("file", file.getBytes()).contentType("text/csv"))
				.andExpect(status().isCreated());
	}

	@Test
	public void uploadJSONListTest() throws Exception {

		mockMvc.perform(post("/vehicle_listings/1").content(mockVehicleJSON).contentType("application/json"))
				.andExpect(status().isOk());

	}
	
	@Test
	public void searchTest() throws Exception{
		
		mockMvc.perform(get(("/search?make=mercedes&model=a 180&year=2014&color=black"))).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].make").value("mercedes"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value("a 180"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].power").value("123"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].year").value("2014"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value("black"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("15950"));
		
		
	}
}
