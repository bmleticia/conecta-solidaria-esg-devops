package br.com.fiap.esgapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DoacaoId implements Serializable {
    @Column(name = "ds_email", length = 100)
    private String email;

    @Column(name = "id_pedido")
    private Long pedidoId;

    public DoacaoId() {}
    public DoacaoId(String email, Long pedidoId) { this.email = email; this.pedidoId = pedidoId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof DoacaoId doacaoId)) return false; return Objects.equals(email, doacaoId.email) && Objects.equals(pedidoId, doacaoId.pedidoId); }
    @Override public int hashCode() { return Objects.hash(email, pedidoId); }
}
