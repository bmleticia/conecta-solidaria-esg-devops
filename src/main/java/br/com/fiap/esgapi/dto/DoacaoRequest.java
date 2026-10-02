package br.com.fiap.esgapi.dto;

import jakarta.validation.constraints.*;

public record DoacaoRequest(
        @NotBlank @Email @Size(max = 100) String emailUsuario,
        @NotNull Long idPedido,
        @Size(max = 500) String mensagem
) {}
