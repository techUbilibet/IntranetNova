package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grups database table.
 * 
 */
@Entity
@Table(name="grups")
@NamedQuery(name="Grup.findAll", query="SELECT g FROM Grup g")
public class Grup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GRUPS_ID_GENERATOR", sequenceName="grups_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRUPS_ID_GENERATOR")
	private Integer id;

	@Column(name="cp")
	private String codiPostal;

	private String direccio;

	private String fax;

	@Column(name="lang")
	private String idioma;

	private String nif;

	private String nom;

	private String pais;

	private String poblacio;

	@Column(name="state")
	private String provincia;

	private String telefon;

	//bi-directional many-to-one association to ContacteFac
	@OneToMany(mappedBy="grup")
	private List<ContacteFac> contacteFac;

	//uni-directional many-to-one association to ContacteFac
	@ManyToOne
	@JoinColumn(name="cf_principal")
	private ContacteFac contacteFacPrincipal;

	//uni-directional many-to-many association to Usuari
	@ManyToMany
	@JoinTable(
		name="usuaris_grups"
		, joinColumns={
			@JoinColumn(name="id_grup")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_usuari")
			}
		)
	private List<Usuari> usuaris;

	public Grup() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodiPostal() {
		return this.codiPostal;
	}

	public void setCodiPostal(String codiPostal) {
		this.codiPostal = codiPostal;
	}

	public String getDireccio() {
		return this.direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPoblacio() {
		return this.poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<ContacteFac> getContacteFac() {
		return this.contacteFac;
	}

	public void setContacteFac(List<ContacteFac> contacteFac) {
		this.contacteFac = contacteFac;
	}

	public ContacteFac addContacteFac(ContacteFac contacteFac) {
		getContacteFac().add(contacteFac);
		contacteFac.setGrup(this);

		return contacteFac;
	}

	public ContacteFac removeContacteFac(ContacteFac contacteFac) {
		getContacteFac().remove(contacteFac);
		contacteFac.setGrup(null);

		return contacteFac;
	}

	public ContacteFac getContacteFacPrincipal() {
		return this.contacteFacPrincipal;
	}

	public void setContacteFacPrincipal(ContacteFac contacteFacPrincipal) {
		this.contacteFacPrincipal = contacteFacPrincipal;
	}

	public List<Usuari> getUsuaris() {
		return this.usuaris;
	}

	public void setUsuaris(List<Usuari> usuaris) {
		this.usuaris = usuaris;
	}

}