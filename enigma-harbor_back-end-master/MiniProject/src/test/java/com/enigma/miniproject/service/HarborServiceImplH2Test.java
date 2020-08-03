package com.enigma.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.enigma.miniproject.entity.Harbor;
import com.enigma.miniproject.entity.HarborStatus;


@RunWith(SpringRunner.class)
@DataJpaTest
public class HarborServiceImplH2Test {
	public HarborServiceImplH2Test() {
	}
	
	@TestConfiguration
	static class HarborServiceImplTestContexConfiguration {
		@Bean
		public HarborService harborService() {
			return new HarborServiceImpl();
		}
		
		@Bean
		public HarborStatusService harborStatusService() {
			return new HarborStatusServiceImpl();
		}
	}
	
	@Autowired
	private HarborService harborService;
	
	@Autowired
	private HarborStatusService harborStatusService;

	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void saveTest() throws Exception {
		for (Harbor harbor : buildListHarbor()) {
			harborService.createharbor(harbor);
		}
		Assert.assertEquals(buildListHarbor().get(0).getHarborCode(), harborService.findByharborCode(buildListHarbor().get(0).getHarborCode()).getHarborCode());
		return;
	}
	
	@Test
	public void getListTest() throws Exception {
		List<Harbor> expected = new ArrayList<Harbor>();
		
		for (Harbor harbor : buildListHarbor()) {
			expected.add(entityManager.merge(harbor));
		}
		entityManager.flush();
		List<Harbor> actual = harborService.getAllharbor();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void getByIdTest() throws Exception {
		Harbor savingharbor = new Harbor();
		savingharbor.setHarborCode("Test");
		HarborStatus harborStatus = new HarborStatus();
		harborStatus.setHarborStatusId(1);
		harborStatus.setHarborStatusName("status"+1);
		harborStatusService.createHarborStatus(harborStatus);		
		savingharbor.setHarborStatus(harborStatusService.findByharborStatusId(1));
		
		Harbor expected = entityManager.persist(savingharbor);
		entityManager.flush();
		
		Harbor actual = harborService.findByharborCode(expected.getHarborCode());
		System.out.println(expected);
		System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}
	
	private List<Harbor> buildListHarbor() {
		List<Harbor> data = new ArrayList<Harbor>();
		for (int i = 0; i < 5; i++) {
			int seq = i+1;
			Harbor harbor = new Harbor();
			harbor.setHarborCode("code"+seq);
			harbor.setHarborName("name"+seq);
			harbor.setHarborCapacity(seq);
			data.add(harbor);
		}
		return data;
	}
}