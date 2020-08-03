package com.enigma.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ms_harbour")
public class Harbour {
	@Id
	private String harbourCode;
	private String harbourName;

	public String getHarbourCode() {
		return harbourCode;
	}

	public void setHarbourCode(String harbourCode) {
		this.harbourCode = harbourCode;
	}

	public String getHarbourName() {
		return harbourName;
	}

	public void setHarbourName(String harbourName) {
		this.harbourName = harbourName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((harbourCode == null) ? 0 : harbourCode.hashCode());
		result = prime * result + ((harbourName == null) ? 0 : harbourName.hashCode());
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
		Harbour other = (Harbour) obj;
		if (harbourCode == null) {
			if (other.harbourCode != null)
				return false;
		} else if (!harbourCode.equals(other.harbourCode))
			return false;
		if (harbourName == null) {
			if (other.harbourName != null)
				return false;
		} else if (!harbourName.equals(other.harbourName))
			return false;
		return true;
	}
	
	
}
