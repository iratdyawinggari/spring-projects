package com.enigma.finalproject.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "master_indicator_status")
public class IndicatorStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String indicatorStatusName;

	private Integer minPoint;
	private Integer maxPoint;
	@ManyToOne
	@JoinColumn(name = "indicator_id", nullable = false)
	@JsonIgnore
	private Indicator indicator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndicatorStatusName() {
		return indicatorStatusName;
	}

	public void setIndicatorStatusName(String indicatorStatusName) {
		this.indicatorStatusName = indicatorStatusName;
	}

	public Integer getMinPoint() {
		return minPoint;
	}

	public void setMinPoint(Integer minPoint) {
		this.minPoint = minPoint;
	}

	public Integer getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(Integer maxPoint) {
		this.maxPoint = maxPoint;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
}
