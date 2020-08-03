package com.enigma.miniproject.dto;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TransactionDto {
	private String transactionHeaderId;
	private String shipCode;
	
	private List<TransactionDetailDto> transactionDetailDtoList;

	public String getTransactionHeaderId() {
		return transactionHeaderId;
	}

	public void setTransactionHeaderId(String transactionHeaderId) {
		this.transactionHeaderId = transactionHeaderId;
	}

	public String getShipCode() {
		return shipCode;
	}

	public void setShipCode(String shipCode) {
		this.shipCode = shipCode;
	}

	public List<TransactionDetailDto> getTransactionDetailDtoList() {
		return transactionDetailDtoList;
	}

	public void setTransactionDetailDtoList(List<TransactionDetailDto> transactionDetailDtoList) {
		this.transactionDetailDtoList = transactionDetailDtoList;
	}
}
