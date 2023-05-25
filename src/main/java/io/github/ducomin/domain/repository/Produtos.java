package io.github.ducomin.domain.repository;

import io.github.ducomin.domain.entity.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
