package com.enigma.miniproject.service;

import java.util.List;

import com.enigma.miniproject.entity.TransactionDetailStatus;

public interface TransactionDetailStatusService {
	TransactionDetailStatus createTransactionDetailStatus(TransactionDetailStatus transactionDetailStatus);
	TransactionDetailStatus updateTransactionDetailStatus(TransactionDetailStatus transactionDetailStatus);
    List<TransactionDetailStatus> getAllTransactionDetailStatus();
    TransactionDetailStatus findByTransactionDetailStatusId (Integer id);

}
