package com.jharbes.vendasapp.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.jharbes.vendasapp.entities.Cliente;
import com.jharbes.vendasapp.entities.ItemPedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*{
    "cliente":1,
    "total":100,
    "itens":[
        {
            "produto":1,
            "quantidade":10
        }
    ]
}*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;
	
	
}
