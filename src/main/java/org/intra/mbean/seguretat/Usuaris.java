package org.intra.mbean.seguretat;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Usuari;
import org.intra.negoci.Seguretat;

import java.util.List;

@RequestScoped
public class Usuaris {

    @Inject
    private Seguretat seguretat;

    private List<Usuari> usuaris;

    // @Named provides access the return value via the EL variable name "usuaris" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Usuari> getUsuaris() {
        return usuaris;
    }

    public void onUsuariListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Usuari usuari) {
        retrieveAllUsuarisOrderedByName();
    }

    @PostConstruct
    public void retrieveAllUsuarisOrderedByName() {
        usuaris = seguretat.listUsuaris();
    }
}
