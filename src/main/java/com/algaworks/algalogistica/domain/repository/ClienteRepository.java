package com.algaworks.algalogistica.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalogistica.domain.model.Cliente;

@Repository // Estamos a dizer que esta interface é um conponente Spring e que o Spring devera gerar instancias de uma implementacao para mim, tambem estamos a dizer que esta interface gere entidades
public interface ClienteRepository extends JpaRepository<Cliente, Long> { // Estamos a dizer que repositorio é para cliente e que o id é do tipo Long
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
}
