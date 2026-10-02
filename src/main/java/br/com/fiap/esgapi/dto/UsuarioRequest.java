package br.com.fiap.esgapi.dto;

import jakarta.validation.constraints.*;

public record UsuarioRequest(
        @NotBlank @Email @Size(max = 100) String email,
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Size(min = 6, max = 50) String senha,
        @NotNull Long idTipoUsuario
) {}
