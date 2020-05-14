/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.intra.negoci;

import javax.ejb.Stateful;
//import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.intra.integracio.Departament;
import org.intra.integracio.Funcio;
import org.intra.integracio.Usuari;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateful
//@ApplicationScoped
public class Seguretat {

    @Inject
    private Logger log;

    @Inject
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public Usuari getUsuariById(int i) {
		Usuari u=em.find(Usuari.class, i);
        return u;
    }


    public Usuari getUsuariByEmail(String email) {
    	TypedQuery<Usuari> query= em.createNamedQuery("Usuari.findByEmail", Usuari.class);
    	try {
    		Usuari u=query.setParameter("email", email).getSingleResult();
    		if (u==null) return null;
        	return u;
    	} catch(NoResultException e) {
    		return null;
    	} catch(Exception e) {
    		//avisar de l'error
    		return null;
    	}
    }
    	
    public List<Usuari> listUsuaris() {
    	TypedQuery<Usuari> query= em.createNamedQuery("Usuari.findAll", Usuari.class);
    	List<Usuari> list=query.getResultList();
    	return list;
    }

	public Departament getDepartamentById(Integer id) {
        log.info("llegint " + id.toString());
        Departament d=em.find(Departament.class, id);
        return d;
	}

	public List<Departament> listDepartaments() {
		TypedQuery<Departament> query= em.createNamedQuery("Departament.findAll", Departament.class);
    	List<Departament> list=query.getResultList();
    	return list;
	}
    
//    @Inject
//    private Event<Usuari> usuariEventSrc;

    public void save(Usuari usuari) throws Exception {
//        log.info(usuari.toString());
//        log.info("Registering " + usuari.getNom());
//        Usuari desat;
//        if (usuari.getId()!=null && usuari.getId()!=0) {
//        	desat=em.find(Usuari.class, usuari.getId());
//        	if (desat==null) throw new EntityNotFoundException("Usuari:"+usuari.getId().toString()); 
//        	setUsuariData(desat, usuari);
//        	em.flush();
//        } else
//        	desat=new Usuari();
//        	setUsuariData(desat, usuari);
//        	em.persist(desat);
    }


//	private void setUsuariData(Usuari desat, Usuari usuari) {
//		desat.setContrasenya(usuari.getContrasenya());
//		Departament d=em.find(Departament.class, usuari.getDepartament().getId());
//		desat.setDepartament(d);
//		desat.setEmail(usuari.getEmail());
//		desat.setIdioma(usuari.getIdioma());
//		desat.setNom(usuari.getNom());
//	}

	public List<Funcio> listFunctions() {
		TypedQuery<Funcio> query= em.createNamedQuery("Funcio.findAll", Funcio.class);
    	List<Funcio> funcs=query.getResultList();
    	return funcs;
	}

	public Funcio getFuncioById(Integer id) {
        log.info("llegint " + id.toString());
        Funcio f=em.find(Funcio.class, id);
        if (f!=null) {
        	log.info("trobat " + f.getDescripcio());
        	return f;
        }
        log.info("no trobat " + id.toString());
        return null;
	}


}
