package br.com.cotiinformatica.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

	/*
	 * Método de consulta com JPQL que retorne 1 ou nenhum pedido
	 * baseado em um ID informado e que seja ativo = true
	 */
	@Query("""
		SELECT p FROM Pedido p
		WHERE p.id = :id 
		  AND p.ativo = true
	""")
	Optional<Pedido> findByIdAndAtivo(@Param("id") UUID id);

	/*
	 * Método de consulta com JPQL que retorne um resultado paginado
	 * de pedidos ativos e em ordem decrescente de data.
	 */
	@Query("""
	    SELECT p FROM Pedido p
	    WHERE p.ativo = true
	    ORDER BY p.dataPedido DESC
	""")
	Page<Pedido> findAtivos(Pageable pageable);
}
