package br.com.fiap.esgapi.repository;

import br.com.fiap.esgapi.model.Doacao;
import br.com.fiap.esgapi.model.DoacaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, DoacaoId> {
    List<Doacao> findByPedido_Id(Long pedidoId);
}
