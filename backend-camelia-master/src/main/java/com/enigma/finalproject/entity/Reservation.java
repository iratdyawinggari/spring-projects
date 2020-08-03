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
@Table(name = "master_reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	private Date reservationDate;

	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;

	private String bpjsNumber;
	private String identityCardNumber;
	private Integer arrivalStatus;

	public Integer getArrivalStatus() {
		return arrivalStatus;
	}

	public void setArrivalStatus(Integer arrivalStatus) {
		this.arrivalStatus = arrivalStatus;
	}

	public Reservation() {

	}

	

	public Reservation(Integer id, String name, Date reservationDate, Hospital hospital, String bpjsNumber,
			String identityCardNumber, Integer arrivalStatus) {
		super();
		this.id = id;
		this.name = name;
		this.reservationDate = reservationDate;
		this.hospital = hospital;
		this.bpjsNumber = bpjsNumber;
		this.identityCardNumber = identityCardNumber;
		this.arrivalStatus = arrivalStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getBpjsNumber() {
		return bpjsNumber;
	}

	public void setBpjsNumber(String bpjsNumber) {
		this.bpjsNumber = bpjsNumber;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

}