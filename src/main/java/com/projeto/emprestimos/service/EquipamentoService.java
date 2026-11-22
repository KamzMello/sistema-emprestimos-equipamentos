// 4. EquipamentoService é responsável pela regra de negócio.
// ou seja pelas regras que o sistema tem que respeitar.

package com.projeto.emprestimos.service;

import com.projeto.emprestimos.repository.EquipamentoRepository;
import com.projeto.emprestimos.repository.EmprestimoRepository;
import com.projeto.emprestimos.model.Equipamento;
import org.springframework.stereotype.Service;
import com.projeto.emprestimos.model.TipoEquipamento;
import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final EmprestimoRepository emprestimoRepository;

    // CONSTRUTOR
    public EquipamentoService(
            EquipamentoRepository equipamentoRepository,
            EmprestimoRepository emprestimoRepository) {

        this.equipamentoRepository = equipamentoRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    // Cadastra um equipamento no sistema
    public Equipamento cadastrar(Equipamento equipamento) {

        // Verifica se o patrimônio já está cadastrado
        if (equipamentoRepository.existsByPatrimonio(equipamento.getPatrimonio())) {
            throw new RuntimeException("Patrimônio já cadastrado!");
        }

        // Salva o equipamento no BD.
        equipamento.setDisponivel(true);

        return equipamentoRepository.save(equipamento);
    }

    // Lista equipamentos, podendo filtrar por tipo e  disponibilidade
    public List<Equipamento> listar(TipoEquipamento tipo, Boolean disponivel) {

        if (tipo != null && disponivel != null) {
            return equipamentoRepository.findByTipoAndDisponivel(tipo, disponivel);
        }

        if (tipo != null) {
            return equipamentoRepository.findByTipo(tipo);
        }

        if (disponivel != null) {
            return equipamentoRepository.findByDisponivel(disponivel);
        }

        return equipamentoRepository.findAll();
    }

    // Busca um equipamento pelo ID
    public Equipamento buscar(Long id) {
        return equipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado!"));
    }

    // Atualiza os dados de um equipamento
    public Equipamento atualizar(Long id, Equipamento dados) {

        Equipamento equipamento = buscar(id);

        equipamento.setNome(dados.getNome());
        equipamento.setTipo(dados.getTipo());

        return equipamentoRepository.save(equipamento);
    }

    // Remove um equipamento do sistema
    public void remover(Long id) {

        // Busca o equipamento pelo ID
        Equipamento equipamento = buscar(id);

        // R9: Verifica se o equipamento já possui empréstimo
        if (emprestimoRepository.existsByEquipamentoId(id)) {
            throw new IllegalStateException(
                    "Não é possível remover um equipamento que possui empréstimos!");
        }

        // Remove o equipamento do banco de dados
        equipamentoRepository.delete(equipamento);
    }
}