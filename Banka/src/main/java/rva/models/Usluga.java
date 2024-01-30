package rva.models;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Usluga implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USLUGA_ID_GENERATOR")
	@SequenceGenerator(name = "USLUGA_ID_GENERATOR", sequenceName = "USLUGA_SEQ", allocationSize = 1)
	private int id;
	
	private String naziv;
	
	@Column(name="opis_usluge")
	private String opisUsluge;
	
	@Column(name="datum_ugovora")
	private Date datumUgovora;
	
	private int provizija;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="filijala")
	private Filijala filijala;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="korisnik_usluge")
	private KorisnikUsluge korisnik;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpisUsluge() {
		return opisUsluge;
	}

	public void setOpisUsluge(String opisUsluge) {
		this.opisUsluge = opisUsluge;
	}

	public Date getDatumUgovora() {
		return datumUgovora;
	}

	public void setDatumUgovora(Date datumUgovora) {
		this.datumUgovora = datumUgovora;
	}

	public int getProvizija() {
		return provizija;
	}

	public void setProvizija(int provizija) {
		this.provizija = provizija;
	}

	public Filijala getFilijala() {
		return filijala;
	}

	public void setFilijala(Filijala filijala) {
		this.filijala = filijala;
	}

	public KorisnikUsluge getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikUsluge korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
