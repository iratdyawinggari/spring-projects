package com.enigma.finalproject.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.finalproject.dto.TransactionDTO;
import com.enigma.finalproject.dto.TransactionDTOService;
import com.enigma.finalproject.entity.Transaction;
import com.enigma.finalproject.entity.Transaction;
import com.enigma.finalproject.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
//
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	TransactionDTOService transactionDTOService;
	
	@PostMapping
	public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionDTO transactionDTO) {
		Transaction result = transactionDTOService.createTransaction(transactionDTO);
		Transaction dfdvdfv = transactionService.addTransaction(result);
		return new ResponseEntity<>(dfdvdfv, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Transaction>> getTransactions() {
		List<Transaction> result = transactionService.getAllTransactions();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/userId")
	public ResponseEntity<List<Transaction>> getTransactionsByUserId(@RequestParam("userId") Integer userId) {
		List<Transaction> result = transactionService.getAllTransactionsByUserId(userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	@PostMapping("/update")
	public ResponseEntity<Transaction> editTransaction(@RequestBody TransactionDTO transactionDTO) {
		Transaction result = transactionDTOService.createTransaction(transactionDTO);
		Transaction dfdvdfv = transactionService.editTransaction(result);
		return new ResponseEntity<>(dfdvdfv, HttpStatus.OK);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Transaction>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		Page<Transaction> result = transactionService.showByPage(page, pageSize);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<Transaction>> showByPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("orderBy") String orderBy,
			@RequestParam("sort") String direction) {
		Page<Transaction> result = transactionService.showByPageSort(page, pageSize, orderBy, direction);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
