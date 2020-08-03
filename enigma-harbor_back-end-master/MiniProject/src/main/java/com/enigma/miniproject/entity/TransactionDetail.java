package com.enigma.miniproject.entity;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "trx_detail")
public class TransactionDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "trx_hr_id", nullable = false)
	private TransactionHeader transactionHeader;

	
	@Temporal(TemporalType.DATE)
	private Date entryDate;
	@Temporal(TemporalType.DATE)
	private Date exitDate;
	@ManyToOne
	@JoinColumn(name = "dock_code", nullable = false)
	private Dock dock;

	private String captainName;

	@ManyToOne
	@JoinColumn(name = "ship_status_id", nullable = false)
	private ShipStatus shipStatus;

	private Integer loadingUnloadingWeight;

	@ManyToOne
	@JoinColumn(name = "transaction_status_id", nullable = false)
	private TransactionDetailStatus transactionDetailStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TransactionHeader getTransactionHeader() {
		return transactionHeader;
	}

	public void setTransactionHeader(TransactionHeader transactionHeader) {
		this.transactionHeader = transactionHeader;
	}

	public String getEntryDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(entryDate);
		return dateString;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getExitDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(exitDate);
		return dateString;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public Dock getDock() {
		return dock;
	}

	public void setDock(Dock dock) {
		this.dock = dock;
	}

	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	public ShipStatus getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(ShipStatus shipStatus) {
		this.shipStatus = shipStatus;
	}

	public Integer getLoadingUnloadingWeight() {
		return loadingUnloadingWeight;
	}

	public void setLoadingUnloadingWeight(Integer loadingUnloadingWeight) {
		this.loadingUnloadingWeight = loadingUnloadingWeight;
	}

	public TransactionDetailStatus getTransactionDetailStatus() {
		return transactionDetailStatus;
	}

	public void setTransactionDetailStatus(TransactionDetailStatus transactionDetailStatus) {
		this.transactionDetailStatus = transactionDetailStatus;
	}

	public TransactionDetail(Integer id, TransactionHeader transactionHeader, Date entryDate, Date exitDate, Dock dock,
			String captainName, ShipStatus shipStatus, Integer loadingUnloadingWeight,
			TransactionDetailStatus transactionDetailStatus) {
		super();
		this.id = id;
		this.transactionHeader = transactionHeader;
		this.entryDate = entryDate;
		this.exitDate = exitDate;
		this.dock = dock;
		this.captainName = captainName;
		this.shipStatus = shipStatus;
		this.loadingUnloadingWeight = loadingUnloadingWeight;
		this.transactionDetailStatus = transactionDetailStatus;
	}

	public TransactionDetail() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionDetail other = (TransactionDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
