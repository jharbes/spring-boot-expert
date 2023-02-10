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
			Cliente cliente = new Cliente();

			cliente.setNome("Jorge Nami Harbes");
			clienteRepository.salvar(cliente);

			Cliente cliente2 = new Cliente();

			cliente2.setNome("Carolina Ferreira Alcantara");
			clienteRepository.salvar(cliente2);

			List<Cliente> todosClientes = clienteRepository.obterTodos();
			todosClientes.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasappApplication.class, args);
	}

}
