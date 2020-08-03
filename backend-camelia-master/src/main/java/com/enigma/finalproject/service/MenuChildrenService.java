package com.enigma.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.MenuChildren;
import com.enigma.finalproject.repository.MenuChildrenRepository;

@Service
public class MenuChildrenService {
	@Autowired
	MenuChildrenRepository menuChildrenRepository;
	
	public List<MenuChildren> getChildrenByRoleId(Integer roleId){
		return menuChildrenRepository.findByRoleId(roleId);
	}
}
