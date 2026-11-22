// OBS.SECURITY:
// Localiza o colaborador pelo e-mail  e assim
// permitir a autenticação.

package com.projeto.emprestimos.service;

import com.projeto.emprestimos.repository.ColaboradorRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ServicoAutenticacao implements UserDetailsService {

    private final ColaboradorRepository colaboradorRepository;

    public ServicoAutenticacao(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Procura o colaborador pelo e-mail no banco.
        var colaborador = colaboradorRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Colaborador não encontrado")
                );

        // Entrega e-mail e senha ao Spring Security para validação.
        return User.withUsername(colaborador.getEmail())
                .password(colaborador.getSenha())
                .authorities("ROLE_USER")
                .build();
    }
}
