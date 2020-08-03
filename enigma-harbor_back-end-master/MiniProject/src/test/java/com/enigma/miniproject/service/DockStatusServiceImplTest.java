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

import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.repository.DockStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DockStatusServiceImplTest {

		@Autowired
		private DockStatusService dockStatusService;
		

		@MockBean
		private DockStatusRepository dockStatusRepository;

		@Test
		public void addDock() {
			DockStatus dockStatus= new DockStatus(1, "status");
			Mockito.when(dockStatusRepository.save(dockStatus)).thenReturn(dockStatus);
			assertEquals(dockStatus, dockStatusService.createDockStatus(dockStatus));
		}
		
		@Test
		public void updateDock() {
			DockStatus dockStatus= new DockStatus(1, "status");
			Mockito.when(dockStatusRepository.save(dockStatus)).thenReturn(dockStatus);
			assertEquals(dockStatus, dockStatusService.updateDockStatus(dockStatus));
		}
		
		@Test
		public void getListTest() throws Exception {
			Mockito.when(dockStatusRepository.findAll()).thenReturn(this.buildListDockStatus());
			Assert.assertEquals(this.buildListDockStatus(), dockStatusService.getAllDockStatus());
		}

		private List<DockStatus> buildListDockStatus() {
			List<DockStatus> data = new ArrayList<DockStatus>();
			for (int i = 0; i < 5; i++) {
				DockStatus dockStatus= new DockStatus();
				int seq = i + 1;
				dockStatus.setDockStatusId(seq);
				dockStatus.setDockStatusName("name"+seq);
				data.add(dockStatus);
			}
			return data;
		}

	
}
