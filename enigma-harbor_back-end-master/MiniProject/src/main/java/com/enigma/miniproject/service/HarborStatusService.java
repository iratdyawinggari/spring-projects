package com.enigma.miniproject.service;

import java.util.List;

import com.enigma.miniproject.entity.HarborStatus;

public interface HarborStatusService {
	HarborStatus createHarborStatus(HarborStatus harborStatus);
	HarborStatus updateHarborStatus(HarborStatus harborStatus);
    List<HarborStatus> getAllHarborStatus();
    HarborStatus findByharborStatusId (Integer harborStatusId);
    Integer deleteHarborStatus(Integer harborStatusId);
    List<HarborStatus> findWithoutSuspend();
}
