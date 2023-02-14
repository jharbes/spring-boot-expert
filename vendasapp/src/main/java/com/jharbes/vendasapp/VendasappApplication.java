package com.jharbes.vendasapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.repositories.ClienteRepository;

@SpringBootApplication
public class VendasappApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
		return args -> {
			System.out.println("Salvando clientes");

			clienteRepository.save(new Cliente("Jorge Nami Harbes"));
			clienteRepository.save(new Cliente("Marcos Attilio Brasil Harbes"));
			clienteRepository.save(new Cliente("Matheus Attilio Brasil Harbes"));

			boolean existe = clienteRepository.existsByNome("Jorge Nami Harbes");
			List<Cliente> result = clienteRepository.encontrarPorNome("Marcos Attilio Brasil Harbes");
			System.out.println("existe um cliente com o nome Jorge Nami Harbes	? " + existe);
			result.forEach(c -> System.out.println(c));
			result.forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasappApplication.class, args);
	}

}
