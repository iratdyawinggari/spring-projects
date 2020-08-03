package com.enigma.miniproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enigma.miniproject.entity.Harbour;
import com.enigma.miniproject.repository.HarbourRepository;

@Service
public class HarbourServiceImpl implements HarbourService {
	@Autowired
	HarbourRepository harbourRepository;

	@Override
	public Harbour createHarbour(Harbour harbour) {
		return harbourRepository.save(harbour);
	}

	@Override
	public Harbour updateHarbour(Harbour harbour) {
		return harbourRepository.save(harbour);
	}

	@Override
	public List<Harbour> getAllHarbour() {
		return harbourRepository.findAll();
	}

	@Override
	public Harbour findByHarbourCode(String harbourCode) {
		return harbourRepository.findHarbourByHarbourCode(harbourCode);
	}

	@Override
	public Integer deleteHarbour(String harbourCode) {
		try {
			Harbour harbour = harbourRepository.findHarbourByHarbourCode(harbourCode);
			harbourRepository.delete(harbour);
			return 1;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return 0;
		}
	}
}
