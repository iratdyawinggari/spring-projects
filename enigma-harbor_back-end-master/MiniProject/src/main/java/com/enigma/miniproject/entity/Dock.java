package com.enigma.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ms_dock")
public class Dock {
	@Id
	private String dockCode;
	private String dockName;

	@ManyToOne
	@JoinColumn(name = "harbor_code")
	private Harbor harbor;

	@ManyToOne
	@JoinColumn(name = "dock_status_id")
	private DockStatus dockStatus;

	public String getDockCode() {
		return dockCode;
	}

	public void setDockCode(String dockCode) {
		this.dockCode = dockCode;
	}

	public String getDockName() {
		return dockName;
	}

	public void setDockName(String dockName) {
		this.dockName = dockName;
	}

	public Harbor getharbor() {
		return harbor;
	}

	public void setharbor(Harbor harbor) {
		this.harbor = harbor;
	}

	public DockStatus getDockStatus() {
		return dockStatus;
	}

	public void setDockStatus(DockStatus dockStatus) {
		this.dockStatus = dockStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dockCode == null) ? 0 : dockCode.hashCode());
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
		Dock other = (Dock) obj;
		if (dockCode == null) {
			if (other.dockCode != null)
				return false;
		} else if (!dockCode.equals(other.dockCode))
			return false;
		return true;
	}

	public Dock() {};
	
	public Dock(String dockCode, String dockName, Harbor harbor, DockStatus dockStatus) {
		super();
		this.dockCode = dockCode;
		this.dockName = dockName;
		this.harbor = harbor;
		this.dockStatus = dockStatus;
	}


	
	
}
