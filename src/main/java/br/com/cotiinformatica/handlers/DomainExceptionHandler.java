package br.com.cotiinformatica.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cotiinformatica.domain.exceptions.PedidoNaoEncontradoException;

@ControllerAdvice
public class DomainExceptionHandler {

	@ExceptionHandler(PedidoNaoEncontradoException.class)
	public ResponseEntity<Object> handlePedidoNaoEncontradoException(PedidoNaoEncontradoException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("erro", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}
}
