package com.cc.ed1.SistemaDeAgendamento.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.ed1.SistemaDeAgendamento.domain.entities.Agendamento;
import com.cc.ed1.SistemaDeAgendamento.domain.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	AgendamentoRepository rep;
	
	public List<Agendamento> getAll(){
		return rep.findAll();
	}
	
	public Agendamento getAt(long id) {
		Agendamento dados = rep.findById(id);
		if(dados!=null) {
			return dados;
		}
		return null;
	}
	
	public Agendamento create(Agendamento obj, long id) {
		obj.setAtendenteId(id);
		return rep.save(obj);
	}
	
	public boolean delete(long id) {
		Agendamento dados = rep.findById(id);
		if(dados!=null) {
			rep.delete(dados);
			return true;
		}
		else return false;
	}
}
