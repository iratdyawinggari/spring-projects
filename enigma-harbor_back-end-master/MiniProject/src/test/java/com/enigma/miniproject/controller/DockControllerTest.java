package com.enigma.miniproject.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.entity.Harbor;
import com.enigma.miniproject.service.DockService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(DockController.class)
@ContextConfiguration(classes={DockController.class, SecurityConfig.class})
public class DockControllerTest {

	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@MockBean
	private DockService dockService;

	private ObjectMapper objectMapper = new ObjectMapper();
	private List<Dock> dock = new ArrayList<>();

	@Before
	public void setUp() {
		Harbor harbor1 = new Harbor();
		harbor1.setHarborCode("code 1");
		DockStatus status1 = new DockStatus();
		status1.setDockStatusId(1);
		Harbor harbor2 = new Harbor();
		harbor2.setHarborCode("code 2");
		DockStatus status2 = new DockStatus();
		status2.setDockStatusId(2);
		DockStatus status3 = new DockStatus();
		status3.setDockStatusId(3);

		
		this.dock.add(new Dock("code 1", "dock 1", harbor1, status1));
		this.dock.add(new Dock("code 2", "dock 2", harbor2, status2));		
		this.dock.add(new Dock("code 3", "dock 3", harbor2, status3));		
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	Page<Dock> pagesDock = new PageImpl<Dock>(dock);

	@Test
	public void getAllDockTest() throws Exception {
		Mockito.when(dockService.doShowByPage(0, 10)).thenReturn(this.pagesDock);

		MvcResult result = mvc
				.perform(get("/docks/page").param("page", "0").param("pageSize", "10")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesDock), result.getResponse().getContentAsString());

	}

	@Test
	public void getAllAvailableDockTest() throws Exception {
		Mockito.when(dockService.findAvailableDock()).thenReturn(this.dock);

		MvcResult result = mvc
				.perform(get("/docks/available")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.dock), result.getResponse().getContentAsString());

	}
//	@Test
//	public void deleteDockTest() throws Exception {
//		Mockito.when(dockService.deleteDock(any(Dock.class).getDockCode())).thenReturn(this.dock.get(0));
//
//		MvcResult result = mvc
//				.perform(post("/docks/delete")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsBytes(this.dock.get(0))))
//				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andReturn();
//
//		assertNotEquals(objectMapper.writeValueAsString(this.dock.get(0)), result.getResponse().getContentAsString());
//	}

	
	@Test
	public void updateDockTest() throws Exception {
		Mockito.when(dockService.updateDock(any(Dock.class))).thenReturn(this.dock.get(0));

		MvcResult result = mvc
				.perform(post("/docks/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.dock.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.dock.get(0)), result.getResponse().getContentAsString());
	}
	
	@Test
	public void createDockTest() throws Exception {
		Mockito.when(dockService.createDock(any(Dock.class))).thenReturn(this.dock.get(0));

		MvcResult result = mvc
				.perform(post("/docks/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.dock.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.dock.get(0)), result.getResponse().getContentAsString());

	}
	
	@Test
	public void findByIdTest() throws Exception {
		Mockito.when(dockService.findByDockCode(Mockito.anyString()))
				.thenReturn(this.dock.get(1));

		MvcResult result = mvc
				.perform(post("/docks/id").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.dock.get(1))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.dock.get(1)),
				result.getResponse().getContentAsString());
	}

	@Test
	public void deleteDockTest() throws Exception {
		Mockito.when(dockService.deleteDock(Mockito.anyString()))
				.thenReturn(this.dock.get(0));

		MvcResult result = mvc
				.perform(post("/docks/delete").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.dock.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.dock.get(0)),
				result.getResponse().getContentAsString());
	}
	
//	@Test
//	public void deleteDockTest() throws Exception {
//
//		Mockito.when(dockService.deleteByDockId(any(Dock.class))).thenReturn(this.dock.get(2));
//
//		MvcResult result = mvc
//				.perform(post("/DeleteDock")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsBytes(this.dock.get(2))))
//				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andReturn();
//
//		assertEquals(objectMapper.writeValueAsString(this.dock.get(2)), result.getResponse().getContentAsString());
//
//	}

	
}
