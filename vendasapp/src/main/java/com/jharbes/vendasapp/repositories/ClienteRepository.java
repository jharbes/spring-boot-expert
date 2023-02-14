package com.jharbes.vendasapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jharbes.vendasapp.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	// aqui utilizamos a notacao @Query para inserir uma query personalizada, ou
	// seja nao utilizaremos as querys automaticas do JpaRepository
	// @Param serve para referenciar o nome do parametro em questao

	// podemos tb fazer um consulta sql nativa como por exemplo:
	// @Query(value="select * from cliente where c.nome like '%:nome%'",
	// nativeQuery=true)
	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);

	@Query("delete from Cliente c where c.nome=:nome") // opcional
	@Modifying // apenas no uso de query manual que modifica
	public void deleteByNome(String nome);

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

	// left join para trazer os clientes tendo eles pedidos ou nao vinculados
	@Query("select c from Cliente c left join fetch c.pedidos p where c.id = :id")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
	
	Cliente findClienteById(Integer id);
}
