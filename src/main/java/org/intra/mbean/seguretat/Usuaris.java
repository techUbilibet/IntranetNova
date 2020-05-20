package org.intra.mbean.seguretat;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Usuari;
import org.intra.negoci.Seguretat;

import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class Usuaris {

//	@Inject
//    private FacesContext context;
//
	@Inject
    private Logger log;

    @Inject
    private Seguretat seguretat;

    @Produces
    @Named
    private List<Usuari> llistaUsuaris;

    public void onUsuariListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Usuari usuari) {
        retrieveAllUsuarisOrderedByName();
    }

    @PostConstruct
    public void retrieveAllUsuarisOrderedByName() {
        llistaUsuaris = seguretat.listUsuaris();
    }
    
}
