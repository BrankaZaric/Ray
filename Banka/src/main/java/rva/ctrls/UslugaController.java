package rva.ctrls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Banka;
import rva.models.Filijala;
import rva.models.KorisnikUsluge;
import rva.models.Usluga;
import rva.services.FilijalaService;
import rva.services.KorisnikUslugeService;
import rva.services.UslugaService;

@CrossOrigin
@RestController
public class UslugaController {
	
	@Autowired
	private UslugaService uslugaService;
	
	@Autowired 
	private FilijalaService filijalaService;
	
	@Autowired
	private KorisnikUslugeService korisnikService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@GetMapping("/usluga")
	public ResponseEntity<?> getAllUsluga(){
		List<Usluga> usluge = uslugaService.getAllUsluga();
		if(usluge.isEmpty()) {
			return new ResponseEntity<>("Usluge - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usluge, HttpStatus.OK);
	}
	
	@GetMapping("/usluga/{id}")
	public ResponseEntity<?> getUslugaById(@PathVariable("id")int uslugaId){
		if(uslugaService.existsById(uslugaId)) {
			Optional<Usluga> usluga= uslugaService.findUslugaById(uslugaId);
			return ResponseEntity.ok(usluga);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usluga by id " + uslugaId + "not found");
	}
	
	@GetMapping("/usluga/naziv/{naziv}")
	public ResponseEntity<?> getAllUslugaByNaziv(@PathVariable("naziv")String naziv){
		List<Usluga> usluge = uslugaService.getAllUslugaByNaziv(naziv);
		if(usluge.isEmpty()) {
			return new ResponseEntity<>("Usluge - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usluge, HttpStatus.OK);
	}
	
	 @GetMapping("uslugaFilijala/{id}")
	 public ResponseEntity<?> getAllForFilijala(@PathVariable("id") int id) {
	        Optional<Filijala> filijala = filijalaService.findById(id);
	        if (filijala.isPresent()) {
	            List<Usluga> uslugaFilijala = uslugaService
	            		.findByFilijala(filijala.get());            
	            if(uslugaFilijala.isEmpty()) {
	            	return new ResponseEntity<>("Usluge za odgovarajucu filijalu nisu pronadjene",
	            			HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<>(uslugaFilijala, HttpStatus.OK);
	        }
	        return new ResponseEntity<>("Filijala nije pronadjena", HttpStatus.NOT_FOUND);
	 }
	 
	 @GetMapping("uslugaKorisnik/{id}")
	 public ResponseEntity<?> getAllForKorisnik(@PathVariable("id") int id) {
	        Optional<KorisnikUsluge> korisnik = korisnikService.findById(id);
	        if (korisnik.isPresent()) {
	            List<Usluga> uslugaKorisnik = uslugaService
	            		.findByKorisnik(korisnik.get());            
	            if(uslugaKorisnik.isEmpty()) {
	            	return new ResponseEntity<>("Usluge za odgovarajucueg korisnika nisu pronadjene",
	            			HttpStatus.NOT_FOUND);
	            }
	            return new ResponseEntity<>(uslugaKorisnik, HttpStatus.OK);
	        }
	        return new ResponseEntity<>("Korisnik nije pronadjen", HttpStatus.NOT_FOUND);
	 }
	 
	 @PostMapping("/usluga")
		public ResponseEntity<?> addUsluga(@RequestBody Usluga usluga){
			if(!filijalaService.existsById(usluga.getFilijala().getId()))
			{
				return new ResponseEntity<>("Filijala with id " + usluga.getFilijala().getId() + " not found",HttpStatus.NOT_FOUND);
			}
			else {
			if(uslugaService.existsById(usluga.getId())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Usluga with id " + usluga.getId() + " already exists");
			}
			else {
				
			Usluga savedUsluga = uslugaService.addUsluga(usluga);
			return new ResponseEntity<>(savedUsluga,HttpStatus.OK);
			}
			}
		}
	 
	 	@PutMapping("/usluga/{id}")
		public ResponseEntity<?> putUsluga(@RequestBody Usluga usluga, @PathVariable("id") int id){
			if(!filijalaService.existsById(usluga.getFilijala().getId()))
			{
				return new ResponseEntity<>("Filijala with id " + usluga.getFilijala().getId() + " not found",HttpStatus.NOT_FOUND);
			}
			else {
			if(!uslugaService.existsById(usluga.getId())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Usluga with id " + usluga.getId() + " not found");
			}
			else {
				usluga.setId(id);
				Usluga updatedUsluga = uslugaService.addUsluga(usluga);
				return new ResponseEntity<>(updatedUsluga,HttpStatus.OK);
			}
			}
		}
	 
	 @DeleteMapping("/usluga/{id}")
		public ResponseEntity<?> deleteUsluga(@PathVariable("id")int uslugaId){
			
			if (uslugaService.existsById(uslugaId)) {
				
				if (uslugaId == -100) {
					uslugaService.deleteUslugaById(uslugaId);
					jdbcTemplate.execute("INSERT INTO \"usluga\"(\"id\", \"naziv\", \"opis_usluge\", \"datum_ugovora\", \"provizija\", \"filijala\", \"korisnik_usluge\") values ('-100', 'Test', 'Test' , to_date('01.03.2023.', 'dd.mm.yyyy.'), 23, 1, 1)");
					return new ResponseEntity <Usluga> (HttpStatus.OK);
				} else {
					uslugaService.deleteUslugaById(uslugaId);
					return new ResponseEntity <Usluga> (HttpStatus.OK);
				}
			} else {
				return new ResponseEntity <Usluga> (HttpStatus.NOT_FOUND);
			}
			
		}
	}
