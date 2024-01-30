package rva.ctrls;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Banka;
import rva.services.BankaService;

@CrossOrigin
@RestController
public class BankaController {

	@Autowired
	private BankaService bankaService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@GetMapping("/banka")
	public ResponseEntity<?> getAllBanka() {
		List<Banka> banke = bankaService.getAllBanka();
		if (banke.isEmpty()) {
			return new ResponseEntity<>("Banke - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(banke, HttpStatus.OK);
	}
	
	
	@GetMapping("/banka/{id}")
	public ResponseEntity<?> getArtikalById(@PathVariable("id")int bankaId){
		if(bankaService.existsById(bankaId)) {
			Optional<Banka> banka = bankaService.getBankaById(bankaId);
			return ResponseEntity.ok(banka);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artikal by id" + ' ' + bankaId + ' ' + "not found.");
	}
	
	
	@GetMapping("/banka/naziv/{naziv}")
	public ResponseEntity<?> getAllBankaByNaziv(@PathVariable("naziv")String naziv){
		List<Banka> banke = bankaService.getAllBankaByNaziv(naziv);
		if (banke.isEmpty()) {
			return new ResponseEntity<>("Banke by naziv - not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(banke, HttpStatus.OK);
	}
	
	
	@GetMapping("/banka/pocetakNaziva/{pocetakNaziva}")
	public ResponseEntity<?> getAllBankalByPocetakNaziva(@PathVariable("pocetakNaziva")String pocetakNaziva){
		List<Banka> banke = bankaService.getAllBankaByPocetakNaziva(pocetakNaziva);
		if (banke.isEmpty()) {
			return new ResponseEntity<>("Banke by naziv - not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(banke, HttpStatus.OK);
	}
	
	@PostMapping("/banka")
	public ResponseEntity<?> postBanka(@RequestBody Banka banka){
		if(bankaService.existsById(banka.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
			        .body("Banka with id " + banka.getId() + " already exists");
		}
		Banka savedBanka = bankaService.addBanka(banka);
		return new ResponseEntity<>(savedBanka, HttpStatus.OK);
	}
	
	@PutMapping("/banka/{id}")
	public ResponseEntity<?> putBanka(@PathVariable("id")int bankaId, 
			@RequestBody Banka banka){
		banka.setId(bankaId);
		if(!bankaService.existsById(banka.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			        .body("Banka with id " + banka.getId() + " not found");
		}		
		Banka updateBanka = bankaService.addBanka(banka);
		return new ResponseEntity<>(updateBanka, HttpStatus.OK);
	}
	
	@DeleteMapping("/banka/{id}")
	public ResponseEntity<?> deleteBanka(@PathVariable("id")int bankaId){
		/*if(!bankaService.existsById(bankaId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			        .body("Banka with id " + bankaId + " not found");
		}
		bankaService.deleteBankaById(bankaId);
		return new ResponseEntity<>("Banka with id " + bankaId + " has been deleted", HttpStatus.OK);
	*/
		
		
		if (bankaService.existsById(bankaId)) {
			
			if (bankaId == -100) {
				bankaService.deleteBankaById(bankaId);
				jdbcTemplate.execute("INSERT INTO \"banka\"(\"id\", \"naziv\", \"kontakt\", \"pib\") values (-100, 'Test', '123', '123')");
				return new ResponseEntity <Banka> (HttpStatus.OK);
			} else {
				bankaService.deleteBankaById(bankaId);
				return new ResponseEntity <Banka> (HttpStatus.OK);
			}
		} else {
			return new ResponseEntity <Banka> (HttpStatus.NOT_FOUND);
		}
		
	}
}
