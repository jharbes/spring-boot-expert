package com.jharbes.vendasapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jharbes.vendasapp.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	// o proprio jpa identifica as propriedades pelo nome que estao nas classes como
	// "nome" e "id" e ao inserir elas no nome dos metodos ele ja cria suas querys
	// automaticamente, o like no fim tambem funcionara como o like do sql (like
	// apenas para strings)
	List<Cliente> findByNomeLike(String nome);

	// parametros do metodo devem sempre estar na mesma ordem que a ordem no nome do
	// metodo
	List<Cliente> findByNomeOrId(String nome, Integer id);

	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

	// Cliente findOneByCpf(String cpf);

	// os nomes dos metodos como find, exists, By etc sao convencoes utilizadas que
	// o sistema ja entende suas funcionalidades
	boolean existsByNome(String nome);
}
