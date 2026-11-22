// OBS. SECURITY- JTW
// Um fitro p/ verificar  o token JWT recebido nas requisições.
// Se o token estiver OK (válido), identifica e autentica o colaborador.
package com.projeto.emprestimos.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtFiltro extends OncePerRequestFilter {

    private final JwtServico jwtServico;
    private final UserDetailsService userDetailsService;

    public JwtFiltro(
            JwtServico jwtServico,
            UserDetailsService userDetailsService) {

        this.jwtServico = jwtServico;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Recupera o cabeçalho Authorization.
        String authorization = request.getHeader("Authorization");

        // Verifica se existe um token Bearer.
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrai o token, removendo a palavra Bearer.
        String token = authorization.substring(7);

        // Verifica se o token é válido.
        if (jwtServico.validarToken(token)) {

            // Extrai o e-mail armazenado no token.
            String email = jwtServico.extrairEmail(token);

            // Busca os dados do colaborador.
            UserDetails usuario = userDetailsService.loadUserByUsername(email);

            // Cria a autenticação do usuário.
            UsernamePasswordAuthenticationToken autenticacao =
                    new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );

            autenticacao.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            // Registra o usuário autenticado no Spring Security.
            SecurityContextHolder.getContext()
                    .setAuthentication(autenticacao);
        }

        // Continua o processamento da requisição.
        filterChain.doFilter(request, response);
    }
}
