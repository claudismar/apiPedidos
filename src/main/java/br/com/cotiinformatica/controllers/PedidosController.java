package br.com.cotiinformatica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(
	name = "Controle de Pedidos",
	description = "Serviços para gerenciamento de solicitações de pedidos."
)
public class PedidosController {

	@Operation(
		summary = "Cadastro de solicitações de pedido",
		description = "Cria uma nova solicitação de pedido no sistema."
	)
	@PostMapping
	public ResponseEntity<?> post() {
		// TODO implementar o cadastro do pedido
		return ResponseEntity.ok().build();
	}
	
	@Operation(
		summary = "Atualização de pedido",
		description = "Modifica uma solicitação de pedido no sistema."
	)
	@PutMapping
	public ResponseEntity<?> put() {
		// TODO implementar a atualização o pedido
		return ResponseEntity.ok().build();
	}
	
	@Operation(
		summary = "Inativação de solicitações de pedido",
		description = "Inativa uma nova solicitação de pedido existente no sistema."
	)
	@DeleteMapping
	public ResponseEntity<?> delete() {
		// TODO implementar a exclusão do pedido
		return ResponseEntity.ok().build();
	}
	
	@Operation(
		summary = "Consulta de solicitações de pedido",
		description = "Retorna uma consulta paginada de solicitações de pedido no sistema."
	)
	@GetMapping
	public ResponseEntity<?> get() {
		// TODO implementar a consulta do pedido
		return ResponseEntity.ok().build();
	}


}
