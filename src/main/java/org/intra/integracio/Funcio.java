package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the funcions database table.
 * 
 */
@Entity
@Table(name="funcions")
@NamedQuery(name="Funcio.findAll", query="SELECT f FROM Funcio f")
public class Funcio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FUNCIONS_ID_GENERATOR", sequenceName="funcions_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FUNCIONS_ID_GENERATOR")
	private Integer id;

	private String comentari;

	private String descripcio;

	public Funcio() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentari() {
		return this.comentari;
	}

	public void setComentari(String comentari) {
		this.comentari = comentari;
	}

	public String getDescripcio() {
		return this.descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

}