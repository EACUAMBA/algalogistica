package com.algaworks.algalogistica.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalogistica.domain.model.Entrega;
import com.algaworks.algalogistica.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistroOcorrenciasService {
	
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional //Me deste trabalho, é necessario para as instrucoes todas serem executadas ate ao fim e so depois disso ele vai passar o comando para outro metodo
	public Ocorrencia registraOcorencia(Long entregaId, String descricao) {
		
		Entrega entrega = this.buscaEntregaService.findById(entregaId);
		return entrega.aidicionaOcorrencia(descricao);
	}

}
