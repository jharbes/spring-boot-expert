package com.jharbes.vendasapp.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Table so é utilizada caso a tabela tenha nome diferente da classe ou haja algum schema a ser determinado para a tabela
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Column(name = "cpf", length = 11)
	private String cpf;

	// esse fetch type ja eh o default, foi colocado apenas para mostrar que existe,
	// o lazy nao tras as consultas de todos os pedidos automaticamente quando fizer
	// uma consulta de cliente

	// @JsonIgnore vai ignorar a propriedade pedidos quando o objeto for convertido
	// em json
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;



	public Cliente(String nome) {
		super();
		this.nome = nome;
	}

	public Cliente(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

}
