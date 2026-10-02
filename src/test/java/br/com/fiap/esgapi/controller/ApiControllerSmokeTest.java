package br.com.fiap.esgapi.controller;

import br.com.fiap.esgapi.dto.AlertaResponse;
import br.com.fiap.esgapi.dto.DoacaoResponse;
import br.com.fiap.esgapi.dto.PedidoAjudaResponse;
import br.com.fiap.esgapi.dto.UsuarioResponse;
import br.com.fiap.esgapi.repository.TipoNecessidadeRepository;
import br.com.fiap.esgapi.service.AlertaService;
import br.com.fiap.esgapi.service.DoacaoService;
import br.com.fiap.esgapi.service.PedidoAjudaService;
import br.com.fiap.esgapi.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApiControllerSmokeTest {

    @Test
    void pedidosDeveRetornar200() throws Exception {
        PedidoAjudaService service = mock(PedidoAjudaService.class);
        when(service.listar()).thenReturn(List.of(
                new PedidoAjudaResponse(1L, "Cesta basica", "Sao Paulo", 1L,
                        "Alimentos", "ana@email.com", "Ana")
        ));
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new PedidoAjudaController(service)).build();
        mvc.perform(get("/api/pedidos")).andExpect(status().isOk());
    }

    @Test
    void doacoesDeveRetornar200() throws Exception {
        DoacaoService service = mock(DoacaoService.class);
        when(service.listarPorPedido(1L)).thenReturn(List.of(
                new DoacaoResponse("doador@email.com", "Doador", 1L, "Posso ajudar")
        ));
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new DoacaoController(service)).build();
        mvc.perform(get("/api/doacoes/pedido/1")).andExpect(status().isOk());
    }

    @Test
    void alertasDeveRetornar200() throws Exception {
        AlertaService service = mock(AlertaService.class);
        when(service.listarTodos()).thenReturn(List.of(new AlertaResponse(1L, "Novo pedido")));
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new AlertaController(service)).build();
        mvc.perform(get("/api/alertas")).andExpect(status().isOk());
    }

    @Test
    void usuariosDeveRetornar200() throws Exception {
        UsuarioService service = mock(UsuarioService.class);
        when(service.listar()).thenReturn(List.of(
                new UsuarioResponse("ana@email.com", "Ana", 1L, "Solicitante")
        ));
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new UsuarioController(service)).build();
        mvc.perform(get("/api/usuarios")).andExpect(status().isOk());
    }

    @Test
    void tiposNecessidadeDeveRetornar200() throws Exception {
        TipoNecessidadeRepository repository = mock(TipoNecessidadeRepository.class);
        when(repository.findAll()).thenReturn(List.of());
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new TipoNecessidadeController(repository)).build();
        mvc.perform(get("/api/tipos-necessidade")).andExpect(status().isOk());
    }
}
