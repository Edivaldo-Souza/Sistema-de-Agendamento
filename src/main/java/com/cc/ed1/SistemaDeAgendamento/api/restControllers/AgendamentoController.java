package com.cc.ed1.SistemaDeAgendamento.api.restControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.ed1.SistemaDeAgendamento.api.dto.AgendamentoDTO;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Agendamento;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Fila;
import com.cc.ed1.SistemaDeAgendamento.domain.services.AgendamentoService;

@RestController
@RequestMapping("api/agendamento")
public class AgendamentoController {

	@Autowired
	private AgendamentoService service;
	@Autowired
	private ModelMapper mapper; 
	
	@GetMapping("/{id}")
	public List<AgendamentoDTO> getAt(@PathVariable long id){
		List<AgendamentoDTO> agds = new ArrayList<AgendamentoDTO>();
		try {
			Fila<Agendamento> fila = service.getAt(id);
			while(!fila.isEmpty()) {
				if(fila.peek()!=null) {
					agds.add(mapper.map(fila.remover(), AgendamentoDTO.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agds;
	}
	
	@PostMapping("/{id_atendente}")
	public ResponseEntity<AgendamentoDTO> post(@Valid @RequestBody AgendamentoDTO dto, @PathVariable long id_atendente){
		
		Agendamento agd;
		try {
			agd = service.create(mapper.map(dto, Agendamento.class),id_atendente);
			if(agd!=null) {
				AgendamentoDTO dto2 = mapper.map(agd, AgendamentoDTO.class);
				return new ResponseEntity<>(dto2,HttpStatus.CREATED);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@RequestBody AgendamentoDTO dto, @PathVariable long id){
		try {
			if(service.delete(dto.getClienteCpf(),dto.getHorario(),id)) {
				return "Agendamento deletado";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Nao foi possivel deletar";
	}
}
