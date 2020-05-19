package org.intra.mbean.seguretat;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Usuari;
import org.intra.model.Credencials;
import org.intra.negoci.Seguretat;
import org.intra.util.LoggedIn;

@Named
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	public Login() {};
	
	@Inject
	Credencials credencials;

    @Inject
    private Seguretat seguretat;

	private Usuari usuari;

    @PostConstruct
    public void init() {
        usuari = null;
    }
    
	
	public String login() {

		usuari=seguretat.getUsuariByEmail(credencials.getEmail());
		if (usuari==null || !usuari.getContrasenya().equals(credencials.getContrasenya())) {
			usuari=null;
			return "loginView.jsf";
		}
		return "index.jsf";
	}

	public void logout() {
		usuari = null;
	}

	public boolean isLoggedIn() {
		return usuari != null;
	}
	
	public Usuari getUsuari() {
		return usuari;
	}

	public void setUsuari(Usuari usuari) {
		this.usuari = usuari;
	}


	@Produces
	@LoggedIn
	Usuari getCurrentUser() {
		return usuari;
	}
	
	
}