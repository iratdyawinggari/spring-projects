package com.enigma.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enigma.miniproject.entity.Dock;
import com.enigma.miniproject.entity.Harbor;
import com.enigma.miniproject.service.HarborService;

@RestController
public class HarborController {
	@Autowired
	HarborService harborService;

	@PostMapping("harbors/id")
	public ResponseEntity<Harbor> getharborByharborCode(@RequestBody Harbor harbor) {
		Harbor searchharbor = harborService.findByharborCode(harbor.getHarborCode());
		return new ResponseEntity<>(searchharbor, HttpStatus.OK);
	}

	@GetMapping("harbors")
	public ResponseEntity<List<Harbor>> getListShip() {
		List<Harbor> harborList = harborService.getAllharbor();
		return new ResponseEntity<>(harborList, HttpStatus.OK);
	}

	@GetMapping("harbors/withoutSuspend")
	public ResponseEntity<List<Harbor>> getListHArborWithout() {
		List<Harbor> harborList = harborService.findWithoutSuspend();
		return new ResponseEntity<>(harborList, HttpStatus.OK);
	}
	
	@PostMapping("harbors/add")
	public ResponseEntity<Harbor> addShip(@RequestBody Harbor harbor) {
		Harbor newharbor = harborService.createharbor(harbor);
		return new ResponseEntity<>(newharbor, HttpStatus.OK);
	}

	@PostMapping("harbors/delete")
	public ResponseEntity<Harbor> deleteCategoryById(@RequestBody Harbor harbor) {
		Harbor Result = harborService.deleteharbor(harbor.getHarborCode());
		return new ResponseEntity<>(Result, HttpStatus.OK);
	}

	@PostMapping("harbors/update")
	public ResponseEntity<Harbor> updateharbor(@RequestBody Harbor harbor) {
		Harbor updateharbor = harborService.updateharbor(harbor);
		return new ResponseEntity<>(updateharbor, HttpStatus.OK);
	}
	
	@GetMapping("/harbors/page")
	public ResponseEntity<Page<Harbor>> listHarborPage(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
		Page<Harbor> harborListPage = harborService.doShowByPage(page, pageSize);
		return new ResponseEntity<>(harborListPage, HttpStatus.OK);
	}


}
