package io.github.ducomin.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.ducomin.domain.entity.Produto;
import io.github.ducomin.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private Produtos repository;

	@GetMapping("{id}")
	public Produto getProdutoById(@PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow(() ->
						new ResponseStatusException(HttpStatus.NOT_FOUND,
								"Produto não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save(@RequestBody Produto produto) {
		return repository.save(produto);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		repository.findById(id)
				.map(produto -> {
					repository.delete(produto);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Produto não encontrado"));

	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id,
			@RequestBody Produto produto) {
		repository
				.findById(id)
				.map(produtoExistente -> {
					produto.setId(produtoExistente.getId());
					repository.save(produto);
					return produtoExistente;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Produto não encontrado"));
	}

	@GetMapping
	public List<Produto> find(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(
						ExampleMatcher.StringMatcher.CONTAINING);

		Example example = Example.of(filtro, matcher);
		return repository.findAll(example);
	}
}