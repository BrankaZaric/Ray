package rva.models;

import java.io.Serializable;

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
public class Filijala implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FILIJALA_ID_GENERATOR")
	@SequenceGenerator(name = "FILIJALA_ID_GENERATOR", sequenceName = "FILIJALA_SEQ", allocationSize = 1)
	private int id;
	
	private String adresa;
	
	@Column(name="broj_pultova")
	private int brojPultova;
	
	@Column(name="poseduje_sef")
	private boolean posedujeSef;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "banka")
	private Banka banka;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getBrojPultova() {
		return brojPultova;
	}

	public void setBrojPultova(int brojPultova) {
		this.brojPultova = brojPultova;
	}

	public boolean isPosedujeSef() {
		return posedujeSef;
	}

	public void setPosedujeSef(boolean posedujeSef) {
		this.posedujeSef = posedujeSef;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}
	
	
}
