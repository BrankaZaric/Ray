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
import rva.models.KorisnikUsluge;
import rva.services.KorisnikUslugeService;

@CrossOrigin
@RestController
public class KorisnikUslugeController {

	@Autowired
	private KorisnikUslugeService korisnikUslugeService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/korisnikusluge")
    public ResponseEntity<List<KorisnikUsluge>> getAll() {
        List<KorisnikUsluge> korisnici = korisnikUslugeService.getAll();
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }

    @GetMapping("/korisnikusluge/{id}")
    public ResponseEntity<KorisnikUsluge> getOne(@PathVariable("id") int id) {
        if (korisnikUslugeService.findById(id).isPresent()) {
            Optional<KorisnikUsluge> korisnik = korisnikUslugeService.findById(id);
            return new ResponseEntity<>(korisnik.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/korisnikusluge/ime/{ime}")
    public ResponseEntity<?> getByIme(@PathVariable("ime") String ime) {
        List<KorisnikUsluge> korisnik = korisnikUslugeService.findByImeContainingIgnoreCase(ime);
        if (korisnik.isEmpty()) {
			return new ResponseEntity<>("Korisnik with this name was not found.", HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }
    
    @PostMapping("/korisnikusluge")
    public ResponseEntity<?> addKorisnik(@RequestBody KorisnikUsluge korisnik) {
    	if(korisnikUslugeService.existsById(korisnik.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
			        .body("Banka with id " + korisnik.getId() + " already exists");
		}
		KorisnikUsluge savedKorisnik = korisnikUslugeService.addKorisnik(korisnik);
		return new ResponseEntity<>(savedKorisnik, HttpStatus.OK);
    }

    
    @PutMapping(value = "/korisnikusluge/{id}")
    public ResponseEntity<KorisnikUsluge> updateKorisnik(@RequestBody KorisnikUsluge korisnik, @PathVariable("id") int id) {
        if (korisnikUslugeService.existsById(id)) {
        	korisnik.setId(id);
            KorisnikUsluge savedKorisnik = korisnikUslugeService.addKorisnik(korisnik);
            return ResponseEntity.ok().body(savedKorisnik);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/korisnikusluge/{id}")
    public ResponseEntity<?> deleteKorisnik(@PathVariable("id") int korisnikId) {
    	/*if(korisnikUslugeService.existsById(id)) {
    		korisnikUslugeService.deleteById(id);
    		return ResponseEntity.ok("Resource with an id:" + id + " has been deleted");
    	}else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested resource has not been found");
    	}*/
    	
    	if (korisnikUslugeService.existsById(korisnikId)) {
			
			if (korisnikId == -100) {
				korisnikUslugeService.deleteKorisnikById(korisnikId);
				jdbcTemplate.execute("INSERT INTO \"korisnik_usluge\"(\"id\", \"ime\", \"prezime\", \"maticni_broj\") values (-100, 'Test', 'Test', '123')");
				return new ResponseEntity <Banka> (HttpStatus.OK);
			} else {
				korisnikUslugeService.deleteKorisnikById(korisnikId);
				return new ResponseEntity <Banka> (HttpStatus.OK);
			}
		} else {
			return new ResponseEntity <Banka> (HttpStatus.NOT_FOUND);
		}
    	
    }
}
