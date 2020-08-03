package com.enigma.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigma.finalproject.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
