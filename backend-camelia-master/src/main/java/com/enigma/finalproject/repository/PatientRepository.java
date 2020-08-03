package com.enigma.finalproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enigma.finalproject.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
	Page<Patient> findByIdContains(Integer id, Pageable paging);
	Page<Patient> findByNameContains(String name, Pageable paging);
//	Page<Patient>findByPatientIdContains(String id, Pageable paging);
//	Page<TrxDetailEntity> findByTrxHeaderEntityDockEntityDockNameStartingWith(String dockName, Pageable paging);
//	Page<TrxDetailEntity> findByTrxHeaderEntityShipEntityShipNameStartingWith(String shipName, Pageable paging);
//	Page<TrxDetailEntity> findByTrxHeaderEntityShipEntityCaptainNameStartingWith(String captainName, Pageable paging);
//	Page<TrxDetailEntity> findByTrxHeaderEntityId(Long id, Pageable paging);
//	Page<TrxDetailEntity> findByDateTrxBetween(Date start, Date end, Pageable paging);
//	Page<TrxDetailEntity> findByStatusEntityIdStatus(Long id, Pageable paging);
//	Page<TrxDetailEntity> findByFinish(Long id, Pageable paging);
}