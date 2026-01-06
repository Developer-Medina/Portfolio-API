# Portfolio API

API REST desenvolvida em **Java com Spring Boot**, criada para servir como backend do meu portfólio pessoal.  
Ela é responsável por gerenciar projetos, seus dados e descrições detalhadas, com foco em **boas práticas**, **validação**, **tratamento de exceções** e **deploy em produção**.

Este projeto não tem como objetivo ser um produto genérico ou comercial, mas sim demonstrar domínio técnico em backend, arquitetura REST e integração com frontend.

---

## ✨ Principais características

- API RESTful seguindo boas práticas
- Uso de **DTOs** para isolamento de camada
- Relacionamento **One-to-One** (`Project` ↔ `ProjectDescription`)
- Validação de dados (Bean Validation + regras de negócio)
- Tratamento global de exceções (`@ControllerAdvice`)
- Validação de datas (formato + range permitido)
- Enum com validação semântica (`Category`)
- Proteção por **API Key**
- Deploy em ambiente real (Heroku)
- Banco de dados PostgreSQL

---

## 🧱 Modelagem de domínio (resumo)

- **Project**
  - Dados principais do projeto (nome, imagem, categoria, data etc.)
- **ProjectDescription**
  - Entidade auxiliar responsável apenas pela descrição longa
  - Campo anotado com `@Lob`
  - Não possui endpoint próprio
  - Ciclo de vida totalmente dependente de `Project`

Essa separação foi uma decisão arquitetural consciente, visando organização, clareza e escalabilidade da aplicação.

---

## 🔐 Segurança (API Key)

Todos os endpoints da API exigem uma **API Key** enviada via header.

### Headers esperados:

PORTFOLIO-API-KEY: sua-chave-aqui
Content-Type: application/json

Sem a API Key correta, a API retorna **403 Forbidden**.

---

## 🌐 Endpoints da API

| Método  | Endpoint              | Descrição                                   |
|-------|-----------------------|----------------------------------------------|
| GET   | `/projects`           | Lista todos os projetos                      |
| GET   | `/projects/{id}`      | Busca um projeto por ID                     |
| POST  | `/projects`           | Cria um novo projeto                        |
| PUT   | `/projects/{id}`      | Atualiza um projeto existente               |
| DELETE| `/projects/{id}`      | Remove um projeto                           |

---

## 📥 Exemplo 1 - Envio de dados (POST)

```json
{
  "visible": true,
  "imgUrl": "https://example.com/updated.png",
  "name": "Updated Project",
  "subtitle": "Another test",
  "creationDate": "02/06/2025",
  "category": 1,
  "githubUrl": "https://github.com/medina/updated",
  "projectDescription": {
    "longDescription": "POST test on localhost"
  }
}

```

Regras importantes:

- id não deve ser enviado
- category aceita apenas:

1 - para FRONT_END

2 - para BACK_END

3 - para FULLSTACK

Além disso, creationDate deve estar no formato dd/MM/yyyy e dentro do range permitido.


## 📥Exemplo 2 - Resposta da API (usando GET)

```json
{
  "id": 2,
  "visible": true,
  "imgUrl": "https://example.com/api.png",
  "name": "Portfolio API",
  "subtitle": "Spring Boot REST API",
  "creationDate": "01/05/2026",
  "category": "BACK_END",
  "githubUrl": "https://github.com/medina/portfolio-api",
  "projectDescription": {
    "id": 2,
    "longDescription": "A RESTful API built with Spring Boot."
  }
}
```

## ⚠️ Tratamento de exceções

A API possui tratamento global de exceções, retornando mensagens claras e códigos HTTP coerentes. Exemplo de projeto não encontrado (retornando HTTP 404):

```json
{
  "timestamp": "2026-01-05T21:03:10Z",
  "status": 404,
  "error": "Project not found",
  "message": "There is no project with id 7",
  "path": "/projects/7"
}
```
---

## 👤 Autor

**Medina**

_Estudante de Análise e Desenvolvimento de Sistemas na Universidade Presbiteriana Mackenzie._

**Backend** (Spring, Java, RESTful APIs) | **Frontend** (HTML, CSS, JS e React)
