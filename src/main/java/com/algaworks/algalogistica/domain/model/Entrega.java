package com.algaworks.algalogistica.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algalogistica.domain.ValidationGroup;
import com.algaworks.algalogistica.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "entrega")
public class Entrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroup.ClienteId.class) // Estou a dizer ao JPA para validar usando o grupo ValidationGroup.ClienteId.class ele somente vai validar as propriedade anotadas com o grupo ValidationGroup
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id") // Esse é o nome da chave estrageira gerado aqui nesta tabela.
	private Cliente cliente;
	
	@Embedded // Classe embutita nesta classe.
	private Destinatario destinatario;
	
	@NotNull
	private BigDecimal taxa;
	

	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING) //Ao inves de guardar o indice (posicao, numero) ele vai guardar o nome do enum
	private StatusEntrega status;
	
	
	@JsonProperty(access = Access.READ_ONLY) // Impossibilita o cliente da APi a não poder escrever ou alterar este valor.
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	
	
	@OneToMany(mappedBy = "entrega", cascade = {CascadeType.ALL})
	private List<Ocorrencia> ocorrencias;
	
	
	public Ocorrencia aidicionaOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDataOcorencia(OffsetDateTime.now());
		ocorrencia.setDescricao(descricao);
		ocorrencia.setEntrega(this);
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
	}


	public void finalizar() {
		if(this.naoPodeSerFinalizada()) 
			throw new NegocioException("Entrega não pode ser finalizada!");

		this.setStatus(StatusEntrega.FINALIZADA);
		this.setDataFinalizacao(OffsetDateTime.now());
	}
	
	public boolean podeSerFinalizada() {
		return this.getStatus().equals(StatusEntrega.PENDENTE);
	}
	
	public boolean naoPodeSerFinalizada() {
		return !this.podeSerFinalizada();
	}
	

}
