package org.intra.util;

import javax.inject.Named;

import model.NivellPermis;

//@Named
public class EdicioPermis {
	

	private Boolean contrasenya;

	private NivellPermis nivell;

	private Integer idFuncio;

	private String descripcioFuncio;
	
	private boolean delete;

	public Boolean getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(Boolean contrasenya) {
		this.contrasenya = contrasenya;
	}

	public NivellPermis getNivell() {
		return nivell;
	}

	public void setNivell(NivellPermis nivell) {
		this.nivell = nivell;
	}

	public Integer getIdFuncio() {
		return idFuncio;
	}

	public void setIdFuncio(Integer idFuncio) {
		this.idFuncio = idFuncio;
	}

	public String getDescripcioFuncio() {
		return descripcioFuncio;
	}

	public void setDescripcioFuncio(String descripcioFuncio) {
		this.descripcioFuncio = descripcioFuncio;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	
	
}
