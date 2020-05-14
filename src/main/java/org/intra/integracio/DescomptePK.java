package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the descomptes database table.
 * 
 */
@Embeddable
public class DescomptePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_c_f", insertable=false, updatable=false)
	private Integer idCF;

	@Column(name="id_prod", insertable=false, updatable=false)
	private Integer idProd;

	public DescomptePK() {
	}
	public Integer getIdCF() {
		return this.idCF;
	}
	public void setIdCF(Integer idCF) {
		this.idCF = idCF;
	}
	public Integer getIdProd() {
		return this.idProd;
	}
	public void setIdProd(Integer idProd) {
		this.idProd = idProd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DescomptePK)) {
			return false;
		}
		DescomptePK castOther = (DescomptePK)other;
		return 
			this.idCF.equals(castOther.idCF)
			&& this.idProd.equals(castOther.idProd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCF.hashCode();
		hash = hash * prime + this.idProd.hashCode();
		
		return hash;
	}
}