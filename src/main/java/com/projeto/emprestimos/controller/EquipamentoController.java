// 11. EquipamentoController é responsável por receber as requisições
// HTTP relacionadas aos equipamentos e chamar o EquipamentoService tbm.

package com.projeto.emprestimos.controller;

import com.projeto.emprestimos.service.EquipamentoService;
import org.springframework.web.bind.annotation.*;
import com.projeto.emprestimos.model.Equipamento;
import com.projeto.emprestimos.model.TipoEquipamento;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    // Atributo que permite acessar as regras de negócio dos equipamentos
    private final EquipamentoService equipamentoService;

    // CONSTRUTOR: recebe o EquipamentoService
    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    // GET: Lista todos os equipamentos ou filtra por tipo e disponibilidade
    @GetMapping
    public List<Equipamento> listar(
            @RequestParam(required = false) TipoEquipamento tipo,
            @RequestParam(required = false) Boolean disponivel) {

        return equipamentoService.listar(tipo, disponivel);
    }
    // GET: Busca um equipamento específico pelo ID
    @GetMapping("/{id}")
    public Equipamento buscar(@PathVariable Long id) {

        return equipamentoService.buscar(id);
    }
    // POST: Cadastra um novo equipamento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipamento cadastrar(@RequestBody Equipamento equipamento) {

        return equipamentoService.cadastrar(equipamento);
    }
    // PUT: Atualiza os dados de um equipamento pelo ID
    @PutMapping("/{id}")
    public Equipamento atualizar(
            @PathVariable Long id,
            @RequestBody Equipamento dados) {

        return equipamentoService.atualizar(id, dados);
    }

    // DELETE: Remove um equipamento pelo ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {

        equipamentoService.remover(id);
    }

}