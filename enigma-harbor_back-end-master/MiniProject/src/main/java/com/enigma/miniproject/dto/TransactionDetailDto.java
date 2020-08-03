package com.enigma.miniproject.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransactionDetailDto {
//	@JsonFormat(pattern="dd-mm-yyyy")
	private Date entryDate;
//	private Date exitDate;
	private String dockCode;
	private String captainName;
	private Integer shipStatusId;
	private Integer loadingUnloadingWeight;
//	private Integer transactionDetailStatusId;

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

//	public Date getExitDate() {
//		return exitDate;
//	}
//
//	public void setExitDate(Date exitDate) {
//		this.exitDate = exitDate;
//	}

	public String getDockCode() {
		return dockCode;
	}

	public void setDockCode(String dockCode) {
		this.dockCode = dockCode;
	}

	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	public Integer getShipStatusId() {
		return shipStatusId;
	}

	public void setShipStatusId(Integer shipStatusId) {
		this.shipStatusId = shipStatusId;
	}

	public Integer getLoadingUnloadingWeight() {
		return loadingUnloadingWeight;
	}

	public void setLoadingUnloadingWeight(Integer loadingUnloadingWeight) {
		this.loadingUnloadingWeight = loadingUnloadingWeight;
	}

//	public Integer getTransactionDetailStatusId() {
//		return transactionDetailStatusId;
//	}
//
//	public void setTransactionDetailStatusId(Integer transactionDetailStatusId) {
//		this.transactionDetailStatusId = transactionDetailStatusId;
//	}
}
