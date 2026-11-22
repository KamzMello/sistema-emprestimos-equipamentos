//3. O EquipamentoRepository é a ponte entre o  sistema Java e o BD.( banco de dados)

package com.projeto.emprestimos.repository;

import com.projeto.emprestimos.model.Equipamento;
import com.projeto.emprestimos.model.TipoEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 3. INTERFACE RESPONSÁVEL PELO ACESSO AOS DADOS DOS EQUIPAMENTOS
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

// Para verifica se já existe um equipamento com esse patrimônio
    boolean existsByPatrimonio(String patrimonio);

//Faz a busca dos equipamentos pelo tipo
    List<Equipamento> findByTipo(TipoEquipamento tipo);

// Busca equipamentos pela disponibilidade
    List<Equipamento> findByDisponivel(Boolean disponivel);

// Busca os equipamentos pelo tipo e pela disponibilidade tbm.
    List<Equipamento> findByTipoAndDisponivel(
            TipoEquipamento tipo, Boolean disponivel);
}