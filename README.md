# Projeto-Eng.Soft.II

Sistema de Cadastro de Clientes (Pessoa Física), Fornecedores (Pessoa Jurídica) e Produtos, com controle de estoque. Backend em Spring Boot (Java 21) e frontend em HTML/JS puro.

## Estrutura do projeto

```
sistemacadastro/       Backend Spring Boot (API REST)
frontend/               Frontend (dashboard administrativo)
ambiente_docker/mysql/  docker-compose do banco MySQL
junit5tutorial/         Projeto à parte, tutorial de JUnit 5
```

## Pré-requisitos

- Java 21
- Docker (para subir o MySQL) ou um MySQL local
- Um navegador (para o frontend)

## 1. Subindo o banco de dados

O jeito mais simples é usando o Docker Compose que já está no projeto:

```bash
cd ambiente_docker/mysql
docker compose up -d
```

Isso sobe um MySQL na porta `3306` (usuário `root`, senha `aluno`) e o phpMyAdmin em `http://localhost:8080`.

O backend está configurado para criar o banco `sistemacadastro` automaticamente na primeira execução (`createDatabaseIfNotExist=true`).

## 2. Rodando o backend

O projeto do backend fica em `sistemacadastro/`. A partir dessa pasta:

```bash
cd sistemacadastro
./mvnw spring-boot:run
```

No Windows (PowerShell/cmd):

```bash
cd sistemacadastro
mvnw.cmd spring-boot:run
```

A API sobe em `http://localhost:8085`. A documentação Swagger fica disponível em `http://localhost:8085/swagger-ui-entity.html`.

## 3. Rodando o frontend

O frontend é um único arquivo estático (`frontend/index.html`), sem build nem dependências. Com o backend já rodando na porta 8085, basta abrir o arquivo diretamente no navegador (duplo clique ou "Abrir com" o navegador).

Pela interface é possível:
- Cadastrar, editar e excluir Clientes (Pessoa Física), Fornecedores (Pessoa Jurídica) e Produtos, pelas abas superiores.
- Consultar um registro específico por ID, na aba "Consulta por ID".

> O frontend aponta para `http://localhost:8085` fixo (constante `API_BASE` no início do `<script>`). Se o backend rodar em outra porta, ajuste esse valor no `index.html`.

## Resumo rápido

```bash
# 1. banco
cd ambiente_docker/mysql && docker compose up -d

# 2. backend (em outro terminal, a partir da raiz do projeto)
cd sistemacadastro && ./mvnw spring-boot:run

# 3. frontend
# abra frontend/index.html no navegador
```
