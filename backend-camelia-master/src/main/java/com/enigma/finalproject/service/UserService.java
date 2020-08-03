package com.enigma.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.dto.UserDTO;
import com.enigma.finalproject.dto.UserDTOService;
import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserDTOService userDTOService;
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
		return userRepository.findUserById(id);
	}
	
	public User addUserDTO(UserDTO userDTO) {
		return userDTOService.register(userDTO);
	}


	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public User editUser(UserDTO userDTO) {
		return userDTOService.update(userDTO);
	}

	public Page<User> showByPageSort(Integer page, Integer pageSize, String orderBy, String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return userRepository.findAll(paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return userRepository.findAll(paging);
		}
	}

	public Page<User> showByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return userRepository.findAll(paging);
	}


	public Page<User> searchByUsername(String username, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return userRepository.findByUsernameContains(username, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return userRepository.findByUsernameContains(username, paging);
		}
	}
	
	public Page<User> searchByFullname(String fullname, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return userRepository.findByUserInfoFullnameContains(fullname, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return userRepository.findByUserInfoFullnameContains(fullname, paging);
		}
	}

	public Page<User> searchByAddress(String address, Integer page, Integer pageSize, String orderBy,
			String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return userRepository.findByUserInfoAddressContains(address, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return userRepository.findByUserInfoAddressContains(address, paging);
		}
	}

	public Page<User> searchByEmail(String email, Integer page, Integer pageSize, String orderBy, String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return userRepository.findByUserInfoEmailContains(email, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return userRepository.findByUserInfoEmailContains(email, paging);
		}
	}

	public Page<User> searchByNoTelp(String noTelp, Integer page, Integer pageSize, String orderBy, String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return userRepository.findByUserInfoNoTelpContains(noTelp, paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return userRepository.findByUserInfoNoTelpContains(noTelp, paging);
		}
	}

	private String chooseShowBy(String orderBy) {
		switch (orderBy) {
		case "id":
			return "Id";
		case "username":
			return "Username";
		case "fullname":
			return "UserInfoFullname";
		case "birthDate":
			return "UserInfoBirthDate";
		case "address":
			return "UserInfoAddress";
		case "email":
			return "UserInfoEmail";
		case "noTelp":
			return "UserInfoNoTelp";
		case "gender":
			return "GenderGenderName";
		case "role":
			return "RoleName";
		default:
			return "Id";
		}
	}
}