package org.intra.mbean.seguretat;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Usuari;
import org.intra.model.Credencials;
import org.intra.model.Idioma;
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
    private Logger log;

	@Inject
    private FacesContext context;

    @Inject
    private Seguretat seguretat;

	private UsuariMD usuari;

	private ResourceBundle msg;
	
	@PostConstruct
    public void init() {
        usuari = null;
        setIdioma();
        
    }

	private void setIdioma() {
		Idioma idm;
		if (usuari==null) idm=Idioma.EN;
		else idm=Idioma.values()[usuari.getIdioma()];
		msg=ResourceBundle.getBundle("org.intra.util.messages_"+idm.name().toLowerCase());
	}
	
	public String login() {
		Usuari u=seguretat.getUsuariByEmail(credencials.getEmail());
		if (u==null) {
			usuari=null;
			return "loginView.jsf";
		}
		usuari=new UsuariMD(u);
		if (usuari==null || !usuari.getContrasenya().equals(credencials.getContrasenya())) {
			usuari=null;
			return "loginView.jsf";
		}
        setIdioma();
		return "index.jsf";
	}

	public String logout() {
		usuari = null;
        setIdioma();
		context.getExternalContext().invalidateSession();
		return "index.jsf";
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

	public String getMsg(String key) {
		return msg.getString(key);
	}
	
}