package com.algaworks.algalogistica.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Define que o Lombook deve fazer o metodo equals e hash usando apenas propriedades explicitamente inclusas
@Getter //Na hora da compilação o lombok vai adicionar metodos get para as nossas propriedades
@Setter //Na hora da compilação o lombok vai adicionar metodos set para as nossas propriedades
@Entity // define esta classe como entidade
@Table(name = "cliente") //define esta classe com um nome diferente no banco de dados
public class Cliente {
	
	@EqualsAndHashCode.Include // definindo que esta propriedade devera estar no equals e hash
	@Id //defini este actibuto como chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Define a forma de geração do ids como o banco decide.
	private Long id;
	
	@NotBlank//Nao deve estar nulo e vazio
	@Column(name = "nome")
	private String nome;
	
	@NotBlank
	@Email //Tem que seguir a estrutura de um email
	private String email;
	
	@NotBlank
	private String telefone;
	
}
