package com.enigma.finalproject.dto;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Disease;
import com.enigma.finalproject.entity.Gender;
import com.enigma.finalproject.entity.Patient;
import com.enigma.finalproject.entity.Transaction;
import com.enigma.finalproject.entity.User;
import com.enigma.finalproject.service.DiseaseService;
import com.enigma.finalproject.service.GenderService;
import com.enigma.finalproject.service.PatientService;
import com.enigma.finalproject.service.TransactionService;
import com.enigma.finalproject.service.UserService;




@Service
public class TransactionDTOService {
	

	@Autowired
	PatientService patientService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	GenderService genderService;
	
	@Autowired 
	TransactionService transactionService;

	@Autowired
	DiseaseService diseaseService;
	
//	id int auto_increment,
//  patient_id int,
//  disease_id INT,
//  input_point_combination varchar(300),
//  output_id INT,

	
	public Transaction createTransaction(TransactionDTO transactionDTO ) {
		Patient patient = new Patient();
		User user = userService.getUserById(transactionDTO.getUserId());
		Gender gender = genderService.getGenderById(transactionDTO.getGenderId());
		patient.setName(transactionDTO.getName());
		patient.setAge(transactionDTO.getAge());
		patient.setUser(user);
		patient.setGender(gender);
		Patient newPatient = patientService.addPatient(patient);
		System.out.println(newPatient.getId());
		Transaction transaction = new Transaction();
		Disease disease = diseaseService.getDiseaseById(transactionDTO.getDiseaseId());
		transaction.setDisease(disease);
		transaction.setInputPointCombination(transactionDTO.getInputPointCombination());
		transaction.setPatient(newPatient);
		transaction.setTransactionDate(new Date());
		return transactionService.addTransaction(transaction);
	} 
}