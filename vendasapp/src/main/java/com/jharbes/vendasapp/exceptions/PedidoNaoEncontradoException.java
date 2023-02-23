package com.jharbes.vendasapp.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException() {
        super("Pedido não encontrado");
    }
}
