package com.cc.ed1.SistemaDeAgendamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.ed1.SistemaDeAgendamento.domain.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
	Cliente findByCpf(String cpf);
	Cliente findById(long id);
}
