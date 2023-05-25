package io.github.ducomin.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.ducomin.domain.entity.Cliente;
import io.github.ducomin.domain.repository.Clientes;

@Controller
@RequestMapping(value = "/api/clientes")
public class ClienteController {

	@Autowired
	private Clientes clientes;

	@RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
	@ResponseBody
	public String helloCliente(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello %s", nomeCliente);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity getClienteById( @PathVariable Integer id ){
		Optional<Cliente> cliente = clientes.findById(id);

		if(cliente.isPresent()){
			return ResponseEntity.ok( cliente.get() );
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity save( @RequestBody Cliente cliente ){
		Cliente clienteSalvo = clientes.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity deletar( @PathVariable Integer id ){
		Optional<Cliente> cliente = clientes.findById(id);

		if(cliente.isPresent()){
			clientes.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity alterar( @PathVariable Integer id,
			@RequestBody Cliente cliente ){
		return clientes
				.findById(id)
				.map( clienteExistente -> {
					cliente.setId(clienteExistente.getId());
					clientes.save(cliente);
					return ResponseEntity.noContent().build();
				}).orElseGet( () -> ResponseEntity.notFound().build() );
	}

}
