package com.enigma.finalproject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menu_header")
public class MenuHeader implements Comparable<MenuHeader>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "header_name")
	private String header;

	@OneToMany(mappedBy = "header")
	private List<MenuChildren> childrens;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getHeader() {
		return header;
	}



	public void setHeader(String header) {
		this.header = header;
	}

	public List<MenuChildren> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<MenuChildren> childrens) {
		this.childrens = childrens;
	}



	@Override
	public int compareTo(MenuHeader header) {
	    if (getId()== null || header.getId() == null) {
	        return 0;
	      }
	      return getId().compareTo(header.getId());	
	}

}
