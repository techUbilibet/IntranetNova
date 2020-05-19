package org.intra.model;

import javax.inject.Named;

import org.intra.integracio.PermisUsuari;

@Named
public class PermisMD {
	
	private FuncioMD funcio;

	private NivellPermis nivell;

	private Boolean contrasenya;

	private boolean delete;

	public PermisMD() {
		funcio=new FuncioMD();
		delete=false;
	}

	public PermisMD(PermisUsuari ori) {
		funcio= new FuncioMD(ori.getFuncio());
		nivell=NivellPermis.values()[ori.getNivell()];
		contrasenya=ori.getContrasenya();
		
		
	}

	public Boolean getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(Boolean contrasenya) {
		this.contrasenya = contrasenya;
	}

	public FuncioMD getFuncio() {
		return funcio;
	}

	public void setFuncio(FuncioMD funcio) {
		this.funcio = funcio;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public NivellPermis getNivell() {
		return nivell;
	}

	public void setNivell(NivellPermis nivell) {
		this.nivell = nivell;
	}

	
}
