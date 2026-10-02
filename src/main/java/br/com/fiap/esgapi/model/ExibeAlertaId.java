package br.com.fiap.esgapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExibeAlertaId implements Serializable {
    @Column(name = "ds_email", length = 100)
    private String email;
    @Column(name = "id_alerta")
    private Long alertaId;

    public ExibeAlertaId() {}
    public ExibeAlertaId(String email, Long alertaId) { this.email = email; this.alertaId = alertaId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getAlertaId() { return alertaId; }
    public void setAlertaId(Long alertaId) { this.alertaId = alertaId; }
    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof ExibeAlertaId that)) return false; return Objects.equals(email, that.email) && Objects.equals(alertaId, that.alertaId); }
    @Override public int hashCode() { return Objects.hash(email, alertaId); }
}
