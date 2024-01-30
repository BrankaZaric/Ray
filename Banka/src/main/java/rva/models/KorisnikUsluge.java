package rva.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name="korisnik_usluge")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class KorisnikUsluge implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="KORISNIK_USLUGE_ID_GENERATOR")
	@SequenceGenerator(name = "KORISNIK_USLUGE_ID_GENERATOR", sequenceName = "KORISNIK_USLUGE_SEQ", allocationSize = 1)
	private int id;
	
	private String ime;
	
	private String prezime;
	
	@Column(name="maticni_broj")
	private String maticniBroj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getMaticniBroj() {
		return maticniBroj;
	}

	public void setMaticniBroj(String maticniBroj) {
		this.maticniBroj = maticniBroj;
	}
	
}
