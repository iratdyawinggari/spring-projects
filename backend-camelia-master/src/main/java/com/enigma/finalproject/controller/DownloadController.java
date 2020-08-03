package com.enigma.finalproject.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.service.UserService;

import kong.unirest.Unirest;

@RestController
@EnableWebMvc
@RequestMapping("/download")
public class DownloadController {

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<String> doAuthenticateUsername(@RequestParam("userId") Integer id) {
		User user = userService.getUserById(id);
		String name = user.getUsername() + "images";
		byte[] data = Unirest.post("https://content.dropboxapi.com/2/files/download")
				.header("Authorization", "Bearer YtUMg6S7KLAAAAAAAAAAbujCVn_NXjGpImb4Kh33QoGe1wnXGHSJFf-Ye91SrfnO")
				.header("Dropbox-API-Arg", "{\"path\":\"/scolilo-images/" + name + ".png\"}").asBytes().getBody();
		String encoded = Base64.getEncoder().encodeToString(data);
		return new ResponseEntity<>(encoded, HttpStatus.OK);
	}
}
