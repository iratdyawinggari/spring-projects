package com.enigma.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ms_transaction_status")
public class TransactionDetailStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionStatusId;
	
	private String transactionStatusName;

	public Integer getTransactionStatusId() {
		return transactionStatusId;
	}

	public void setTransactionStatusId(Integer transactionStatusId) {
		this.transactionStatusId = transactionStatusId;
	}

	public String getTransactionStatusName() {
		return transactionStatusName;
	}

	public void setTransactionStatusName(String transactionStatusName) {
		this.transactionStatusName = transactionStatusName;
	}

	public TransactionDetailStatus(Integer transactionStatusId, String transactionStatusName) {
		super();
		this.transactionStatusId = transactionStatusId;
		this.transactionStatusName = transactionStatusName;
	}

	public TransactionDetailStatus() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transactionStatusId == null) ? 0 : transactionStatusId.hashCode());
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
		TransactionDetailStatus other = (TransactionDetailStatus) obj;
		if (transactionStatusId == null) {
			if (other.transactionStatusId != null)
				return false;
		} else if (!transactionStatusId.equals(other.transactionStatusId))
			return false;
		return true;
	}
	
	
}
