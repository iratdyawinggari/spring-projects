package com.enigma.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.TransactionHeader;

public interface TransactionHeaderRepository extends JpaRepository<TransactionHeader, String>{
	@Query("select th from TransactionHeader th where th.transactionHeaderId=:transactionHeaderId")
	TransactionHeader findByTransactionHeaderId(@Param("transactionHeaderId")String transactionHeaderId);
}
