package com.enigma.miniproject.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.security.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.service.HarborStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(HarborStatusController.class)
@ContextConfiguration(classes = { HarborStatusController.class,  SecurityConfig.class })
public class HarborStatusControllerTest {
	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@MockBean
	private HarborStatusService harborStatusService;

	private ObjectMapper objectMapper = new ObjectMapper();
	private List<HarborStatus> listHarborStatus = new ArrayList<>();

	@Before
	public void setUp() {
		this.listHarborStatus.add(new HarborStatus(1, "status1"));
		this.listHarborStatus.add(new HarborStatus(2, "status2"));
		this.listHarborStatus.add(new HarborStatus(3, "status3"));

		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	@Test
	public void updateHarborStatusTest() throws Exception {
		Mockito.when(harborStatusService.updateHarborStatus(any(HarborStatus.class))).thenReturn(this.listHarborStatus.get(0));

		MvcResult result = mvc
				.perform(post("/harborStatus/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.listHarborStatus.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listHarborStatus.get(0)), result.getResponse().getContentAsString());
	}
	
	@Test
	public void createHarborTest() throws Exception {
		Mockito.when(harborStatusService.createHarborStatus(any(HarborStatus.class))).thenReturn(this.listHarborStatus.get(0));

		MvcResult result = mvc
				.perform(post("/harborStatus/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.listHarborStatus.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listHarborStatus.get(0)), result.getResponse().getContentAsString());

	}
	
	@Test
	public void getListHarborTest() throws Exception {
		Mockito.when(harborStatusService.getAllHarborStatus()).thenReturn(this.listHarborStatus);

		MvcResult result = mvc
				.perform(get("/harborStatus")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listHarborStatus), result.getResponse().getContentAsString());

	}

	@Test
	public void getListHarborStatusWithoutSuspendTest() throws Exception {
		Mockito.when(harborStatusService.findWithoutSuspend()).thenReturn(this.listHarborStatus);

		MvcResult result = mvc
				.perform(get("/harborStatus/withoutSuspend")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listHarborStatus), result.getResponse().getContentAsString());

	}

	@Test
	public void findByIdTest() throws Exception {
		Mockito.when(harborStatusService.findByharborStatusId(Mockito.anyInt()))
				.thenReturn(this.listHarborStatus.get(1));

		MvcResult result = mvc
				.perform(post("/harborStatus/id").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.listHarborStatus.get(1))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listHarborStatus.get(1)),
				result.getResponse().getContentAsString());
	}
}