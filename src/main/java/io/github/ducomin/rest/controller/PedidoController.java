package io.github.ducomin.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.github.ducomin.domain.entity.ItemPedido;
import io.github.ducomin.domain.entity.Pedido;
import io.github.ducomin.rest.controller.dto.InformacaoItemPedidoDTO;
import io.github.ducomin.rest.controller.dto.InformacoesPedidoDTO;
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

	@GetMapping("{id}")
	public InformacoesPedidoDTO getById( @PathVariable Integer id ){
		return service
				.obterPedidoCompleto(id)
				.map( p -> converter(p) )
				.orElseThrow(() ->
						new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
	}

	private InformacoesPedidoDTO converter(Pedido pedido){
		return InformacoesPedidoDTO
				.builder()
				.codigo(pedido.getId())
				.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.cpf(pedido.getCliente().getCpf())
				.nomeCliente(pedido.getCliente().getNome())
				.total(pedido.getTotal())
				.status(pedido.getStatus().name())
				.items(converter(pedido.getItens()))
				.build();
	}

	private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)){
			return Collections.emptyList();
		}
		return itens.stream().map(
				item -> InformacaoItemPedidoDTO
						.builder().descricaoProduto(item.getProduto().getDescricao())
						.precoUnitario(item.getProduto().getPreco())
						.quantidade(item.getQuantidade())
						.build()
		).collect(Collectors.toList());
	}

}
