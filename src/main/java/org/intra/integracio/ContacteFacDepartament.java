package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the contactes_fac_departaments database table.
 * 
 */
@Entity
@Table(name="contactes_fac_departaments")
@NamedQuery(name="ContacteFacDepartament.findAll", query="SELECT c FROM ContacteFacDepartament c")
public class ContacteFacDepartament implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ContacteFacDepartamentPK id;

	@Column(name="city")
	private String poblacio;

	@Column(name="codi_face")
	private String codiFace;

	@Column(name="country")
	private String pais;

	private String cp;

	private String direccio;

	private String nom;

	@Column(name="state")
	private String provincia;

	//bi-directional many-to-one association to ContacteFac
	@ManyToOne
	@JoinColumn(name="id_c_f", insertable=false, updatable=false)
	private ContacteFac contacteFac;

	public ContacteFacDepartament() {
	}

	public ContacteFacDepartamentPK getId() {
		return this.id;
	}

	public void setId(ContacteFacDepartamentPK id) {
		this.id = id;
	}

	public String getPoblacio() {
		return this.poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public String getCodiFace() {
		return this.codiFace;
	}

	public void setCodiFace(String codiFace) {
		this.codiFace = codiFace;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDireccio() {
		return this.direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public ContacteFac getContacteFac() {
		return this.contacteFac;
	}

	public void setContacteFac(ContacteFac contacteFac) {
		this.contacteFac = contacteFac;
	}

}