package com.enigma.miniproject.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.entity.TransactionDetail;
import com.enigma.miniproject.entity.TransactionDetailStatus;
import com.enigma.miniproject.repository.DockRepository;
import com.enigma.miniproject.repository.DockStatusRepository;
import com.enigma.miniproject.repository.ShipRepository;
import com.enigma.miniproject.repository.ShipStatusRepository;
import com.enigma.miniproject.repository.TransactionDetailRepository;
import com.enigma.miniproject.repository.TransactionDetailStatusRepository;
import com.ibm.icu.text.SimpleDateFormat;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	@Autowired
	TransactionDetailStatusRepository transactionDetailStatusRepository;

	@Autowired
	ShipStatusRepository shipStatusRepository;

	@Autowired
	ShipRepository shipRepository;

	@Autowired
	DockRepository dockRepository;

	@Autowired
	DockStatusRepository dockStatusRepository;

	@Override
	public TransactionDetail createTransaction(TransactionDetail transactionDetail) {
		return transactionDetailRepository.save(transactionDetail);
	}

	@Override
	public TransactionDetail updateTransaction(TransactionDetail transactionDetail) {
		return transactionDetailRepository.save(transactionDetail);
	}

	@Override
	public List<TransactionDetail> getAllTransactions() {
		return transactionDetailRepository.findAll();
	}

	@Override
	public TransactionDetail findByTransactionId(Integer id) {
		return transactionDetailRepository.findTrxDtlById(id);
	}

	@Override
	public TransactionDetail doneTransaction(Integer id) {
		TransactionDetail transactionDetail = transactionDetailRepository.findTrxDtlById(id);
		Ship ship = transactionDetail.getTransactionHeader().getShip();
//		ShipStatus shipStatus = shipStatusRepository.findByShipStatusId(1);
//		ship.setShipStatus(shipStatus);
//		shipRepository.save(ship);
		Date date = new Date();
		Dock dock = transactionDetail.getDock();		
		
		TransactionDetailStatus transactionDetailStatus = transactionDetailStatusRepository
				.findBytransactionStatusId(2);

		transactionDetail.setTransactionDetailStatus(transactionDetailStatus);
		transactionDetail.setExitDate(date);

		transactionDetailRepository.save(transactionDetail);
		if(transactionDetailRepository.findSumDockAfter(transactionDetail.getDock().getDockCode())==0) {
			DockStatus dockStatus = dockStatusRepository.findDockStatusByDockStatusId(1);
			dock.setDockStatus(dockStatus);
			dockRepository.save(dock);
		}

		if (transactionDetailRepository.findSumShipAfter(ship.getShipCode()) > 0) {
			List<Object[]> obj = transactionDetailRepository.findShip(ship.getShipCode());

			for (Object[] objects : obj) {
				ship.setCaptainName(objects[1].toString());
				ShipStatus shipStatus = shipStatusRepository.findByShipStatusId(Integer.valueOf(objects[2].toString()));
				ship.setShipStatus(shipStatus);
				System.out.println(objects[0].toString());
				shipRepository.save(ship);
			}
		} else {
			ShipStatus shipStatus = shipStatusRepository.findByShipStatusId(1);
			ship.setShipStatus(shipStatus);
			shipRepository.save(ship);
		}
		return transactionDetail;
	}

	@Override
	public List<Object[]> findShip(String shipCode) {
		return transactionDetailRepository.findShip(shipCode);
	}

	@Override
	public Integer countShipAfter(String shipCode) {
		return transactionDetailRepository.findSumShipAfter(shipCode);
	}

	@Override
	public Page<TransactionDetail> doShowByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findAll(paging);
	}

	@Override
	public Page<TransactionDetail> findAllByIdBetween(Integer idStart, Integer idEnd, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findAllByIdBetween(idStart, idEnd, paging);
	}

	@Override
	public Page<TransactionDetail> findByTransactionHeaderId(String transactionHeaderId, Integer page,
			Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findByTransactionHeaderTransactionHeaderIdContains(transactionHeaderId,
				paging);
	}

	@Override
	public Page<TransactionDetail> findByShipName(String shipName, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findByTransactionHeaderShipShipNameContains(shipName, paging);
	}

	@Override
	public Page<TransactionDetail> findByDockName(String dockName, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findByDockDockNameContains(dockName, paging);
	}

	@Override
	public Page<TransactionDetail> findByCaptainName(String captainName, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findByCaptainNameContains(captainName, paging);
	}

	@Override
	public Page<TransactionDetail> findByShipStatusName(String shipStatusName, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findByShipStatusShipStatusNameContains(shipStatusName, paging);
	}

	@Override
	public Page<TransactionDetail> findByStatusName(String transactionStatusName, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository
				.findByTransactionDetailStatusTransactionStatusNameContains(transactionStatusName, paging);
	}

	@Override
	public Page<TransactionDetail> findAllByEntryDate(String entryDateStart, String entryDateEnd, Integer page,
			Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date DateStart = null;
		Date DateEnd = null;
		try {
			DateStart = df.parse(entryDateStart);
			DateEnd = df.parse(entryDateEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return transactionDetailRepository.findAllByEntryDateBetween(DateStart, DateEnd, paging);
	}

	@Override
	public Page<TransactionDetail> findAllByExitDate(String exitDateStart, String exitDateEnd, Integer page,
			Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date DateStart = null;
		Date DateEnd = null;
		try {
			DateStart = df.parse(exitDateStart);
			DateEnd = df.parse(exitDateEnd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return transactionDetailRepository.findAllByExitDateBetween(DateStart, DateEnd, paging);
	}

	@Override
	public Page<TransactionDetail> findAllByWeight(Integer weightStart, Integer weightEnd, Integer page,
			Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findAllByLoadingUnloadingWeightBetween(weightStart, weightEnd, paging);
	}

	@Override
	public Page<TransactionDetail> getByHarborName(String harborName, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionDetailRepository.findByDockHarborHarborNameContains(harborName, paging);
	}

	@Override
	public Page<TransactionDetail> findAllOrderById(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByIdAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByIdDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByTransactionHeaderId(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByTransactionHeaderTransactionHeaderIdAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByTransactionHeaderTransactionHeaderIdDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByShipName(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByTransactionHeaderShipShipNameAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByTransactionHeaderShipShipNameDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByDockName(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByDockDockNameAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByDockDockNameDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByCaptainName(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByCaptainNameAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByCaptainNameDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByShipStatusName(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByShipStatusShipStatusNameAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByShipStatusShipStatusNameDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByStatusName(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByTransactionDetailStatusTransactionStatusNameAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByTransactionDetailStatusTransactionStatusNameDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByEntryDate(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByEntryDateAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByEntryDateDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByExitDate(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByExitDateAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByExitDateDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByWeight(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByLoadingUnloadingWeightAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByLoadingUnloadingWeightDesc(paging);
		}
	}

	@Override
	public Page<TransactionDetail> findAllOrderByHarborName(String direction, Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		if (direction.equals("asc")) {
			return transactionDetailRepository.findByOrderByDockHarborHarborNameAsc(paging);
		} else {
			return transactionDetailRepository.findByOrderByDockHarborHarborNameDesc(paging);
		}
	}

}
