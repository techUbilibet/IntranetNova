package org.intra.model;

import javax.inject.Named;

import org.intra.integracio.Funcio;

@Named
public class FuncioMD {

	private Integer id;
	private String comentari;
	private String descripcio;

	public FuncioMD() {
	}

	public FuncioMD(Funcio ori) {
		id=ori.getId();
		comentari=ori.getComentari();
		descripcio=ori.getDescripcio();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentari() {
		return comentari;
	}

	public void setComentari(String comentari) {
		this.comentari = comentari;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	
	
}
