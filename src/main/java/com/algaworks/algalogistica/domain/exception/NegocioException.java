package com.algaworks.algalogistica.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class NegocioException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4031371129374493305L;
	
	private HttpStatus status;

	public NegocioException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
