package br.com.fiap.esgapi.repository;

import br.com.fiap.esgapi.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {}
