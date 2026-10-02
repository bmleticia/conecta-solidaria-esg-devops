package br.com.fiap.esgapi.repository;

import br.com.fiap.esgapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {}
