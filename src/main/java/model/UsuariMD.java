package model;

import javax.enterprise.inject.Model;

import org.intra.integracio.Usuari;

@Model
public class UsuariMD {

	private Integer id;
	private String nom;
	private String email;
	private Integer idioma;
	private Boolean certificat;
	private String contrasenya;
	private DepartamentMD departament;

	public UsuariMD() {
		departament=new DepartamentMD();
	}

	public UsuariMD(Usuari ori) {
    	id=ori.getId();
		nom=ori.getNom();
    	idioma=ori.getIdioma();
    	email=ori.getEmail();
    	certificat=ori.getCertificat();
    	contrasenya=ori.getContrasenya();
    	departament=new DepartamentMD(ori.getDepartament()); 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdioma() {
		return idioma;
	}

	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}

	public Boolean getCertificat() {
		return certificat;
	}

	public void setCertificat(Boolean certificat) {
		this.certificat = certificat;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public DepartamentMD getDepartament() {
		return departament;
	}

	public void setDepartament(DepartamentMD departament) {
		this.departament = departament;
	}

}
