package com.algaworks.algalogistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogistica.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalogistica.api.model.OcorrenciaOutput;
import com.algaworks.algalogistica.api.model.input.OcorrenciaInput;
import com.algaworks.algalogistica.domain.model.Ocorrencia;
import com.algaworks.algalogistica.domain.service.BuscaOcorrenciasService;
import com.algaworks.algalogistica.domain.service.RegistroOcorrenciasService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping(path = "entregas/{entregaId}/ocorrencias")
@RestController
public class RegistraOcorrenciaController {
	
	private RegistroOcorrenciasService registroOcorrenciasService;
	private BuscaOcorrenciasService buscaOcorrenciasService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public OcorrenciaOutput registrarOcorencia (@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
//		Ocorrencia ocorrencia = this.ocorrenciaAssembler.toDomainModel(ocorrenciaInput);
		Ocorrencia ocorrencia = this.registroOcorrenciasService.registraOcorencia(entregaId, ocorrenciaInput.getDescricao());
		return this.ocorrenciaAssembler.toApiModel(ocorrencia);
	}
	
	@GetMapping
	public ResponseEntity<List<OcorrenciaOutput>> listar(@PathVariable Long entregaId){
		List<Ocorrencia> ocoreList = this.buscaOcorrenciasService.findOcorrenciaByEntrega(entregaId);
		List<OcorrenciaOutput> ocorrenciaOutputs = this.ocorrenciaAssembler.toCollectionOutput(ocoreList);
		
		return ResponseEntity.ok(ocorrenciaOutputs);
		
	}

}
