package com.enigma.finalproject.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "master_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String username;
	@Column
	private String password;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_info_id", referencedColumnName = "id")
	private UserInfo userInfo;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "gender_id")
	private Gender gender;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public User(Integer id, String username, String password, UserInfo userInfo, Role role, Gender gender) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userInfo = userInfo;
		this.role = role;
		this.gender = gender;
	}

	public User() {
		
	}
	
}
