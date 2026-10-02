package br.com.fiap.esgapi.service;

import br.com.fiap.esgapi.dto.UsuarioRequest;
import br.com.fiap.esgapi.dto.UsuarioResponse;
import br.com.fiap.esgapi.exception.BusinessException;
import br.com.fiap.esgapi.exception.NotFoundException;
import br.com.fiap.esgapi.model.Usuario;
import br.com.fiap.esgapi.repository.TipoUsuarioRepository;
import br.com.fiap.esgapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll().stream().map(this::toResponse).toList();
    }

    public Usuario buscarEntidade(String email) {
        return usuarioRepository.findById(email)
                .orElseThrow(() -> new NotFoundException("Usuario nao encontrado: " + email));
    }

    public UsuarioResponse criar(UsuarioRequest request) {
        if (usuarioRepository.existsById(request.email())) {
            throw new BusinessException("Ja existe usuario cadastrado com este e-mail.");
        }
        var tipo = tipoUsuarioRepository.findById(request.idTipoUsuario())
                .orElseThrow(() -> new NotFoundException("Tipo de usuario nao encontrado."));

        var usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setNome(request.nome());
        usuario.setSenha(request.senha());
        usuario.setTipoUsuario(tipo);

        return toResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getTipoUsuario().getId(),
                usuario.getTipoUsuario().getNome()
        );
    }
}
