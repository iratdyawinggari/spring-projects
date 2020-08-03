package com.enigma.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "menu_children")
public class MenuChildren {
	@Id
	private String name;
	private String path;
	private String icon;
	private String header;
	
	
//	@ManyToOne
//	@JoinColumn(name = "header", nullable = false)
//	private MenuHeader header;

//	public String getHeader() {
//		return header;
//	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

//	public MenuHeader getHeader() {
//		return header;
//	}

//	public void setHeader(MenuHeader header) {
//		this.header = header;
//	}

}
