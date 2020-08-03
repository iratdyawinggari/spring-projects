package com.enigma.miniproject.service;

import java.util.List;

import com.enigma.miniproject.entity.MenuHeader;

public interface MenuHeaderService {
	MenuHeader createMenuHeader(MenuHeader menuHeader);
	MenuHeader updateMenuHeader(MenuHeader menuHeader);
    List<MenuHeader> getAllMenuHeader();
    MenuHeader findByHeader (String header);
}
