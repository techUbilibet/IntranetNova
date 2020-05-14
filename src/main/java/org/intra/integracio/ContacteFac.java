package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the contactes_fac database table.
 * 
 */
@Entity
@Table(name="contactes_fac")
@NamedQuery(name="ContacteFac.findAll", query="SELECT c FROM ContacteFac c")
public class ContacteFac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTACTES_FAC_ID_GENERATOR", sequenceName="contactes_fac_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTACTES_FAC_ID_GENERATOR")
	private Integer id;

	private Boolean admin;

	private String atencio;

	private Boolean autofirma;

	private Integer avis1;

	private Integer avis2;

	private Integer avis3;

	private Boolean avisos;

	private Integer categoria;

	@Column(name="city")
	private String poblacio;

	@Column(name="compte_bancari")
	private String compteBancari;

	@Column(name="compte_comptable")
	private String compteComptable;

	@Column(name="contrasenya_zona")
	private String contrasenyaZona;

	@Column(name="country")
	private String pais;

	@Column(name="cp")
	private String codiPostal;

	@Column(name="crm_comentari")
	private String crmComentari;

	@Column(name="crm_login")
	private String crmLogin;

	@Column(name="crm_password")
	private String crmPassword;

	@Column(name="crm_url")
	private String crmUrl;

	private String defaultpo;

	private String direccio;

	private Boolean domiciliacio;

	private String enviament;

	@Column(name="fac_e")
	private Boolean facE;

	@Column(name="fac_unica_month")
	private Integer facUnicaMonth;

	@Column(name="fac_unica_month2")
	private Integer facUnicaMonth2;

	@Column(name="fac_unica_type")
	private Integer facUnicaType;

	private String fax;

	private Integer fiscalitat;

	@Column(name="id_cons")
	private Integer idCons;

	private String incidencies;

	@Column(name="incidencies_pagaments")
	private String incidenciesPagaments;

	@Column(name="lang")
	private String idioma;

	@Column(name="mail_consultor")
	private String mailConsultor;

	@Column(name="mail_zona")
	private String mailZona;

	private Boolean needpo;

	private String nif;

	private String nom;

	@Column(name="nom_intern")
	private String nomIntern;

	@Column(name="phone")
	private String telefon;

	@Column(name="send_email")
	private Boolean sendEmail;

	@Column(name="send_mail")
	private Boolean sendMail;

	@Column(name="state")
	private String provincia;

	private Integer tipus;

	@Column(name="tipus_annexos")
	private Integer tipusAnnexos;

	@Column(name="tipus_zona")
	private Integer tipusZona;

	@Column(name="usuari_zona")
	private String usuariZona;

	//bi-directional many-to-one association to Grup
	@ManyToOne
	@JoinColumn(name="id_grup")
	private Grup grup;

	//uni-directional many-to-many association to Sector
	@ManyToMany
	@JoinTable(
		name="contactes_fac_sectors"
		, joinColumns={
			@JoinColumn(name="id_c_f")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_sector")
			}
		)
	private List<Sector> sectors;

	//bi-directional many-to-one association to ContacteFacAutoritzat
	@OneToMany(mappedBy="contacteFac")
	private List<ContacteFacAutoritzat> contactesFacAutoritzats;

	//bi-directional many-to-one association to ContacteFacDepartament
	@OneToMany(mappedBy="contacteFac")
	private List<ContacteFacDepartament> contactesFacDepartaments;

	//bi-directional many-to-one association to Descompte
	@OneToMany(mappedBy="contacteFac")
	private List<Descompte> descomptes;

	//bi-directional many-to-one association to DescompteFamilia
	@OneToMany(mappedBy="contacteFac")
	private List<DescompteFamilia> descomptesFamilies;

	//uni-directional one-to-one association to PlantillaCertificat
	@OneToOne
	@JoinColumn(name="id")
	private PlantillaCertificat plantillaCertificat;

	//uni-directional one-to-one association to PlantillaDomini
	@OneToOne
	@JoinColumn(name="id")
	private PlantillaDomini plantillaDomini;

	//uni-directional one-to-one association to PlantillaMark
	@OneToOne
	@JoinColumn(name="id")
	private PlantillaMark plantillaMark;

	//uni-directional many-to-many association to Usuari
	@ManyToMany
	@JoinTable(
		name="usuaris_contactes_fac"
		, joinColumns={
			@JoinColumn(name="id_c_f")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_usuari")
			}
		)
	private List<Usuari> usuaris;

	public ContacteFac() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getAtencio() {
		return this.atencio;
	}

	public void setAtencio(String atencio) {
		this.atencio = atencio;
	}

	public Boolean getAutofirma() {
		return this.autofirma;
	}

	public void setAutofirma(Boolean autofirma) {
		this.autofirma = autofirma;
	}

	public Integer getAvis1() {
		return this.avis1;
	}

	public void setAvis1(Integer avis1) {
		this.avis1 = avis1;
	}

	public Integer getAvis2() {
		return this.avis2;
	}

	public void setAvis2(Integer avis2) {
		this.avis2 = avis2;
	}

	public Integer getAvis3() {
		return this.avis3;
	}

	public void setAvis3(Integer avis3) {
		this.avis3 = avis3;
	}

	public Boolean getAvisos() {
		return this.avisos;
	}

	public void setAvisos(Boolean avisos) {
		this.avisos = avisos;
	}

	public Integer getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public String getPoblacio() {
		return this.poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public String getCompteBancari() {
		return this.compteBancari;
	}

	public void setCompteBancari(String compteBancari) {
		this.compteBancari = compteBancari;
	}

	public String getCompteComptable() {
		return this.compteComptable;
	}

	public void setCompteComptable(String compteComptable) {
		this.compteComptable = compteComptable;
	}

	public String getContrasenyaZona() {
		return this.contrasenyaZona;
	}

	public void setContrasenyaZona(String contrasenyaZona) {
		this.contrasenyaZona = contrasenyaZona;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodiPostal() {
		return this.codiPostal;
	}

	public void setCodiPostal(String codiPostal) {
		this.codiPostal = codiPostal;
	}

	public String getCrmComentari() {
		return this.crmComentari;
	}

	public void setCrmComentari(String crmComentari) {
		this.crmComentari = crmComentari;
	}

	public String getCrmLogin() {
		return this.crmLogin;
	}

	public void setCrmLogin(String crmLogin) {
		this.crmLogin = crmLogin;
	}

	public String getCrmPassword() {
		return this.crmPassword;
	}

	public void setCrmPassword(String crmPassword) {
		this.crmPassword = crmPassword;
	}

	public String getCrmUrl() {
		return this.crmUrl;
	}

	public void setCrmUrl(String crmUrl) {
		this.crmUrl = crmUrl;
	}

	public String getDefaultpo() {
		return this.defaultpo;
	}

	public void setDefaultpo(String defaultpo) {
		this.defaultpo = defaultpo;
	}

	public String getDireccio() {
		return this.direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public Boolean getDomiciliacio() {
		return this.domiciliacio;
	}

	public void setDomiciliacio(Boolean domiciliacio) {
		this.domiciliacio = domiciliacio;
	}

	public String getEnviament() {
		return this.enviament;
	}

	public void setEnviament(String enviament) {
		this.enviament = enviament;
	}

	public Boolean getFacE() {
		return this.facE;
	}

	public void setFacE(Boolean facE) {
		this.facE = facE;
	}

	public Integer getFacUnicaMonth() {
		return this.facUnicaMonth;
	}

	public void setFacUnicaMonth(Integer facUnicaMonth) {
		this.facUnicaMonth = facUnicaMonth;
	}

	public Integer getFacUnicaMonth2() {
		return this.facUnicaMonth2;
	}

	public void setFacUnicaMonth2(Integer facUnicaMonth2) {
		this.facUnicaMonth2 = facUnicaMonth2;
	}

	public Integer getFacUnicaType() {
		return this.facUnicaType;
	}

	public void setFacUnicaType(Integer facUnicaType) {
		this.facUnicaType = facUnicaType;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getFiscalitat() {
		return this.fiscalitat;
	}

	public void setFiscalitat(Integer fiscalitat) {
		this.fiscalitat = fiscalitat;
	}

	public Integer getIdCons() {
		return this.idCons;
	}

	public void setIdCons(Integer idCons) {
		this.idCons = idCons;
	}

	public String getIncidencies() {
		return this.incidencies;
	}

	public void setIncidencies(String incidencies) {
		this.incidencies = incidencies;
	}

	public String getIncidenciesPagaments() {
		return this.incidenciesPagaments;
	}

	public void setIncidenciesPagaments(String incidenciesPagaments) {
		this.incidenciesPagaments = incidenciesPagaments;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getMailConsultor() {
		return this.mailConsultor;
	}

	public void setMailConsultor(String mailConsultor) {
		this.mailConsultor = mailConsultor;
	}

	public String getMailZona() {
		return this.mailZona;
	}

	public void setMailZona(String mailZona) {
		this.mailZona = mailZona;
	}

	public Boolean getNeedpo() {
		return this.needpo;
	}

	public void setNeedpo(Boolean needpo) {
		this.needpo = needpo;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomIntern() {
		return this.nomIntern;
	}

	public void setNomIntern(String nomIntern) {
		this.nomIntern = nomIntern;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Boolean getSendEmail() {
		return this.sendEmail;
	}

	public void setSendEmail(Boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	public Boolean getSendMail() {
		return this.sendMail;
	}

	public void setSendMail(Boolean sendMail) {
		this.sendMail = sendMail;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Integer getTipus() {
		return this.tipus;
	}

	public void setTipus(Integer tipus) {
		this.tipus = tipus;
	}

	public Integer getTipusAnnexos() {
		return this.tipusAnnexos;
	}

	public void setTipusAnnexos(Integer tipusAnnexos) {
		this.tipusAnnexos = tipusAnnexos;
	}

	public Integer getTipusZona() {
		return this.tipusZona;
	}

	public void setTipusZona(Integer tipusZona) {
		this.tipusZona = tipusZona;
	}

	public String getUsuariZona() {
		return this.usuariZona;
	}

	public void setUsuariZona(String usuariZona) {
		this.usuariZona = usuariZona;
	}

	public Grup getGrup() {
		return this.grup;
	}

	public void setGrup(Grup grup) {
		this.grup = grup;
	}

	public List<Sector> getSectors() {
		return this.sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public List<ContacteFacAutoritzat> getContactesFacAutoritzats() {
		return this.contactesFacAutoritzats;
	}

	public void setContactesFacAutoritzats(List<ContacteFacAutoritzat> contactesFacAutoritzats) {
		this.contactesFacAutoritzats = contactesFacAutoritzats;
	}

	public ContacteFacAutoritzat addContactesFacAutoritzat(ContacteFacAutoritzat contactesFacAutoritzat) {
		getContactesFacAutoritzats().add(contactesFacAutoritzat);
		contactesFacAutoritzat.setContacteFac(this);

		return contactesFacAutoritzat;
	}

	public ContacteFacAutoritzat removeContactesFacAutoritzat(ContacteFacAutoritzat contactesFacAutoritzat) {
		getContactesFacAutoritzats().remove(contactesFacAutoritzat);
		contactesFacAutoritzat.setContacteFac(null);

		return contactesFacAutoritzat;
	}

	public List<ContacteFacDepartament> getContactesFacDepartaments() {
		return this.contactesFacDepartaments;
	}

	public void setContactesFacDepartaments(List<ContacteFacDepartament> contactesFacDepartaments) {
		this.contactesFacDepartaments = contactesFacDepartaments;
	}

	public ContacteFacDepartament addContactesFacDepartament(ContacteFacDepartament contactesFacDepartament) {
		getContactesFacDepartaments().add(contactesFacDepartament);
		contactesFacDepartament.setContacteFac(this);

		return contactesFacDepartament;
	}

	public ContacteFacDepartament removeContactesFacDepartament(ContacteFacDepartament contactesFacDepartament) {
		getContactesFacDepartaments().remove(contactesFacDepartament);
		contactesFacDepartament.setContacteFac(null);

		return contactesFacDepartament;
	}

	public List<Descompte> getDescomptes() {
		return this.descomptes;
	}

	public void setDescomptes(List<Descompte> descomptes) {
		this.descomptes = descomptes;
	}

	public Descompte addDescompte(Descompte descompte) {
		getDescomptes().add(descompte);
		descompte.setContacteFac(this);

		return descompte;
	}

	public Descompte removeDescompte(Descompte descompte) {
		getDescomptes().remove(descompte);
		descompte.setContacteFac(null);

		return descompte;
	}

	public List<DescompteFamilia> getDescomptesFamilies() {
		return this.descomptesFamilies;
	}

	public void setDescomptesFamilies(List<DescompteFamilia> descomptesFamilies) {
		this.descomptesFamilies = descomptesFamilies;
	}

	public DescompteFamilia addDescomptesFamily(DescompteFamilia descomptesFamily) {
		getDescomptesFamilies().add(descomptesFamily);
		descomptesFamily.setContacteFac(this);

		return descomptesFamily;
	}

	public DescompteFamilia removeDescomptesFamily(DescompteFamilia descomptesFamily) {
		getDescomptesFamilies().remove(descomptesFamily);
		descomptesFamily.setContacteFac(null);

		return descomptesFamily;
	}

	public PlantillaCertificat getPlantillaCertificat() {
		return this.plantillaCertificat;
	}

	public void setPlantillaCertificat(PlantillaCertificat plantillaCertificat) {
		this.plantillaCertificat = plantillaCertificat;
	}

	public PlantillaDomini getPlantillaDomini() {
		return this.plantillaDomini;
	}

	public void setPlantillaDomini(PlantillaDomini plantillaDomini) {
		this.plantillaDomini = plantillaDomini;
	}

	public PlantillaMark getPlantillaMark() {
		return this.plantillaMark;
	}

	public void setPlantillaMark(PlantillaMark plantillaMark) {
		this.plantillaMark = plantillaMark;
	}

	public List<Usuari> getUsuaris() {
		return this.usuaris;
	}

	public void setUsuaris(List<Usuari> usuaris) {
		this.usuaris = usuaris;
	}

}