package com.enigma.miniproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.enigma.miniproject.entity.Harbor;

public interface HarborService {
	Harbor createharbor(Harbor harbor);
	Harbor updateharbor(Harbor harbor);
    List<Harbor> getAllharbor();
    Harbor findByharborCode (String harborCode);
    Harbor deleteharbor(String harborCode);
    List<Harbor> findWithoutSuspend();
    Integer findCapacityHarbor(String harborCode);
    Page<Harbor> doShowByPage(Integer page, Integer pageSize);
}
