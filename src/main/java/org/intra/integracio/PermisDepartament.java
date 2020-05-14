package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the permisos_departaments database table.
 * 
 */
@Entity
@Table(name="permisos_departaments")
@NamedQuery(name="PermisDepartament.findAll", query="SELECT p FROM PermisDepartament p")
public class PermisDepartament implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PermisDepartamentPK id;

	private Integer nivell;

	//bi-directional many-to-one association to Departament
	@ManyToOne
	@JoinColumn(name="id_departament", insertable=false, updatable=false)
	private Departament departament;

	//uni-directional many-to-one association to Funcio
	@ManyToOne
	@JoinColumn(name="id_funcio", insertable=false, updatable=false)
	private Funcio funcio;

	public PermisDepartament() {
	}

	public PermisDepartamentPK getId() {
		return this.id;
	}

	public void setId(PermisDepartamentPK id) {
		this.id = id;
	}

	public Integer getNivell() {
		return this.nivell;
	}

	public void setNivell(Integer nivell) {
		this.nivell = nivell;
	}

	public Departament getDepartament() {
		return this.departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public Funcio getFuncio() {
		return this.funcio;
	}

	public void setFuncio(Funcio funcio) {
		this.funcio = funcio;
	}

}