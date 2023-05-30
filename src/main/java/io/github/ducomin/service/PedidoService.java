package io.github.ducomin.service;

import io.github.ducomin.domain.entity.Pedido;
import io.github.ducomin.rest.controller.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar( PedidoDTO dto );
}