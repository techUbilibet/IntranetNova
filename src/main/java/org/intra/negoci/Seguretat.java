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
import javax.persistence.Query;
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
    
   	public Usuari getUsuariById(Integer id, boolean ambPermisos) {
		Usuari u=em.find(Usuari.class, id);
		if (u!=null && ambPermisos) {
			log.info("getUsuari,permisos="+Integer.toString(u.getPermisos().size()));
		}
        return u;
    }


    public Usuari getUsuariByEmail(String email) {
    	TypedQuery<Usuari> query= em.createNamedQuery("Usuari.findByEmail", Usuari.class);
    	try {
    		Usuari u=query.setParameter("email", email).getSingleResult();
    		if (u==null) return null;
    		em.refresh(u);
			log.info("getUsuariEmail,permisos="+Integer.toString(u.getPermisos().size()));
    		em.refresh(u);
			log.info("getUsuariEmail,permisos="+Integer.toString(u.getPermisos().size()));
//			for (PermisUsuari p:u.getPermisos()) {
//				log.info("getUsuariEmail,permis="+p.getFuncio().getId().toString());
//			}
        	return u;
    	} catch(NoResultException e) {
    		return null;
    	} catch(Exception e) {
    		//avisar de l'error
    		return null;
    	}
    }
    	
    public List<Usuari> listUsuaris(String ordre) {
    	String sql="SELECT u FROM Usuari u order by u."+ordre;
    	log.info(sql);
    	TypedQuery<Usuari> query= em.createQuery(sql, Usuari.class);
    	List<Usuari> list=query.getResultList();
    	return list;
    }

	public Departament getDepartamentById(Integer id) {
		return getDepartamentById(id, false);
	}
	public Departament getDepartamentById(Integer id, boolean ambPermisos) {
        log.info("llegint " + id.toString());
        Departament d=em.find(Departament.class, id);
		if (d!=null && ambPermisos) {
			log.info("getDepartament,permisos="+Integer.toString(d.getPermisos().size()));
		}
        return d;
	}

	public List<Departament> listDepartaments() {
		TypedQuery<Departament> query= em.createNamedQuery("Departament.findAll", Departament.class);
    	List<Departament> list=query.getResultList();
    	return list;
	}
    
	public List<Departament> listDepartamentsNom(String nom) {
		TypedQuery<Departament> query= em.createNamedQuery("Departament.findLikeName", Departament.class);
		if (nom==null) nom="%";
		else nom="%"+nom.toLowerCase()+"%";
		log.info("cercar "+nom);
		query.setParameter("nom", nom);
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

		for (PermisUsuari p:usuari.getPermisos()) {
			log.info("JPA="+p.getFuncio().getDescripcio());
			log.info("Eliminant "+p.getFuncio().getDescripcio());
			em.remove(p);
		}
		usuari.setPermisos(new ArrayList<PermisUsuari>());
		for (PermisMD e:ori.getPermisos()) {
			log.info("MD="+e.getFuncio().getDescripcio());
			if (e.isDelete()) continue;
			PermisUsuari nou=new PermisUsuari();
			Funcio funcio=getFuncioById(e.getFuncio().getId());
			nou.setContrasenya(e.getContrasenya());
			nou.setFuncio(funcio);
			nou.setNivell(e.getNivell().ordinal());
			usuari.addPermis(nou);
			em.persist(nou);
		}
		em.flush();
		
		return new UsuariMD(usuari);
	}

	public boolean eliminarUsuari(UsuariMD usuari) {
		try {
			Usuari u=getUsuariById(usuari.getId());
			for (PermisUsuari p:u.getPermisos()) {
				em.remove(p);
			}
			em.remove(u);
    	} catch(Exception e) {
    		return false;
    	}
		return true;
	}

}
