package br.com.cotiinformatica.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	/*
	 * Mapeamento de uma fila para gravar / ler
	 * eventos de pedidos criados
	 */
	@Bean
	Queue filaPedidosCriados() {
		/*
		 * Nome da fila: fila.pedidos_criados
		 * Durável: true (a fila será mantida mesmo que o servidor RabbitMQ reinicie)
		 */
		return new Queue("fila.pedidos_criados", true);
	}
	
}
