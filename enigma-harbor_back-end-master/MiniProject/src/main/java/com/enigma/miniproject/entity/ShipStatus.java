package com.enigma.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ms_ship_status")
public class ShipStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shipStatusId;
	private String shipStatusName;
	public Integer getShipStatusId() {
		return shipStatusId;
	}
	public void setShipStatusId(Integer shipStatusId) {
		this.shipStatusId = shipStatusId;
	}
	public String getShipStatusName() {
		return shipStatusName;
	}
	public void setShipStatusName(String shipStatusName) {
		this.shipStatusName = shipStatusName;
	}
	
	public ShipStatus(Integer shipStatusId, String shipStatusName) {
		super();
		this.shipStatusId = shipStatusId;
		this.shipStatusName = shipStatusName;
	}

	public ShipStatus() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shipStatusId == null) ? 0 : shipStatusId.hashCode());
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
		ShipStatus other = (ShipStatus) obj;
		if (shipStatusId == null) {
			if (other.shipStatusId != null)
				return false;
		} else if (!shipStatusId.equals(other.shipStatusId))
			return false;
		return true;
	}
	
	
	
}
