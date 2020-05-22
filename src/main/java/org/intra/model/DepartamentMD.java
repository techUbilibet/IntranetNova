package org.intra.model;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;

import org.intra.integracio.Departament;
import org.intra.integracio.PermisDepartament;

@Model
public class DepartamentMD {

	private Integer id;
	private String nom;

	private List<PermisMD> permisos; 
	
	public DepartamentMD() {
	}

	public DepartamentMD(Departament ori) {
		id=ori.getId();
		nom=ori.getNom();
    	permisos=new ArrayList<PermisMD>();
    	for (PermisDepartament permis:ori.getPermisos()) {
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
}
