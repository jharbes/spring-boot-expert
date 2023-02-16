package com.jharbes.vendasapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jharbes.vendasapp.entities.Produto;
import com.jharbes.vendasapp.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/{id}")
	public Produto getProdutoById(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não localizado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
}
