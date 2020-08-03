package com.enigma.finalproject.entity;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "master_indicator")
public class Indicator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String indicatorName;
	@ManyToOne
	@JoinColumn(name = "disease_id", nullable = false)
	private Disease disease;

	@OneToMany(mappedBy = "indicator")
	private List<IndicatorStatus> indicatorStatus;
	
	public List<IndicatorStatus> getIndicatorStatus() {
		return indicatorStatus;
	}

	public void setIndicatorStatus(List<IndicatorStatus> indicatorStatus) {
		this.indicatorStatus = indicatorStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

}
