package org.intra.integracio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuaris database table.
 * 
 */
@Entity
@Table(name="usuaris")
@NamedQueries({
	@NamedQuery(name="Usuari.findAll", query="SELECT u FROM Usuari u"),
	@NamedQuery(name="Usuari.findByEmail", query="SELECT u FROM Usuari u where u.email=:email")
})
public class Usuari implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIS_ID_GENERATOR", sequenceName="usuaris_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIS_ID_GENERATOR")
	private Integer id;

	private Boolean certificat;

	private String contrasenya;

	private String email;

	private Integer idioma;

	private String nom;

	//bi-directional many-to-one association to PermisUsuari
	@OneToMany(mappedBy="usuari")
	private List<PermisUsuari> permisos;

	//bi-directional many-to-one association to Departament
	@ManyToOne
	@JoinColumn(name="id_departament")
	private Departament departament;

	public Usuari() {
		this.id=null;
		this.certificat=false;
		this.contrasenya=null;
		this.departament=null;
		this.email=null;
		this.idioma=null;
		this.nom=null;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCertificat() {
		return this.certificat;
	}

	public void setCertificat(Boolean certificat) {
		this.certificat = certificat;
	}

	public String getContrasenya() {
		return this.contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<PermisUsuari> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(List<PermisUsuari> permisos) {
		this.permisos = permisos;
	}

	public PermisUsuari addPermis(PermisUsuari permis) {
		getPermisos().add(permis);
		permis.setUsuari(this);
		return permis;
	}

	public PermisUsuari removePermis(PermisUsuari permis) {
		getPermisos().remove(permis);
		permis.setUsuari(null);

		return permis;
	}

	public Departament getDepartament() {
		return this.departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

}