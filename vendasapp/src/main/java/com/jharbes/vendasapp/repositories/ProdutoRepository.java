package com.jharbes.vendasapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jharbes.vendasapp.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
