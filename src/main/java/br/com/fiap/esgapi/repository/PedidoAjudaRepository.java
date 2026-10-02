package br.com.fiap.esgapi.repository;

import br.com.fiap.esgapi.model.PedidoAjuda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoAjudaRepository extends JpaRepository<PedidoAjuda, Long> {
    long countByTipoNecessidade_Id(Long tipoNecessidadeId);
}
