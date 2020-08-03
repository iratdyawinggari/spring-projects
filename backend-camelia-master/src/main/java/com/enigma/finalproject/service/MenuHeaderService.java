package com.enigma.finalproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.MenuHeader;
import com.enigma.finalproject.repository.MenuHeaderRepository;

@Service
public class MenuHeaderService {
@Autowired
MenuHeaderRepository menuHeaderRepository;

public List<MenuHeader> getHeaderByRoleId(Integer roleId) {
Map<MenuHeader, Integer> menu = new HashMap<MenuHeader, Integer>();

List<MenuHeader> res = menuHeaderRepository.findAllByChildrensRoleId(roleId);
for (MenuHeader menuHeader : res) {
menu.put(menuHeader, menuHeader.getId());
}
Map<Object, Object> result = sortByValues(menu);

List<MenuHeader> list = new ArrayList<MenuHeader>();
for (Entry<Object, Object> entry : result.entrySet()) {
list.add((MenuHeader) entry.getKey());
}
return list;
}

public static Map<Object, Object> sortByValues(final Map<MenuHeader, Integer> wordCounts) {
return wordCounts.entrySet().stream().sorted((Map.Entry.<MenuHeader, Integer>comparingByValue()))
.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
}
}