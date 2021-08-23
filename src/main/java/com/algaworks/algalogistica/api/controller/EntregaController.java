package com.algaworks.algalogistica.api.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogistica.api.assembler.EntregaAssembler;
import com.algaworks.algalogistica.api.model.DestinatarioModel;
import com.algaworks.algalogistica.api.model.EntregaModel;
import com.algaworks.algalogistica.api.model.input.EntregaInput;
import com.algaworks.algalogistica.domain.model.Entrega;
import com.algaworks.algalogistica.domain.repository.EntregaRepository;
import com.algaworks.algalogistica.domain.service.FinalizaEntregaService;
import com.algaworks.algalogistica.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "entregas")
public class EntregaController {
	
	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	private FinalizaEntregaService finalizaEntregaService;

	@Transactional
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega entrega = this.entregaAssembler.toDomainModel(entregaInput); //Converto de classe de API input a classe de dominio
		
		Entrega entregaSolicitada = this.solicitacaoEntregaService.solicitarEntrega(entrega); //Uso a classe de dominio
		
		return this.entregaAssembler.toModel(entregaSolicitada); //Converto de classe de domino a classe de API de output

	}
	
	@GetMapping
	public ResponseEntity<List<EntregaModel>> listar(){
		return ResponseEntity.ok(this.entregaAssembler.toCollectionModel(this.entregaRepository.findAll()));
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> listar(@PathVariable Long entregaId){
		return this.entregaRepository.findById(entregaId)
				.map((entrega) -> ResponseEntity.ok(this.entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizar")
	public ResponseEntity<Void> finalizar(@PathVariable Long entregaId){
		
		this.finalizaEntregaService.finalizar(entregaId);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
