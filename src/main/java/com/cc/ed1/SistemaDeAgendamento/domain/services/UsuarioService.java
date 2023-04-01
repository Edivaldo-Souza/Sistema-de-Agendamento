package com.cc.ed1.SistemaDeAgendamento.domain.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.cc.ed1.SistemaDeAgendamento.api.dto.UsuarioDTO;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Fila;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Usuario;
import com.opencsv.CSVWriter;

@Service
public class UsuarioService {
	private String path = "src/main/java/com/cc/ed1/SistemaDeAgendamento/domain/tabelas/tb_usuarios.csv";
	
	public Fila<Usuario> getAt(long id) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String linha = "";
		
		Fila<Usuario> fila = new Fila<Usuario>(5);

		while(linha!=null) {
			linha = br.readLine();
			if(linha!=null) {
				String[] dados = linha.split(";");
				if(Long.valueOf(dados[0])==id) {
					Usuario user = new Usuario();
					user.setId(Long.valueOf(dados[0]));
					user.setNome(dados[1]);
					user.setSenha(dados[2]);
					if(dados[3].compareTo("1")==0)user.setAdmin(true);
					else user.setAdmin(false);
					fila.adicionar(user);
				}
			}
		}
		
		br.close();
		return fila;
	}
	
	public Usuario create(Usuario obj) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(path,true));
		
		String isAdmin;
		if(obj.isAdmin()) isAdmin = "1";
		else isAdmin = "0";
		
		String text = obj.getId()+";"+obj.getNome()+";"+obj.getSenha()+";"+isAdmin+"\n";
		
		bw.append(text);
		bw.close();
		return obj;
	}
	
	public boolean delete(UsuarioDTO user) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String linha = "";
		Fila<Usuario> fila = new Fila<Usuario>(5);
		
		while(linha!=null) {
			linha = br.readLine();
			if(linha!=null) {
				String[] dados = linha.split(";");
				if(Long.valueOf(dados[0])!=user.getId() && dados[1].compareTo(user.getNome())!=0) {
					Usuario obj = new Usuario();
					obj.setId(Long.valueOf(dados[0]));
					obj.setNome(dados[1]);
					obj.setSenha(dados[2]);
					if(dados[3].compareTo("1")==0) obj.setAdmin(true);
					else obj.setAdmin(false);
					fila.adicionar(obj);
				}
			}
		}
		br.close();
		
		CSVWriter csvWr = new CSVWriter(new FileWriter(path));
		String[] text = new String[3];
		
		while(!fila.isEmpty()) {
			text[0] = fila.peek().getId()+";";
			text[1] = fila.peek().getNome()+";";
			text[2] = fila.peek().getSenha()+";";
			if(fila.peek().isAdmin()) text[3] = "1\n";
			else text[3] = "0\n";
			csvWr.writeNext(text);
			
			fila.remover();
		}
		return true;
	}
}
