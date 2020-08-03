package com.enigma.miniproject.service;

import java.util.ArrayList;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.enigma.test.entity.Batch;
import com.enigma.test.repository.BatchRepository;


@RunWith(SpringRunner.class)
public class BatchServiceImplTest {
	public BatchServiceImplTest() {
	}

	@TestConfiguration
	static class BatchServiceImplTestContexConfiguration {
		@Bean
		public BatchService batchService() {
			return new BatchServiceImpl();
		}
	}

	@Autowired
	private BatchService service;

	@MockBean
	private BatchRepository repository;

	@Test
	public void getListTest() throws Exception {
		Mockito.when(repository.findAll()).thenReturn(this.buildListBatch());
		Assert.assertEquals(this.buildListBatch(), service.getList());
	}

	@Test
	public void getByIdTest() throws Exception {
		Batch expected = this.buildListBatch().get(0);
		expected.setId(4);
		Mockito.when(repository.getById(Mockito.anyInt())).thenReturn(expected);
		Assert.assertEquals(expected, service.getById(4));
	}

	private List<Batch> buildListBatch() {
		List<Batch> data = new ArrayList<Batch>();
		for (int i = 0; i < 5; i++) {
			Batch batch = new Batch();
			int seq = i + 1;
			batch.setId(seq);
			batch.setSequence(seq);
			batch.setName("Batch" + seq);
			batch.setDescription(batch.getName());
			data.add(batch);
		}
		return data;
	}
}
