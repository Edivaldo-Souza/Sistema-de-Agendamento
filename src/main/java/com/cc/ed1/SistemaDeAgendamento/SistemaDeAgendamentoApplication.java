package com.cc.ed1.SistemaDeAgendamento;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaDeAgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeAgendamentoApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
