package com.cc.ed1.SistemaDeAgendamento.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.ed1.SistemaDeAgendamento.domain.repository.ClienteRepository;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Cliente;


@Service
public class ClienteService {
	@Autowired
	ClienteRepository rep;
	
	public List<Cliente> getAll(){
		return rep.findAll();
	}
	
	public Cliente getAt(long id) {
		Cliente dados = rep.findById(id);
		if(dados!=null) {
			return dados;
		}
		return null;
	}
	
	public Cliente create(Cliente obj) {
		return rep.save(obj);
	}
	
	public boolean delete(long id) {
		Cliente cliente = rep.findById(id);
		if(cliente!=null) {
			rep.delete(cliente);
			return true;
		}
		else return false;
	}
	
}
