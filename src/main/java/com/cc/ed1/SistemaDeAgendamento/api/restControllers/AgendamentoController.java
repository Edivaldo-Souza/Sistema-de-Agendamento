package com.cc.ed1.SistemaDeAgendamento.api.restControllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.ed1.SistemaDeAgendamento.api.dto.AgendamentoDTO;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Agendamento;
import com.cc.ed1.SistemaDeAgendamento.domain.services.AgendamentoService;

@RestController
@RequestMapping("api/agendamento")
public class AgendamentoController {

	@Autowired
	private AgendamentoService service;
	@Autowired
	private ModelMapper mapper; 
	
	@GetMapping
	public List<AgendamentoDTO> getAll(){
		List<AgendamentoDTO> alunos = new ArrayList<AgendamentoDTO>();
		for(Agendamento a: service.getAll()) {
			alunos.add(mapper.map(a, AgendamentoDTO.class));
		}
		return alunos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AgendamentoDTO> getAt(@PathVariable long id){
		AgendamentoDTO dto = mapper.map(service.getAt(id), AgendamentoDTO.class);
		if(dto!=null) {
			return new ResponseEntity<>(dto,HttpStatus.OK);
		}
		else return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/{id_atendente}")
	public ResponseEntity<AgendamentoDTO> post(@Valid @RequestBody AgendamentoDTO dto, @PathVariable long id_atendente){
		Agendamento aluno = service.create(mapper.map(dto, Agendamento.class),id_atendente);
		if(aluno!=null) {
			AgendamentoDTO dto2 = mapper.map(aluno, AgendamentoDTO.class);
			return new ResponseEntity<>(dto2,HttpStatus.CREATED);
		}
		else return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable long id){
		if(service.delete(id)) {
			return "Cliente deletado";
		}
		else {
			return "Nao foi possivel deletar";
		}
	}
}
