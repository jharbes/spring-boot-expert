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
			Cliente cliente = new Cliente();

			cliente.setNome("Jorge Nami Harbes");
			clienteRepository.salvar(cliente);

			Cliente cliente2 = new Cliente();

			cliente2.setNome("Carolina Ferreira Alcantara");
			clienteRepository.salvar(cliente2);

			List<Cliente> todosClientes = clienteRepository.obterTodos();
			todosClientes.forEach(System.out::println);
			
			
			System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clienteRepository.atualizar(c);
            });

            todosClientes = clienteRepository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clienteRepository.buscarPorNome("Jor").forEach(System.out::println);
            

//            System.out.println("deletando clientes");
//            clientes.obterTodos().forEach(c -> {
//                clientes.deletar(c);
//            });

            todosClientes = clienteRepository.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
            
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasappApplication.class, args);
	}

}
