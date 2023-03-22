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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.ed1.SistemaDeAgendamento.api.dto.AtendenteDTO;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Atendente;
import com.cc.ed1.SistemaDeAgendamento.domain.services.AtendenteService;

@RestController
@RequestMapping("api/atendente")
public class AtendenteController {
	
	@Autowired
	private AtendenteService service;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public List<AtendenteDTO> getAll(){
		List<AtendenteDTO> alunos = new ArrayList<AtendenteDTO>();
		for(Atendente a: service.getAll()) {
			alunos.add(mapper.map(a, AtendenteDTO.class));
		}
		return alunos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AtendenteDTO> getAt(@PathVariable long id){
		AtendenteDTO dto = mapper.map(service.getAt(id), AtendenteDTO.class);
		if(dto!=null) {
			return new ResponseEntity<>(dto,HttpStatus.OK);
		}
		else return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<AtendenteDTO> post(@Valid @RequestBody AtendenteDTO dto){
		Atendente aluno = service.create(mapper.map(dto, Atendente.class));
		if(aluno!=null) {
			AtendenteDTO dto2 = mapper.map(aluno, AtendenteDTO.class);
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
