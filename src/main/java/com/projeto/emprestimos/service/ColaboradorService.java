// 7. ColaboradorService é responsável pelas regras de negócio
// dos colaboradores, garantindo que não existam e-mails duplicados.

package com.projeto.emprestimos.service;

import com.projeto.emprestimos.model.Colaborador;
import com.projeto.emprestimos.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    // CONSTRUTOR
    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    // Lista todos os colaboradores
    public List<Colaborador> listar() {
        return colaboradorRepository.findAll();
    }

    // Busca um colaborador pelo ID
    public Colaborador buscar(Long id) {
        return colaboradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado!"));
    }

    // Cadastra um colaborador no sistema
    public Colaborador cadastrar(Colaborador colaborador) {

        // R3: Verifica se o e-mail já está cadastrado
        if (colaboradorRepository.existsByEmail(colaborador.getEmail())) {
            throw new IllegalStateException("E-mail já cadastrado!");
        }

        // Salva o colaborador no BD
        return colaboradorRepository.save(colaborador);
    }

    // Atualiza os dados de um colaborador pelo ID
    public Colaborador atualizar(Long id, Colaborador dadosAtualizados) {

        // Busca o colaborador existente
        Colaborador colaborador = buscar(id);

        // Atualiza os atributos
        colaborador.setNome(dadosAtualizados.getNome());
        colaborador.setEmail(dadosAtualizados.getEmail());
        colaborador.setCargo(dadosAtualizados.getCargo());

        // Salva as alterações no banco de dados
        return colaboradorRepository.save(colaborador);
    }

    // Exclui um colaborador pelo ID
    public void excluir(Long id) {

        // Verifica se o colaborador existe
        Colaborador colaborador = buscar(id);

        // Exclui o colaborador do banco de dados
        colaboradorRepository.delete(colaborador);
    }
}
