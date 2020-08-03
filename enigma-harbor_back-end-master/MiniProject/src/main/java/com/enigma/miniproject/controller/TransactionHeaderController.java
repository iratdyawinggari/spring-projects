package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.TransactionHeader;
import com.enigma.miniproject.service.TransactionHeaderService;

@RestController
public class TransactionHeaderController {
	@Autowired
	TransactionHeaderService transactionHeaderService; 
	
//	@PostMapping("transactionHeaders/id")
//	public ResponseEntity<TransactionHeader> getByTransactionHeaderId(@RequestBody TransactionHeader transactionHeader) {
//		TransactionHeader searchTransactionHeader = transactionHeaderService.findByTransactionHeaderId(transactionHeader.getTransactionHeaderId());
//		return new ResponseEntity<>(searchTransactionHeader, HttpStatus.OK);
//	}
//
//	@GetMapping("transactionHeaders")
//	public ResponseEntity<List<TransactionHeader>> getListTransactionHeader() {
//		List<TransactionHeader> transactionHeaderList = transactionHeaderService.getAllTransactionHeaders();
//		return new ResponseEntity<>(transactionHeaderList, HttpStatus.OK);
//	}
//
//	@PostMapping("transactionHeaders/add")
//	public ResponseEntity<TransactionHeader> addTransactionHeader(@RequestBody TransactionHeader transactionHeader) {
//		TransactionHeader newTransactionHeader = transactionHeaderService.createTransactionHeader(transactionHeader);
//		return new ResponseEntity<>(newTransactionHeader, HttpStatus.OK);
//	}
}
