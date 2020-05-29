package org.intra.mbean.seguretat;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Departament;
import org.intra.negoci.Seguretat;

@Model
public class DepartamentSeleccio implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private Seguretat seguretat;

	@Inject
    private Logger log;

	@Produces
    @Named
    private String nom;
	
    private Integer selected;

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
    	llistaSelDepartament = seguretat.listDepartamentsNom(nom);
    }
    
    public void select(Integer id) {
    	setSelected(id);
    }

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

}
