package com.enigma.finalproject.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "master_user_info")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fullname;
	private Date birthDate;
	private String address;
	private String email;
	private String noTelp;
	private String photoPath;
	
	
	public UserInfo(Integer id, String fullname, Date birthDate, String address, String email, String noTelp,
			String photoPath) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.birthDate = birthDate;
		this.address = address;
		this.email = email;
		this.noTelp = noTelp;
		this.photoPath = photoPath;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNoTelp() {
		return noTelp;
	}


	public void setNoTelp(String noTelp) {
		this.noTelp = noTelp;
	}


	public String getPhotoPath() {
		return photoPath;
	}


	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}


	public UserInfo() {

	};
}
