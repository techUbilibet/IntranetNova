package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the plantilles_marks database table.
 * 
 */
@Entity
@Table(name="plantilles_marks")
@NamedQuery(name="PlantillaMark.findAll", query="SELECT p FROM PlantillaMark p")
public class PlantillaMark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_c_f")
	private Integer idCF;

	//uni-directional many-to-one association to Titular
	@ManyToOne
	@JoinColumn(name="id_titular")
	private Titular titular;

	public PlantillaMark() {
	}

	public Integer getIdCF() {
		return this.idCF;
	}

	public void setIdCF(Integer idCF) {
		this.idCF = idCF;
	}

	public Titular getTitular() {
		return this.titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

}