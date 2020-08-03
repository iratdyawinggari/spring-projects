package com.enigma.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.dto.TransactionDto;
import com.enigma.miniproject.dto.TrxDtoService;
@RestController
public class TransactionController {

	@Autowired
	TrxDtoService trxDtoService;

	@PostMapping("transactions/add")
	public ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto transactionDto) {
		TransactionDto newTransactionDto = trxDtoService.createNewTransaction(transactionDto);
		return new ResponseEntity<>(newTransactionDto, HttpStatus.OK);
	}
}
