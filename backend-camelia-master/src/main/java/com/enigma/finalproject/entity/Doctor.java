package com.enigma.finalproject.entity;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "master_doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nameDoctor;
	private Time hoursPractice;
	private Time hoursEnd;
	
	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameDoctor() {
		return nameDoctor;
	}

	public void setNameDoctor(String nameDoctor) {
		this.nameDoctor = nameDoctor;
	}

	public Time getHoursPractice() {
		return hoursPractice;
	}

	public void setHoursPractice(Time hoursPractice) {
		this.hoursPractice = hoursPractice;
	}

	public Time getHoursEnd() {
		return hoursEnd;
	}

	public void setHoursEnd(Time hoursEnd) {
		this.hoursEnd = hoursEnd;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Doctor(Integer id, String nameDoctor, Time hoursPractice, Time hoursEnd, Hospital hospital) {
		super();
		this.id = id;
		this.nameDoctor = nameDoctor;
		this.hoursPractice = hoursPractice;
		this.hoursEnd = hoursEnd;
		this.hospital = hospital;
	}
	
	public Doctor() {
		
	}
}