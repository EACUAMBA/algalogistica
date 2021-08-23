package com.algaworks.algalogistica.api.assembler;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalogistica.api.model.EntregaModel;
import com.algaworks.algalogistica.api.model.input.EntregaInput;
import com.algaworks.algalogistica.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {
	
	private ModelMapper modelMapper;
	
	public EntregaModel toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModel.class);
	}
	
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream()
				.map((entrega) -> this.toModel(entrega))
				.collect(Collectors.toList());
	}
	
	public Entrega toDomainModel(EntregaInput entregaInput) {
		return this.modelMapper.map(entregaInput, Entrega.class);
	}
}
