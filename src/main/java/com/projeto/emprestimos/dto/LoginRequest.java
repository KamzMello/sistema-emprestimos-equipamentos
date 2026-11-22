// OBSERVAÇÃO SECURITY- DADOS (DTO):
// Esta classe recebe o e-mail e a senha enviados no login.

package com.projeto.emprestimos.dto;

public class LoginRequest {

    private String email;
    private String senha;

    // Retorna o e-mail.
    public String getEmail() {
        return email;
    }

    // Define o e-mail.
    public void setEmail(String email) {
        this.email = email;
    }

    // Retorna a senha
    public String getSenha() {
        return senha;
    }

    // Define a senha
    public void setSenha(String senha) {
        this.senha = senha;
    }
}