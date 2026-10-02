package br.com.fiap.esgapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TIPO_USUARIO")
public class TipoUsuario {
    @Id
    @Column(name = "id_tipo_usuario")
    private Long id;

    @Column(name = "nm_tipo_usuario", nullable = false, length = 100)
    private String nome;

    public TipoUsuario() {}
    public TipoUsuario(Long id, String nome) { this.id = id; this.nome = nome; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
