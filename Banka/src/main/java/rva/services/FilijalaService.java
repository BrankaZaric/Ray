package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Filijala;
import rva.repository.FilijalaRepository;

@Service
public class FilijalaService {

	@Autowired
	private FilijalaRepository filijalaRepository;
	
	public List<Filijala> getAll(){
		return filijalaRepository.findAll();
	}
	
	public Optional <Filijala> findById (int id) {
		return filijalaRepository.findById(id);
	}
	
	public List <Filijala> findByPosedujeSefTrue(){
		return filijalaRepository.findByPosedujeSefTrue();
	}
	
	public Filijala save (Filijala filijala) {
		return filijalaRepository.save(filijala);
	}
	
	public boolean existsById(int id) {
		if (filijalaRepository.findById(id).isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteFilijalaById(int id) {
		filijalaRepository.deleteById(id);
	}
}
