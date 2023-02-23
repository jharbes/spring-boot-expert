package com.jharbes.vendasapp.services;

import java.util.Optional;

import com.jharbes.vendasapp.dtos.PedidoDTO;
import com.jharbes.vendasapp.entities.Pedido;
import com.jharbes.vendasapp.enums.StatusPedido;

public interface PedidoService {
	
	public Pedido salvar(PedidoDTO pedidoDto);
	
	public Optional<Pedido> obterPedidoCompleto(Integer id);
	
	public void atualizaStatus(Integer id, StatusPedido statusPedido);
}
