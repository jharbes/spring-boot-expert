package com.jharbes.vendasapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jharbes.vendasapp.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
