package com.enigma.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ms_harbor_status")
public class HarborStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer harborStatusId; 
	
	private String harborStatusName;
	
	public Integer getHarborStatusId() {
		return harborStatusId;
	}

	public void setHarborStatusId(Integer harborStatusId) {
		this.harborStatusId = harborStatusId;
	}

	public String getHarborStatusName() {
		return harborStatusName;
	}

	public void setHarborStatusName(String harborStatusName) {
		this.harborStatusName = harborStatusName;
	}

	public HarborStatus() {
	}

	public HarborStatus(Integer harborStatusId, String harborStatusName) {
		super();
		this.harborStatusId = harborStatusId;
		this.harborStatusName = harborStatusName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((harborStatusId == null) ? 0 : harborStatusId.hashCode());
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
		HarborStatus other = (HarborStatus) obj;
		if (harborStatusId == null) {
			if (other.harborStatusId != null)
				return false;
		} else if (!harborStatusId.equals(other.harborStatusId))
			return false;
		return true;
	}
	
	
}
