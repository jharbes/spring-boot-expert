package com.jharbes.vendasapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jharbes.vendasapp.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String nome);

}
