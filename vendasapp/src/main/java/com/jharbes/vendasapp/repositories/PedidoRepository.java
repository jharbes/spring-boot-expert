package com.jharbes.vendasapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	public List<Pedido> findByCliente(Cliente cliente);
	
	@Query(" select p from Pedido p left join fetch p.items where p.id = :id ")
    public Optional<Pedido> findByIdFetchItems(@Param("id") Integer id);
}
