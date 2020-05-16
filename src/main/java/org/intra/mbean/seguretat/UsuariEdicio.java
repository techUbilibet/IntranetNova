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
import org.intra.util.EdicioPermis;
import org.intra.util.NivellPermis;

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
	private Integer id;

	@Produces
	@Named
	private String nom;

	@Produces
	@Named
	private String email;

	@Produces
	@Named
	private Integer idioma;

	@Produces
	@Named
	private Boolean certificat;

	@Produces
	@Named
	private String contrasenya;

	@Produces
	@Named
	private Integer idDepartament;
	
	@Produces
	@Named
	private String nomDepartament;

	@Produces
	@Named
	private List<EdicioPermis> permisos;

	@Produces
    @Named
    private List<SelectItem> llistaFuncions;
    
    @Produces
    @Named
    private Integer novaFuncio; 
    
    @PostConstruct
    public void init() {
    	log.info("INIT");

    	this.permisos=new ArrayList<EdicioPermis>();
    	log.info("Funcions");
    	llistaFuncions=new ArrayList<SelectItem>();
    	List<Funcio> funcions=seguretat.listFunctions();
		llistaFuncions.add(new SelectItem(0,"Seleccionar"));
    	for (Funcio f:funcions) {
    		llistaFuncions.add(new SelectItem(f.getId(),f.getDescripcio()));
    	}

    	Map<String,String> params=context.getExternalContext().getRequestParameterMap();
    	if (params.containsKey("id")) {
        	this.id=new Integer(params.get("id"));
        	log.info("Editant usuari id="+id.toString());
        	Usuari u=seguretat.getUsuariById(id, true); 
        	this.nom=u.getNom();
        	this.idioma=u.getIdioma();
        	this.email=u.getEmail();
        	this.certificat=u.getCertificat();
        	this.contrasenya=u.getContrasenya();
        	this.idDepartament=u.getDepartament().getId();
        	this.nomDepartament=u.getDepartament().getNom();
        	for (PermisUsuari p:u.getPermisos()) {
        		EdicioPermis nou=new EdicioPermis();
        		nou.setContrasenya(p.getContrasenya());
        		nou.setDelete(false);
        		nou.setIdFuncio(p.getFuncio().getId());
        		nou.setDescripcioFuncio(p.getFuncio().getDescripcio());
        		nou.setNivell(NivellPermis.values()[p.getNivell()]);
        	}
        	
    	} else {
        	log.info("Creant nou usuari");
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
		Departament d=seguretat.getDepartamentById(Integer.parseInt((String) idChanged.getNewValue()));
		
		if (d==null) {
			d=seguretat.getDepartamentById((Integer) idChanged.getOldValue());
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departament incorrecte, No es podran desar les dades fins que s'assigni un de correcte", "Departament incorrecte");
        	context.addMessage(null, m);
		}
		this.idDepartament=d==null?null:d.getId();
		this.nomDepartament=d==null?null:d.getNom();
			
	}

	public void nouPermis(ValueChangeEvent nouPermis) {
		log.info("new " + nouPermis.getNewValue().toString());
		Integer id=(Integer.parseInt((String) nouPermis.getNewValue()));
		if (id==0) return;
		log.info("Size(1) "+Integer.toString(permisos.size()));
		for (EdicioPermis p:permisos) {
			log.info("permis "+p.getIdFuncio());
			if (p.getIdFuncio().equals(id)) {
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aquesta funció ja està a la llista", "Funció duplicada");
	            context.addMessage(null, m);
	            this.novaFuncio=null;
	            return;
			}
		}
		EdicioPermis permis= new EdicioPermis();
		Funcio f=seguretat.getFuncioById(id);
		permis.setIdFuncio(f.getId());
		permisos.add(permis);
		log.info("Size(2) "+Integer.toString(permisos.size()));
	}
	
    public void save() throws Exception {
        try {
//        	edUsuari.setDepartament(deptUs);
        	log.info("Destant Usuari");
        	id=seguretat.saveUsuari(id,nom,email, idioma, certificat, contrasenya, idDepartament, permisos);
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "desat amb id "+id.toString(), "Registration successful");
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
