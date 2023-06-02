package io.github.ducomin.service;

import java.util.Optional;

import io.github.ducomin.domain.entity.Pedido;
import io.github.ducomin.rest.controller.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar( PedidoDTO dto );
	Optional<Pedido> obterPedidoCompleto(Integer id);
}