package com.jharbes.vendasapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class AppConfiguration {
	
	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("RODANDO A CONFIGURACAO DE DESENVOLVIMENTO");
		};
	}
}
