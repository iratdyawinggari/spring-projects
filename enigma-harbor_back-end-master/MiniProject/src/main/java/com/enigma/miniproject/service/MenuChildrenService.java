package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.MenuChildren;
import com.enigma.miniproject.entity.MenuHeader;
import com.enigma.miniproject.repository.MenuChildrenRepository;
@Service
public class MenuChildrenService {
	@Autowired
	MenuChildrenRepository menuChildrenRepository;
	
	public List<MenuChildren> getAllMenuChildren() {
		return menuChildrenRepository.findAll();
	}

}
