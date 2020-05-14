package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the plantilles_certificats database table.
 * 
 */
@Entity
@Table(name="plantilles_certificats")
@NamedQuery(name="PlantillaCertificat.findAll", query="SELECT p FROM PlantillaCertificat p")
public class PlantillaCertificat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_c_f")
	private Integer idCF;

	@Column(name="id_c_a")
	private Integer idcontacteAdmin;

	@Column(name="id_c_t")
	private Integer idContacteTech;

	@Column(name="id_tipus")
	private Integer idTipus;

	@Column(name="years")
	private Integer anys;

	//uni-directional many-to-one association to Titular
	@ManyToOne
	@JoinColumn(name="id_titular")
	private Titular titular;

	public PlantillaCertificat() {
	}

	public Integer getIdCF() {
		return this.idCF;
	}

	public void setIdCF(Integer idCF) {
		this.idCF = idCF;
	}

	public Integer getIdcontacteAdmin() {
		return this.idcontacteAdmin;
	}

	public void setIdcontacteAdmin(Integer idcontacteAdmin) {
		this.idcontacteAdmin = idcontacteAdmin;
	}

	public Integer getIdContacteTech() {
		return this.idContacteTech;
	}

	public void setIdContacteTech(Integer idContacteTech) {
		this.idContacteTech = idContacteTech;
	}

	public Integer getIdTipus() {
		return this.idTipus;
	}

	public void setIdTipus(Integer idTipus) {
		this.idTipus = idTipus;
	}

	public Integer getAnys() {
		return this.anys;
	}

	public void setAnys(Integer anys) {
		this.anys = anys;
	}

	public Titular getTitular() {
		return this.titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

}