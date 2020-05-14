package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the descomptes database table.
 * 
 */
@Entity
@Table(name="descomptes")
@NamedQuery(name="Descompte.findAll", query="SELECT d FROM Descompte d")
public class Descompte implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DescomptePK id;

	private float descompte;

	//bi-directional many-to-one association to ContacteFac
	@ManyToOne
	@JoinColumn(name="id_c_f", insertable=false, updatable=false)
	private ContacteFac contacteFac;

	//uni-directional many-to-one association to Tarifa
	@ManyToOne
	@JoinColumn(name="id_prod", insertable=false, updatable=false)
	private Tarifa tarifa;

	public Descompte() {
	}

	public DescomptePK getId() {
		return this.id;
	}

	public void setId(DescomptePK id) {
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

	public Tarifa getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

}