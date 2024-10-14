package com.serratec.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class Autor {

	@Column
	private String nome;
	
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
