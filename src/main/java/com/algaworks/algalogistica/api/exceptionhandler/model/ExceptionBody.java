package com.algaworks.algalogistica.api.exceptionhandler.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class ExceptionBody { //Classe que representa o body, da mensagem de erro
	private int status;
	private LocalDateTime data;
	private String titulo;
	private List<Campo> campos;
	
	@Getter
	@AllArgsConstructor
	public static class Campo{ // Essa classe representa um campo, com o nome e a mensagem de erro que ele capturar. Esta classe poderia estar fora, em otro arquivo, não interessa
		private String nome;
		private String mensagem;
	}
	
}
