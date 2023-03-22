package com.cc.ed1.SistemaDeAgendamento.api.restControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.ed1.SistemaDeAgendamento.api.dto.ClienteDTO;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Cliente;
import com.cc.ed1.SistemaDeAgendamento.domain.services.ClienteService;


@RestController
@RequestMapping("api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	@Autowired
	private ModelMapper mapper; 
	
	@GetMapping
	public List<ClienteDTO> getAll(){
		List<ClienteDTO> alunos = new ArrayList<ClienteDTO>();
		for(Cliente a: service.getAll()) {
			alunos.add(mapper.map(a, ClienteDTO.class));
		}
		return alunos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getAt(@PathVariable long id){
		ClienteDTO dto = mapper.map(service.getAt(id), ClienteDTO.class);
		if(dto!=null) {
			return new ResponseEntity<>(dto,HttpStatus.OK);
		}
		else return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> post(@Valid @RequestBody ClienteDTO dto){
		Cliente aluno = service.create(mapper.map(dto, Cliente.class));
		if(aluno!=null) {
			ClienteDTO dto2 = mapper.map(aluno, ClienteDTO.class);
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
