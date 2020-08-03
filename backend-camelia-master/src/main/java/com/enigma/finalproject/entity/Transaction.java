package com.enigma.finalproject.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "master_transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Date transactionDate;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "disease_id", nullable = false)
	private Disease disease;

	private String inputPointCombination;

	@ManyToOne
	@JoinColumn(name = "output_status_id", nullable = false)
	private OutputStatus outputStatus;

	private Double outputPoint;
	
	
	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getOutputPoint() {
		return outputPoint;
	}

	public void setOutputPoint(Double outputPoint) {
		this.outputPoint = outputPoint;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public String getInputPointCombination() {
		return inputPointCombination;
	}

	public void setInputPointCombination(String inputPointCombination) {
		this.inputPointCombination = inputPointCombination;
	}

	public OutputStatus getOutputStatus() {
		return outputStatus;
	}

	public void setOutputStatus(OutputStatus outputStatus) {
		this.outputStatus = outputStatus;
	}
}