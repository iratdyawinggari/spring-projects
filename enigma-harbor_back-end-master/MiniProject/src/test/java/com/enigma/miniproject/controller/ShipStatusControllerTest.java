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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.service.ShipService;
import com.enigma.miniproject.service.ShipStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ShipStatusController.class)
@ContextConfiguration(classes = { ShipStatusController.class,  SecurityConfig.class })
public class ShipStatusControllerTest {
	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@MockBean
	private ShipStatusService shipStatusService;

	private ObjectMapper objectMapper = new ObjectMapper();
	private List<ShipStatus> listShipStatus = new ArrayList<>();

	@Before
	public void setUp() {
		this.listShipStatus.add(new ShipStatus(1, "status1"));
		this.listShipStatus.add(new ShipStatus(2, "status2"));
		this.listShipStatus.add(new ShipStatus(3, "status3"));

		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	@Test
	public void updateShipStatusTest() throws Exception {
		Mockito.when(shipStatusService.updateShipStatus(any(ShipStatus.class))).thenReturn(this.listShipStatus.get(0));

		MvcResult result = mvc
				.perform(post("/shipStatus/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.listShipStatus.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listShipStatus.get(0)), result.getResponse().getContentAsString());
	}
	
	@Test
	public void createShipTest() throws Exception {
		Mockito.when(shipStatusService.createShipStatus(any(ShipStatus.class))).thenReturn(this.listShipStatus.get(0));

		MvcResult result = mvc
				.perform(post("/shipStatus/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.listShipStatus.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listShipStatus.get(0)), result.getResponse().getContentAsString());

	}
	
	@Test
	public void getListShipTest() throws Exception {
		Mockito.when(shipStatusService.getAllShipStatus()).thenReturn(this.listShipStatus);

		MvcResult result = mvc
				.perform(get("/shipStatus")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listShipStatus), result.getResponse().getContentAsString());

	}

	@Test
	public void getListShipStatusWithoutSuspendTest() throws Exception {
		Mockito.when(shipStatusService.findWithoutSuspend()).thenReturn(this.listShipStatus);

		MvcResult result = mvc
				.perform(get("/shipStatus/withoutSuspend")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listShipStatus), result.getResponse().getContentAsString());

	}

	@Test
	public void findByIdTest() throws Exception {
		Mockito.when(shipStatusService.findByShipStatusId(Mockito.anyInt()))
				.thenReturn(this.listShipStatus.get(1));

		MvcResult result = mvc
				.perform(post("/shipStatus/id").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.listShipStatus.get(1))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.listShipStatus.get(1)),
				result.getResponse().getContentAsString());
	}

//	@Test
//	public void deleteShipTest() throws Exception {
//		Mockito.when(shipService.deleteShip(Mockito.anyString()))
//				.thenReturn(this.ships.get(1));
//
//		MvcResult result = mvc
//				.perform(post("/ships/delete").contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsBytes(this.ships.get(1))))
//				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andReturn();
//
//		assertEquals(objectMapper.writeValueAsString(this.ships.get(1)),
//				result.getResponse().getContentAsString());
//	}
//	
}
