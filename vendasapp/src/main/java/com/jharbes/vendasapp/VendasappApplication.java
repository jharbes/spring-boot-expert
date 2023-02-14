package com.jharbes.vendasapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.entities.Pedido;
import com.jharbes.vendasapp.repositories.ClienteRepository;
import com.jharbes.vendasapp.repositories.PedidoRepository;

@SpringBootApplication
public class VendasappApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRepository,
			@Autowired PedidoRepository pedidoRepository) {
		return args -> {
			System.out.println("Salvando clientes");

			Cliente jorge = new Cliente("Jorge Nami Harbes");
			clienteRepository.save(jorge);

			Pedido p = new Pedido();
			p.setCliente(jorge);
			p.setDataPedido(LocalDate.now());
			p.setTotalPedido(BigDecimal.valueOf(100));

			pedidoRepository.save(p);

			Cliente cliente = clienteRepository.findClienteFetchPedidos(jorge.getId());

			boolean existe = clienteRepository.existsByNome("Jorge Nami Harbes");
			List<Cliente> result = clienteRepository.encontrarPorNome("Marcos Attilio Brasil Harbes");
			System.out.println("existe um cliente com o nome Jorge Nami Harbes	? " + existe);
			result.forEach(c -> System.out.println(c));
			result.forEach(System.out::println);

			System.out.println(p);
			System.out.println(cliente);
			System.out.println(cliente.getPedidos());

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasappApplication.class, args);
	}

}
