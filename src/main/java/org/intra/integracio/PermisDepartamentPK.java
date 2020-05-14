package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the permisos_departaments database table.
 * 
 */
@Embeddable
public class PermisDepartamentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_funcio", insertable=false, updatable=false)
	private Integer idFuncio;

	@Column(name="id_departament", insertable=false, updatable=false)
	private Integer idDepartament;

	public PermisDepartamentPK() {
	}
	public Integer getIdFuncio() {
		return this.idFuncio;
	}
	public void setIdFuncio(Integer idFuncio) {
		this.idFuncio = idFuncio;
	}
	public Integer getIdDepartament() {
		return this.idDepartament;
	}
	public void setIdDepartament(Integer idDepartament) {
		this.idDepartament = idDepartament;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PermisDepartamentPK)) {
			return false;
		}
		PermisDepartamentPK castOther = (PermisDepartamentPK)other;
		return 
			this.idFuncio.equals(castOther.idFuncio)
			&& this.idDepartament.equals(castOther.idDepartament);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idFuncio.hashCode();
		hash = hash * prime + this.idDepartament.hashCode();
		
		return hash;
	}
}