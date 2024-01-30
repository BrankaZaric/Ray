package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Banka;
import rva.models.Filijala;
import rva.models.KorisnikUsluge;
import rva.models.Usluga;
import rva.repository.UslugaRepository;

@Service
public class UslugaService {

	@Autowired
	private UslugaRepository uslugaRepository;

	public List<Usluga> getAllUsluga() {
		return uslugaRepository.findAll();
	}

	public Optional<Usluga> findUslugaById(int id) {
		return uslugaRepository.findById(id);
	}

	public Usluga addUsluga(Usluga stavkaPorudzbine) {
		return uslugaRepository.save(stavkaPorudzbine);
	}
	

	public List<Usluga> getAllUslugaByNaziv(String naziv){
		return uslugaRepository.findByNazivContainingIgnoreCase(naziv);
	}

	public boolean existsById(int id) {
		return findUslugaById(id).isPresent();
	}

	public void deleteUslugaById(int id) {
		uslugaRepository.deleteById(id);
	}

	
	public List<Usluga> findByFilijala(Filijala filijala) {
		return uslugaRepository.findByFilijala(filijala);
	}
	
	public List<Usluga> findByKorisnik(KorisnikUsluge korisnik) {
		return uslugaRepository.findByKorisnik(korisnik);
	}

	
}
