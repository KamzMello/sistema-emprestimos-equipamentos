// OBS.SECURITY:
// Classe responsável por disponibilizar a criptografia BCrypt.
// Ela será utilizada para proteger as senhas dos colaboradores.
package com.projeto.emprestimos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class ConfiguracaoSenha {

    @Bean
    public PasswordEncoder passwordEncoder() {

        // Cria o mecanismo de criptografia das senhas.
        return new BCryptPasswordEncoder();
    }
}
