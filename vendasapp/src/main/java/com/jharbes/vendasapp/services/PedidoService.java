package com.jharbes.vendasapp.services;

import com.jharbes.vendasapp.dtos.PedidoDTO;
import com.jharbes.vendasapp.entities.Pedido;

public interface PedidoService {
	
	public Pedido salvar(PedidoDTO pedidoDto);
}
