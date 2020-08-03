package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.miniproject.entity.MenuHeader;
import com.enigma.miniproject.repository.MenuHeaderRepository;
@Service
public class MenuHeaderServiceImpl implements MenuHeaderService{

	@Autowired
	MenuHeaderRepository menuHeaderRepository;
	
	@Override
	public MenuHeader createMenuHeader(MenuHeader menuHeader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuHeader updateMenuHeader(MenuHeader menuHeader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuHeader> getAllMenuHeader() {
		return menuHeaderRepository.findAll();
	}

	@Override
	public MenuHeader findByHeader(String header) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
