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

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.entity.Harbor;
import com.enigma.miniproject.repository.DockRepository;
import com.enigma.miniproject.repository.DockStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DockServiceImplTest {

		@Autowired
		private DockService dockService;
		
		@MockBean
		private DockRepository dockRepository;

		@MockBean
		private DockStatusRepository dockStatusRepository;

		@Test
		public void addDock() {
			Dock Dock = new Dock("Code 1", "Dock 1",new Harbor(), new DockStatus());
			Mockito.when(dockRepository.save(Dock)).thenReturn(Dock);
			assertEquals(Dock, dockService.createDock(Dock));
		}
		
		@Test
		public void updateDock() {
			Dock Dock = new Dock("Code 2", "Dock 2",new Harbor(), new DockStatus());
			Mockito.when(dockRepository.save(Dock)).thenReturn(Dock);
			assertEquals(Dock, dockService.updateDock(Dock));
		}
		
		@Test
		public void getListTest() throws Exception {
			Mockito.when(dockRepository.findAll()).thenReturn(this.buildListDock());
			Assert.assertEquals(this.buildListDock(), dockService.getAllDock());
		}

		private List<Dock> buildListDock() {
			List<Dock> data = new ArrayList<Dock>();
			for (int i = 0; i < 5; i++) {
				Dock dock= new Dock();
				int seq = i + 1;
				dock.setDockCode("code"+seq);
				dock.setDockName("dock"+seq);
				dock.setDockStatus(new DockStatus());
				dock.setharbor(new Harbor());
				
				data.add(dock);
			}
			return data;
		}

	
}
