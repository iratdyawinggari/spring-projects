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

import com.enigma.miniproject.entity.TransactionDetailStatus;
import com.enigma.miniproject.repository.TransactionDetailStatusRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionDetailStatusServiceImplTest {

		@Autowired
		private TransactionDetailStatusService transactionDetailStatusService;
		

		@MockBean
		private TransactionDetailStatusRepository transactionDetailStatusRepository;

		@Test
		public void addShip() {
			TransactionDetailStatus transactionDetailStatus= new TransactionDetailStatus(1, "status");
			Mockito.when(transactionDetailStatusRepository.save(transactionDetailStatus)).thenReturn(transactionDetailStatus);
			assertEquals(transactionDetailStatus, transactionDetailStatusService.createTransactionDetailStatus(transactionDetailStatus));
		}
		
		@Test
		public void updateShip() {
			TransactionDetailStatus transactionDetailStatus= new TransactionDetailStatus(1, "status");
			Mockito.when(transactionDetailStatusRepository.save(transactionDetailStatus)).thenReturn(transactionDetailStatus);
			assertEquals(transactionDetailStatus, transactionDetailStatusService.updateTransactionDetailStatus(transactionDetailStatus));
		}
		
		@Test
		public void getListTest() throws Exception {
			Mockito.when(transactionDetailStatusRepository.findAll()).thenReturn(this.buildListTransactionDetailStatus());
			Assert.assertEquals(this.buildListTransactionDetailStatus(), transactionDetailStatusService.getAllTransactionDetailStatus());
		}

		private List<TransactionDetailStatus> buildListTransactionDetailStatus() {
			List<TransactionDetailStatus> data = new ArrayList<TransactionDetailStatus>();
			for (int i = 0; i < 5; i++) {
				TransactionDetailStatus transactionDetailStatus= new TransactionDetailStatus();
				int seq = i + 1;
				transactionDetailStatus.setTransactionStatusId(seq);
				transactionDetailStatus.setTransactionStatusName("name"+seq);
				data.add(transactionDetailStatus);
			}
			return data;
		}

	
}
