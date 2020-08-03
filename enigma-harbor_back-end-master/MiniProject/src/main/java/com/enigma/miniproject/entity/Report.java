package com.enigma.miniproject.entity;

import java.util.Date;

public class Report {

	private Integer id;
	private String transactionHeaderId;
	private String shipName;
	private Date entryDate;
	private Date exitDate;
	private String captainName;
	private String shipStatusName;
	private Integer weight;
	private String harborName;
	private String dockName;
	private String transactionStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransactionHeaderId() {
		return transactionHeaderId;
	}

	public void setTransactionHeaderId(String transactionHeaderId) {
		this.transactionHeaderId = transactionHeaderId;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	public String getShipStatusName() {
		return shipStatusName;
	}

	public void setShipStatusName(String shipStatusName) {
		this.shipStatusName = shipStatusName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getHarborName() {
		return harborName;
	}

	public void setHarborName(String harborName) {
		this.harborName = harborName;
	}

	public String getDockName() {
		return dockName;
	}

	public void setDockName(String dockName) {
		this.dockName = dockName;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
}
