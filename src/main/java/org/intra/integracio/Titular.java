package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the titulars database table.
 * 
 */
@Entity
@Table(name="titulars")
@NamedQuery(name="Titular.findAll", query="SELECT t FROM Titular t")
public class Titular implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TITULARS_ID_GENERATOR", sequenceName="titulars_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TITULARS_ID_GENERATOR")
	private Integer id;

	@Column(name="city")
	private String poblacio;

	@Column(name="country")
	private String pais;

	@Column(name="cp")
	private String codiPostal;

	private String duns;

	private String fax;

	@Column(name="first_name")
	private String nom;

	@Column(name="form_juridica")
	private Integer formaJuridica;

	@Column(name="lang")
	private String idioma;

	@Column(name="last_name")
	private String cognoms;

	private String mail;

	private String nif;

	@Column(name="organization")
	private String organitzacio;

	@Column(name="phone")
	private String telefon;

	@Column(name="registrant_class")
	private String registrantClass;

	@Column(name="registrant_date")
	private String registrantDate;

	@Column(name="registrant_number")
	private String registrantNumber;

	@Column(name="registrant_type")
	private String registrantType;

	@Column(name="state")
	private String provincia;

	@Column(name="street")
	private String direccio;

	@Column(name="street2")
	private String direccio2;

	private String uin;

	public Titular() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoblacio() {
		return this.poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodiPostal() {
		return this.codiPostal;
	}

	public void setCodiPostal(String codiPostal) {
		this.codiPostal = codiPostal;
	}

	public String getDuns() {
		return this.duns;
	}

	public void setDuns(String duns) {
		this.duns = duns;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getFormaJuridica() {
		return this.formaJuridica;
	}

	public void setFormaJuridica(Integer formaJuridica) {
		this.formaJuridica = formaJuridica;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getCognoms() {
		return this.cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getOrganitzacio() {
		return this.organitzacio;
	}

	public void setOrganitzacio(String organitzacio) {
		this.organitzacio = organitzacio;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getRegistrantClass() {
		return this.registrantClass;
	}

	public void setRegistrantClass(String registrantClass) {
		this.registrantClass = registrantClass;
	}

	public String getRegistrantDate() {
		return this.registrantDate;
	}

	public void setRegistrantDate(String registrantDate) {
		this.registrantDate = registrantDate;
	}

	public String getRegistrantNumber() {
		return this.registrantNumber;
	}

	public void setRegistrantNumber(String registrantNumber) {
		this.registrantNumber = registrantNumber;
	}

	public String getRegistrantType() {
		return this.registrantType;
	}

	public void setRegistrantType(String registrantType) {
		this.registrantType = registrantType;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDireccio() {
		return this.direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public String getDireccio2() {
		return this.direccio2;
	}

	public void setDireccio2(String direccio2) {
		this.direccio2 = direccio2;
	}

	public String getUin() {
		return this.uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

}