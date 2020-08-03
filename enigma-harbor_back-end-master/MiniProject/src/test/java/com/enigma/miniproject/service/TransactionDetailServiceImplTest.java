package com.enigma.miniproject.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.entity.TransactionDetail;
import com.enigma.miniproject.entity.TransactionDetailStatus;
import com.enigma.miniproject.entity.TransactionHeader;
import com.enigma.miniproject.repository.DockRepository;
import com.enigma.miniproject.repository.HarborRepository;
import com.enigma.miniproject.repository.HarborStatusRepository;
import com.enigma.miniproject.repository.TransactionDetailRepository;
import com.ibm.icu.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionDetailServiceImplTest {
	@Autowired
	private TransactionDetailService transactionDetailService;

	@MockBean
	private TransactionDetailRepository transactionDetailRepository;

	@MockBean
	private HarborRepository harborRepository;

	@MockBean
	private DockRepository dockRepository;

	@MockBean
	private HarborStatusRepository harborStatusRepository;

	Page<TransactionDetail> pagesTransactionDetail = new PageImpl<TransactionDetail>(this.buildListTransactionDetail());

//	@Test
//	public void addDock() {
//		Mockito.when(transactionDetailRepository.save(this.buildListTransactionDetail().get(0))).thenReturn(this.buildListTransactionDetail().get(0));
//		assertEquals(this.buildListTransactionDetail().get(0), transactionDetailService.);
//	}
//	
//	@Test
//	public void updateDock() {
//		Dock Dock = new Dock("Code 2", "Dock 2",new Harbor(), new DockStatus());
//		Mockito.when(dockRepository.save(Dock)).thenReturn(Dock);
//		assertEquals(Dock, dockService.updateDock(Dock));
//	}

	@Test
	public void getListTest() throws Exception {
		Pageable paging = PageRequest.of(0, 5);
		Mockito.when(transactionDetailRepository.findAll(paging)).thenReturn(this.pagesTransactionDetail);
		Assert.assertEquals(this.pagesTransactionDetail, transactionDetailService.doShowByPage(0, 5));
	}

	@Test
	public void getByIdTest() throws Exception {
		TransactionDetail expected = this.buildListTransactionDetail().get(0);
		expected.setId(4);
		Mockito.when(transactionDetailRepository.findTrxDtlById(Mockito.anyInt())).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findByTransactionId(4));
	}

	@Test
	public void countShipTest() throws Exception {
		Integer expected=0;
		Mockito.when(transactionDetailRepository.findSumShipAfter("S-0001")).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.countShipAfter("S-0001"));
	}
	
	@Test
	public void findAttributteShipTest() throws Exception {
		List<Object[]> expected = new ArrayList<Object[]>();
		Mockito.when(transactionDetailRepository.findShip("S-0001")).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findShip("S-0001"));
	}
	
	
	@Test
	public void searchByIdTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findAllByIdBetween(1, 4, paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllByIdBetween(1, 4, 0, 5));
	}

	@Test
	public void searchByThIdTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByTransactionHeaderTransactionHeaderIdContains("header1", paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findByTransactionHeaderId("header1", 0, 5));
	}

	@Test
	public void searchByShipTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByTransactionHeaderShipShipNameContains("ship", paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findByShipName("ship", 0, 5));
	}

	@Test
	public void searchByDockTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByDockDockNameContains("dock", paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findByDockName("dock", 0, 5));
	}

	@Test
	public void searchByCaptainTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByCaptainNameContains("captain", paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findByCaptainName("captain", 0, 5));
	}

	@Test
	public void searchByShipStatusTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByShipStatusShipStatusNameContains("shipStatus", paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findByShipStatusName("shipStatus", 0, 5));
	}

	@Test
	public void searchByStatusTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByTransactionDetailStatusTransactionStatusNameContains("status",
				paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findByStatusName("status", 0, 5));
	}

	@Test
	public void searchByEntryDateTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date DateStart = null;
		Date DateEnd = null;
		DateStart = df.parse("2019-01-01");
		DateEnd = df.parse("2019-11-11");
		Mockito.when(transactionDetailRepository.findAllByEntryDateBetween(DateStart, DateEnd, paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllByEntryDate("2019-01-01", "2019-11-11", 0, 5));
	}

	@Test
	public void searchByExitDateTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date DateStart = null;
		Date DateEnd = null;
		DateStart = df.parse("2019-01-01");
		DateEnd = df.parse("2019-11-11");
		Mockito.when(transactionDetailRepository.findAllByExitDateBetween(DateStart, DateEnd, paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllByExitDate("2019-01-01", "2019-11-11", 0, 5));
	}

	@Test
	public void searchByWeightTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findAllByLoadingUnloadingWeightBetween(100, 200, paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllByWeight(100, 200, 0, 5));
	}

	@Test
	public void searchByHarborTest() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByDockHarborHarborNameContains("har", paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.getByHarborName("har", 0, 5));
	}

	@Test
	public void orderByIdAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByIdAsc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderById("asc", 0, 5));
	}

	@Test
	public void orderByIdDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByIdDesc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderById("desc", 0, 5));
	}

	@Test
	public void orderByThIdAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByTransactionHeaderTransactionHeaderIdAsc(paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByTransactionHeaderId("asc", 0, 5));
	}

	@Test
	public void orderByThIdDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByTransactionHeaderTransactionHeaderIdDesc(paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByTransactionHeaderId("desc", 0, 5));
	}

	@Test
	public void orderByShipAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByTransactionHeaderShipShipNameAsc(paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByShipName("asc", 0, 5));
	}

	@Test
	public void orderByShipDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByTransactionHeaderShipShipNameDesc(paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByShipName("desc", 0, 5));
	}

	@Test
	public void orderByDockAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByDockDockNameAsc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByDockName("asc", 0, 5));
	}

	@Test
	public void orderByDockDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByDockDockNameDesc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByDockName("desc", 0, 5));
	}

	@Test
	public void orderByCaptainAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByCaptainNameAsc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByCaptainName("asc", 0, 5));
	}

	@Test
	public void orderByCaptainDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByCaptainNameDesc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByCaptainName("desc", 0, 5));
	}

	@Test
	public void orderByShipStatusAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByShipStatusShipStatusNameAsc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByShipStatusName("asc", 0, 5));
	}

	@Test
	public void orderByShipStatusDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByShipStatusShipStatusNameDesc(paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByShipStatusName("desc", 0, 5));
	}

	@Test
	public void orderByStatusAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByTransactionDetailStatusTransactionStatusNameAsc(paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByStatusName("asc", 0, 5));
	}

	@Test
	public void orderByStatusDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByTransactionDetailStatusTransactionStatusNameDesc(paging))
				.thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByStatusName("desc", 0, 5));
	}

	@Test
	public void orderByEntryDateAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByEntryDateAsc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByEntryDate("asc", 0, 5));
	}

	@Test
	public void orderByEntryDateDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByEntryDateDesc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByEntryDate("desc", 0, 5));
	}

	@Test
	public void orderByExitDateAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByExitDateAsc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByExitDate("asc", 0, 5));
	}

	@Test
	public void orderByExitDateDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByExitDateDesc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByExitDate("desc", 0, 5));
	}

	@Test
	public void orderByWeightAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByLoadingUnloadingWeightAsc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByWeight("asc", 0, 5));
	}

	@Test
	public void orderByWeightDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByLoadingUnloadingWeightDesc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByWeight("desc", 0, 5));
	}

	@Test
	public void orderByHarborAsc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByDockHarborHarborNameAsc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByHarborName("asc", 0, 5));
	}

	@Test
	public void orderByHarborDesc() throws Exception {
		Page<TransactionDetail> expected = this.pagesTransactionDetail;
		Pageable paging = PageRequest.of(0, 5);

		Mockito.when(transactionDetailRepository.findByOrderByDockHarborHarborNameDesc(paging)).thenReturn(expected);
		Assert.assertEquals(expected, transactionDetailService.findAllOrderByHarborName("desc", 0, 5));
	}

	private List<TransactionDetail> buildListTransactionDetail() {
		List<TransactionDetail> data = new ArrayList<TransactionDetail>();
		for (int i = 0; i < 5; i++) {
			TransactionDetail transactionDetail = new TransactionDetail();
			int seq = i + 1;
			transactionDetail.setId(seq);
			transactionDetail.setCaptainName("captain" + seq);
			transactionDetail.setDock(new Dock());
			transactionDetail.setEntryDate(new Date());
			transactionDetail.setExitDate(new Date());
			transactionDetail.setLoadingUnloadingWeight(seq * 100);
			transactionDetail.setShipStatus(new ShipStatus());
			transactionDetail.setTransactionDetailStatus(new TransactionDetailStatus());
			transactionDetail.setTransactionHeader(new TransactionHeader());
			data.add(transactionDetail);
		}
		return data;
	}

}
