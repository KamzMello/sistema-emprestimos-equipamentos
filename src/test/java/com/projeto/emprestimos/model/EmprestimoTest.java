
package com.projeto.emprestimos.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {

    @Test
    void deveCriarEmprestimoComDevolucaoPrevistaEm14Dias() {

        // PARA PREPARAR
        LocalDate data = LocalDate.of(2026, 11, 14);

        // P/ EXECUTAR
        Emprestimo emprestimo = new Emprestimo(1L, 1L, data);

        // P/ VERIFICAR
        assertEquals(data, emprestimo.getDataEmprestimo());
        assertEquals(LocalDate.of(2026, 11, 28),
                emprestimo.getDataPrevistaDevolucao());
    }

    @Test
    void deveRegistrarDataDeDevolucao() {

        // PARA PREPARAR
        Emprestimo emprestimo = new Emprestimo(
                1L, 1L, LocalDate.of(2026, 11, 6));

        LocalDate dataDevolucao = LocalDate.of(2026, 11, 20);

        // EXECUTAR
        emprestimo.devolver(dataDevolucao);

        // VERIFICAR
        assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
    }

    @Test
    void naoDevePermitirDevolverEmprestimoDuasVezes() {

        // PREPARAR
        Emprestimo emprestimo = new Emprestimo(
                1L, 1L, LocalDate.of(2026, 11, 6));

        emprestimo.devolver(LocalDate.of(2026, 11, 12));

        // EXECUTAR E VERIFICAR
        assertThrows(IllegalStateException.class, () -> {
            emprestimo.devolver(LocalDate.of(2026, 11, 14));
        });
    }

    @Test
    void deveIdentificarEmprestimoAtrasado() {

        // PREPARAR
        Emprestimo emprestimo = new Emprestimo(
                1L, 1L, LocalDate.of(2026, 10, 27));

        // EXECUTAR
        boolean atrasado = emprestimo.estaAtrasadoEm(
                LocalDate.of(2026, 11, 14));

        // VERIFICAR
        assertTrue(atrasado);
    }

    @Test
    void naoDeveConsiderarAtrasadoAntesDaDataPrevista() {

        // PREPARAR
        Emprestimo emprestimo = new Emprestimo(
                1L, 1L, LocalDate.of(2026, 11, 6));

        // EXECUTAR
        boolean atrasado = emprestimo.estaAtrasadoEm(
                LocalDate.of(2026, 11, 14));

        // VERIFICAR
        assertFalse(atrasado);
    }

    @Test
    void naoDeveConsiderarAtrasadoEmprestimoJaDevolvido() {

        // PREPARAR
        Emprestimo emprestimo = new Emprestimo(
                1L, 1L, LocalDate.of(2026, 10, 27));

        emprestimo.devolver(LocalDate.of(2026, 11, 12));

        // EXECUTAR
        boolean atrasado = emprestimo.estaAtrasadoEm(
                LocalDate.of(2026, 11, 14));

        // VERIFICAR
        assertFalse(atrasado);
    }

    @Test
    void naoDeveConsiderarAtrasadoNoProprioDiaDaDevolucaoPrevista() {

        // PREPARAR
        Emprestimo emprestimo = new Emprestimo(
                1L, 1L, LocalDate.of(2026, 10, 31));

        // EXECUTAR
        boolean atrasado = emprestimo.estaAtrasadoEm(
                LocalDate.of(2026, 11, 14));

        // VERIFICAR
        assertFalse(atrasado);
    }
}
