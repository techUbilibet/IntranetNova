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
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Departament;
import org.intra.integracio.Funcio;
import org.intra.integracio.Usuari;
import org.intra.model.PermisMD;
import org.intra.model.DepartamentMD;
import org.intra.model.FuncioMD;
import org.intra.model.NivellPermis;
import org.intra.model.UsuariMD;
import org.intra.negoci.Seguretat;

@Named
@ViewScoped
public class UsuariEdicio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private FacesContext context;

	@Inject
    private Logger log;

    @Inject
    private Seguretat seguretat;

	@Inject
    private Login login;
	
	@Produces
	@Named
	private UsuariMD usuari;

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
    	log.info("INIT"); //$NON-NLS-1$

    	log.info("Funcions"); //$NON-NLS-1$
    	llistaFuncions=new ArrayList<SelectItem>();
    	List<Funcio> funcions=seguretat.listFunctions();
		llistaFuncions.add(new SelectItem(0,login.getMsg("txt.select"))); //$NON-NLS-1$
    	for (Funcio f:funcions) {
    		llistaFuncions.add(new SelectItem(f.getId(),f.getDescripcio()));
    	}
    	llistaNivells=new ArrayList<SelectItem>();
    	for (NivellPermis n:NivellPermis.values()) {
    		String name=login.getMsg("level."+n.name());
    		llistaNivells.add(new SelectItem(n,name));
    	}

    	Map<String,String> params=context.getExternalContext().getRequestParameterMap();
    	if (params.containsKey("id")) { //$NON-NLS-1$
        	Integer id=new Integer(params.get("id")); //$NON-NLS-1$
        	log.info("Editant usuari id="+id.toString()); //$NON-NLS-1$
        	Usuari u=seguretat.getUsuariById(id, true); 
        	usuari=new UsuariMD(u);
        	
    	} else {
        	log.info("Creant nou usuari"); //$NON-NLS-1$
        	usuari=new UsuariMD();
    	}
    	log.info("Final"); //$NON-NLS-1$
    }

	public void finalEdicio() {
    	log.info("exit edicio"); //$NON-NLS-1$
	}

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = login.getMsg("err.delete.log"); //$NON-NLS-1$
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
		log.info("new id " + idChanged.getNewValue().toString()); //$NON-NLS-1$
		int nou=((Long) idChanged.getNewValue()).intValue();
		int old=0;
		if (idChanged.getOldValue()!=null) {
			old=(int) idChanged.getOldValue();
			log.info("old id" + idChanged.getOldValue().toString()); //$NON-NLS-1$
		}
		if (old==nou) return;

    	Departament d=seguretat.getDepartamentById(nou, true);
		
		if (d==null) {
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, login.getMsg("err.dep.no.ext"), login.getMsg("err.dep.no")); //$NON-NLS-1$ //$NON-NLS-2$
        	context.addMessage(null, m);
			usuari.setDepartament(new DepartamentMD());
		} else {
			usuari.setDepartament(new DepartamentMD(d));
			usuari.resetPermisos();
		}
	}
	
	public void nouPermis(ValueChangeEvent nouPermis) {
		log.info("new " + nouPermis.getNewValue().toString()); //$NON-NLS-1$
		Integer id=(Integer.parseInt((String) nouPermis.getNewValue()));
		if (id==0) return;
		log.info("Size(1) "+Integer.toString(usuari.getPermisos().size())); //$NON-NLS-1$
		for (PermisMD p:usuari.getPermisos()) {
			log.info("permis "+p.getFuncio().getId()); //$NON-NLS-1$
			if (p.getFuncio().getId().equals(id)) {
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, login.getMsg("err.fun.dup.ext"), login.getMsg("err.fun.dup")); //$NON-NLS-1$ //$NON-NLS-2$
	            context.addMessage(null, m);
	            this.novaFuncio=null;
	            return;
			}
		}
		PermisMD permis= new PermisMD();
		Funcio f=seguretat.getFuncioById(id);
		permis.setFuncio(new FuncioMD(f));
		usuari.getPermisos().add(permis);
		log.info("Size(2) "+Integer.toString(usuari.getPermisos().size())); //$NON-NLS-1$
		nouPermis.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
		novaFuncio=0;
	}
	
    public void save() throws Exception {
        try {
        	if (!login.isLoggedIn()) return;
        	boolean doit=true;
        	FacesMessage m; 
        	if (!usuari.getEmail().matches("^[a-zA-Z0-9-_.]+@[a-zA-Z]+\\.[a-zA-Z]{2,}(\\.[a-zA-Z]{2,}|)")) { //$NON-NLS-1$
            	m = new FacesMessage(FacesMessage.SEVERITY_ERROR, login.getMsg("err.email.no"), login.getMsg("err.save.no")); //$NON-NLS-1$ //$NON-NLS-2$
            	context.addMessage("save", m);
        		doit=false;
        	}
        	if (usuari.getDepartament().getId()!=null) {
        		if (seguretat.getDepartamentById(usuari.getDepartament().getId())==null) {
                	m = new FacesMessage(FacesMessage.SEVERITY_ERROR, login.getMsg("err.dep.no.ext"), login.getMsg("err.save.no")); //$NON-NLS-1$ //$NON-NLS-2$
                	context.addMessage("save", m);
        			doit=false;
        		}
        	} else {
        		
        	}
        	if (usuari.getNovaContrasenya()!=null && !usuari.getNovaContrasenya().isEmpty()) {
        		if (!usuari.getNovaContrasenya().matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{10,50}")) {
                	m = new FacesMessage(FacesMessage.SEVERITY_ERROR, login.getMsg("err.psw.no"), login.getMsg("err.save.no")); //$NON-NLS-1$ //$NON-NLS-2$
                	context.addMessage("save", m);
                	doit=false;
        		} else 
        			if (doit) usuari.setContrasenya();
        	}
        	if (!doit) {
        		return;
        	}
        	
        	log.info("Destant Usuari"); //$NON-NLS-1$
        	log.info("id "+usuari.getId()); //$NON-NLS-1$
        	log.info("nom "+usuari.getNom()); //$NON-NLS-1$
        	log.info("email "+usuari.getEmail()); //$NON-NLS-1$
        	log.info("idioma "+usuari.getIdioma()); //$NON-NLS-1$
        	log.info("certificat "+usuari.getCertificat()); //$NON-NLS-1$
        	log.info("idDepartament "+usuari.getDepartament().getId()); //$NON-NLS-1$
        	log.info("contrasenya "+usuari.getContrasenya()); //$NON-NLS-1$
        	usuari=seguretat.saveUsuari(usuari);
        	m = new FacesMessage(FacesMessage.SEVERITY_INFO, login.getMsg("msg.save.ok.id")+usuari.getId().toString(), login.getMsg("msg.save.ok")); //$NON-NLS-1$ //$NON-NLS-2$
        	context.addMessage("save", m);
        } catch (Exception e) {
        	String errorMessage = getRootErrorMessage(e);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, login.getMsg("err.save.no")); //$NON-NLS-1$
        	context.addMessage("save", m);
        }
    }
    
    public String delete() throws Exception {
        try {
        	
        	log.info("Esborrant Usuari id "+usuari.getId()); //$NON-NLS-1$
        	String id=usuari.getId().toString();
        	seguretat.eliminarUsuari(usuari);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, login.getMsg("msg.deleted.id")+id, login.getMsg("msg.deleted")); //$NON-NLS-1$ //$NON-NLS-2$
        	context.addMessage("delete", m);
        } catch (Exception e) {
        	String errorMessage = getRootErrorMessage(e);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, login.getMsg("err.delete.no")); //$NON-NLS-1$
        	context.addMessage("delete", m);
        	return ""; //$NON-NLS-1$
        }
        return "window.close()"; //$NON-NLS-1$
    }

    public void exit() {
    	log.info("cancel"); //$NON-NLS-1$
    }

}
