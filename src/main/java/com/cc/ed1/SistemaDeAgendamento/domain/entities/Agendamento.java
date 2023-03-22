package com.cc.ed1.SistemaDeAgendamento.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agendamentos")
public class Agendamento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String horario;
	private String clienteCpf;
	private long atendenteId;
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
