package br.com.fiap.esgapi.controller;

import br.com.fiap.esgapi.dto.TipoNecessidadeResponse;
import br.com.fiap.esgapi.repository.TipoNecessidadeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-necessidade")
public class TipoNecessidadeController {
    private final TipoNecessidadeRepository repository;

    public TipoNecessidadeController(TipoNecessidadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TipoNecessidadeResponse> listar() {
        return repository.findAll().stream()
                .map(tipo -> new TipoNecessidadeResponse(tipo.getId(), tipo.getDescricao()))
                .toList();
    }
}
