package com.apoorv.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.apoorv.api.model.Registration;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class SpringMongoApplicationTestsRegistrationController {

	private MockMvc mockMvc;

	ObjectMapper om = new ObjectMapper();

	@Autowired
	private WebApplicationContext context;

	@BeforeAll
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

//	@Test
	public void saveRegistrationTest() throws Exception {
		Registration r = new Registration("1", "Amit", "Address Test", "India", "Goa", "test@test.com", "Admin@123",
				"ASDFG1212W", "9171558594", "06/22/2021", "test@test.com");
		String jsonRequest = om.writeValueAsString(r);
		MvcResult result = mockMvc
				.perform(post("/saveRegistration").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Registration responseReg = om.readValue(resultContent, Registration.class);
		assertEquals(r.getAddress(), responseReg.getAddress());
	}
	
//	@Test
	public void fetchRegistrationTest() throws Exception {
		String email = "q@q.comc";
		MvcResult result = mockMvc
				.perform(post("/fetchRegistration").content(email).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		String resultContent = result.getResponse().getContentAsString();
		Registration responseReg = om.readValue(resultContent, Registration.class);
		assertEquals(email, responseReg.getEmail());
	}
	
	@Test
	public void updateRegistrationTest() throws Exception {
		Registration r = new Registration("1", "Amit", "Address Test", "India", "Goa", "test@test.com", "Admin@123",
				"ASDFG1212W", "9171558594", "06/22/2021", "test@test.com");

		String requestContent = om.writeValueAsString(r);
		MvcResult result = mockMvc
				.perform(post("/updateRegistration").content(requestContent).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		
		String resultContent = result.getResponse().getContentAsString();
		Registration responseReg = om.readValue(resultContent, Registration.class);
		assertEquals(r.getId(), responseReg.getId());
	}
}
