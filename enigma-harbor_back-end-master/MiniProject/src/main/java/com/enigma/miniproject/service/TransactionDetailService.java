package com.enigma.miniproject.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.TransactionDetail;

public interface TransactionDetailService {
	TransactionDetail createTransaction(TransactionDetail transactionDetail);
	TransactionDetail updateTransaction(TransactionDetail transactionDetail);
    List<TransactionDetail> getAllTransactions();
    TransactionDetail findByTransactionId (Integer id);
    TransactionDetail doneTransaction(Integer id);
    List<Object[]> findShip(String shipCode);
    Page<TransactionDetail> doShowByPage(Integer page, Integer pageSize);
    Integer countShipAfter(String shipCode);
    
    Page<TransactionDetail> findAllByIdBetween(Integer idStart,Integer idEnd,Integer page, Integer pageSize);
    Page<TransactionDetail> findAllOrderById(String direction,Integer page, Integer pageSize);

    Page<TransactionDetail> findByTransactionHeaderId(String transactionHeaderId, Integer page, Integer pageSize);
    Page<TransactionDetail> findAllOrderByTransactionHeaderId( String direction,Integer page, Integer pageSize);
    
    Page<TransactionDetail> findByShipName(String shipName, Integer page, Integer pageSize);
    Page<TransactionDetail> findAllOrderByShipName(String direction,Integer page, Integer pageSize);

    Page<TransactionDetail> findByDockName(String dockName, Integer page, Integer pageSize);
    Page<TransactionDetail> findAllOrderByDockName(String direction, Integer page, Integer pageSize);
    
	Page<TransactionDetail> findByCaptainName(String captainName, Integer page, Integer pageSize);
	Page<TransactionDetail> findAllOrderByCaptainName(String direction, Integer page, Integer pageSize);
	
	Page<TransactionDetail> findByShipStatusName(String shipStatusName, Integer page, Integer pageSize);
	Page<TransactionDetail> findAllOrderByShipStatusName(String direction, Integer page, Integer pageSize);
	
	Page<TransactionDetail> findByStatusName(String transactionStatusName, Integer page, Integer pageSize);
	Page<TransactionDetail> findAllOrderByStatusName(String direction, Integer page, Integer pageSize);
	
	Page<TransactionDetail> findAllByEntryDate(String entryDateStart,String entryDateEnd,Integer page, Integer pageSize);
	Page<TransactionDetail> findAllOrderByEntryDate(String direction,Integer page, Integer pageSize);

	
	Page<TransactionDetail> findAllByExitDate(String exitDateStart,String exitDateEnd,Integer page, Integer pageSize);
	Page<TransactionDetail> findAllOrderByExitDate(String direction,Integer page, Integer pageSize);

	Page<TransactionDetail> findAllByWeight(Integer weightStart,Integer weightEnd,Integer page, Integer pageSize);
	Page<TransactionDetail> findAllOrderByWeight(String direction,Integer page, Integer pageSize);
	
	Page<TransactionDetail> getByHarborName(String harborName,Integer page, Integer pageSize);
	Page<TransactionDetail> findAllOrderByHarborName(String direction,Integer page, Integer pageSize);


	
}
