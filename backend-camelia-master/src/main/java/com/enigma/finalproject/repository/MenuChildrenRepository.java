package com.enigma.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigma.finalproject.entity.MenuChildren;
import com.enigma.finalproject.entity.MenuHeader;

public interface MenuChildrenRepository extends JpaRepository<MenuChildren, Integer>{
	List<MenuChildren> findByRoleId(Integer id);

}
