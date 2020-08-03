package com.enigma.miniproject.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "menu_header")
public class MenuHeader {
	
	@Id
	private String header;

	@OneToMany(mappedBy = "header")
//	@Cascade(value = { CascadeType.DELETE, CascadeType.SAVE_UPDATE })
	private List<MenuChildren> children;

	
	
	public List<MenuChildren> getChildren() {
		return children;
	}

	public void setChildren(List<MenuChildren> children) {
		this.children = children;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	
}
