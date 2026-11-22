// OBSERVAÇÃO SECURITY:
// Este controller recebe os dados do login,
// valida o e-mail e a senha e gera o token JWT.

package com.projeto.emprestimos.controller;

import com.projeto.emprestimos.dto.LoginRequest;
import com.projeto.emprestimos.model.Colaborador;
import com.projeto.emprestimos.security.JwtServico;
import com.projeto.emprestimos.service.ColaboradorService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtServico jwtServico;
    private final ColaboradorService colaboradorService;

    // Recebe os serviços necessários.
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtServico jwtServico,
            ColaboradorService colaboradorService) {

        this.authenticationManager = authenticationManager;
        this.jwtServico = jwtServico;
        this.colaboradorService = colaboradorService;
    }

    // Cadastra um novo colaborador.
    @PostMapping("/registro")
    public ResponseEntity<Colaborador> registrar(
            @RequestBody Colaborador colaborador) {

        Colaborador novoColaborador =
                colaboradorService.cadastrar(colaborador);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoColaborador);
    }

    // Realiza o login e gera o token JWT.
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody LoginRequest dados) {

        authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        dados.getEmail(),
                        dados.getSenha()
                )
        );

        String token = jwtServico.gerarToken(dados.getEmail());

        return ResponseEntity.ok(Map.of("token", token));
    }
}