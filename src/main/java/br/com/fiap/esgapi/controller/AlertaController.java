package br.com.fiap.esgapi.controller;

import br.com.fiap.esgapi.dto.AlertaResponse;
import br.com.fiap.esgapi.service.AlertaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {
    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    @GetMapping
    public List<AlertaResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/usuario/{email}")
    public List<AlertaResponse> listarPorUsuario(@PathVariable String email) {
        return service.listarPorUsuario(email);
    }
}
