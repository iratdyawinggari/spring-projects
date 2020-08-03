package com.enigma.miniproject.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.TransactionDetail;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
	@Query("select td from TransactionDetail td where td.id=:id")
	TransactionDetail findTrxDtlById(@Param("id") Integer id);

	@Query(value = "SELECT s.ship_code,td.captain_name,td.ship_status_id from trx_detail td\r\n"
			+ "INNER JOIN trx_header th ON td.trx_hr_id = th.trx_hr_id \r\n"
			+ "INNER JOIN ms_ship s ON th.ship_code = s.ship_code\r\n"
			+ "INNER JOIN ms_ship_status mss ON s.ship_status_id = mss.ship_status_id\r\n"
			+ "WHERE td.transaction_status_id = 1 and s.ship_code =:shipCode\r\n"
			+ "order by(td.id) LIMIT 1;\r\n", nativeQuery = true)
	List<Object[]> findShip(@Param("shipCode") String shipCode);

	@Query(value = "SELECT COUNT(*) from trx_detail td \r\n"
			+ "	INNER JOIN trx_header th ON td.trx_hr_id = th.trx_hr_id \r\n"
			+ "	INNER JOIN ms_ship s ON th.ship_code = s.ship_code\r\n"
			+ "	WHERE td.transaction_status_id = 1 and s.ship_code =:shipCode\r\n"
			+ "	order by(td.id) ", nativeQuery = true)
	Integer findSumShipAfter(@Param("shipCode") String shipCode);

	@Query(value = "SELECT COUNT(*) from trx_detail td   \r\n"
			+ "inner join ms_dock d ON td.dock_code = d.dock_code\r\n"
			+ "WHERE td.transaction_status_id = 1 and td.dock_code=:dockCode", nativeQuery = true)
	Integer findSumDockAfter(@Param("dockCode") String dockCode);

	Page<TransactionDetail> findAllByIdBetween(Integer idStart, Integer idEnd, Pageable pageable);

	Page<TransactionDetail> findByOrderByIdAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByIdDesc(Pageable pageable);

	Page<TransactionDetail> findByTransactionHeaderTransactionHeaderIdContains(String transactionHeaderId,
			Pageable pageable);

	Page<TransactionDetail> findByOrderByTransactionHeaderTransactionHeaderIdAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByTransactionHeaderTransactionHeaderIdDesc(Pageable pageable);

	Page<TransactionDetail> findByTransactionHeaderShipShipNameContains(String shipName, Pageable pageable);

	Page<TransactionDetail> findByOrderByTransactionHeaderShipShipNameAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByTransactionHeaderShipShipNameDesc(Pageable pageable);

	Page<TransactionDetail> findByDockDockNameContains(String dockName, Pageable pageable);

	Page<TransactionDetail> findByOrderByDockDockNameAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByDockDockNameDesc(Pageable pageable);

	Page<TransactionDetail> findByCaptainNameContains(String captainName, Pageable pageable);

	Page<TransactionDetail> findByOrderByCaptainNameAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByCaptainNameDesc(Pageable pageable);

	Page<TransactionDetail> findByShipStatusShipStatusNameContains(String shipStatusName, Pageable pageable);

	Page<TransactionDetail> findByOrderByShipStatusShipStatusNameAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByShipStatusShipStatusNameDesc(Pageable pageable);

	Page<TransactionDetail> findByTransactionDetailStatusTransactionStatusNameContains(String TransactionStatusName,
			Pageable pageable);

	Page<TransactionDetail> findByOrderByTransactionDetailStatusTransactionStatusNameAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByTransactionDetailStatusTransactionStatusNameDesc(Pageable pageable);

	Page<TransactionDetail> findAllByEntryDateBetween(Date entryDateStart, Date entryDateEnd, Pageable pageable);

	Page<TransactionDetail> findByOrderByEntryDateAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByEntryDateDesc(Pageable pageable);

	Page<TransactionDetail> findAllByExitDateBetween(Date exitDateStart, Date exitDateEnd, Pageable pageable);

	Page<TransactionDetail> findByOrderByExitDateAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByExitDateDesc(Pageable pageable);

	Page<TransactionDetail> findAllByLoadingUnloadingWeightBetween(Integer weightStart, Integer weightEnd,
			Pageable pageable);

	Page<TransactionDetail> findByOrderByLoadingUnloadingWeightAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByLoadingUnloadingWeightDesc(Pageable pageable);

	Page<TransactionDetail> findByOrderByDockHarborHarborNameAsc(Pageable pageable);

	Page<TransactionDetail> findByOrderByDockHarborHarborNameDesc(Pageable pageable);

	Page<TransactionDetail> findByDockHarborHarborNameContains(String harborName, Pageable pageable);
}
