package com.jharbes.vendasapp.services;

import org.springframework.stereotype.Service;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.repositories.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository repository;

	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}

	public void salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		this.repository.persistir(cliente);
	}

	public void validarCliente(Cliente cliente) {
		// aplica validações
	}
}