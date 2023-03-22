package com.cc.ed1.SistemaDeAgendamento.api.dto;

import javax.validation.constraints.NotBlank;

public class ClienteDTO {
	@NotBlank(message="O nome não deve estar vazio")
	private String nome;
	@NotBlank(message="O cpf não deve estar vazio")
	private String cpf;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
