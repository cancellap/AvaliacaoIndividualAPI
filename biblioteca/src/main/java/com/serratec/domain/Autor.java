package com.serratec.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;


@Embeddable
public class Autor {
	
	@NotBlank(message="Preencha o nome do autor ")
	@Column
	private String nome;
	
	@NotBlank(message="Preencha a nacionalidade do autor ")
	@Column
	private String nacionalidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	
}
