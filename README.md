# Sistema Bancário API

API REST desenvolvida em **Java com Spring Boot** para simular operações básicas de um sistema bancário, como **criação de contas**, **depósitos**, **saques**, **transferências** e **consulta de saldo**, com foco em **boas práticas de arquitetura**, **validação**, **tratamento de exceções** e **testes unitários**.

---
## O que eu aprendi com este projeto

###  Modelagem de Domínio
- Representação de entidades do sistema bancário
- Consistência e integridade dos dados

###  Regras de Negócio
- Validações críticas (saldo, valores, CPF)
- Centralização da lógica na camada de serviço

###  Arquitetura em Camadas
- Separação entre Controller, Service, Repository e DTOs
- Código organizado e de fácil manutenção

###  APIs REST
- Criação de endpoints REST seguindo boas práticas
- Uso correto dos métodos HTTP
- Documentação com Swagger

###  Tratamento de Exceções
- Criação de exceções personalizadas
- Tratamento global de erros com @RestControllerAdvice

###  Testes Unitários
- Testes da camada de serviço
- Cobertura de cenários de sucesso e erro
- Uso de JUnit 5 e Mockito

###  Boas Práticas de Desenvolvimento
- Código limpo e legível
- Validações com Bean Validation
- Versionamento com Git

---

## Funcionalidades

- Criação de contas bancárias
- Consulta de saldo por CPF
- Depósito em conta
- Saque com validação de saldo
- Transferência entre contas
- Registro de transações
- Validação de dados de entrada
- Tratamento global de exceções
- Documentação automática com Swagger
- Testes unitários

---

## Arquitetura

O projeto segue uma **arquitetura em camadas**, separando responsabilidades:

- **Controller** – Camada de entrada (API REST)
- **Service** – Regras de negócio
- **Repository** – Acesso a dados (JPA)
- **DTOs** – Transferência de dados
- **Entities** – Modelo de domínio
- **Exceptions** – Exceções personalizadas
- **Handler** – Tratamento global de erros

Essa separação facilita **manutenção, testes e escalabilidade**.

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4**
  - Spring Web
  - Spring Data JPA
  - Bean Validation
- **Banco de dados H2**
- **Swagger / OpenAPI**
- **JUnit 5**
- **Mockito**
- **Maven**

---

## Documentação da API

A documentação da API é gerada automaticamente com **Swagger**.

Após iniciar a aplicação, acesse:
http://localhost:8080/swagger-ui.html


---

## Tratamento de Erros

O projeto possui **tratamento global de exceções** utilizando `@RestControllerAdvice`, retornando respostas HTTP padronizadas para:

- Conta não encontrada
- Conta já existente (CPF duplicado)
- Saldo insuficiente
- Valor inválido para operações
- Erros de validação de entrada
- Violações de integridade do banco de dados

---

## Testes

Foram implementados **testes unitários** para a camada de **Service**, validando:

- Casos de sucesso
- Principais cenários de erro
- Regras de negócio críticas (ex: saldo negativo)

Os testes utilizam **JUnit 5** e **Mockito**, garantindo isolamento das dependências.

---

Desenvolvido por **Lucas Henrique**.
