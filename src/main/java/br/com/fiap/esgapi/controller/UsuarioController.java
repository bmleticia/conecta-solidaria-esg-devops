package br.com.fiap.esgapi.controller;

import br.com.fiap.esgapi.dto.UsuarioRequest;
import br.com.fiap.esgapi.dto.UsuarioResponse;
import br.com.fiap.esgapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse criar(@RequestBody @Valid UsuarioRequest request) {
        return service.criar(request);
    }
}
