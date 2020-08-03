package com.enigma.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigma.finalproject.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{

}
