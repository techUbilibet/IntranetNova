package org.intra.mbean.seguretat;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Departament;
import org.intra.integracio.Funcio;
import org.intra.integracio.PermisUsuari;
import org.intra.integracio.Usuari;
import org.intra.model.PermisMD;
import org.intra.model.FuncioMD;
import org.intra.model.NivellPermis;
import org.intra.model.UsuariMD;
import org.intra.negoci.Seguretat;

@Named
@ViewScoped
public class UsuariEdicio  implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Inject
    private FacesContext context;

	@Inject
    private Logger log;

    @Inject
    private Seguretat seguretat;

	@Produces
	@Named
	private UsuariMD usuari;

//	@Produces
//	@Named
//	private List<EdicioPermis> permisos;

	@Produces
    @Named
    private List<SelectItem> llistaFuncions;
    
	@Produces
    @Named
    private List<SelectItem> llistaNivells;
    
    @Produces
    @Named
    private Integer novaFuncio; 
    
    @PostConstruct
    public void init() {
    	log.info("INIT");

    	log.info("Funcions");
    	llistaFuncions=new ArrayList<SelectItem>();
    	List<Funcio> funcions=seguretat.listFunctions();
		llistaFuncions.add(new SelectItem(0,"Seleccionar"));
    	for (Funcio f:funcions) {
    		llistaFuncions.add(new SelectItem(f.getId(),f.getDescripcio()));
    	}
    	llistaNivells=new ArrayList<SelectItem>();
    	for (NivellPermis n:NivellPermis.values()) {
    		llistaNivells.add(new SelectItem(n,n.name()));
    	}

    	Map<String,String> params=context.getExternalContext().getRequestParameterMap();
    	if (params.containsKey("id")) {
        	Integer id=new Integer(params.get("id"));
        	log.info("Editant usuari id="+id.toString());
        	Usuari u=seguretat.getUsuariById(id, true); 
        	usuari=new UsuariMD(u);
        	
    	} else {
        	log.info("Creant nou usuari");
        	usuari=new UsuariMD();
    	}
    	log.info("Final");
    }

	public void finalEdicio() {
    	log.info("exit edicio");
	}

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

	public void departamentChanged(ValueChangeEvent idChanged) {
		log.info("new " + idChanged.getNewValue().toString());
		if (idChanged.getOldValue()!=null)
			log.info("old " + idChanged.getOldValue().toString());
		Departament d=seguretat.getDepartamentById(((Long) idChanged.getNewValue()).intValue());
		
		if (d==null) {
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departament incorrecte, No es podran desar les dades fins que s'assigni un de correcte", "Departament incorrecte");
        	context.addMessage(null, m);
        	if (idChanged.getOldValue()!=null)
        		d=seguretat.getDepartamentById((Integer) idChanged.getOldValue());
		}
		if (d!=null) {
			usuari.getDepartament().setId(d.getId());
			usuari.getDepartament().setNom(d.getNom());
		}
	}

	public void nomChanged(ValueChangeEvent nomChanged) {
//		nom=(String) nomChanged.getNewValue();
	}

	public void emailChanged(ValueChangeEvent emailChanged) {
//		email=(String) emailChanged.getNewValue();
	}

	public void contrasenyaChanged(ValueChangeEvent contrasenyaChanged) {
//		contrasenya=(String) contrasenyaChanged.getNewValue();
	}

	public void idiomaChanged(ValueChangeEvent idiomaChanged) {
//		idioma=Integer.parseInt((String) idiomaChanged.getNewValue());
	}

	public void certificatChanged(ValueChangeEvent certificatChanged) {
//		certificat=(Boolean) certificatChanged.getNewValue();
	}

	public void nouPermis(ValueChangeEvent nouPermis) {
		log.info("new " + nouPermis.getNewValue().toString());
		Integer id=(Integer.parseInt((String) nouPermis.getNewValue()));
		if (id==0) return;
		log.info("Size(1) "+Integer.toString(usuari.getPermisos().size()));
		for (PermisMD p:usuari.getPermisos()) {
			log.info("permis "+p.getFuncio().getId());
			if (p.getFuncio().getId().equals(id)) {
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aquesta funció ja està a la llista", "Funció duplicada");
	            context.addMessage(null, m);
	            this.novaFuncio=null;
	            return;
			}
		}
		PermisMD permis= new PermisMD();
		Funcio f=seguretat.getFuncioById(id);
		permis.setFuncio(new FuncioMD(f));
		usuari.getPermisos().add(permis);
		log.info("Size(2) "+Integer.toString(usuari.getPermisos().size()));
	}
	
    public void save() throws Exception {
        try {
        	
        	log.info("Destant Usuari");
        	log.info("id "+usuari.getId());
        	log.info("nom "+usuari.getNom());
        	log.info("email "+usuari.getEmail());
        	log.info("idioma "+usuari.getIdioma());
        	log.info("certificat "+usuari.getCertificat());
        	log.info("contrasenya "+usuari.getContrasenya());
        	log.info("idDepartament "+usuari.getDepartament().getId());
        	usuari=seguretat.saveUsuari(usuari);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "desat amb id "+usuari.getId().toString(), "Registration successful");
        	context.addMessage(null, m);
        } catch (Exception e) {
        	String errorMessage = getRootErrorMessage(e);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
        	context.addMessage(null, m);
        }
    }
    
    public void exit() {
    	log.info("cancel");
    }

}
