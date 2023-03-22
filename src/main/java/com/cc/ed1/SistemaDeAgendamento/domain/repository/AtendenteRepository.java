package com.cc.ed1.SistemaDeAgendamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.ed1.SistemaDeAgendamento.domain.entities.Atendente;

public interface AtendenteRepository extends JpaRepository<Atendente,Long>{
	Atendente findById(long id);
}
