package br.com.cotiinformatica.infrastructure.outbox;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class OutboxMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String aggregateType; 	// Ex.: Pedido
	private String aggregateId;		// Ex.: "123"
	private String type; 			// Ex.: "PedidoCriado"
	private String payload; 		// Ex.: Dados do evento em formato JSON
	
	private boolean published = false; // Indica se o evento já foi publicado
	
	private LocalDateTime createdAt = LocalDateTime.now(); // Data de criação do evento
	private LocalDateTime transmittedAt; // Data de transmissão do evento (opcional)
}
