package org.intra.mbean.seguretat;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Usuari;
import org.intra.model.Credencials;
import org.intra.model.NivellPermis;
import org.intra.model.PermisMD;
import org.intra.model.UsuariMD;
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
    private FacesContext context;

    @Inject
    private Seguretat seguretat;

	private UsuariMD usuari;

    @PostConstruct
    public void init() {
        usuari = null;
    }
    
	
	public String login() {
		Usuari u=seguretat.getUsuariByEmail(credencials.getEmail());
		usuari=new UsuariMD(u);
		if (usuari==null || !usuari.getContrasenya().equals(credencials.getContrasenya())) {
			usuari=null;
			return "loginView.jsf";
		}
		return "index.jsf";
	}

	public void logout() {
		usuari = null;
//		context.getViewRoot().getViewMap().clear();
		context.getExternalContext().invalidateSession();
	}

	public boolean isLoggedIn() {
		return usuari != null;
	}
	
	public UsuariMD getUsuari() {
		return usuari;
	}

	public void setUsuari(UsuariMD usuari) {
		this.usuari = usuari;
	}


	@Produces
	@LoggedIn
	UsuariMD getCurrentUser() {
		return usuari;
	}
	
}