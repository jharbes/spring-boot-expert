package com.jharbes.vendasapp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jharbes.vendasapp.entities.Cliente;

@Repository
public class ClienteRepository {

	private static String INSERT = "insert into cliente (nome) values (?) ";
	private static String SELECT_ALL = "SELECT * FROM CLIENTE";
	private static String DELETE = "delete from cliente where id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		entityManager.persist(cliente);
		return cliente;
	}

	@Transactional
	public Cliente atualizar(Cliente cliente) {
		entityManager.merge(cliente);
		return cliente;
	}

	@Transactional
	public void deletar(Cliente cliente) {
		if (!entityManager.contains(cliente))
			cliente = entityManager.merge(cliente);

		entityManager.remove(cliente);
	}

	@Transactional
	public void deletar(Integer id) {
		entityManager.remove(entityManager.find(Cliente.class, id));
	}

	// operacao apenas de leitura, sera otimizada
	@Transactional(readOnly = true)
	public List<Cliente> buscarPorNome(String nome) {
		// os dois pontos abaixo na string serve para indicar um parametro jpa
		String jpql = "select c from Cliente c where c.nome like :nome";
		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

	@Transactional
	public List<Cliente> obterTodos() {
		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	private RowMapper<Cliente> obterClienteMapper() {
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
				return new Cliente(resultSet.getInt("id"), resultSet.getString("nome"));
			}
		};
	}
}
