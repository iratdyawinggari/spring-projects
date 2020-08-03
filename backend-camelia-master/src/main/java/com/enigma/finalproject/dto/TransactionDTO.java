package com.enigma.finalproject.dto;

public class TransactionDTO {
	private Integer id;
	private String name;
	private Integer age;
	private Integer userId;
	private Integer genderId;
	private Integer diseaseId;
	private String inputPointCombination;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Integer getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(Integer diseaseId) {
		this.diseaseId = diseaseId;
	}

	public String getInputPointCombination() {
		return inputPointCombination;
	}

	public void setInputPointCombination(String inputPointCombination) {
		this.inputPointCombination = inputPointCombination;
	}

}