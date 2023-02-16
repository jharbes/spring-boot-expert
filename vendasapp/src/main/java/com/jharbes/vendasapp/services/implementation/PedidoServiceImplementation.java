package com.jharbes.vendasapp.services.implementation;

import org.springframework.stereotype.Service;

import com.jharbes.vendasapp.repositories.PedidoRepository;
import com.jharbes.vendasapp.services.PedidoService;

@Service
public class PedidoServiceImplementation implements PedidoService {
	
	private PedidoRepository pedidoRepository;

	public PedidoServiceImplementation(PedidoRepository pedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
	}
	
}
