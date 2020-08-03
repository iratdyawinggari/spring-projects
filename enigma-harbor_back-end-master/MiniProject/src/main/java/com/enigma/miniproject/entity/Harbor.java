package com.enigma.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ms_harbor")
public class Harbor {
	@Id
	private String harborCode;
	private String harborName;
	private Integer harborCapacity;
	
	@ManyToOne
	@JoinColumn(name = "harbor_status_id")
	private HarborStatus harborStatus;

	public String getHarborCode() {
		return harborCode;
	}

	public void setHarborCode(String harborCode) {
		this.harborCode = harborCode;
	}

	public String getHarborName() {
		return harborName;
	}

	public void setHarborName(String harborName) {
		this.harborName = harborName;
	}

	public Integer getHarborCapacity() {
		return harborCapacity;
	}

	public void setHarborCapacity(Integer harborCapacity) {
		this.harborCapacity = harborCapacity;
	}

	public HarborStatus getHarborStatus() {
		return harborStatus;
	}

	public void setHarborStatus(HarborStatus harborStatus) {
		this.harborStatus = harborStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((harborCode == null) ? 0 : harborCode.hashCode());
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
		Harbor other = (Harbor) obj;
		if (harborCode == null) {
			if (other.harborCode != null)
				return false;
		} else if (!harborCode.equals(other.harborCode))
			return false;
		return true;
	}
	
	public Harbor() {
	}

	public Harbor(String harborCode, String harborName, Integer harborCapacity, HarborStatus harborStatus) {
		super();
		this.harborCode = harborCode;
		this.harborName = harborName;
		this.harborCapacity = harborCapacity;
		this.harborStatus = harborStatus;
	}
	
	

}
