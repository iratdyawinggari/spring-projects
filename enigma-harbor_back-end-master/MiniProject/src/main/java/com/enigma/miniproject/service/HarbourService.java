package com.enigma.miniproject.service;

import java.util.List;

import com.enigma.miniproject.entity.Harbour;

public interface HarbourService {
	Harbour createHarbour(Harbour harbour);
	Harbour updateHarbour(Harbour harbour);
    List<Harbour> getAllHarbour();
    Harbour findByHarbourCode (String harbourCode);
    Integer deleteHarbour(String harbourCode);
}
