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

import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.repository.HarborStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HarborStatusServiceImplTest {

		@Autowired
		private HarborStatusService HarborStatusService;
		

		@MockBean
		private HarborStatusRepository HarborStatusRepository;

		@Test
		public void addHarbor() {
			HarborStatus HarborStatus= new HarborStatus(1, "status");
			Mockito.when(HarborStatusRepository.save(HarborStatus)).thenReturn(HarborStatus);
			assertEquals(HarborStatus, HarborStatusService.createHarborStatus(HarborStatus));
		}
		
		@Test
		public void updateHarbor() {
			HarborStatus HarborStatus= new HarborStatus(1, "status");
			Mockito.when(HarborStatusRepository.save(HarborStatus)).thenReturn(HarborStatus);
			assertEquals(HarborStatus, HarborStatusService.updateHarborStatus(HarborStatus));
		}
		
		@Test
		public void getListTest() throws Exception {
			Mockito.when(HarborStatusRepository.findAll()).thenReturn(this.buildListHarborStatus());
			Assert.assertEquals(this.buildListHarborStatus(), HarborStatusService.getAllHarborStatus());
		}

		private List<HarborStatus> buildListHarborStatus() {
			List<HarborStatus> data = new ArrayList<HarborStatus>();
			for (int i = 0; i < 5; i++) {
				HarborStatus HarborStatus= new HarborStatus();
				int seq = i + 1;
				HarborStatus.setHarborStatusId(seq);
				HarborStatus.setHarborStatusName("name"+seq);
				data.add(HarborStatus);
			}
			return data;
		}

	
}
