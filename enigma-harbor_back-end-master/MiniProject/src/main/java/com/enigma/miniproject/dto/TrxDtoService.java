package com.enigma.miniproject.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.DockStatus;
import com.enigma.miniproject.entity.Ship;
import com.enigma.miniproject.entity.ShipStatus;
import com.enigma.miniproject.entity.TransactionDetail;
import com.enigma.miniproject.entity.TransactionHeader;
import com.enigma.miniproject.repository.DockRepository;
import com.enigma.miniproject.repository.DockStatusRepository;
import com.enigma.miniproject.repository.ShipRepository;
import com.enigma.miniproject.repository.ShipStatusRepository;
import com.enigma.miniproject.repository.TransactionDetailRepository;
import com.enigma.miniproject.repository.TransactionDetailStatusRepository;
import com.enigma.miniproject.repository.TransactionHeaderRepository;

@Service
public class TrxDtoService {
	@Autowired
	ShipRepository shipRepository;

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	@Autowired
	TransactionHeaderRepository transactionHeaderRepository;

	@Autowired
	DockRepository dockRepository;

	@Autowired
	ShipStatusRepository shipStatusRepository;

	@Autowired
	TransactionDetailStatusRepository transactionDetailStatusRepository;

	@Autowired
	DockStatusRepository dockStatusRepository;
	
	public TransactionDto createNewTransaction(TransactionDto transactionDto) {
		TransactionHeader newTh = new TransactionHeader();
		newTh.setTransactionHeaderId(transactionDto.getTransactionHeaderId());
		Ship ship = shipRepository.findShipByShipCode(transactionDto.getShipCode());
		newTh.setShip(ship);
		TransactionHeader thResult = transactionHeaderRepository.save(newTh);

		List<TransactionDetailDto> transactionDetailDtos = transactionDto.getTransactionDetailDtoList();
		List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();

		ShipStatus shipStatus = shipStatusRepository.findByShipStatusId(transactionDetailDtos.get(0).getShipStatusId());
		ship.setShipStatus(shipStatus);
		ship.setCaptainName(transactionDetailDtos.get(0).getCaptainName());
		shipRepository.save(ship);
		
		for (TransactionDetailDto transactionDetailDto : transactionDetailDtos) {
			TransactionDetail newTd = new TransactionDetail();
			newTd.setTransactionHeader(
					transactionHeaderRepository.findByTransactionHeaderId(thResult.getTransactionHeaderId()));
			newTd.setCaptainName(transactionDetailDto.getCaptainName());
			newTd.setDock(dockRepository.findDockByDockCode(transactionDetailDto.getDockCode()));
			Dock dock= dockRepository.findDockByDockCode(transactionDetailDto.getDockCode());
			DockStatus dockStatus= dockStatusRepository.findDockStatusByDockStatusId(2);
			dock.setDockStatus(dockStatus);
			dockRepository.save(dock);
			newTd.setEntryDate(transactionDetailDto.getEntryDate());
//			newTd.setExitDate(transactionDetailDto.getExitDate());
			newTd.setExitDate(new Date());
			newTd.setLoadingUnloadingWeight(transactionDetailDto.getLoadingUnloadingWeight());
			newTd.setShipStatus(shipStatusRepository.findByShipStatusId(transactionDetailDto.getShipStatusId()));
//			ship.setShipStatus(shipStatusRepository.findByShipStatusId(transactionDetailDto.getShipStatusId()));
//			ship.setCaptainName(transactionDetailDto.getCaptainName());
//			shipRepository.save(ship);
			newTd.setTransactionDetailStatus(transactionDetailStatusRepository
					.findBytransactionStatusId(1));
			transactionDetails.add(newTd);
		}

		transactionDetailRepository.saveAll(transactionDetails);
		return transactionDto;
	}
}
