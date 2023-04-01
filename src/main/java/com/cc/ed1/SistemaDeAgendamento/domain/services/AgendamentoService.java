package com.cc.ed1.SistemaDeAgendamento.domain.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.buf.B2CConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.ed1.SistemaDeAgendamento.domain.entities.Agendamento;
import com.cc.ed1.SistemaDeAgendamento.domain.entities.Fila;
import com.opencsv.CSVWriter;

@Service
public class AgendamentoService {
	
	private String path = "src/main/java/com/cc/ed1/SistemaDeAgendamento/domain/tabelas/tb_agendamentos.csv";
	
	public Fila<Agendamento> getAt(long id) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String linha = "";
		
		Fila<Agendamento> fila = new Fila<Agendamento>(5);

		while(linha!=null) {
			linha = br.readLine();
			if(linha!=null) {
				String[] dados = linha.split(";");
				if(Long.valueOf(dados[0])==id) {
					Agendamento agd = new Agendamento();
					agd.setAtendenteId(Long.valueOf(dados[0]));
					agd.setClienteCpf(dados[1]);
					agd.setHorario(dados[2]);
					fila.adicionar(agd);
				}
			}
		}
		
		br.close();
		return fila;
	}
	
	public Agendamento create(Agendamento obj, long id) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(path,true));
		
		obj.setAtendenteId(id);
		
		String text = obj.getAtendenteId()+";"+obj.getClienteCpf()+";"+obj.getHorario()+"\n";
		
		bw.append(text);
		bw.close();
		return obj;
	}
	
	public boolean delete(String cpf, String horario, long id) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String linha = "";
		Fila<Agendamento> fila = new Fila<Agendamento>(5);
		
		while(linha!=null) {
			linha = br.readLine();
			if(linha!=null) {
				String[] dados = linha.split(";");
				if(Long.valueOf(dados[0])!=id && dados[1].compareTo(cpf)!=0 && dados[2].compareTo(horario)!=0) {
					Agendamento agd = new Agendamento();
					agd.setAtendenteId(Long.valueOf(dados[0]));
					agd.setClienteCpf(dados[1]);
					agd.setHorario(dados[2]);
					fila.adicionar(agd);
				}
			}
		}
		br.close();
		
		CSVWriter csvWr = new CSVWriter(new FileWriter(path));
		String[] text = new String[3];
		
		while(!fila.isEmpty()) {
			text[0] = fila.peek().getAtendenteId()+";";
			text[1] = fila.peek().getClienteCpf()+";";
			text[2] = fila.peek().getHorario()+"\n";
			csvWr.writeNext(text);
			
			fila.remover();
		}
		return true;
	}
}
