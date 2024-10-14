package com.serratec.exception;


import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

	private Integer status;
	private String titulo;
	private LocalDateTime datahora;
	private List<String> erros;

	public ErrorResponse(Integer status, String titulo, LocalDateTime datahora, List<String> erros) {
		this.status = status;
		this.titulo = titulo;
		this.datahora = datahora;
		this.erros = erros;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getDatahora() {
		return datahora;
	}

	public void setDatahora(LocalDateTime datahora) {
		this.datahora = datahora;
	}

}
