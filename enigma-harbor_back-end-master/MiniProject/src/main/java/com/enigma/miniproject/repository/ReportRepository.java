package com.enigma.miniproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enigma.miniproject.entity.TransactionDetail;

public interface ReportRepository extends JpaRepository<TransactionDetail, Integer> {
	@Query(value = "SELECT td.id, th.trx_hr_id,s.ship_name,td.entry_date,td.exit_date,s.captain_name,mss.ship_status_name,td.loading_unloading_weight, h.harbor_name, d.dock_name, mts.transaction_status_name  from trx_detail td\r\n" + 
			"INNER JOIN ms_dock d ON td.dock_code=d.dock_code\r\n" + 
			"INNER JOIN ms_harbor h ON d.harbor_code = h.harbor_code\r\n" + 
			"INNER JOIN trx_header th ON td.trx_hr_id = th.trx_hr_id \r\n" + 
			"INNER JOIN ms_ship s ON th.ship_code = s.ship_code\r\n" + 
			"INNER JOIN ms_ship_status mss ON td.ship_status_id = mss.ship_status_id\r\n" + 
			"inner join ms_transaction_status mts ON td.transaction_status_id = mts.transaction_status_id\r\n" + 
			"order by(td.id) ", nativeQuery = true)
	List<Object[]> getReport();
}
