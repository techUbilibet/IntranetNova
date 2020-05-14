package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dns database table.
 * 
 */
@Entity
@NamedQuery(name="Dns.findAll", query="SELECT d FROM Dns d")
public class Dns implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DNS_ID_GENERATOR", sequenceName="dns_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DNS_ID_GENERATOR")
	private Integer id;

	private String ip;

	private String nom;

	private Boolean prioritari;

	public Dns() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getPrioritari() {
		return this.prioritari;
	}

	public void setPrioritari(Boolean prioritari) {
		this.prioritari = prioritari;
	}

}