package com.enigma.finalproject.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enigma.finalproject.entity.Transaction;
import com.enigma.finalproject.entity.Transaction;
import com.enigma.finalproject.repository.TransactionRepository;
@Service
public class TransactionService {
	@Autowired
	FuzzyProcessService fuzzyProcessService;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public Transaction addTransaction(Transaction transaction) {
		fuzzyProcessService.process(transaction);
		return transactionRepository.save(transaction);
	}
	
	public List<Transaction> getAllTransactionsByUserId(Integer id) {
		return transactionRepository.findByPatientUserId(id);
	}
	
	
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public Transaction editTransaction(Transaction transaction) {
		fuzzyProcessService.process(transaction);
		return transactionRepository.save(transaction);
	}

	public Page<Transaction> showByPageSort(Integer page, Integer pageSize, String orderBy, String direction) {
		if (direction.equals("asc")) {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).ascending());
			return transactionRepository.findAll(paging);
		} else {
			Pageable paging = PageRequest.of(page, pageSize, Sort.by(chooseShowBy(orderBy)).descending());
			return transactionRepository.findAll(paging);
		}
	}

	private String chooseShowBy(String orderBy) {
		switch (orderBy) {
		case "id":
			return "Id";
		case "patient":
			return "Username";
		case "fullname":
			return "UserInfoFullname";
		case "birthDate":
			return "UserInfoBirthDate";
		case "address":
			return "UserInfoAddress";
		case "email":
			return "UserInfoEmail";
		case "noTelp":
			return "UserInfoNoTelp";
		case "gender":
			return "GenderGenderName";
		case "role":
			return "RoleName";
		default:
			return "Id";
		}
	}

	public Page<Transaction> showByPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return transactionRepository.findAll(paging);
	}

	
}