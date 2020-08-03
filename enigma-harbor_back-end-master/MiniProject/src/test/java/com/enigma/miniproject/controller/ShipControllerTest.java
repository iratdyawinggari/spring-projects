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
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ShipController.class)
@ContextConfiguration(classes = { ShipController.class,  SecurityConfig.class })
public class ShipControllerTest {
	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@MockBean
	private ShipService shipService;

	private ObjectMapper objectMapper = new ObjectMapper();
	private List<Ship> ships = new ArrayList<>();

	@Before
	public void setUp() {
		ShipStatus status1 = new ShipStatus();
		status1.setShipStatusId(1);
		ShipStatus status2 = new ShipStatus();
		status2.setShipStatusId(2);
		ShipStatus status3 = new ShipStatus();
		status2.setShipStatusId(3);

		this.ships.add(new Ship("code1", "ship1", "Captain1", status1));
		this.ships.add(new Ship("code2", "ship2", "Captain2", status2));		
		this.ships.add(new Ship("code3", "ship3", "Captain3", status3));		

		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	Page<Ship> pagesShip = new PageImpl<Ship>(ships);

	@Test
	public void getAllShipTest() throws Exception {
		Mockito.when(shipService.doShowByPage(0, 10)).thenReturn(this.pagesShip);

		MvcResult result = mvc
				.perform(get("/ships/page").param("page", "0").param("pageSize", "10")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesShip), result.getResponse().getContentAsString());

	}

	
	@Test
	public void updateShipTest() throws Exception {
		Mockito.when(shipService.updateShip(any(Ship.class))).thenReturn(this.ships.get(0));

		MvcResult result = mvc
				.perform(post("/ships/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.ships.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.ships.get(0)), result.getResponse().getContentAsString());
	}
	
	@Test
	public void createShipTest() throws Exception {
		Mockito.when(shipService.createShip(any(Ship.class))).thenReturn(this.ships.get(0));

		MvcResult result = mvc
				.perform(post("/ships/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(this.ships.get(0))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.ships.get(0)), result.getResponse().getContentAsString());

	}
	
	@Test
	public void getListShipTest() throws Exception {
		Mockito.when(shipService.getAllShip()).thenReturn(this.ships);

		MvcResult result = mvc
				.perform(get("/ships")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.ships), result.getResponse().getContentAsString());

	}

	
	@Test
	public void findByIdTest() throws Exception {
		Mockito.when(shipService.findByShipCode(Mockito.anyString()))
				.thenReturn(this.ships.get(1));

		MvcResult result = mvc
				.perform(post("/ships/id").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.ships.get(1))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.ships.get(1)),
				result.getResponse().getContentAsString());
	}

	@Test
	public void deleteShipTest() throws Exception {
		Mockito.when(shipService.deleteShip(Mockito.anyString()))
				.thenReturn(this.ships.get(1));

		MvcResult result = mvc
				.perform(post("/ships/delete").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.ships.get(1))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.ships.get(1)),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void searchByShipNameTest() throws Exception {
		Mockito.when(shipService.getByShipNameLike("ship", 0, 10)).thenReturn(this.pagesShip);

		MvcResult result = mvc
				.perform(get("/ships/shipName").param("shipName", "ship").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		assertEquals(objectMapper.writeValueAsString(this.pagesShip),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void searchByShipCodeTest() throws Exception {
		Mockito.when(shipService.getByShipCodeLike("ship", 0, 10)).thenReturn(this.pagesShip);

		MvcResult result = mvc
				.perform(get("/ships/shipCode").param("shipCode", "ship").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		assertEquals(objectMapper.writeValueAsString(this.pagesShip),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void searchByCAptainTest() throws Exception {
		Mockito.when(shipService.findByCaptainNameContains("captain", 0, 10)).thenReturn(this.pagesShip);

		MvcResult result = mvc
				.perform(get("/ships/captainName").param("captainName", "captain").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		assertEquals(objectMapper.writeValueAsString(this.pagesShip),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void searchByStatusTest() throws Exception {
		Mockito.when(shipService.getByStatusShipNameLike("status", 0, 10)).thenReturn(this.pagesShip);

		MvcResult result = mvc
				.perform(get("/ships/status").param("status", "status").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		assertEquals(objectMapper.writeValueAsString(this.pagesShip),
				result.getResponse().getContentAsString());
	}
}
