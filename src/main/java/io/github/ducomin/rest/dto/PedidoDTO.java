package io.github.ducomin.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.github.ducomin.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	@NotNull(message = "Informe o código do cliente.")
	private Integer cliente;
	@NotNull(message = "Campo Total do pedido é obrigatório.")
	private BigDecimal total;
	@NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
	private List<ItemPedidoDTO> items;
}