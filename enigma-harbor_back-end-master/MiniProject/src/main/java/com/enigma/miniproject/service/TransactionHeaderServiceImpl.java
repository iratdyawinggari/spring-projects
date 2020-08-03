package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.TransactionHeader;
import com.enigma.miniproject.repository.TransactionHeaderRepository;

@Service
public class TransactionHeaderServiceImpl implements TransactionHeaderService {

	@Autowired
	TransactionHeaderRepository transactionHeaderRepository;
	
	@Override
	public TransactionHeader createTransactionHeader(TransactionHeader transactionHeader) {
		return transactionHeaderRepository.save(transactionHeader);
	}

	@Override
	public List<TransactionHeader> getAllTransactionHeaders() {
		return transactionHeaderRepository.findAll();
	}

	@Override
	public TransactionHeader findByTransactionHeaderId(String transactionHeaderId) {
		return transactionHeaderRepository.findByTransactionHeaderId(transactionHeaderId);
	}
}
