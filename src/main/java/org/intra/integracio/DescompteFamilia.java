package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the descomptes_families database table.
 * 
 */
@Entity
@Table(name="descomptes_families")
@NamedQuery(name="DescompteFamilia.findAll", query="SELECT d FROM DescompteFamilia d")
public class DescompteFamilia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DescompteFamiliaPK id;

	private float descompte;

	//bi-directional many-to-one association to ContacteFac
	@ManyToOne
	@JoinColumn(name="id_c_f", insertable=false, updatable=false)
	private ContacteFac contacteFac;

	//uni-directional many-to-one association to Familia
	@ManyToOne
	@JoinColumn(name="id_familia", insertable=false, updatable=false)
	private Familia familia;

	public DescompteFamilia() {
	}

	public DescompteFamiliaPK getId() {
		return this.id;
	}

	public void setId(DescompteFamiliaPK id) {
		this.id = id;
	}

	public float getDescompte() {
		return this.descompte;
	}

	public void setDescompte(float descompte) {
		this.descompte = descompte;
	}

	public ContacteFac getContacteFac() {
		return this.contacteFac;
	}

	public void setContacteFac(ContacteFac contacteFac) {
		this.contacteFac = contacteFac;
	}

	public Familia getFamilia() {
		return this.familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

}