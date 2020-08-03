package com.enigma.miniproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="trx_header")
public class TransactionHeader {
	
	@Id
	@Column(name="trx_hr_id")
	private String transactionHeaderId;

	@ManyToOne
	@JoinColumn(name = "ship_code", nullable = false)
	private Ship ship;

	public String getTransactionHeaderId() {
		return transactionHeaderId;
	}

	public void setTransactionHeaderId(String transactionHeaderId) {
		this.transactionHeaderId = transactionHeaderId;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}
}
