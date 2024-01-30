package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Banka;
import rva.repository.BankaRepository;

@Service
public class BankaService {

	@Autowired
	private BankaRepository bankaRepository;
	
	public List<Banka> getAllBanka(){
		return bankaRepository.findAll();
	}
	
	public Optional<Banka> getBankaById (int bankaId) { 
		return bankaRepository.findById(bankaId);
	}
	
	public boolean existsById (int id) {
		return getBankaById(id).isPresent();
	}
	
	public List<Banka> getAllBankaByNaziv(String naziv){
		return bankaRepository.findByNazivContainingIgnoreCase(naziv);
	}

	public List<Banka> getAllBankaByPocetakNaziva(String pocetakNaziva){
		return bankaRepository.getBankaByPocetakNaziva(pocetakNaziva.toLowerCase());
	}
	
	public Banka addBanka (Banka banka) {
		return bankaRepository.save(banka);
	}
	
	public void deleteBankaById(int id) {
		bankaRepository.deleteById(id);
	}
}
