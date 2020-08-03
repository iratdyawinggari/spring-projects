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

import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.service.DockStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(DockStatusController.class)
@ContextConfiguration(classes = { DockStatusController.class,  SecurityConfig.class })
public class DockStatusControllerTest {
	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@MockBean
	private DockStatusService DockStatusService;

	private ObjectMapper objectMapper = new ObjectMapper();
	private List<DockStatus> listDockStatus = new ArrayList<>();

	@Before
	public void setUp() {
		this.listDockStatus.add(new DockStatus(1, "status1"));
		this.listDockStatus.add(new DockStatus(2, "status2"));
		this.listDockStatus.add(new DockStatus(3, "status3"));

		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	@Test
	public void updateDockStatusTest() throws Exception {
		Mockito.when(DockStatusService.updateDockStatus(any(DockStatus.class))).thenReturn(this.listDockStatus.get(0));

		MvcResult result = mvc
				.perform(post("/dockStatus/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.listDockStatus.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listDockStatus.get(0)), result.getResponse().getContentAsString());
	}
	
	@Test
	public void createDockTest() throws Exception {
		Mockito.when(DockStatusService.createDockStatus(any(DockStatus.class))).thenReturn(this.listDockStatus.get(0));

		MvcResult result = mvc
				.perform(post("/dockStatus/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.listDockStatus.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listDockStatus.get(0)), result.getResponse().getContentAsString());

	}
	
	@Test
	public void getListDockTest() throws Exception {
		Mockito.when(DockStatusService.getAllDockStatus()).thenReturn(this.listDockStatus);

		MvcResult result = mvc
				.perform(get("/dockStatus")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listDockStatus), result.getResponse().getContentAsString());

	}

	@Test
	public void getListDockStatusWithoutSuspendTest() throws Exception {
		Mockito.when(DockStatusService.findWithoutSuspend()).thenReturn(this.listDockStatus);

		MvcResult result = mvc
				.perform(get("/dockStatus/withoutSuspend")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listDockStatus), result.getResponse().getContentAsString());

	}

	@Test
	public void findByIdTest() throws Exception {
		Mockito.when(DockStatusService.findByDockStatusId(Mockito.anyInt()))
				.thenReturn(this.listDockStatus.get(1));

		MvcResult result = mvc
				.perform(post("/dockStatus/id").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.listDockStatus.get(1))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listDockStatus.get(1)),
				result.getResponse().getContentAsString());
	}
}