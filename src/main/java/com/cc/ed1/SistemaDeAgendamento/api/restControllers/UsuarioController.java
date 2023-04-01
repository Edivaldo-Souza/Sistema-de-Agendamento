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

import com.cc.ed1.SistemaDeAgendamento.api.dto.CriarUsuarioDTO;
import com.cc.ed1.SistemaDeAgendamento.api.dto.UsuarioDTO;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Fila;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Usuario;
import com.cc.ed1.SistemaDeAgendamento.domain.services.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/{id}")
	public List<UsuarioDTO> getAt(@PathVariable long id){
		List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		try {
			Fila<Usuario> fila = service.getAt(id);
			while(!fila.isEmpty()) {
				if(fila.peek()!=null) {
					usuarios.add(mapper.map(fila.remover(), UsuarioDTO.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> post(@Valid @RequestBody CriarUsuarioDTO dto){
		
		Usuario agd;
		try {
			agd = service.create(mapper.map(dto, Usuario.class));
			if(agd!=null) {
				UsuarioDTO dto2 = mapper.map(agd, UsuarioDTO.class);
				return new ResponseEntity<>(dto2,HttpStatus.CREATED);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping
	public String delete(@RequestBody UsuarioDTO dto){
		try {
			if(service.delete(dto)) {
				return "usuario deletado deletado";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Nao foi possivel deletar";
	}
}
