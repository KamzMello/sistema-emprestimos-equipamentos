// 13. EmprestimoController é responsável por receber as requisições
// HTTP relacionadas aos empréstimos e chamar o EmprestimoService.

package com.projeto.emprestimos.controller;

import com.projeto.emprestimos.service.EmprestimoService;
import com.projeto.emprestimos.model.Emprestimo;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    // Atributo que permite acessar as regras de negócio dos empréstimos
    private final EmprestimoService emprestimoService;

    // CONSTRUTOR: recebe o EmprestimoService
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    // POST: Realiza o empréstimo de um equipamento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Emprestimo emprestar(
            @RequestParam Long equipamentoId,
            @RequestParam Long colaboradorId) {

        return emprestimoService.emprestar(equipamentoId, colaboradorId);
    }

    // GET: Lista todos os empréstimos, podendo filtrar os atrasados
    @GetMapping
    public List<Emprestimo> listar(
            @RequestParam(required = false) Boolean atrasados) {

        return emprestimoService.listar(atrasados);
    }

    // GET: Busca um empréstimo específico pelo ID
    @GetMapping("/{id}")
    public Emprestimo buscar(@PathVariable Long id) {

        return emprestimoService.buscar(id);
    }

    // PUT: Realiza a devolução de um equipamento emprestado
    @PutMapping("/{id}/devolucao")
    public Emprestimo devolver(@PathVariable Long id) {

        return emprestimoService.devolver(id);
    }
}