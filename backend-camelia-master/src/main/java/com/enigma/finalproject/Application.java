package com.enigma.finalproject;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	private final Logger logger = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
	
	@Override
	public void run(String...args) throws Exception{
		logger.trace("A trace message");
		logger.debug("A debug message");
		logger.info("An Info message");
		logger.warn("A Warn message");
		logger.error("An Error Message");
	}
}
