package com.algaworks.algalogistica.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogistica.domain.model.Cliente;

@RestController //Isto diz ao spring que esta classe é um componente spring e diz ainda que ela pode receber pedidos ou requests.
public class ClienteController {
	
	@PersistenceContext // FAz o Spring injectar uma instancia do EntityManager
	private EntityManager entityManager;
	
	@GetMapping("/clientes") // Esta anotacao diz ao spring que ela deve esperar receber um pedido do tipo GET e que o nome desse recurso será /clientes.
	public List<Cliente> listar() {
		var clientes = this.entityManager.createQuery("from Cliente", Cliente.class).getResultList();
		return clientes;
	}
}
