package com.algaworks.algalogistica.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.algaworks.algalogistica.domain.model.Cliente;
import com.algaworks.algalogistica.domain.model.Entrega;
import com.algaworks.algalogistica.domain.model.StatusEntrega;
import com.algaworks.algalogistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private CatalogoClienteService catalogoClienteService;
	private EntregaRepository entregaRepository;
	
	public Entrega solicitarEntrega(Entrega entrega) {
		Cliente cliente = this.catalogoClienteService.findById(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return this.entregaRepository.save(entrega);
	}
}
