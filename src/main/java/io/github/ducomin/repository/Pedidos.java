package io.github.ducomin.repository;

import io.github.ducomin.domain.entity.Cliente;
import io.github.ducomin.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByCliente(Cliente cliente);
}