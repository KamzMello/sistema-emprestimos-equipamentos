// OBS. SECURITY:
//Classe  responsável por gerar e validar tokens JWT.


package com.projeto.emprestimos.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.stereotype.Component;


@Component
public class JwtServico {

    // Tempo de validade do token: 24 horas.
    private static final long TEMPO_EXPIRACAO = 86400000;

    // Recupera a chave secreta configurada no sistema.
    private SecretKey obterChave() {

        String segredo = System.getenv("JWT_SECRET");

        if (segredo == null || segredo.isBlank()) {
            throw new IllegalStateException(
                    "A variável JWT_SECRET não está configurada."
            );
        }

        return Keys.hmacShaKeyFor(
                segredo.getBytes(StandardCharsets.UTF_8)
        );
    }

    // Gera um token JWT utilizando o e-mail do colaborador.
    public String gerarToken(String email) {

        Date agora = new Date();

        Date expiracao = new Date(
                agora.getTime() + TEMPO_EXPIRACAO
        );

        return Jwts.builder()
                .subject(email)
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(obterChave())
                .compact();
    }

    // Valida a assinatura e a validade do token.
    public boolean validarToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(obterChave())
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extrai o e-mail armazenado no token.
    public String extrairEmail(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(obterChave())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}
