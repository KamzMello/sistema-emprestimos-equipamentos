// 2. A CLASSE EQUIPAMENTO representa os equipamentos cadastrados
// no sistema, armazenando ID, patrimônio, nome, tipo e disponibilidade.

package com.projeto.emprestimos.model;
import jakarta.persistence.*;

@Entity
public class Equipamento {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String patrimonio;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEquipamento tipo;

    @Column(nullable = false)
    private boolean disponivel = true;

    // CONSTRUTOR
    public Equipamento() {
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEquipamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEquipamento tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // R4: Realiza o empréstimo do equipamento
    public void emprestar() {

        // Verifica se o equipamento está disponível
        if (!disponivel) {
            throw new IllegalStateException("Equipamento indisponível");
        }

        // Altera a disponibilidade para false
        disponivel = false;
    }

    // Devolve o equipamento, tornando-o disponível novamente
    public void devolver() {
        disponivel = true;
    }
}