package com.jharbes.vendasapp.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// @Table so é utilizada caso a tabela tenha nome diferente da classe ou haja algum schema a ser determinado para a tabela
@Entity
@Table(name = "cliente")
public class Cliente {

	// annotation @Id é obrigatoria ter para que funcione o JPA, a annotation
	// GeneratedValue comanda a forma como sera gerada o ID (auto increment), o
	// @Column serve de maneira parecida com o @Table para a caso o atributo tenha
	// nome diferente no banco de dados podemos aqui referenciar isso, nao eh o caso
	// nesse exemplo mas colocaremos apenas pra registrar a possibilidade
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	// length limita o numero de caracteres desse atributo no banco de dados
	@Column(name = "nome", length = 100)
	private String nome;
	
	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos = new HashSet<>();

	public Cliente() {
	}

	public Cliente(String nome) {
		super();
		this.nome = nome;
	}

	public Cliente(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + "]";
	}

}
