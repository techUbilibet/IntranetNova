package model;

import javax.enterprise.inject.Model;

import org.intra.integracio.Departament;

@Model
public class DepartamentMD {

	private Integer id;
	private String nom;

	public DepartamentMD() {
	}

	public DepartamentMD(Departament ori) {
		id=ori.getId();
		nom=ori.getNom();
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

}
