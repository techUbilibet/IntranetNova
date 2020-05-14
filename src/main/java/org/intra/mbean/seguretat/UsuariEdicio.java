package org.intra.mbean.seguretat;

import java.io.Serializable;
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
    private Usuari usuariEd;

    @Produces
    @Named
    private List<SelectItem> llistaFuncions;
    
    @Produces
    @Named
    private Integer novaFuncio; 
    
    @PostConstruct
    public void init() {
    	log.info("INIT");
    	Map<String,String> params=context.getExternalContext().getRequestParameterMap();
    	if (params.containsKey("id")) {
        	log.info("amb param");
    		if (params.get("id").equals("0")) {
	        	log.info("new");
	        	usuariEd=new Usuari();
	        	usuariEd.setDepartament(new Departament());
	        	usuariEd.setPermisos(new ArrayList<PermisUsuari>()); 
    		} else {
	        	log.info("edit");
	        	usuariEd=seguretat.getUsuariById(Integer.parseInt(params.get("id")));
    		}
//    		desa();
    	} else {
        	log.info("Sense param");
//        	recupera();
//        	log.info("Usuari:"+usuariEd==null?"null":usuariEd.getNom());
//        	if (usuariEd==null) {
	        	usuariEd=new Usuari();
	        	usuariEd.setDepartament(new Departament());
	        	usuariEd.setPermisos(new ArrayList<PermisUsuari>()); 
//        	}
    	}
    	log.info("Funcions");
    	novaFuncio=0;
    	llistaFuncions=new ArrayList<SelectItem>();
    	List<Funcio> funcions=seguretat.listFunctions();
		llistaFuncions.add(new SelectItem(0,"Seleccionar"));
    	for (Funcio f:funcions) {
    		llistaFuncions.add(new SelectItem(f.getId(),f.getDescripcio()));
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
		Departament d=seguretat.getDepartamentById((Integer) idChanged.getNewValue());
		
		if (d==null) {
			d=seguretat.getDepartamentById((Integer) idChanged.getOldValue());
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departament incorrecte, No es podran desar les dades fins que s'assigni un de correcte", "Departament incorrecte");
        	context.addMessage(null, m);
		}
		usuariEd.setDepartament(d);
			
	}

	public void nouPermis(ValueChangeEvent nouPermis) {
		log.info("new " + nouPermis.getNewValue().toString());
		Integer id=(Integer.parseInt((String) nouPermis.getNewValue()));
		if (id==0) return;
		log.info("Size(1) "+Integer.toString(usuariEd.getPermisos().size()));
		for (PermisUsuari p:usuariEd.getPermisos()) {
			log.info("permis "+p.getFuncio().getId().toString());
			if (p.getFuncio().getId().equals(id)) {
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aquesta funció ja està a la llista", "Funció duplicada");
	            context.addMessage(null, m);
			}
		}
		PermisUsuari permis= new PermisUsuari();
		permis.setFuncio(seguretat.getFuncioById(id));
		usuariEd.getPermisos().add(permis);
		log.info("Size(2) "+Integer.toString(usuariEd.getPermisos().size()));
	}
	
    public void save() throws Exception {
        try {
//        	edUsuari.setDepartament(deptUs);
        	log.info(usuariEd.toString());
        	seguretat.save(usuariEd);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "desat amb id "+usuariEd.getId().toString(), "Registration successful");
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
