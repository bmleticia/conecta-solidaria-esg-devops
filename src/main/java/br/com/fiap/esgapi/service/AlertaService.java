package br.com.fiap.esgapi.service;

import br.com.fiap.esgapi.dto.AlertaResponse;
import br.com.fiap.esgapi.model.Alerta;
import br.com.fiap.esgapi.repository.AlertaRepository;
import br.com.fiap.esgapi.repository.ExibeAlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {
    private final AlertaRepository alertaRepository;
    private final ExibeAlertaRepository exibeAlertaRepository;

    public AlertaService(AlertaRepository alertaRepository, ExibeAlertaRepository exibeAlertaRepository) {
        this.alertaRepository = alertaRepository;
        this.exibeAlertaRepository = exibeAlertaRepository;
    }

    public Alerta criar(String descricao) {
        return alertaRepository.save(new Alerta(descricao));
    }

    public List<AlertaResponse> listarTodos() {
        return alertaRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<AlertaResponse> listarPorUsuario(String email) {
        return exibeAlertaRepository.findByUsuario_Email(email).stream()
                .map(Exibe -> toResponse(Exibe.getAlerta()))
                .toList();
    }

    private AlertaResponse toResponse(Alerta alerta) {
        return new AlertaResponse(alerta.getId(), alerta.getDescricao());
    }
}
