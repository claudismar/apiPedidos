package br.com.cotiinformatica.domain.services.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.entities.Pedido;
import br.com.cotiinformatica.domain.models.PedidoRequestModel;
import br.com.cotiinformatica.domain.models.PedidoResponseModel;
import br.com.cotiinformatica.domain.services.interfaces.PedidoService;
import br.com.cotiinformatica.infrastructure.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // com esta anotação, o construtor é gerado automaticamente pelo lombok
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ModelMapper mapper;

	@Override
	public PedidoResponseModel criarPedido(PedidoRequestModel model) {
		
		var pedido = mapper.map(model, Pedido.class);

		pedidoRepository.save(pedido);
		
		return mapper.map(pedido, PedidoResponseModel.class);
	}

	@Override
	public PedidoResponseModel alterarPedido(UUID id, PedidoRequestModel model) {
		// Lógica para alterar um pedido
		return null; // Retorne o modelo de resposta adequado
	}

	@Override
	public PedidoResponseModel inativarPedido(UUID id) {
		// Lógica para inativar um pedido
		return null; // Retorne o modelo de resposta adequado
	}

	@Override
	public Page<PedidoResponseModel> consultarPedidos(Pageable pageable) {
		// Lógica para consultar pedidos com paginação
		return null; // Retorne a página de pedidos
	}

	@Override
	public PedidoResponseModel obterPedidoPorId(UUID id) {
		// Lógica para obter um pedido por ID
		return null; // Retorne o modelo de resposta adequado
	}

}
