package com.algaworks.algalogistica.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalogistica.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalogistica.domain.model.Entrega;
import com.algaworks.algalogistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaEntregaService {
	private EntregaRepository entregaRepository;
	
	public Entrega findById(Long id) {
		return this.entregaRepository.findById(id)
				.orElseThrow(()->new EntidadeNaoEncontradaException("Entrega não foi encontrada"));
	}
}
