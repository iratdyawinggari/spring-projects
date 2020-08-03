package com.enigma.finalproject.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "master_rule")
public class MasterRule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ruleCombination;
	@ManyToOne
	@JoinColumn(name = "output_status_id", nullable = false)
	private OutputStatus outputStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuleCombination() {
		return ruleCombination;
	}

	public void setRuleCombination(String ruleCombination) {
		this.ruleCombination = ruleCombination;
	}

	public OutputStatus getOutputStatus() {
		return outputStatus;
	}

	public void setOutputStatus(OutputStatus outputStatus) {
		this.outputStatus = outputStatus;
	}

}
