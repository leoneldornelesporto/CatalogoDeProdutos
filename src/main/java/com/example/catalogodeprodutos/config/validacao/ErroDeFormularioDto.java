package com.example.catalogodeprodutos.config.validacao;

import org.springframework.http.HttpStatus;

public class ErroDeFormularioDto {

	private HttpStatus status_code;
	private String campo;
	private String erro;
	
	public ErroDeFormularioDto(HttpStatus status_code, String campo, String erro) {
		this.status_code = status_code;
		this.campo = campo;
		this.erro = erro;
	}

	public HttpStatus getStatus_code() {
		return status_code;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	

}
