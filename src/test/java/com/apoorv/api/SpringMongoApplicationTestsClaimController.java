package com.apoorv.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.apoorv.api.model.ClaimForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class SpringMongoApplicationTestsClaimController {

	private MockMvc mockMvc;

	ObjectMapper om = new ObjectMapper();

	@Autowired
	private WebApplicationContext context;

	@BeforeAll
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

//	@Test
	public void saveClaimTest() throws Exception {
		ClaimForm claim = new ClaimForm(1000, "Junit test", "Kumar", "6/22/2021", "6/22/2021", "6/22/2021",
				"Test Provider", 100, "Self");
		String jsonRequest = om.writeValueAsString(claim);
		MvcResult result = mockMvc
				.perform(post("/addClaim").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ClaimForm responseClaim = om.readValue(resultContent, ClaimForm.class);
		assertEquals(claim.getFirstName(), responseClaim.getFirstName());
	}

//	@Test
	public void getAllClaimTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/findAllClaims").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		String resultContent = result.getResponse().getContentAsString();
		List<ClaimForm> responseClaim = om.readValue(resultContent, new TypeReference<List<ClaimForm>>() {
		});
		assertEquals(2, responseClaim.size());
	}

//	@Test
	public void deleteClaimTest() throws Exception {
		// first check if this id is available in db or not
		String id = "5";
		MvcResult result = mockMvc.perform(delete("/deleteClaim/{id}", id)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		String resultContent = result.getResponse().getContentAsString();
		List<ClaimForm> responseClaim = om.readValue(resultContent, new TypeReference<List<ClaimForm>>() {
		});
		System.out.println(responseClaim.size());
		assertEquals(1, responseClaim.size());
	}

//	@Test
	public void fetchClaimByIdTest() throws Exception {
		String id = "6";
		MvcResult result = mockMvc
				.perform(post("/fetchClaimById").content(id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		String resultContent = result.getResponse().getContentAsString();
		ClaimForm responseClaim = om.readValue(resultContent, ClaimForm.class);
		assertEquals(Integer.parseInt(id), responseClaim.getId());
	}

	@Test
	public void updateClaimByIdTest() throws Exception {
		ClaimForm claim = new ClaimForm(6, "Junit test", "Kumar", "6/22/2021", "6/22/2021", "6/22/2021",
				"Test Provider", 100, "Self");
		String requestContent = om.writeValueAsString(claim);
		MvcResult result = mockMvc
				.perform(post("/updateClaimById").content(requestContent).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		String resultContent = result.getResponse().getContentAsString();
		ClaimForm responseClaim = om.readValue(resultContent, ClaimForm.class);
		assertEquals(claim.getId(), responseClaim.getId());
	}

}
