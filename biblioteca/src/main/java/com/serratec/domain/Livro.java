package com.serratec.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="Preencha o titulo ")
	@Column(name = "livro_titulo")
	private String titulo;

	@NotNull(message="Preencha a quantidade de paginas ")
	@Column
	private Integer qtdPagina;

	@Embedded
	private Publicacao publicacao;

	public Livro(Long id, String titulo, Integer qtdPagina) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.qtdPagina = qtdPagina;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}
	
	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
	public Livro() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getQtdPagina() {
		return qtdPagina;
	}

	public void setQtdPagina(Integer qtdPagina) {
		this.qtdPagina = qtdPagina;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}

	
}
