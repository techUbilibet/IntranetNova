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
package org.intra.mbean.seguretat;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.intra.integracio.Departament;
import org.intra.integracio.Usuari;
import org.intra.negoci.Seguretat;

import java.util.List;

@RequestScoped
public class Departaments {

    @Inject
    private Seguretat seguretat;
    
    private List<Departament> departaments;

    @Produces
    @Named
    public List<Departament> getDepartaments() {
        return departaments;
    }

    public void onDepartamentListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Usuari usuari) {
        retrieveAllDepartaments();
    }

    @PostConstruct
    public void retrieveAllDepartaments() {
        departaments = seguretat.listDepartaments();
    }
}
