package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the contactes_fac_departaments database table.
 * 
 */
@Embeddable
public class ContacteFacDepartamentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_c_f", insertable=false, updatable=false)
	private Long idCF;

	private Integer tipus;

	public ContacteFacDepartamentPK() {
	}
	public Long getIdCF() {
		return this.idCF;
	}
	public void setIdCF(Long idCF) {
		this.idCF = idCF;
	}
	public Integer getTipus() {
		return this.tipus;
	}
	public void setTipus(Integer tipus) {
		this.tipus = tipus;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ContacteFacDepartamentPK)) {
			return false;
		}
		ContacteFacDepartamentPK castOther = (ContacteFacDepartamentPK)other;
		return 
			this.idCF.equals(castOther.idCF)
			&& this.tipus.equals(castOther.tipus);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCF.hashCode();
		hash = hash * prime + this.tipus.hashCode();
		
		return hash;
	}
}