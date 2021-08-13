package com.algaworks.algalogistica.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter //Na hora da compilação o lombok vai adicionar metodos get para as nossas propriedades
@Setter //Na hora da compilação o lombok vai adicionar metodos set para as nossas propriedades
public class Cliente {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
}
