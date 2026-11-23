# 📦 Sistema de Empréstimo de Equipamentos

API REST para gerenciamento de empréstimos de equipamentos, desenvolvida com **Java 25** e **Spring Boot 4.1.2**.

O sistema permite cadastrar e consultar equipamentos e colaboradores, realizar empréstimos e devoluções, acompanhar prazos e identificar empréstimos atrasados.

## 🛠️ Tecnologias utilizadas

- Java 25
- Spring Boot 4.1.2
- Maven
- Spring Data JPA
- H2 Database
- Spring Security
- JWT (JSON Web Token)
- BCrypt
- JUnit 5

## 🚀 Como executar o projeto

**Pré-requisito:** Java 25 instalado.

1. Clone o repositório:

```bash
git clone https://github.com/KamzMello/sistema-emprestimos-equipamentos.git
```

2. Entre na pasta do projeto:

```bash
cd sistema-emprestimos-equipamentos
```

3. Configure a variável de ambiente utilizada para assinar os tokens JWT:

```bash
export JWT_SECRET="$(openssl rand -base64 32)"
```

4. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

5. A API estará disponível em:

http://localhost:8080

## 🔗 Rotas da API

### 🔐 Autenticação

| Método | Rota | Descrição |
|---|---|---|
| POST | `/auth/registro` | Cadastrar colaborador |
| POST | `/auth/login` | Realizar login e gerar token JWT |

### 💻 Equipamentos

| Método | Rota | Descrição |
|---|---|---|
| POST | `/equipamentos` | Cadastrar equipamento |
| GET | `/equipamentos` | Listar equipamentos |
| GET | `/equipamentos/{id}` | Buscar equipamento por ID |
| PUT | `/equipamentos/{id}` | Atualizar equipamento |
| DELETE | `/equipamentos/{id}` | Remover equipamento |

O GET `/equipamentos` permite filtrar por tipo e disponibilidade.

### 👥 Colaboradores

| Método | Rota | Descrição |
|---|---|---|
| POST | `/colaboradores` | Cadastrar colaborador |
| GET | `/colaboradores` | Listar colaboradores |
| GET | `/colaboradores/{id}` | Buscar colaborador por ID |
| PUT | `/colaboradores/{id}` | Atualizar colaborador |
| DELETE | `/colaboradores/{id}` | Excluir colaborador |
| GET | `/colaboradores/{id}/emprestimos` | Listar empréstimos do colaborador |

### 📋 Empréstimos

| Método | Rota | Descrição |
|---|---|---|
| POST | `/emprestimos?equipamentoId=1&colaboradorId=2` | Realizar empréstimo |
| GET | `/emprestimos` | Listar empréstimos |
| GET | `/emprestimos?atrasados=true` | Listar empréstimos atrasados |
| GET | `/emprestimos/{id}` | Buscar empréstimo por ID |
| PUT | `/emprestimos/{id}/devolucao` | Registrar devolução |

## 🔒 Segurança — Spring Security + JWT

O projeto possui autenticação e proteção de rotas com Spring Security e JWT.

**Funcionalidades implementadas:**

- Cadastro de colaboradores com senha criptografada usando BCrypt.
- Login com validação de e-mail e senha.
- Geração de token JWT com validade de 24 horas.
- Validação do token nas requisições.
- Proteção das rotas da API, exigindo autenticação.
- Senhas não são exibidas nas respostas da API.

As rotas `/auth/registro` e `/auth/login` são públicas. O console H2 (`/h2-console/**`) também está liberado. As demais rotas exigem um token JWT válido.

Para acessar uma rota protegida, envie o token no cabeçalho:

```http
Authorization: Bearer SEU_TOKEN
```

## 🧪 Testes

O projeto possui **11 testes automatizados com JUnit 5**, executados com sucesso:

- **10 testes obrigatórios:** validam o comportamento das entidades e as principais regras de negócio.
- **1 teste adicional (`contextLoads`):** teste padrão do Spring Boot, que verifica se o contexto da aplicação inicia corretamente.

**Resultado:** 11 testes executados, 0 falhas e 0 erros.

A autenticação JWT também foi verificada por testes manuais de:

- Cadastro de usuário;
- Login e geração de token;
- Bloqueio de acesso sem token;
- Acesso autorizado com token válido.

## ⭐ Bônus implementados

- **Spring Security + JWT:** autenticação de usuários, geração e validação de tokens e proteção das rotas da API.

## ☁️ Deploy — Backend

Aplicação hospedada no Render:

https://sistema-emprestimos-equipamentos-tndv.onrender.com

## 🌐 Site — Front-end

Interface web do sistema de empréstimo de equipamentos, desenvolvida com React e Vite.

**Repositório:** [sistema-emprestimos-frontend](https://github.com/KamzMello/sistema-emprestimos-frontend)

**Site publicado:** Em breve.

## 🎥 Vídeo de apresentação

Vídeo de apresentação do projeto, demonstrando suas funcionalidades e explicando seu desenvolvimento.

**Link:** ADD AINDA
