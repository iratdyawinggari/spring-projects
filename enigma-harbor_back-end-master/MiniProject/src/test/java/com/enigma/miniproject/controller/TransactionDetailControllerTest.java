package com.enigma.miniproject.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
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
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.entity.TransactionDetail;
import com.enigma.miniproject.entity.TransactionDetailStatus;
import com.enigma.miniproject.entity.TransactionHeader;
import com.enigma.miniproject.service.TransactionDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(DockController.class)
@ContextConfiguration(classes = { TransactionDetailController.class, SecurityConfig.class })
public class TransactionDetailControllerTest {
	@Autowired
	WebApplicationContext context;

	private MockMvc mvc;

	@MockBean
	private TransactionDetailService transactionDetailService;

	private ObjectMapper objectMapper = new ObjectMapper();
	private List<TransactionDetail> transactionDetails = new ArrayList<>();

	@Before
	public void setUp() {
		TransactionDetailStatus status1 = new TransactionDetailStatus();
		status1.setTransactionStatusId(1);
		TransactionDetailStatus status2 = new TransactionDetailStatus();
		status2.setTransactionStatusId(2);
		TransactionDetailStatus status3 = new TransactionDetailStatus();
		status3.setTransactionStatusId(3);

		this.transactionDetails.add(new TransactionDetail(1, new TransactionHeader(), new Date(), new Date(),
				new Dock(), "captain1", new ShipStatus(), 100, new TransactionDetailStatus()));
		this.transactionDetails.add(new TransactionDetail(2, new TransactionHeader(), new Date(), new Date(),
				new Dock(), "captain2", new ShipStatus(), 200, new TransactionDetailStatus()));
		this.transactionDetails.add(new TransactionDetail(3, new TransactionHeader(), new Date(), new Date(),
				new Dock(), "captain3", new ShipStatus(), 200, new TransactionDetailStatus()));

		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	Page<TransactionDetail> pagesTransactionDetail = new PageImpl<TransactionDetail>(transactionDetails);

	@Test
	public void getAllDockTest() throws Exception {
		Mockito.when(transactionDetailService.doShowByPage(0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/page").param("page", "0").param("pageSize", "10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());

	}

	@Test
	public void getListTrxTest() throws Exception {
		Mockito.when(transactionDetailService.getAllTransactions()).thenReturn(this.transactionDetails);

		MvcResult result = mvc
				.perform(get("/transactionDetails")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.transactionDetails), result.getResponse().getContentAsString());

	}
	
	@Test
	public void searchByIdTest() throws Exception {
		Mockito.when(transactionDetailService.findAllByIdBetween(1, 5, 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/trxId").param("idStart", "1").param("idEnd", "5").param("page", "0")
						.param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());

	}
	
	@Test
	public void sortByIdTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderById("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/trxId/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void searchByThIdTest() throws Exception {
		Mockito.when(transactionDetailService.findByTransactionHeaderId("abc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/thId").param("thId", "abc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	@Test
	public void sortByThIdTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByTransactionHeaderId("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/thId/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void searchByShipTest() throws Exception {
		Mockito.when(transactionDetailService.findByShipName("ship", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/shipName").param("shipName", "ship").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();
		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	@Test
	public void sortByShipTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByShipName("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/shipName/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void searchByDockTest() throws Exception {
		Mockito.when(transactionDetailService.findByDockName("dock", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/dockName").param("dockName", "dock").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void sortByDockTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByDockName("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/dockName/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}


	@Test
	public void searchByCaptainTest() throws Exception {
		Mockito.when(transactionDetailService.findByCaptainName("captain", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/captainName").param("captainName", "captain").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void sortByCaptainTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByCaptainName("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/captainName/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void searchByShipStatusTest() throws Exception {
		Mockito.when(transactionDetailService.findByShipStatusName("shipStatus", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/shipStatusName").param("shipStatusName", "shipStatus").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void sortByShipStatusTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByShipStatusName("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/shipStatusName/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void searchByStatusTest() throws Exception {
		Mockito.when(transactionDetailService.findByStatusName("status", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/transactionStatusName").param("transactionStatusName", "status").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void sortByStatusTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByStatusName("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/transactionStatusName/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void searchByEntryDateTest() throws Exception {
		Mockito.when(transactionDetailService.findAllByEntryDate("2019-01-01", "2019-11-11", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/entryDate").param("entryDateStart", "2019-01-01").param("entryDateEnd", "2019-11-11").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	@Test
	public void sortByEntryDateTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByEntryDate("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/entryDate/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void searchByExitDateTest() throws Exception {
		Mockito.when(transactionDetailService.findAllByExitDate("2019-01-01", "2019-11-11", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/exitDate").param("exitDateStart", "2019-01-01").param("exitDateEnd", "2019-11-11").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	@Test
	public void sortByExitDateTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByExitDate("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/exitDate/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void searchByWeightTest() throws Exception {
		Mockito.when(transactionDetailService.findAllByWeight(100, 200, 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/weight").param("weightStart", "100").param("weightEnd", "200").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void sortByWeightTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByWeight("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/weight/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void searchByHarborTest() throws Exception {
		Mockito.when(transactionDetailService.getByHarborName("harbor", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/harborName").param("harborName", "harbor").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}
	
	@Test
	public void sortByHarborTest() throws Exception {
		Mockito.when(transactionDetailService.findAllOrderByHarborName("asc", 0, 10)).thenReturn(this.pagesTransactionDetail);

		MvcResult result = mvc
				.perform(get("/transactionDetails/harborName/sort").param("direction", "asc").param("page", "0").param("pageSize", "10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.pagesTransactionDetail),
				result.getResponse().getContentAsString());
	}

	
	@Test
	public void findByIdTest() throws Exception {
		Mockito.when(transactionDetailService.findByTransactionId(Mockito.anyInt()))
				.thenReturn(this.transactionDetails.get(1));

		MvcResult result = mvc
				.perform(post("/transactionDetails/id").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.transactionDetails.get(1))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.transactionDetails.get(1)),
				result.getResponse().getContentAsString());
	}
	
	

	@Test
	public void doneTrxTest() throws Exception {
		Mockito.when(transactionDetailService.doneTransaction(Mockito.anyInt()))
				.thenReturn(this.transactionDetails.get(2));

		MvcResult result = mvc
				.perform(post("/transactionDetails/done").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(this.transactionDetails.get(2))))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(objectMapper.writeValueAsString(this.transactionDetails.get(2)),
				result.getResponse().getContentAsString());
	}

//	
//	@Test
//	public void createDockTest() throws Exception {
//		Mockito.when(TransactionDetailService.createTransaction(any(TransactionDetail.class))).thenReturn(this.TransactionDetails.get(0));
//
//		MvcResult result = mvc
//				.perform(post("/transactionDetails/add")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsBytes(this.TransactionDetails.get(0))))
//				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andReturn();
//
//		assertEquals(objectMapper.writeValueAsString(this.TransactionDetails.get(0)), result.getResponse().getContentAsString());
//
//	}

}
