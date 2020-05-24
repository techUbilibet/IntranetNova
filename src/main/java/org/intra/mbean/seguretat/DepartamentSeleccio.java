package org.intra.mbean.seguretat;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Departament;
import org.intra.model.Parametres;
import org.intra.negoci.Seguretat;

@Named
@ViewScoped
public class DepartamentSeleccio implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private Seguretat seguretat;

	@Inject
    private Logger log;

	@Inject
    private Parametres param;
	
//    public String getNomDepartament() {
//    	log.info("get nom");
//		return nomDepartament;
//	}
//
//	public void setNomDepartament(String nomDepartament) {
//    	log.info("set nom");
//		this.nomDepartament = nomDepartament;
//	}

	@Produces
    @Named
    private List<Departament> llistaSelDepartament;
    
    @PostConstruct
    public void init() {
    	try {
			cercar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void cercar() throws Exception {
    	log.info("Cercar");
    	llistaSelDepartament = seguretat.listDepartamentsNom(param.getNom());
    }
}
