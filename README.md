# Sistema de Empréstimo de Equipamentos

API REST para gerenciamento de empréstimos de equipamentos, desenvolvida com **Java 25** e **Spring Boot 4.1.2**.

O sistema permite cadastrar e consultar equipamentos e colaboradores, realizar empréstimos e devoluções, além de acompanhar prazos e identificar empréstimos atrasados.

## Tecnologias utilizadas

- Java 25
- Spring Boot 4.1.2
- Maven
- Spring Data JPA
- H2 Database
- JUnit 5

## Como executar o projeto

**Pré-requisito:** Java 25 instalado.

1. Clone o repositório:
   ```bash
   git clone https://github.com/KamzMello/sistema-emprestimos-equipamentos.git
   ```

2. Entre na pasta do projeto:
   ```bash
   cd sistema-emprestimos-equipamentos
   ```

3. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

4. A API estará disponível em:
   `http://localhost:8080`

## Rotas da API

### Equipamentos

| Método | Rota | Descrição |
|---|---|---|
| POST | `/equipamentos` | Cadastrar equipamento |
| GET | `/equipamentos` | Listar equipamentos |
| GET | `/equipamentos/{id}` | Buscar equipamento por ID |
| PUT | `/equipamentos/{id}` | Atualizar equipamento |
| DELETE | `/equipamentos/{id}` | Remover equipamento |

O GET `/equipamentos` permite filtrar por tipo e disponibilidade.

### Colaboradores

| Método | Rota | Descrição |
|---|---|---|
| POST | `/colaboradores` | Cadastrar colaborador |
| GET | `/colaboradores` | Listar colaboradores |
| GET | `/colaboradores/{id}` | Buscar colaborador por ID |
| PUT | `/colaboradores/{id}` | Atualizar colaborador |
| DELETE | `/colaboradores/{id}` | Excluir colaborador |
| GET | `/colaboradores/{id}/emprestimos` | Listar empréstimos do colaborador |

### Empréstimos

| Método | Rota | Descrição |
|---|---|---|
| POST | `/emprestimos?equipamentoId=1&colaboradorId=2` | Realizar empréstimo |
| GET | `/emprestimos` | Listar empréstimos |
| GET | `/emprestimos?atrasados=true` | Listar empréstimos atrasados |
| GET | `/emprestimos/{id}` | Buscar empréstimo por ID |
| PUT | `/emprestimos/{id}/devolucao` | Registrar devolução |

## Testes

O projeto possui testes unitários com JUnit 5 para verificar o comportamento das entidades e suas regras.

## Bônus implementados

Nenhum bônus adicional declarado. O projeto contempla os requisitos obrigatórios da atividade.

## Deploy

Link da aplicação no Render: https://sistema-emprestimos-equipamentos-tndv.onrender.com