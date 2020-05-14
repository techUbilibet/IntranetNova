package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the permisos_usuaris database table.
 * 
 */
@Embeddable
public class PermisUsuariPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_funcio", insertable=false, updatable=false)
	private Integer idFuncio;

	@Column(name="id_usuari", insertable=false, updatable=false)
	private Integer idUsuari;

	public PermisUsuariPK() {
	}
	public Integer getIdFuncio() {
		return this.idFuncio;
	}
	public void setIdFuncio(Integer idFuncio) {
		this.idFuncio = idFuncio;
	}
	public Integer getIdUsuari() {
		return this.idUsuari;
	}
	public void setIdUsuari(Integer idUsuari) {
		this.idUsuari = idUsuari;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PermisUsuariPK)) {
			return false;
		}
		PermisUsuariPK castOther = (PermisUsuariPK)other;
		return 
			this.idFuncio.equals(castOther.idFuncio)
			&& this.idUsuari.equals(castOther.idUsuari);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idFuncio.hashCode();
		hash = hash * prime + this.idUsuari.hashCode();
		
		return hash;
	}
}