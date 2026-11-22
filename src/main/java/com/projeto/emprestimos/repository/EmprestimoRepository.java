// 9. INTERFACE RESPONSÁVEL PELO ACESSO DOS DADOS DOS EMPRÉSTIMOS

package com.projeto.emprestimos.repository;


import com.projeto.emprestimos.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Busca os empréstimos de um colaborador
    List<Emprestimo> findByColaboradorId(Long colaboradorId);

    // Verifica se um equipamento possui empréstimo
    boolean existsByEquipamentoId(Long equipamentoId);

    // Busca empréstimos não devolvidos e com prazo vencido
    List<Emprestimo> findByDataDevolucaoIsNullAndDataPrevistaDevolucaoBefore(LocalDate data);

    // Verifica se o colaborador possui empréstimo atrasado
    boolean existsByColaboradorIdAndDataDevolucaoIsNullAndDataPrevistaDevolucaoBefore(
            Long colaboradorId, LocalDate data);
}
