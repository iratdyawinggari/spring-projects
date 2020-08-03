package com.enigma.miniproject.service;

import java.util.List;

import com.enigma.miniproject.entity.TransactionHeader;

public interface TransactionHeaderService {
	TransactionHeader createTransactionHeader(TransactionHeader transactionHeader);
    List<TransactionHeader> getAllTransactionHeaders();
    TransactionHeader findByTransactionHeaderId (String transactionHeaderId);
}
