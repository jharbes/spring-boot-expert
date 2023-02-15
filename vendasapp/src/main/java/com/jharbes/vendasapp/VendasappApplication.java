package com.jharbes.vendasapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.repositories.ClienteRepository;

@SpringBootApplication
public class VendasappApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(VendasappApplication.class, args);
	}

}
