package com.algaworks.algalogistica.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalogistica.api.exceptionhandler.model.ExceptionBody;
import com.algaworks.algalogistica.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalogistica.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor // Injecao
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ExceptionBody.Campo> campos = new ArrayList<ExceptionBody.Campo>();
		
		for(ObjectError error: ex.getBindingResult().getAllErrors()){
			FieldError fieldError = ((FieldError)error);
			campos.add(new ExceptionBody.Campo(fieldError.getField(), this.messageSource.getMessage(error, new Locale("pt", "MZ"))));
			
			
		}
		
		ExceptionBody exceptionBody = new ExceptionBody();
		exceptionBody.setStatus(status.value());
		exceptionBody.setData(LocalDateTime.now());
		exceptionBody.setTitulo("Ocorreu um erro ao validar os campos, verifique se não ouve um problema!");
		exceptionBody.setCampos(campos);
		
		
		return handleExceptionInternal(ex, exceptionBody, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class) //Dizendo ao Spring que deve tratar todas as excepcoes NegocioException com este metodo desta classse
	public ResponseEntity<Object> handleNegocioException(NegocioException nex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExceptionBody exceptionBody = new ExceptionBody();
		exceptionBody.setStatus(status.value());
		exceptionBody.setData(LocalDateTime.now());
		exceptionBody.setTitulo(nex.getMessage());
		
		return handleExceptionInternal(nex, exceptionBody, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class) //Dizendo ao Spring que deve tratar todas as excepcoes NegocioException com este metodo desta classse
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException nex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ExceptionBody exceptionBody = new ExceptionBody();
		exceptionBody.setStatus(status.value());
		exceptionBody.setData(LocalDateTime.now());
		exceptionBody.setTitulo(nex.getMessage());
		
		return handleExceptionInternal(nex, exceptionBody, new HttpHeaders(), status, request);
	}
}
