package com.algaworks.algalogistica.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaOutput {
	private Long id;
	private String descricao;
	private OffsetDateTime dataOcorrencia;
}
