package com.jharbes.vendasapp.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jharbes.vendasapp.dtos.InformacaoItemPedidoDTO;
import com.jharbes.vendasapp.dtos.InformacoesPedidoDTO;
import com.jharbes.vendasapp.dtos.PedidoDTO;
import com.jharbes.vendasapp.entities.ItemPedido;
import com.jharbes.vendasapp.entities.Pedido;
import com.jharbes.vendasapp.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDTO pedidoDto) {
		Pedido pedido = pedidoService.salvar(pedidoDto);
		return pedido.getId();
	}

	@GetMapping("/{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return pedidoService.obterPedidoCompleto(id).map(p -> converter(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
	}

	private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .totalPedido(pedido.getTotalPedido())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItems()))
                .build();
    }

	private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> items) {
		if (CollectionUtils.isEmpty(items))
			return Collections.emptyList();

		return items.stream()
				.map(item -> InformacaoItemPedidoDTO
						.builder().descricaoProduto(item.getProduto().getDescricao())
						.precoUnitario(item.getProduto().getPreco())
						.quantidade(item.getQuantidade())
						.build())
				.collect(Collectors.toList());
	}
}
