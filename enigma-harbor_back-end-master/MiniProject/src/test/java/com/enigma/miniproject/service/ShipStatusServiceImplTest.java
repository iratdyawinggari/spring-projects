package com.enigma.miniproject.service;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.repository.ShipStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipStatusServiceImplTest {

		@Autowired
		private ShipStatusService ShipStatusService;
		

		@MockBean
		private ShipStatusRepository ShipStatusRepository;

		@Test
		public void addShip() {
			ShipStatus ShipStatus= new ShipStatus(1, "status");
			Mockito.when(ShipStatusRepository.save(ShipStatus)).thenReturn(ShipStatus);
			assertEquals(ShipStatus, ShipStatusService.createShipStatus(ShipStatus));
		}
		
		@Test
		public void updateShip() {
			ShipStatus ShipStatus= new ShipStatus(1, "status");
			Mockito.when(ShipStatusRepository.save(ShipStatus)).thenReturn(ShipStatus);
			assertEquals(ShipStatus, ShipStatusService.updateShipStatus(ShipStatus));
		}
		
		@Test
		public void getListTest() throws Exception {
			Mockito.when(ShipStatusRepository.findAll()).thenReturn(this.buildListShipStatus());
			Assert.assertEquals(this.buildListShipStatus(), ShipStatusService.getAllShipStatus());
		}

		private List<ShipStatus> buildListShipStatus() {
			List<ShipStatus> data = new ArrayList<ShipStatus>();
			for (int i = 0; i < 5; i++) {
				ShipStatus ShipStatus= new ShipStatus();
				int seq = i + 1;
				ShipStatus.setShipStatusId(seq);
				ShipStatus.setShipStatusName("name"+seq);
				data.add(ShipStatus);
			}
			return data;
		}

	
}
