package com.enigma.miniproject.service;


import static org.junit.Assert.assertEquals;

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
import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.entity.TransactionDetail;
import com.enigma.miniproject.entity.TransactionDetailStatus;
import com.enigma.miniproject.entity.TransactionHeader;
import com.enigma.miniproject.repository.ShipRepository;
import com.enigma.miniproject.repository.ShipStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipServiceImplTest {

		@Autowired
		private ShipService shipService;
		
		@MockBean
		private ShipRepository shipRepository;

		@MockBean
		private ShipStatusRepository shipStatusRepository;
		
		Page<Ship> pagesShip = new PageImpl<Ship>(this.buildListShip());

		@Test
		public void addShip() {
			Ship ship = new Ship("Code 1", "Ship 1","captain1", new ShipStatus());
			Mockito.when(shipRepository.save(ship)).thenReturn(ship);
			assertEquals(ship, shipService.createShip(ship));
		}
		
		@Test
		public void updateShip() {
			Ship ship = new Ship("Code 1", "Ship 1","captain1", new ShipStatus());
			Mockito.when(shipRepository.save(ship)).thenReturn(ship);
			assertEquals(ship, shipService.updateShip(ship));
		}

		@Test
		public void getListTest() throws Exception {
			Mockito.when(shipRepository.findAll()).thenReturn(this.buildListShip());
			Assert.assertEquals(this.buildListShip(), shipService.getAllShip());
		}
		
		@Test
		public void getByIdTest() throws Exception {
			Ship expected = this.buildListShip().get(0);
			expected.setShipCode("ship1");;
			Mockito.when(shipRepository.findShipByShipCode(Mockito.anyString())).thenReturn(expected);
			Assert.assertEquals(expected, shipService.findByShipCode("ship1"));
		}
		
		@Test
		public void getListByPageTest() throws Exception {
			Pageable paging = PageRequest.of(0, 5);
			Mockito.when(shipRepository.findAll(paging)).thenReturn(this.pagesShip);
			Assert.assertEquals(this.pagesShip, shipService.doShowByPage(0, 5));
		}
		
//		@Test
//		public void deleteByIdTest() throws Exception {
//			Ship expected = this.buildListShip().get(0);
//			Mockito.when(shipRepository.findShipByShipCode("ship1")).thenReturn(expected);
//			Assert.assertEquals(expected, shipService.deleteShip("ship1"));
//		}
		
		@Test
		public void searchByShipCodeTest() throws Exception {
			Page<Ship> expected = this.pagesShip;
			Pageable paging = PageRequest.of(0, 5);

			Mockito.when(shipRepository.findByShipCodeContains("ship", paging))
					.thenReturn(expected);
			Assert.assertEquals(expected, shipService.getByShipCodeLike("ship", 0, 5));
		}
		
		@Test
		public void searchByShipNameTest() throws Exception {
			Page<Ship> expected = this.pagesShip;
			Pageable paging = PageRequest.of(0, 5);

			Mockito.when(shipRepository.findByShipNameContains("ship", paging))
					.thenReturn(expected);
			Assert.assertEquals(expected, shipService.getByShipNameLike("ship", 0, 5));
		}
		
		@Test
		public void searchByCaptainNameTest() throws Exception {
			Page<Ship> expected = this.pagesShip;
			Pageable paging = PageRequest.of(0, 5);

			Mockito.when(shipRepository.findByCaptainNameContains("captain", paging))
					.thenReturn(expected);
			Assert.assertEquals(expected, shipService.findByCaptainNameContains("captain", 0, 5));
		}

		@Test
		public void searchByStatusNameTest() throws Exception {
			Page<Ship> expected = this.pagesShip;
			Pageable paging = PageRequest.of(0, 5);

			Mockito.when(shipRepository.findByShipStatusShipStatusNameContains("status", paging))
					.thenReturn(expected);
			Assert.assertEquals(expected, shipService.getByStatusShipNameLike("status", 0, 5));
		}
		
		
		private List<Ship> buildListShip() {
			List<Ship> data = new ArrayList<Ship>();
			for (int i = 0; i < 5; i++) {
				Ship ship = new Ship();
				int seq = i + 1;
				ship.setShipCode("code"+seq);
				ship.setShipName("ship"+seq);
				ship.setCaptainName("captain"+seq);
				ship.setShipStatus(new ShipStatus());
				data.add(ship);
			}
			return data;
		}

	
}
