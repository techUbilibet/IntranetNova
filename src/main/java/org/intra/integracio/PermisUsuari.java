package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the permisos_usuaris database table.
 * 
 */
@Entity
@Table(name="permisos_usuaris")
@NamedQueries({
	@NamedQuery(name="PermisUsuari.findAll", query="SELECT p FROM PermisUsuari p"),
	@NamedQuery(name="PermisUsuari.findByUsuari", query="SELECT p FROM PermisUsuari p WHERE p.usuari=:usuari")
})
public class PermisUsuari implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PermisUsuariPK id;

	private Boolean contrasenya;

	private Integer nivell;
	
	//uni-directional many-to-one association to Funcio
	@ManyToOne
	@JoinColumn(name="id_funcio", insertable=false, updatable=false)
	private Funcio funcio;

	//bi-directional many-to-one association to Usuari
	@ManyToOne
	@JoinColumn(name="id_usuari", insertable=false, updatable=false)
	private Usuari usuari;

	public PermisUsuari() {
	}

	public PermisUsuariPK getId() {
		return this.id;
	}

	public void setId(PermisUsuariPK id) {
		this.id = id;
	}

	public Boolean getContrasenya() {
		return this.contrasenya;
	}

	public void setContrasenya(Boolean contrasenya) {
		this.contrasenya = contrasenya;
	}

	public Integer getNivell() {
		return this.nivell;
	}

	public void setNivell(Integer nivell) {
		this.nivell = nivell;
	}

	public Funcio getFuncio() {
		return this.funcio;
	}

	public void setFuncio(Funcio funcio) {
		this.funcio = funcio;
	}

	public Usuari getUsuari() {
		return this.usuari;
	}

	public void setUsuari(Usuari usuari) {
		this.usuari = usuari;
	}

}