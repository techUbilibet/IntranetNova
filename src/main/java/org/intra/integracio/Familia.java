package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the families database table.
 * 
 */
@Entity
@Table(name="families")
@NamedQuery(name="Familia.findAll", query="SELECT f FROM Familia f")
public class Familia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FAMILIES_ID_GENERATOR", sequenceName="families_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAMILIES_ID_GENERATOR")
	private Integer id;

	private Boolean editable;

	private Integer nleft;

	private Integer nlevel;

	private String nom;

	private Integer nright;

	@Column(name="parent_id")
	private Integer parentId;

	//bi-directional many-to-one association to Tarifa
	@OneToMany(mappedBy="family")
	private List<Tarifa> tarifes;

	public Familia() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEditable() {
		return this.editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Integer getNleft() {
		return this.nleft;
	}

	public void setNleft(Integer nleft) {
		this.nleft = nleft;
	}

	public Integer getNlevel() {
		return this.nlevel;
	}

	public void setNlevel(Integer nlevel) {
		this.nlevel = nlevel;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getNright() {
		return this.nright;
	}

	public void setNright(Integer nright) {
		this.nright = nright;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Tarifa> getTarifes() {
		return this.tarifes;
	}

	public void setTarifes(List<Tarifa> tarifes) {
		this.tarifes = tarifes;
	}

	public Tarifa addTarifa(Tarifa tarifa) {
		getTarifes().add(tarifa);
		tarifa.setFamily(this);

		return tarifa;
	}

	public Tarifa removeTarifa(Tarifa tarifa) {
		getTarifes().remove(tarifa);
		tarifa.setFamily(null);

		return tarifa;
	}

}