package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sectors database table.
 * 
 */
@Entity
@Table(name="sectors")
@NamedQuery(name="Sector.findAll", query="SELECT s FROM Sector s")
public class Sector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SECTORS_ID_GENERATOR", sequenceName="sectors_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SECTORS_ID_GENERATOR")
	private Integer id;

	private Boolean general;

	private String nom;

	public Sector() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getGeneral() {
		return this.general;
	}

	public void setGeneral(Boolean general) {
		this.general = general;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}