package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the plantilles_dominis database table.
 * 
 */
@Entity
@Table(name="plantilles_dominis")
@NamedQuery(name="PlantillaDomini.findAll", query="SELECT p FROM PlantillaDomini p")
public class PlantillaDomini implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_c_f")
	private Integer idCF;

	@Column(name="dns_type")
	private Integer dnsType;

	@Column(name="id_c_a")
	private Integer idContacteAdmin;

	@Column(name="id_c_t")
	private Integer idContacteTecnic;

	private Boolean redirec;

	//uni-directional many-to-one association to Dns
	@ManyToOne
	@JoinColumn(name="id_dns1")
	private Dns dns1;

	//uni-directional many-to-one association to Dns
	@ManyToOne
	@JoinColumn(name="id_dns2")
	private Dns dns2;

	//uni-directional many-to-one association to Dns
	@ManyToOne
	@JoinColumn(name="id_dns3")
	private Dns dns3;

	//uni-directional many-to-one association to Dns
	@ManyToOne
	@JoinColumn(name="id_dns4")
	private Dns dns4;

	//uni-directional many-to-one association to Dns
	@ManyToOne
	@JoinColumn(name="id_dns5")
	private Dns dns5;

	//uni-directional many-to-one association to Dns
	@ManyToOne
	@JoinColumn(name="id_dns6")
	private Dns dns6;

	//uni-directional many-to-one association to Titular
	@ManyToOne
	@JoinColumn(name="id_titular")
	private Titular titular;

	public PlantillaDomini() {
	}

	public Integer getIdCF() {
		return this.idCF;
	}

	public void setIdCF(Integer idCF) {
		this.idCF = idCF;
	}

	public Integer getDnsType() {
		return this.dnsType;
	}

	public void setDnsType(Integer dnsType) {
		this.dnsType = dnsType;
	}

	public Integer getIdContacteAdmin() {
		return this.idContacteAdmin;
	}

	public void setIdContacteAdmin(Integer idContacteAdmin) {
		this.idContacteAdmin = idContacteAdmin;
	}

	public Integer getIdContacteTecnic() {
		return this.idContacteTecnic;
	}

	public void setIdContacteTecnic(Integer idContacteTecnic) {
		this.idContacteTecnic = idContacteTecnic;
	}

	public Boolean getRedirec() {
		return this.redirec;
	}

	public void setRedirec(Boolean redirec) {
		this.redirec = redirec;
	}

	public Dns getDns1() {
		return this.dns1;
	}

	public void setDns1(Dns dns1) {
		this.dns1 = dns1;
	}

	public Dns getDns2() {
		return this.dns2;
	}

	public void setDns2(Dns dns2) {
		this.dns2 = dns2;
	}

	public Dns getDns3() {
		return this.dns3;
	}

	public void setDns3(Dns dns3) {
		this.dns3 = dns3;
	}

	public Dns getDns4() {
		return this.dns4;
	}

	public void setDns4(Dns dns4) {
		this.dns4 = dns4;
	}

	public Dns getDns5() {
		return this.dns5;
	}

	public void setDns5(Dns dns5) {
		this.dns5 = dns5;
	}

	public Dns getDns6() {
		return this.dns6;
	}

	public void setDns6(Dns dns6) {
		this.dns6 = dns6;
	}

	public Titular getTitular() {
		return this.titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

}