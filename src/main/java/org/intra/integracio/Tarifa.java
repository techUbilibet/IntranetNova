package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tarifes database table.
 * 
 */
@Entity
@Table(name="tarifes")
@NamedQuery(name="Tarifa.findAll", query="SELECT t FROM Tarifa t")
public class Tarifa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TARIFES_ID_GENERATOR", sequenceName="tarifes_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TARIFES_ID_GENERATOR")
	private Integer id;

	@Column(name="abr_cat")
	private String abrCat;

	@Column(name="abr_en")
	private String abrEn;

	@Column(name="abr_es")
	private String abrEs;

	private String codi;

	@Column(name="desc_cat")
	private String descCat;

	@Column(name="desc_en")
	private String descEn;

	@Column(name="desc_es")
	private String descEs;

	private Boolean fixe;

	private double tarifa;

	@Column(name="tarifa_adm")
	private double tarifaAdm;

	@Column(name="tarifa_cost")
	private double tarifaCost;

	@Column(name="tarifa_divisa")
	private double tarifaDivisa;

	@Column(name="tarifa_premium")
	private double tarifaPremium;

	//uni-directional many-to-one association to Divisa
	@ManyToOne
	@JoinColumn(name="divisa")
	private Divisa divisa;

	//bi-directional many-to-one association to Familia
	@ManyToOne
	@JoinColumn(name="id_familia")
	private Familia family;

	public Tarifa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbrCat() {
		return this.abrCat;
	}

	public void setAbrCat(String abrCat) {
		this.abrCat = abrCat;
	}

	public String getAbrEn() {
		return this.abrEn;
	}

	public void setAbrEn(String abrEn) {
		this.abrEn = abrEn;
	}

	public String getAbrEs() {
		return this.abrEs;
	}

	public void setAbrEs(String abrEs) {
		this.abrEs = abrEs;
	}

	public String getCodi() {
		return this.codi;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public String getDescCat() {
		return this.descCat;
	}

	public void setDescCat(String descCat) {
		this.descCat = descCat;
	}

	public String getDescEn() {
		return this.descEn;
	}

	public void setDescEn(String descEn) {
		this.descEn = descEn;
	}

	public String getDescEs() {
		return this.descEs;
	}

	public void setDescEs(String descEs) {
		this.descEs = descEs;
	}

	public Boolean getFixe() {
		return this.fixe;
	}

	public void setFixe(Boolean fixe) {
		this.fixe = fixe;
	}

	public double getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	public double getTarifaAdm() {
		return this.tarifaAdm;
	}

	public void setTarifaAdm(double tarifaAdm) {
		this.tarifaAdm = tarifaAdm;
	}

	public double getTarifaCost() {
		return this.tarifaCost;
	}

	public void setTarifaCost(double tarifaCost) {
		this.tarifaCost = tarifaCost;
	}

	public double getTarifaDivisa() {
		return this.tarifaDivisa;
	}

	public void setTarifaDivisa(double tarifaDivisa) {
		this.tarifaDivisa = tarifaDivisa;
	}

	public double getTarifaPremium() {
		return this.tarifaPremium;
	}

	public void setTarifaPremium(double tarifaPremium) {
		this.tarifaPremium = tarifaPremium;
	}

	public Divisa getDivisa() {
		return this.divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public Familia getFamily() {
		return this.family;
	}

	public void setFamily(Familia family) {
		this.family = family;
	}

}