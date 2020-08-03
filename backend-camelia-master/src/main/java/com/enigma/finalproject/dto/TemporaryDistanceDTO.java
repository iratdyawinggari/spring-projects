package com.enigma.finalproject.dto;

import com.enigma.finalproject.entity.Hospital;

public class TemporaryDistanceDTO {
	private Hospital hospital;
	private Double distance;

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

}
