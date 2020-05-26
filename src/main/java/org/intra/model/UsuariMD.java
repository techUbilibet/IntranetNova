package org.intra.model;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;

import org.intra.integracio.PermisUsuari;
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
	
	private List<PermisMD> permisos; 

	public UsuariMD() {
		departament=new DepartamentMD();
		permisos=new ArrayList<PermisMD>();
	}

	public UsuariMD(Usuari ori) {
    	id=ori.getId();
		nom=ori.getNom();
    	idioma=ori.getIdioma();
    	email=ori.getEmail();
    	certificat=ori.getCertificat();
    	contrasenya=ori.getContrasenya();
    	departament=new DepartamentMD(ori.getDepartament()); 
    	permisos=new ArrayList<PermisMD>();
    	for (PermisUsuari permis:ori.getPermisos()) {
    		permisos.add(new PermisMD(permis));
    	}
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

	public List<PermisMD> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<PermisMD> permisos) {
		this.permisos = permisos;
	}

	public PermisMD addPermis(PermisMD permis) {
		permisos.add(permis);
		return permis;
	}

	public void resetPermisos() {
		if (departament.getPermisos()!=null && !departament.getPermisos().isEmpty())
			permisos=departament.getPermisos();
		else
			permisos=new ArrayList<PermisMD>();
	}

	public boolean potAccedir(Integer idFuncio) {
		for (PermisMD p:permisos) {
			if (p.getFuncio().getId().equals(idFuncio)) return true;
		}
		return false;
	}

	public boolean tePermis(Integer idFuncio, String nivell) {
		NivellPermis niv=NivellPermis.valueOf(nivell); 
		for (PermisMD p:permisos) {
			if (!p.getFuncio().getId().equals(idFuncio)) continue;
			if (p.getNivell().compareTo(niv)>=0) return true;
			break;
		}
		return false;
	}
}
