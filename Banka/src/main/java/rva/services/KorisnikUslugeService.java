package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.KorisnikUsluge;
import rva.repository.KorisnikUslugeRepository;

@Service
public class KorisnikUslugeService {

	@Autowired
	private KorisnikUslugeRepository korisnikUslugeRepository;
	
	public List<KorisnikUsluge> getAll(){
		return korisnikUslugeRepository.findAll();
	}
	
	public Optional<KorisnikUsluge> findById(int id) {
		return korisnikUslugeRepository.findById(id);
	}
	
	public List<KorisnikUsluge> findByImeContainingIgnoreCase(String ime) {
        return korisnikUslugeRepository.findByImeContainingIgnoreCase(ime);
    }
	
	public KorisnikUsluge addKorisnik(KorisnikUsluge korisnikUsluge) {
		return korisnikUslugeRepository.save(korisnikUsluge);
	}
	
	public boolean existsById(int id) {
		return findById(id).isPresent();
	}
	
	public void deleteKorisnikById(int id) {
		korisnikUslugeRepository.deleteById(id);
	}
}
