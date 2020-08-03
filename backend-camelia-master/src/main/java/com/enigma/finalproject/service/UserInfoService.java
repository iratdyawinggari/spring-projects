package com.enigma.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.entity.UserInfo;
import com.enigma.finalproject.repository.UserInfoRepository;

@Service
public class UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	public List<UserInfo> getAllUserInfo(){
		return userInfoRepository.findAll();
	}

	public UserInfo addUserInfo(UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}
	
	public UserInfo editUserInfo(UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}

	public Page<UserInfo> showByPageSort(Integer page, Integer pageSize, String orderBy, String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return userInfoRepository.findAll(paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return userInfoRepository.findAll(paging);
		}
	}

	private String chooseShowBy(String orderBy) {
		switch (orderBy) {
		case "id":
			return "Id";
		case "fullname":
			return "Fullname";
		case "birthDate":
			return "BirthDate";
		case "address":
			return "Address";
		case "email":
			return "Email";
		case "noTelp":
			return "NoTelp";
		default:
			return "Id";
		}
	}

	public Page<UserInfo> showByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return userInfoRepository.findAll(paging);
	}
	
}