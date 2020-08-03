package com.enigma.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.enigma.miniproject.entity.TransactionDetailStatus;

public interface TransactionDetailStatusRepository extends JpaRepository<TransactionDetailStatus, Integer>{
	@Query("select t from TransactionDetailStatus t where t.transactionStatusId=:transactionStatusId")
	TransactionDetailStatus findBytransactionStatusId(@Param("transactionStatusId")Integer transactionStatusId);
}
