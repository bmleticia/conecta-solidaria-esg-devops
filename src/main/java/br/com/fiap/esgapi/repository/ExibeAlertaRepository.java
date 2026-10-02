package br.com.fiap.esgapi.repository;

import br.com.fiap.esgapi.model.ExibeAlerta;
import br.com.fiap.esgapi.model.ExibeAlertaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExibeAlertaRepository extends JpaRepository<ExibeAlerta, ExibeAlertaId> {
    List<ExibeAlerta> findByUsuario_Email(String email);
}
