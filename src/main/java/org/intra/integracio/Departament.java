package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departaments database table.
 * 
 */
@Entity
@Table(name="departaments")
@NamedQueries({
	@NamedQuery(name="Departament.findAll", query="SELECT d FROM Departament d"),
	@NamedQuery(name="Departament.findLikeName", query="SELECT d FROM Departament d WHERE lower(d.nom) LIKE :nom")
})
public class Departament implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTAMENTS_ID_GENERATOR", sequenceName="departaments_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTAMENTS_ID_GENERATOR")
	private Integer id;

	private String nom;

	//bi-directional many-to-one association to PermisDepartament
	@OneToMany(mappedBy="departament")
	private List<PermisDepartament> permisos;

	//bi-directional many-to-one association to Usuari
	@OneToMany(mappedBy="departament")
	private List<Usuari> usuaris;

	public Departament() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<PermisDepartament> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(List<PermisDepartament> permisos) {
		this.permisos = permisos;
	}

	public PermisDepartament addPermis(PermisDepartament permis) {
		getPermisos().add(permis);
		permis.setDepartament(this);

		return permis;
	}

	public PermisDepartament removePermis(PermisDepartament permis) {
		getPermisos().remove(permis);
		permis.setDepartament(null);

		return permis;
	}

	public List<Usuari> getUsuaris() {
		return this.usuaris;
	}

	public void setUsuaris(List<Usuari> usuaris) {
		this.usuaris = usuaris;
	}

	public Usuari addUsuari(Usuari usuari) {
		getUsuaris().add(usuari);
		usuari.setDepartament(this);

		return usuari;
	}

	public Usuari removeUsuari(Usuari usuari) {
		getUsuaris().remove(usuari);
		usuari.setDepartament(null);

		return usuari;
	}

}