package com.enigma.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ms_dock_status")
public class DockStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dockStatusId;
	private String dockStatusName;

	public Integer getDockStatusId() {
		return dockStatusId;
	}

	public void setDockStatusId(Integer dockStatusId) {
		this.dockStatusId = dockStatusId;
	}

	public String getDockStatusName() {
		return dockStatusName;
	}

	public void setDockStatusName(String dockStatusName) {
		this.dockStatusName = dockStatusName;
	}

	public DockStatus(Integer dockStatusId, String dockStatusName) {
		super();
		this.dockStatusId = dockStatusId;
		this.dockStatusName = dockStatusName;
	}

	public DockStatus() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dockStatusId == null) ? 0 : dockStatusId.hashCode());
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
		DockStatus other = (DockStatus) obj;
		if (dockStatusId == null) {
			if (other.dockStatusId != null)
				return false;
		} else if (!dockStatusId.equals(other.dockStatusId))
			return false;
		return true;
	}
	
	
}
