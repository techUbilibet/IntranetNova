package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the divises database table.
 * 
 */
@Entity
@Table(name="divises")
@NamedQuery(name="Divisa.findAll", query="SELECT d FROM Divisa d")
public class Divisa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codi;

	private Boolean activa;

	private Integer decimals;

	private String nom;

	private Integer numero;

	private String zona;

	public Divisa() {
	}

	public String getCodi() {
		return this.codi;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public Boolean getActiva() {
		return this.activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public Integer getDecimals() {
		return this.decimals;
	}

	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}