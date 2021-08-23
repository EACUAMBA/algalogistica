package com.algaworks.algalogistica.api.assembler;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalogistica.api.model.OcorrenciaOutput;
import com.algaworks.algalogistica.api.model.input.OcorrenciaInput;
import com.algaworks.algalogistica.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper mapper;
	
	public Ocorrencia toDomainModel(OcorrenciaInput ocorrenciaInput) {
		return this.mapper.map(ocorrenciaInput, Ocorrencia.class);
	}
	
	public OcorrenciaOutput toApiModel(Ocorrencia ocorrencia) {
		return this.mapper.map(ocorrencia, OcorrenciaOutput.class);
	}
	
	public List<OcorrenciaOutput> toCollectionOutput(List<Ocorrencia> ocorreList){
		return ocorreList.stream().map((ocorrencia)-> this.mapper.map(ocorrencia, OcorrenciaOutput.class)).collect(Collectors.toList());
	}
	
}
