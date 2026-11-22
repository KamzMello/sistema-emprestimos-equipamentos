// 6. INTERFACE RESPONSÁVEL PELO ACESSO AOS DADOS DOS COLABORADORES
package com.projeto.emprestimos.repository;

import com.projeto.emprestimos.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// OBS.:
// Interface responsável pelo acesso aos dados dos colaboradores,
// incluindo consultas pelo e-mail para autenticação.

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    // Verifica se já existe um colaborador com esse e-mail.
    boolean existsByEmail(String email);

    // Busca um colaborador pelo e-mail durante o login.
    Optional<Colaborador> findByEmail(String email);

}
