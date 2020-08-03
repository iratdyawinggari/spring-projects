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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.enigma.miniproject.entity.Harbor;
import com.enigma.miniproject.entity.HarborStatus;
import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.repository.HarborRepository;
import com.enigma.miniproject.repository.HarborStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HarborServiceImplTest {

		@Autowired
		private HarborService harborService;
		
		@MockBean
		private HarborRepository harborRepository;

		@MockBean
		private HarborStatusRepository harborStatusRepository;
		
		Page<Harbor> pagesHarbor = new PageImpl<Harbor>(this.buildListHarbor());

		@Test
		public void addHarbor() {
			
			Harbor harbor = new Harbor("Code 1", "harbor 1", 100, new HarborStatus());
			Mockito.when(harborRepository.save(harbor)).thenReturn(harbor);
			assertEquals(harbor, harborService.createharbor(harbor));
		}
		
		@Test
		public void updateHarbor() {
			Harbor harbor = new Harbor("Code 1", "harbor 1", 100, new HarborStatus());
			Mockito.when(harborRepository.save(harbor)).thenReturn(harbor);
			assertEquals(harbor, harborService.updateharbor(harbor));
		}
		
		@Test
		public void getListTest() throws Exception {
			Mockito.when(harborRepository.findAll()).thenReturn(this.buildListHarbor());
			Assert.assertEquals(this.buildListHarbor(), harborService.getAllharbor());
		}
		
		@Test
		public void getListWithoutSuspendTest() throws Exception {
			Mockito.when(harborRepository.findWithoutSuspend()).thenReturn(this.buildListHarbor());
			Assert.assertEquals(this.buildListHarbor(), harborService.findWithoutSuspend());
		}
		
		@Test
		public void findCapacityHarborTest() throws Exception {
			Integer expected=0;
			Mockito.when(harborRepository.findCapacityHarbor("harbor")).thenReturn(expected);
			Assert.assertEquals(expected, harborService.findCapacityHarbor("harbor"));
		}
		
		
		@Test
		public void getByIdTest() throws Exception {
			Harbor expected = this.buildListHarbor().get(0);
			expected.setHarborCode("harbor");;
			Mockito.when(harborRepository.findharborByharborCode(Mockito.anyString())).thenReturn(expected);
			Assert.assertEquals(expected, harborService.findByharborCode("harbor"));
		}
		
		@Test
		public void getListByPageTest() throws Exception {
			Pageable paging = PageRequest.of(0, 5);
			Mockito.when(harborRepository.findAll(paging)).thenReturn(this.pagesHarbor);
			Assert.assertEquals(this.pagesHarbor, harborService.doShowByPage(0, 5));
		}


		private List<Harbor> buildListHarbor() {
			List<Harbor> data = new ArrayList<Harbor>();
			for (int i = 0; i < 5; i++) {
				Harbor harbor= new Harbor();
				int seq = i + 1;
				harbor.setHarborCode("code"+seq);
				harbor.setHarborName("harbor"+seq);
				harbor.setHarborCapacity(seq*10);
				harbor.setHarborStatus(new HarborStatus());
				data.add(harbor);
			}
			return data;
		}
		

	
}
