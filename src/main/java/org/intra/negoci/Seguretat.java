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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.intra.integracio.Departament;
import org.intra.integracio.Funcio;
import org.intra.integracio.PermisUsuari;
import org.intra.integracio.Usuari;
import org.intra.model.PermisMD;
import org.intra.model.UsuariMD;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateful
public class Seguretat {

    @Inject
    private Logger log;

    @Inject
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public Usuari getUsuariById(int id) {
    	return getUsuariById(id, false);
    }
    
   	public Usuari getUsuariById(Integer id, boolean b) {
		Usuari u=em.find(Usuari.class, id);
    	TypedQuery<PermisUsuari> query= em.createNamedQuery("PermisUsuari.findByUsuari", PermisUsuari.class);
//		List<PermisUsuari> permisos=query.setParameter("usuari", u).getResultList();
		log.info("getUsuari,permisos="+Integer.toString(u.getPermisos().size()));
//		log.info("getUsuari,permisos="+Integer.toString(permisos.size()));
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

	public UsuariMD saveUsuari(UsuariMD ori) {
		Usuari usuari;
		if (ori.getId()!=null && ori.getId()!=0) {
			log.info("Desant usuari "+ori.getId().toString()+" "+ori.getNom());
			usuari=getUsuariById(ori.getId(), true);
			
		} else {
			log.info("Desant nou usuari "+ori.getNom());
			usuari=new Usuari();
			usuari.setPermisos(new ArrayList<PermisUsuari>());
		}
		usuari.setNom(ori.getNom());
		usuari.setEmail(ori.getEmail());
		usuari.setIdioma(ori.getIdioma());
		usuari.setCertificat(ori.getCertificat());
		usuari.setContrasenya(ori.getContrasenya());
		
		Departament departament=getDepartamentById(ori.getDepartament().getId());
		usuari.setDepartament(departament);
		
		log.info("JPA size="+usuari.getPermisos().size());
		log.info("MD size="+ori.getPermisos().size());
		
		if (usuari.getId()==null) {
			em.persist(usuari);
		}

		for (PermisMD e:ori.getPermisos()) {
			log.info("MD="+e.getFuncio().getDescripcio());
			for (PermisUsuari p:usuari.getPermisos()) {
				log.info("JPA="+p.getFuncio().getDescripcio());
				if (e.getFuncio().getId().equals(p.getFuncio().getId())) {
					if (e.isDelete()) {
						log.info("Eliminant "+p.getFuncio().getDescripcio());
						em.remove(p);
						usuari.removePermis(p);
					} else {
						log.info("Actualitzant "+p.getFuncio().getDescripcio());
						p.setNivell(e.getNivell().ordinal());
						p.setContrasenya(e.getContrasenya());
					}
					e.setFuncio(null);
					break;
				}
			}
			if (e.getFuncio()!=null && !e.isDelete()) {
				log.info("Afegint "+e.getFuncio().getDescripcio());
				PermisUsuari nou=new PermisUsuari();
				Funcio funcio=getFuncioById(e.getFuncio().getId());
				nou.setContrasenya(e.getContrasenya());
				nou.setFuncio(funcio);
				nou.setNivell(e.getNivell().ordinal());
				usuari.addPermis(nou);
				em.persist(nou);
			}
		}
		em.flush();
		
		return new UsuariMD(usuari);
	}
}
