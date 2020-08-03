package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.TransactionDetail;
import com.enigma.miniproject.service.TransactionDetailService;

@RestController
public class TransactionDetailController {
	@Autowired
	TransactionDetailService transactionDetailService;

	@PostMapping("transactionDetails/id")
	public ResponseEntity<TransactionDetail> getByTransactionDetailId(
			@RequestBody TransactionDetail transactionDetail) {
		TransactionDetail searchTransactionDetail = transactionDetailService
				.findByTransactionId(transactionDetail.getId());
		return new ResponseEntity<>(searchTransactionDetail, HttpStatus.OK);
	}

	@GetMapping("transactionDetails")
	public ResponseEntity<List<TransactionDetail>> getListTransactionDetail() {
		List<TransactionDetail> transactionDetailList = transactionDetailService.getAllTransactions();
		return new ResponseEntity<>(transactionDetailList, HttpStatus.OK);
	}

//	@PostMapping("transactionDetails/add")
//	public ResponseEntity<TransactionDetail> addTransaction(@RequestBody TransactionDetail transactionDetail) {
//		TransactionDetail newTransactionDetail = transactionDetailService.createTransaction(transactionDetail);
//		return new ResponseEntity<>(newTransactionDetail, HttpStatus.OK);
//	}
//	
//	@GetMapping("transactionDetails/ship")
//	public ResponseEntity<List<Object[]>> getListShipTransactionDetail(@RequestParam String shipCode) {
//		List<Object[]> Result = transactionDetailService.findShip(shipCode);
//		return new ResponseEntity<>(Result, HttpStatus.OK);
//	}
	
//	@GetMapping("transactionDetails/countShipAfter")
//	public ResponseEntity<Integer> getListShipDetail(@RequestParam String shipCode) {
//		Integer Result = transactionDetailService.countShipAfter(shipCode);
//		return new ResponseEntity<>(Result, HttpStatus.OK);
//	}

	@PostMapping("transactionDetails/done")
	public ResponseEntity<TransactionDetail> doneTransaction(@RequestBody TransactionDetail transactionDetail) {
		TransactionDetail updateTransaction = transactionDetailService.doneTransaction(transactionDetail.getId());
		return new ResponseEntity<>(updateTransaction, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/page")
	public ResponseEntity<Page<TransactionDetail>> listShipPage(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.doShowByPage(page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/trxId")
	public ResponseEntity<Page<TransactionDetail>> findAllByIdBetween(@RequestParam("idStart") Integer idStart,@RequestParam("idEnd") Integer idEnd,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllByIdBetween(idStart, idEnd, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}

	@GetMapping("/transactionDetails/trxId/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderById(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderById(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}

	@GetMapping("/transactionDetails/thId")
	public ResponseEntity<Page<TransactionDetail>> findAllByIdBetween(@RequestParam("thId") String thId,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findByTransactionHeaderId(thId, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}

	@GetMapping("/transactionDetails/thId/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderBythId(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByTransactionHeaderId(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/shipName")
	public ResponseEntity<Page<TransactionDetail>> findByTransactionHeaderShipShipNameContains(@RequestParam("shipName") String shipName,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findByShipName(shipName, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/shipName/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByShipName(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByShipName(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	
	
	@GetMapping("/transactionDetails/dockName")
	public ResponseEntity<Page<TransactionDetail>> findByDockDockNameContains(@RequestParam("dockName") String dockName,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findByDockName(dockName, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/dockName/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByDockName(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByDockName(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	
	
	@GetMapping("/transactionDetails/captainName")
	public ResponseEntity<Page<TransactionDetail>> findByCaptainNameContains(@RequestParam("captainName") String captainName,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findByCaptainName(captainName, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/captainName/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByCaptainName(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByCaptainName(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	
	
	@GetMapping("/transactionDetails/shipStatusName")
	public ResponseEntity<Page<TransactionDetail>> findByShipStatusShipStatusNameContains(@RequestParam("shipStatusName") String shipStatusName,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findByShipStatusName(shipStatusName, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/shipStatusName/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByShipStatusName(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByShipStatusName(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	
	
	@GetMapping("/transactionDetails/transactionStatusName")
	public ResponseEntity<Page<TransactionDetail>> findByTransactionDetailStatusTransactionStatusNameContains(@RequestParam("transactionStatusName") String transactionStatusName,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findByStatusName(transactionStatusName, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/transactionStatusName/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByStatus(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByStatusName(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	

	@GetMapping("/transactionDetails/entryDate")
	public ResponseEntity<Page<TransactionDetail>> findAllByEntryDateBetween(@RequestParam("entryDateStart") String entryDateStart,@RequestParam("entryDateEnd") String entryDateEnd,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllByEntryDate(entryDateStart, entryDateEnd, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/entryDate/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByEntryDate(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByEntryDate(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	
	
	@GetMapping("/transactionDetails/exitDate")
	public ResponseEntity<Page<TransactionDetail>> findAllByExitDateBetween(@RequestParam("exitDateStart") String exitDateStart,@RequestParam("exitDateEnd") String exitDateEnd,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllByExitDate(exitDateStart, exitDateEnd, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/exitDate/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByExitDate(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByExitDate(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	
	
	@GetMapping("/transactionDetails/weight")
	public ResponseEntity<Page<TransactionDetail>> findAllByLoadingUnloadingWeightBetween(@RequestParam("weightStart") Integer weightStart,@RequestParam("weightEnd") Integer weightEnd,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllByWeight(weightStart, weightEnd, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/weight/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByWeight(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByWeight(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	

	@GetMapping("/transactionDetails/harborName")
	public ResponseEntity<Page<TransactionDetail>> getByHarborNameLike(@RequestParam("harborName") String harborName,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.getByHarborName(harborName, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}
	
	@GetMapping("/transactionDetails/harborName/sort")
	public ResponseEntity<Page<TransactionDetail>> findAllOrderByHarbor(@RequestParam("direction") String direction,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<TransactionDetail> trxDetailList = transactionDetailService.findAllOrderByHarborName(direction, page, pageSize);
		return new ResponseEntity<>(trxDetailList, HttpStatus.OK);
	}	

}
