package com.projeto.emprestimos.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipamentoTest {

    @Test
    void deveTornarEquipamentoIndisponivelAoEmprestar() {

        // PREPARAR
        Equipamento equipamento = new Equipamento();

        // EXECUTAR
        equipamento.emprestar();

        // VERIFICAR
        assertFalse(equipamento.isDisponivel());
    }

    @Test
    void naoDeveEmprestarEquipamentoIndisponivel() {

        // PREPARAR
        Equipamento equipamento = new Equipamento();
        equipamento.setDisponivel(false);

        // EXECUTAR E VERIFICAR
        assertThrows(IllegalStateException.class, () -> {
            equipamento.emprestar();
        });
    }

    @Test
    void deveTornarEquipamentoDisponivelAoDevolver() {

        // PREPARAR
        Equipamento equipamento = new Equipamento();
        equipamento.setDisponivel(false);

        // EXECUTAR
        equipamento.devolver();

        // VERIFICAR
        assertTrue(equipamento.isDisponivel());
    }
}