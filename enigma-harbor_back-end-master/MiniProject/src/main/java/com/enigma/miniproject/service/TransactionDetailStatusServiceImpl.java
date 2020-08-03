package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.TransactionDetailStatus;
import com.enigma.miniproject.repository.TransactionDetailStatusRepository;

@Service
public class TransactionDetailStatusServiceImpl implements TransactionDetailStatusService {
	@Autowired
	TransactionDetailStatusRepository transactionDetailStatusRepository;
	
	@Override
	public TransactionDetailStatus createTransactionDetailStatus(TransactionDetailStatus transactionDetailStatus) {
		return transactionDetailStatusRepository.save(transactionDetailStatus);
	}

	@Override
	public TransactionDetailStatus updateTransactionDetailStatus(TransactionDetailStatus transactionDetailStatus) {
		return transactionDetailStatusRepository.save(transactionDetailStatus);
	}

	@Override
	public List<TransactionDetailStatus> getAllTransactionDetailStatus() {
		return transactionDetailStatusRepository.findAll();
	}

	@Override
	public TransactionDetailStatus findByTransactionDetailStatusId(Integer id) {
		return transactionDetailStatusRepository.findBytransactionStatusId(id);
	}

}
