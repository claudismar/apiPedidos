package br.com.cotiinformatica.domain.services.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.domain.entities.Pedido;
import br.com.cotiinformatica.domain.events.PedidoCriadoEvent;
import br.com.cotiinformatica.domain.exceptions.PedidoNaoEncontradoException;
import br.com.cotiinformatica.domain.models.PedidoRequestModel;
import br.com.cotiinformatica.domain.models.PedidoResponseModel;
import br.com.cotiinformatica.domain.services.interfaces.PedidoService;
import br.com.cotiinformatica.infrastructure.outbox.OutboxMessage;
import br.com.cotiinformatica.infrastructure.repositories.OutboxMessageRepository;
import br.com.cotiinformatica.infrastructure.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // com esta anotação, o construtor é gerado automaticamente pelo lombok
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final OutboxMessageRepository outboxMessageRepository;
	private final ModelMapper mapper;
	private final ObjectMapper objectMapper;

	@Transactional
	@Override
	public PedidoResponseModel criarPedido(PedidoRequestModel model) {
		
		// Capturando os dados da requisição e criando um novo pedido
		var pedido = mapper.map(model, Pedido.class);
		
		// Salvando o pedido no banco de dados
		var pedidoCriado = pedidoRepository.save(pedido);
		
		// Criação um evento "PedidoCriado" com os dados do pedido
		var event = new PedidoCriadoEvent(
				pedidoCriado.getId(), 
				pedidoCriado.getDataPedido(), 
				pedidoCriado.getValorPedido(),
				pedidoCriado.getNomeCliente(),
				pedidoCriado.getDescricaoPedido(), 
				pedidoCriado.getStatusPedido().name());
		
		// Criando um registro para a tabela de saída (OutboxMessage)
		var message = new OutboxMessage();
		message.setAggregateType("Pedido");
		message.setAggregateId(pedidoCriado.getId().toString());
		message.setType("PedidoCriado");

		try {
			// Serializando os dados do evento em JSON
			message.setPayload(objectMapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e.getMessage());
		}
		
		// Salvando o registro do evento no banco de dados
		outboxMessageRepository.save(message);
		
		// Retornando os dados do pedido cadastrado
		return mapper.map(pedidoCriado, PedidoResponseModel.class);
	}

	@Override
	public PedidoResponseModel alterarPedido(UUID id, PedidoRequestModel model) {
		var pedido = pedidoRepository.findByIdAndAtivo(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
		mapper.map(model, pedido);
		pedidoRepository.save(pedido);
		return mapper.map(pedido, PedidoResponseModel.class); 
	}

	@Override
	public PedidoResponseModel inativarPedido(UUID id) {
		var pedido = pedidoRepository.findByIdAndAtivo(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
		pedido.setAtivo(false);
		pedidoRepository.save(pedido);
		return mapper.map(pedido, PedidoResponseModel.class); 
	}

	@Override
	public Page<PedidoResponseModel> consultarPedidos(Pageable pageable) {
        var pedidos = pedidoRepository.findAtivos(pageable);
        return pedidos.map(pedido -> mapper.map(pedido, PedidoResponseModel.class));
	}

	@Override
	public PedidoResponseModel obterPedidoPorId(UUID id) {
		var pedido = pedidoRepository.findByIdAndAtivo(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
		return mapper.map(pedido, PedidoResponseModel.class);
	}

}
