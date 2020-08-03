package com.enigma.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.enigma.miniproject.entity.Harbour;

public interface HarbourRepository extends JpaRepository<Harbour, String>{
    @Query("select h from Harbour h where h.harbourCode=:harbourCode")
    Harbour findHarbourByHarbourCode(@Param("harbourCode")String harbourCode);

}
