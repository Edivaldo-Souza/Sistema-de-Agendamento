package com.cc.ed1.SistemaDeAgendamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.ed1.SistemaDeAgendamento.domain.entities.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long>{
	Agendamento findById(long id);
	Agendamento findByClienteCpf(String cpf);
}
