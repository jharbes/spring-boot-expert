package com.jharbes.vendasapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	public List<Pedido> findByCliente(Cliente cliente);
}
