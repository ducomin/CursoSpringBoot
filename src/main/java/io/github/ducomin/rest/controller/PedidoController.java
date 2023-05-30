package io.github.ducomin.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ducomin.domain.entity.Pedido;
import io.github.ducomin.rest.controller.dto.PedidoDTO;
import io.github.ducomin.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@PostMapping
	@ResponseStatus(CREATED)
	public Integer save( @RequestBody PedidoDTO dto ){
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}

}
