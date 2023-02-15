package com.jharbes.vendasapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.repositories.ClienteRepository;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	// o @PathVariable indica que o id é uma variavel da url "/{id}" no caso
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id); // retorna optional porque pode ou nao existir um
																	// cliente com esse id
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}
}
