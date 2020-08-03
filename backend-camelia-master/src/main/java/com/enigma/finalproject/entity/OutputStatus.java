package com.enigma.finalproject.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "master_output_status")
public class OutputStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String outputStatusName;
	private Integer minPoint;
	private Integer maxPoint;
	@ManyToOne
	@JoinColumn(name = "output_id", nullable = false)
	private Output output;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOutputStatusName() {
		return outputStatusName;
	}

	public void setOutputStatusName(String outputStatusName) {
		this.outputStatusName = outputStatusName;
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

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}
}
