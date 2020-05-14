package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name="country")
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String iso;

	private String continent;

	private String iso3;

	@Column(name="name_cat")
	private String nameCat;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="name_es")
	private String nameEs;

	private Integer numcode;

	@Column(name="phone")
	private String telefon;

	private Boolean ue;

	public Pais() {
	}

	public String getIso() {
		return this.iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getContinent() {
		return this.continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getIso3() {
		return this.iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getNameCat() {
		return this.nameCat;
	}

	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameEs() {
		return this.nameEs;
	}

	public void setNameEs(String nameEs) {
		this.nameEs = nameEs;
	}

	public Integer getNumcode() {
		return this.numcode;
	}

	public void setNumcode(Integer numcode) {
		this.numcode = numcode;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Boolean getUe() {
		return this.ue;
	}

	public void setUe(Boolean ue) {
		this.ue = ue;
	}

}