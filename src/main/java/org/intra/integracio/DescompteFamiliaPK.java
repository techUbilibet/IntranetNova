package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the descomptes_families database table.
 * 
 */
@Embeddable
public class DescompteFamiliaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_c_f", insertable=false, updatable=false)
	private Integer idCF;

	@Column(name="id_familia", insertable=false, updatable=false)
	private Integer idFamilia;

	public DescompteFamiliaPK() {
	}
	public Integer getIdCF() {
		return this.idCF;
	}
	public void setIdCF(Integer idCF) {
		this.idCF = idCF;
	}
	public Integer getIdFamilia() {
		return this.idFamilia;
	}
	public void setIdFamilia(Integer idFamilia) {
		this.idFamilia = idFamilia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DescompteFamiliaPK)) {
			return false;
		}
		DescompteFamiliaPK castOther = (DescompteFamiliaPK)other;
		return 
			this.idCF.equals(castOther.idCF)
			&& this.idFamilia.equals(castOther.idFamilia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCF.hashCode();
		hash = hash * prime + this.idFamilia.hashCode();
		
		return hash;
	}
}