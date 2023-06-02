package io.github.ducomin.service;

import java.util.Optional;

import io.github.ducomin.domain.entity.Pedido;
import io.github.ducomin.domain.enums.StatusPedido;
import io.github.ducomin.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar( PedidoDTO dto );
	Optional<Pedido> obterPedidoCompleto(Integer id);
	void atualizaStatus(Integer id, StatusPedido statusPedido);
}