package com.enigma.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Role;
import com.enigma.finalproject.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}
	
	public Role getRoleById(Integer id){
		return roleRepository.getRoleById(id);
	}

	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	public Role editRole(Role role) {
		return roleRepository.save(role);
	}
}
