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
import javax.faces.event.PhaseId;
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
import org.intra.model.DepartamentMD;
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
		int nou=((Long) idChanged.getNewValue()).intValue();
		int old=0;
		if (idChanged.getOldValue()!=null) {
			old=(int) idChanged.getOldValue();
			log.info("old " + idChanged.getOldValue().toString());
		}
		if (old==nou) return;
		Departament d=seguretat.getDepartamentById(nou, true);
		
		if (d==null) {
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departament incorrecte, No es podran desar les dades fins que s'assigni un de correcte", "Departament incorrecte");
        	context.addMessage(null, m);
        	if (old!=0)
        		d=seguretat.getDepartamentById(old);
		}
		if (d!=null) {
			usuari.setDepartament(new DepartamentMD(d));
			usuari.resetPermisos();
		} else { 
			usuari.setDepartament(new DepartamentMD());
		}
    	idChanged.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
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
		nouPermis.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
		novaFuncio=0;
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
    
    public String delete() throws Exception {
        try {
        	
        	log.info("Esborrant Usuari");
        	log.info("id "+usuari.getId());
        	log.info("nom "+usuari.getNom());
        	log.info("email "+usuari.getEmail());
        	log.info("idioma "+usuari.getIdioma());
        	log.info("certificat "+usuari.getCertificat());
        	log.info("contrasenya "+usuari.getContrasenya());
        	log.info("idDepartament "+usuari.getDepartament().getId());
        	seguretat.eliminarUsuari(usuari);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "esborrat", "Deletion successful");
        	context.addMessage(null, m);
        } catch (Exception e) {
        	String errorMessage = getRootErrorMessage(e);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Deletion unsuccessful");
        	context.addMessage(null, m);
        	return "";
        }
        return "window.close()";
    }

    public void exit() {
    	log.info("cancel");
    }

}
