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
import rva.services.FilijalaService;

@CrossOrigin
@RestController
public class FilijalaController {

	@Autowired
	private FilijalaService filijalaService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/filijala")
	public ResponseEntity<List<Filijala>> getAll() {
		List<Filijala> filijale = filijalaService.getAll();
		return new ResponseEntity<> (filijale, HttpStatus.OK);
	}
	
	@GetMapping("/filijala/{id}")
	public ResponseEntity <Filijala> getOne(@PathVariable("id")int id) {
		if (filijalaService.existsById(id)) {
			Optional <Filijala> filijala = filijalaService.findById(id);
			return new ResponseEntity<> (filijala.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/posedujeSefa")
	public ResponseEntity<List<Filijala>> posedujeSefa() {
		List<Filijala> filijale = filijalaService.findByPosedujeSefTrue();
		return new ResponseEntity<> (filijale, HttpStatus.OK);
	}
	
	@PostMapping("/filijala")
    public ResponseEntity<?> addFilijala(@RequestBody Filijala filijala) {
    	if(!filijalaService.existsById(filijala.getId())) {
    		Filijala savedFilijala = filijalaService.save(filijala);
            return ResponseEntity.status(HttpStatus.OK).body(savedFilijala);
    	}else {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource with the same ID already exists");
    	}       
    }

	@PutMapping(value = "/filijala/{id}")
	public ResponseEntity<Filijala> updatePorudzbina(@RequestBody Filijala filijala,
			@PathVariable("id") int id) {
		if (filijalaService.existsById(id)) {
			filijala.setId(id);
			Filijala savedFilijala = filijalaService.save(filijala);
			return ResponseEntity.ok().body(savedFilijala);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/filijala/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int filijalaId) {
    	/*if(filijalaService.existsById(id)) {
    		filijalaService.deleteFilijalaById(id);
    		return ResponseEntity.ok("Resource with an id:" + id + " has been deleted");
    	}else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested resource has not been found");
    	}*/
		
		if (filijalaService.existsById(filijalaId)) {
			
			if (filijalaId == -100) {
				filijalaService.deleteFilijalaById(filijalaId);
				jdbcTemplate.execute("INSERT INTO \"filijala\"(\"id\", \"adresa\", \"broj_pultova\", \"poseduje_sef\", \"banka\") values (-100, 'Test', '123', 'true', '1')");
				return new ResponseEntity <Filijala> (HttpStatus.OK);
			} else {
				filijalaService.deleteFilijalaById(filijalaId);
				return new ResponseEntity <Filijala> (HttpStatus.OK);
			}
		} else {
			return new ResponseEntity <Filijala> (HttpStatus.NOT_FOUND);
		}
    }
}
