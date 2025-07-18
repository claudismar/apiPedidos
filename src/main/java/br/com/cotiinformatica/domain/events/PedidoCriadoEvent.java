package br.com.cotiinformatica.domain.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PedidoCriadoEvent (
	UUID id,
	LocalDate dataPedido,
	BigDecimal valorPedido,
	String nomeCliente,
	String descricaoPedido,
	String status
){
}
