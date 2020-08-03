package com.enigma.miniproject.controller;

//
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.enigma.miniproject.entity.Harbor;
import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.service.HarborService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(HarborController.class)
@ContextConfiguration(classes = { HarborController.class, SecurityConfig.class })
public class HarborControllerTest {
	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@MockBean
	private HarborService harborService;

	private ObjectMapper objectMapper = new ObjectMapper();
	private List<Harbor> harbor = new ArrayList<>();

	@Before
	public void setUp() {
		HarborStatus status1 = new HarborStatus();
		status1.setHarborStatusId(1);
		HarborStatus status2 = new HarborStatus();
		status2.setHarborStatusId(2);
		HarborStatus status3 = new HarborStatus();
		status2.setHarborStatusId(3);

		this.harbor.add(new Harbor("code 1", "dock 1", 100, status1));
		this.harbor.add(new Harbor("code 2", "dock 2", 200, status2));
		this.harbor.add(new Harbor("code 3", "dock 3", 300, status3));
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	Page<Harbor> pagesHarbor = new PageImpl<Harbor>(harbor);

	@Test
	public void getAllDockTest() throws Exception {
		Mockito.when(harborService.doShowByPage(0, 10)).thenReturn(this.pagesHarbor);

		MvcResult result = mvc
				.perform(get("/harbors/page").param("page", "0").param("pageSize", "10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesHarbor), result.getResponse().getContentAsString());

	}

	@Test
	public void updateDockTest() throws Exception {
		Mockito.when(harborService.updateharbor(any(Harbor.class))).thenReturn(this.harbor.get(0));

		MvcResult result = mvc
				.perform(post("/harbors/update").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.harbor.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.harbor.get(0)), result.getResponse().getContentAsString());
	}

	@Test
	public void createDockTest() throws Exception {
		Mockito.when(harborService.createharbor(any(Harbor.class))).thenReturn(this.harbor.get(0));

		MvcResult result = mvc
				.perform(post("/harbors/add").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.harbor.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.harbor.get(0)), result.getResponse().getContentAsString());

	}
	
	@Test
	public void getListShipTest() throws Exception {
		Mockito.when(harborService.getAllharbor()).thenReturn(this.harbor);

		MvcResult result = mvc
				.perform(get("/harbors")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.harbor), result.getResponse().getContentAsString());

	}

	
	@Test
	public void findByIdTest() throws Exception {
		Mockito.when(harborService.findByharborCode(Mockito.anyString()))
				.thenReturn(this.harbor.get(1));

		MvcResult result = mvc
				.perform(post("/harbors/id").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.harbor.get(1))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.harbor.get(1)),
				result.getResponse().getContentAsString());
	}

}
