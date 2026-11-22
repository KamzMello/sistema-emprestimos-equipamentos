// 12. ColaboradorController é responsável por receber as requisições
// HTTP relacionadas aos colaboradores e chamar os Services.

package com.projeto.emprestimos.controller;

import com.projeto.emprestimos.service.ColaboradorService;
import com.projeto.emprestimos.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;
import com.projeto.emprestimos.model.Colaborador;
import com.projeto.emprestimos.model.Emprestimo;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    // Atributos que permitem acessar as regras de negócio.
    private final ColaboradorService colaboradorService;
    private final EmprestimoService emprestimoService;

    // CONSTRUTOR: recebe os 2 Services
    public ColaboradorController(
            ColaboradorService colaboradorService,
            EmprestimoService emprestimoService) {

        this.colaboradorService = colaboradorService;
        this.emprestimoService = emprestimoService;
    }

    // POST: Cadastra um novo colaborador.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Colaborador cadastrar(@RequestBody Colaborador colaborador) {
        return colaboradorService.cadastrar(colaborador);
    }

    // GET: Lista todos os colaboradores cadastrados
    @GetMapping
    public List<Colaborador> listar() {
        return colaboradorService.listar();
    }

    // GET: Busca um colaborador específico pelo ID
    @GetMapping("/{id}")
    public Colaborador buscar(@PathVariable Long id) {
        return colaboradorService.buscar(id);
    }

    // PUT: Atualiza os dados de um colaborador pelo ID.
    @PutMapping("/{id}")
    public Colaborador atualizar(
            @PathVariable Long id,
            @RequestBody Colaborador colaborador) {

        return colaboradorService.atualizar(id, colaborador);
    }

    // DELETE: Exclui um colaborador pelo ID.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        colaboradorService.excluir(id);
    }

    // GET: Lista todos os empréstimos de um colaborador específico
    @GetMapping("/{id}/emprestimos")
    public List<Emprestimo> listarEmprestimos(@PathVariable Long id) {
        return emprestimoService.listarDoColaborador(id);
    }
}
