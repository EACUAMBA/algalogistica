package com.algaworks.algalogistica.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.algaworks.algalogistica.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaOcorrenciasService {

	private BuscaEntregaService buscaEntregaService ;
	
	public List<Ocorrencia> findOcorrenciaByEntrega(Long entregaId){
		return this.buscaEntregaService.findById(entregaId).getOcorrencias();
		
	}
	
}
