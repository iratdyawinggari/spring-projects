package com.enigma.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigma.miniproject.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	// QueryDSL
	User findByUserNameAndUserPassword(String UserName, String UserPassword);
	User findByUserName(String UserName);
	User findByUserPassword(String userPassword);
	

}
