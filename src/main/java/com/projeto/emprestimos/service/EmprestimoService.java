// 10. EmprestimoService é o responsável pelas regras de negócio dos empréstimos.

package com.projeto.emprestimos.service;

import com.projeto.emprestimos.repository.EmprestimoRepository;
import com.projeto.emprestimos.repository.EquipamentoRepository;
import com.projeto.emprestimos.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;
import com.projeto.emprestimos.model.Emprestimo;
import java.time.LocalDate;
import java.util.List;
import com.projeto.emprestimos.model.Equipamento;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final ColaboradorRepository colaboradorRepository;

    // CONSTRUTOR
    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            EquipamentoRepository equipamentoRepository,
            ColaboradorRepository colaboradorRepository) {

        this.emprestimoRepository = emprestimoRepository;
        this.equipamentoRepository = equipamentoRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    // Lista os empréstimos, para poder filtrar os empréstimos atrasados.
    public List<Emprestimo> listar(Boolean atrasados) {

        if (Boolean.TRUE.equals(atrasados)) {
            return emprestimoRepository
                    .findByDataDevolucaoIsNullAndDataPrevistaDevolucaoBefore(LocalDate.now());
        }

        return emprestimoRepository.findAll();
    }

    // P/ fazer a busca de um empréstimo pelo ID
    public Emprestimo buscar(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));
    }

    // Para lista os empréstimos de um colaborador específico
    public List<Emprestimo> listarDoColaborador(Long colaboradorId) {
        return emprestimoRepository.findByColaboradorId(colaboradorId);
    }

    // REGRAS = R4, R5 e R10: Realiza o empréstimo de um equipamento
    @Transactional
    public Emprestimo emprestar(Long equipamentoId, Long colaboradorId) {

        // Verifica se o colaborador está cadastrado
        if (!colaboradorRepository.existsById(colaboradorId)) {
            throw new IllegalStateException("Colaborador não encontrado!");
        }

        // R10: Verifica se o colaborador possui empréstimo atrasado
        if (emprestimoRepository
                .existsByColaboradorIdAndDataDevolucaoIsNullAndDataPrevistaDevolucaoBefore(
                        colaboradorId, LocalDate.now())) {
            throw new IllegalStateException("Colaborador possui empréstimo atrasado!");
        }

        // Busca o equipamento pelo ID
        Equipamento equipamento = equipamentoRepository.findById(equipamentoId)
                .orElseThrow(() -> new IllegalStateException("Equipamento não encontrado!"));

        // R4: Verifica a disponibilidade do equipamento
        equipamento.emprestar();

        // R5: Cria o empréstimo com a data de hoje
        Emprestimo emprestimo = new Emprestimo(
                equipamentoId, colaboradorId, LocalDate.now());

        // Para salvar as alterações no BD.
        equipamentoRepository.save(equipamento);
        return emprestimoRepository.save(emprestimo);

    }

    // REGRAS  R6 e R7: Realiza a devolução de um equipamento
    @Transactional
    public Emprestimo devolver(Long id) {

        // Busca o empréstimo pelo ID
        Emprestimo emprestimo = buscar(id);

        // Busca o equipamento relacionado ao empréstimo
        Equipamento equipamento = equipamentoRepository
                .findById(emprestimo.getEquipamentoId())
                .orElseThrow(() -> new IllegalStateException("Equipamento não encontrado!"));

        // Registra a data da devolução
        emprestimo.devolver(LocalDate.now());

        // Torna o equipamento disponível novamente
        equipamento.devolver();

        // Salva as alterações no banco de dados
        equipamentoRepository.save(equipamento);
        return emprestimoRepository.save(emprestimo);
    }

}
