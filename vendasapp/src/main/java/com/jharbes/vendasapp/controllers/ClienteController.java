package com.jharbes.vendasapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.repositories.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	// o @PathVariable indica que o id é uma variavel da url "/{id}" no caso
	@GetMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id); // retorna optional porque pode ou nao existir um
																	// cliente com esse id
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/api/clientes")
	@ResponseBody
	public ResponseEntity save(@RequestBody Cliente cliente) { // @RequestBody sao os valores da requisicao que chegam
																// para que sejam tratados
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
}
