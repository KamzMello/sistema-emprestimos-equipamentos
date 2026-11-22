// 6. INTERFACE RESPONSÁVEL PELO ACESSO AOS DADOS DOS COLABORADORES

package com.projeto.emprestimos.repository;

import com.projeto.emprestimos.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    // Verifica se já existe um colaborador com esse e-mail
    boolean existsByEmail(String email);

}