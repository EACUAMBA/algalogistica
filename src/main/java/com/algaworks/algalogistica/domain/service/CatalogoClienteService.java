package com.algaworks.algalogistica.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalogistica.domain.exception.NegocioException;
import com.algaworks.algalogistica.domain.model.Cliente;
import com.algaworks.algalogistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;
	
	@Transactional // Diz ao Spring que se algo der errado nada é valido, é salvo.
	public Cliente salvar(Cliente cliente) {
		this.verificaEmailEmUso(cliente);
		return this.clienteRepository.save(cliente);
	}
	
	@Transactional
	public Cliente actualizar(Cliente cliente) {
		this.verificaEmailEmUso(cliente);
		return this.clienteRepository.save(cliente);
	}
	
	@Transactional
	public void apagar(Long clienteId) {
		this.clienteRepository.deleteById(clienteId);
	}
	
	private void verificaEmailEmUso(Cliente cliente) {
		boolean emUso = this.clienteRepository.findByEmail(cliente.getEmail())
		.stream()
		.anyMatch(c -> !c.equals(cliente));
		
		if(emUso)
			throw new NegocioException("Este email já esta em uso!");
	}
	
	public Cliente findById(Long id) {
		Cliente cliente = this.clienteRepository
				.findById(id)
				.orElseThrow(() -> new NegocioException("Cliente não foi cadastrado"));
		
		return cliente;
	}
}
