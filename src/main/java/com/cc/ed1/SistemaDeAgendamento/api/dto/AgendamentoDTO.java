package com.cc.ed1.SistemaDeAgendamento.api.dto;

import javax.validation.constraints.NotBlank;

public class AgendamentoDTO {

	private long atendenteId;
	@NotBlank(message="O cpf não deve estar vazio")
	private String clienteCpf;
	@NotBlank(message="O horario não deve estar vazio")
	private String horario;
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getClienteCpf() {
		return clienteCpf;
	}
	public void setClienteCpf(String clienteCpf) {
		this.clienteCpf = clienteCpf;
	}
	public long getAtendenteId() {
		return atendenteId;
	}
	public void setAtendenteId(long atendenteId) {
		this.atendenteId = atendenteId;
	}
}
