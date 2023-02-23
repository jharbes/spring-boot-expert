package com.jharbes.vendasapp.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jharbes.vendasapp.dtos.ItemPedidoDTO;
import com.jharbes.vendasapp.dtos.PedidoDTO;
import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.entities.ItemPedido;
import com.jharbes.vendasapp.entities.Pedido;
import com.jharbes.vendasapp.entities.Produto;
import com.jharbes.vendasapp.enums.StatusPedido;
import com.jharbes.vendasapp.exceptions.PedidoNaoEncontradoException;
import com.jharbes.vendasapp.exceptions.RegraNegocioException;
import com.jharbes.vendasapp.repositories.ClienteRepository;
import com.jharbes.vendasapp.repositories.ItemPedidoRepository;
import com.jharbes.vendasapp.repositories.PedidoRepository;
import com.jharbes.vendasapp.repositories.ProdutoRepository;
import com.jharbes.vendasapp.services.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // injecao de dependencia via construtor, instacia todos os atributos com final
							// que sao os que devem ser instaciados na criacao da classe
public class PedidoServiceImplementation implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;
	private final ItemPedidoRepository itemPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO pedidoDto) {
		Integer idCliente = pedidoDto.getCliente();
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

		Pedido pedido = new Pedido();
		pedido.setTotalPedido(pedidoDto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);

		List<ItemPedido> itemPedido = converterItems(pedido, pedidoDto.getItems());
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(itemPedido);

		pedido.setItens(itemPedido);

		return pedido;
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itemsPedidoDto) {
		if (itemsPedidoDto.isEmpty()) {
			throw new RegraNegocioException("Não é possível fazer um pedido sem items!");
		}

		return itemsPedidoDto.stream().map(pedidoDto -> {
			Integer idProduto = pedidoDto.getProduto();
			Produto produto = produtoRepository.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(pedidoDto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);

			return itemPedido;
		}).collect(Collectors.toList());

	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidoRepository.findByIdFetchItems(id);
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {
		pedidoRepository.findById(id).map(pedido -> {
			pedido.setStatus(statusPedido);
			return pedidoRepository.save(pedido);
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
	}

}
