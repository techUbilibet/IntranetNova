package org.intra.model;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;

@Model
public class Credencials {
	private String email;
	private String contrasenya;

	@NotNull
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}



}
