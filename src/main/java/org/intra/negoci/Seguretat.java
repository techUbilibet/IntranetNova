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
import org.intra.util.EdicioPermis;

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
		List<PermisUsuari> permisos=query.setParameter("usuari", u).getResultList();
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

	public Integer saveUsuari(Integer id, String nom, String email, Integer idioma, Boolean certificat,
			String contrasenya, Integer idDepartament, List<EdicioPermis> permisos) {
		Usuari usuari;
		if (id!=null && id!=0) {
			log.info("Desant usuari "+id.toString()+" "+nom);
			usuari=getUsuariById(id, true);
			
		} else {
			log.info("Desant nou usuari "+nom);
			usuari=new Usuari();
			usuari.setPermisos(new ArrayList<PermisUsuari>());
		}
		usuari.setCertificat(certificat);
		usuari.setContrasenya(contrasenya);
		Departament departament=getDepartamentById(idDepartament);
		usuari.setDepartament(departament);
		usuari.setEmail(email);
		usuari.setIdioma(idioma);
		usuari.setNom(nom);

		for (EdicioPermis e:permisos) {
			for (PermisUsuari p:usuari.getPermisos()) {
				if (e.getIdFuncio().equals(p.getFuncio().getId())) {
					if (e.isDelete()) {
						usuari.removePermisos(p);
					} else {
						p.setNivell(e.getNivell().ordinal());
						p.setContrasenya(e.getContrasenya());
					}
					e.setIdFuncio(null);
					break;
				}
			}
			if (e.getIdFuncio()!=null) {
				PermisUsuari nou=new PermisUsuari();
				Funcio funcio=getFuncioById(e.getIdFuncio());
				nou.setContrasenya(e.getContrasenya());
				nou.setFuncio(funcio);
				nou.setNivell(e.getNivell().ordinal());
				usuari.addPermisos(nou);
			}
		}
		if (usuari.getId()==null) {
			em.persist(usuari);
		} else {
			em.flush();
		}
		return usuari.getId();
	}

}
