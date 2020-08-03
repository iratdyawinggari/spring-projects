package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.TransactionDetailStatus;
import com.enigma.miniproject.service.TransactionDetailStatusService;

@RestController
public class TransactionDetailStatusController {
	@Autowired
	TransactionDetailStatusService transactionDetailStatusService;
	
	@PostMapping("transactionDetailStatus/id")
	public ResponseEntity<TransactionDetailStatus> getByTransactionDetailStatusId(@RequestBody TransactionDetailStatus transactionDetailStatus) {
		TransactionDetailStatus searchTransactionDetailStatus = transactionDetailStatusService.findByTransactionDetailStatusId(transactionDetailStatus.getTransactionStatusId());
		return new ResponseEntity<>(searchTransactionDetailStatus, HttpStatus.OK);
	}

	@GetMapping("transactionDetailStatus")
	public ResponseEntity<List<TransactionDetailStatus>> getListTransactionDetailStatus() {
		List<TransactionDetailStatus> transactionDetailStatusList = transactionDetailStatusService.getAllTransactionDetailStatus();
		return new ResponseEntity<>(transactionDetailStatusList, HttpStatus.OK);
	}

	@PostMapping("transactionDetailStatus/add")
	public ResponseEntity<TransactionDetailStatus> addTransactionHeader(@RequestBody TransactionDetailStatus transactionDetailStatus) {
		TransactionDetailStatus newTransactionDetailStatus = transactionDetailStatusService.createTransactionDetailStatus(transactionDetailStatus);
		return new ResponseEntity<>(newTransactionDetailStatus, HttpStatus.OK);
	}
}
