package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the contactes_fac_autoritzats database table.
 * 
 */
@Entity
@Table(name="contactes_fac_autoritzats")
@NamedQuery(name="ContacteFacAutoritzat.findAll", query="SELECT c FROM ContacteFacAutoritzat c")
public class ContacteFacAutoritzat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTACTES_FAC_AUTORITZATS_ID_GENERATOR", sequenceName="contactes_fac_autoritzats_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTACTES_FAC_AUTORITZATS_ID_GENERATOR")
	private Long id;

	private String email;

	private String nom;

	private String observacions;

	private String telefon;

	@Column(name="tipus_correu")
	private Integer tipusCorreu;

	//bi-directional many-to-one association to ContacteFac
	@ManyToOne
	@JoinColumn(name="id_c_f")
	private ContacteFac contacteFac;

	public ContacteFacAutoritzat() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getObservacions() {
		return this.observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Integer getTipusCorreu() {
		return this.tipusCorreu;
	}

	public void setTipusCorreu(Integer tipusCorreu) {
		this.tipusCorreu = tipusCorreu;
	}

	public ContacteFac getContacteFac() {
		return this.contacteFac;
	}

	public void setContacteFac(ContacteFac contacteFac) {
		this.contacteFac = contacteFac;
	}

}