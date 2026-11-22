// 8. A CLASSE EMPRESTIMO representa os empréstimos realizados,
// armazenando os IDs do equipamento e do colaborador e as datas.

package com.projeto.emprestimos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Emprestimo {

    // ATRIBUTOS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long equipamentoId;

    private Long colaboradorId;

    private LocalDate dataEmprestimo;

    private LocalDate dataPrevistaDevolucao;

    private LocalDate dataDevolucao;

    // CONSTRUTOR VAZIO

    public Emprestimo() {
    }

    // CONSTRUTOR COM PARÂMETROS
    // R5: Registra o empréstimo e prevê a devolução em 14 dias
    public Emprestimo(Long equipamentoId, Long colaboradorId, LocalDate hoje) {
        this.equipamentoId = equipamentoId;
        this.colaboradorId = colaboradorId;
        this.dataEmprestimo = hoje;
        this.dataPrevistaDevolucao = hoje.plusDays(14);
    }

    // R6 e R7: Registra a devolução do equipamento
    public void devolver(LocalDate data) {

        // Verifica se o empréstimo já foi devolvido
        if (dataDevolucao != null) {
            throw new IllegalStateException("Este empréstimo já foi devolvido");
        }

        // Registra a data da devolução
        dataDevolucao = data;
    }

    // R8: Verifica se o empréstimo está atrasado
    public boolean estaAtrasadoEm(LocalDate data) {
        return dataDevolucao == null
                && dataPrevistaDevolucao.isBefore(data);
    }

    // Verifica se está atrasado na data atual
    public boolean isAtrasado() {
        return estaAtrasadoEm(LocalDate.now());
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public Long getEquipamentoId() {
        return equipamentoId;
    }

    public Long getColaboradorId() {
        return colaboradorId;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}