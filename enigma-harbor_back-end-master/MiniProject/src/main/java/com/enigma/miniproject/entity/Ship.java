package com.enigma.miniproject.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ms_ship")
public class Ship {
	@Id
	private String shipCode;

	private String shipName;

	private String captainName;

	@ManyToOne
	@JoinColumn(name = "ship_status_id", nullable = false)
	private ShipStatus shipStatus;

	public String getShipCode() {
		return shipCode;
	}

	public void setShipCode(String shipCode) {
		this.shipCode = shipCode;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}



	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	public ShipStatus getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(ShipStatus shipStatus) {
		this.shipStatus = shipStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(shipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (shipCode == null) {
			if (other.shipCode != null)
				return false;
		} else if (!shipCode.equals(other.shipCode))
			return false;
		return true;
	}
	
	public Ship() {}

	public Ship(String shipCode, String shipName, String captainName, ShipStatus shipStatus) {
		super();
		this.shipCode = shipCode;
		this.shipName = shipName;
		this.captainName = captainName;
		this.shipStatus = shipStatus;
	}
	
	
	
}
