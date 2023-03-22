package com.cc.ed1.SistemaDeAgendamento.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.ed1.SistemaDeAgendamento.domain.entities.Atendente;
import com.cc.ed1.SistemaDeAgendamento.domain.repository.AtendenteRepository;

@Service
public class AtendenteService {
	@Autowired
	AtendenteRepository rep;
	
	public List<Atendente> getAll(){
		return rep.findAll();
	}
	
	public Atendente getAt(long id) {
		Atendente dados = rep.findById(id);
		if(dados!=null) {
			return dados;
		}
		return null;
	}
	
	public Atendente create(Atendente obj) {
		return rep.save(obj);
	}
	
	public boolean delete(long id) {
		Atendente atendente = rep.findById(id);
		if(atendente!=null) {
			rep.delete(atendente);
			return true;
		}
		else return false;
	}
}
