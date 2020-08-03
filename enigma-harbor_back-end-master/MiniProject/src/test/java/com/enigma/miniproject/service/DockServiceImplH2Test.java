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

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.DockStatus;


@RunWith(SpringRunner.class)
@DataJpaTest
public class DockServiceImplH2Test {
	public DockServiceImplH2Test() {
	}
	
	@TestConfiguration
	static class DockServiceImplTestContexConfiguration {
		@Bean
		public DockService dockService() {
			return new DockServiceImpl();
		}
		
		@Bean
		public DockStatusService dockStatusService() {
			return new DockStatusServiceImpl();
		}
	}
	
	@Autowired
	private DockService dockService;
	
	@Autowired
	private DockStatusService dockStatusService;

	
	@Autowired
	private TestEntityManager entityManager;
	
//	@Test
//	public void saveTest() throws Exception {
//		for (Dock dock : buildListDock()) {
//			dockService.createDock(dock);
//		}
//		Assert.assertEquals(buildListDock().get(0).getDockCode(), dockService.findByDockCode(buildListDock().get(0).getDockCode()).getDockCode());
//		return;
//	}
//	
//	@Test
//	public void getListTest() throws Exception {
//		List<Dock> expected = new ArrayList<Dock>();
//		
//		for (Dock dock : buildListDock()) {
//			expected.add(entityManager.merge(dock));
//		}
//		entityManager.flush();
//		List<Dock> actual = dockService.getAllDock();
//		Assert.assertEquals(expected, actual);
//	}
	
	@Test
	public void getByIdTest() throws Exception {
		Dock savingdock = new Dock();
		savingdock.setDockCode("Test");
		DockStatus dockStatus = new DockStatus();
		dockStatus.setDockStatusId(1);
		dockStatus.setDockStatusName("status"+1);
		dockStatusService.createDockStatus(dockStatus);		
		savingdock.setDockStatus(dockStatusService.findByDockStatusId(1));
		
		Dock expected = entityManager.persist(savingdock);
		entityManager.flush();
		
		Dock actual = dockService.findByDockCode(expected.getDockCode());
		System.out.println(expected);
		System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}
	
//	private List<Dock> buildListDock() {
//		List<Dock> data = new ArrayList<Dock>();
//		for (int i = 0; i < 5; i++) {
//			Dock dock = new Dock();
//			int seq = i+1;
//			dock.setDockCode("code"+seq );
//			dock.setDockName("name"+seq);
//			
//			DockStatus dockStatus = new DockStatus();
//			dockStatus.setDockStatusId(seq);
//			dockStatus.setDockStatusName("status"+seq);
//			dock.setDockStatus(dockStatus);
//			data.add(dock);
//		}
//		return data;
//	}
}