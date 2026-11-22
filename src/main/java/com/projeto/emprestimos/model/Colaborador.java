// 5. A CLASSE COLABORADOR representa as pessoas cadastradas
// no sistema, armazenando ID, nome, e-mail e cargo.

package com.projeto.emprestimos.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Colaborador {

    // ATRIBUTOS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String cargo;

    // OBS.SECURITY:
   // Recebe a senha, mas impede que ela apareça nas respostas da API.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    // CONSTRUTOR
    public Colaborador() {
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
