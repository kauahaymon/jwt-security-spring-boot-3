# 📦 Sistema de Gerenciamento de Pedidos

Uma aplicação RESTful desenvolvida com Spring Boot para gerenciar usuários, produtos, pedidos e autenticação com JWT. Inclui níveis de permissão (admin e usuário), endpoints bem definidos, versionamento de API e suporte à execução via Docker.

---

## 🧵 Descrição

O sistema permite:

- Registro e autenticação de usuários
- Gestão de produtos (admin)
- Criação e visualização de pedidos (usuário)
- Atribuição de permissões e controle de acesso com roles
- Versionamento da API (`/api/v1/...`)
- Padrão de camadas e boas práticas de design REST

---

## 🛠️ Tecnologias

- Java 17+
- Spring Boot 3
- Spring Security com JWT
- Spring Data JPA
- Flyway (migrações)
- PostgreSQL (ou H2 para dev/teste)
- Maven
- Docker & Docker Compose
- Postman (coleção incluída)

---

## 📚 Documentação da API

---

## 🔐 Autenticação

Autenticação via JWT:

- Endpoints protegidos exigem header:

  `Authorization: Bearer <token>`


---

## ↺ Endpoints principais

### 🧑 Auth

| Método | Endpoint | Descrição |
| --- | --- | --- |
| POST | `/api/v1/auth/register` | Registrar novo usuário |
| POST | `/api/v1/auth/authenticate` | Login e obtenção do token |
| GET | `/api/v1/auth/activate-account?token={token}` | Ativa conta com token |
| POST | `/api/v1/auth/refresh-token` | Gera novo token com o refresh token |

---

### 👤 Usuário

| Método | Endpoint | Descrição |
| --- | --- | --- |
| GET | `/api/v1/products` | Listar todos os produtos |
| GET | `/api/v1/products/{id}` | Buscar produto por ID |
| POST | `/api/v1/orders` | Criar pedido |
| GET | `/api/v1/orders` | Listar pedidos do usuário |
| GET | `/api/v1/orders/{id}` | Ver detalhes de um pedido |

---

### 🔧 Admin

| Método | Endpoint | Descrição |
| --- | --- | --- |
| POST | `/api/v1/admin/products` | Criar produto |
| PUT | `/api/v1/admin/products/{id}` | Atualizar produto |
| DELETE | `/api/v1/admin/products/{id}` | Remover produto |
| GET | `/api/v1/admin/users` | Listar usuários |
| PUT | `/api/v1/admin/users/{id}` | Atualizar usuário |
| DELETE | `/api/v1/admin/users/{id}` | Remover usuário |
| GET | `/api/v1/admin/orders` | Listar todos os pedidos |
| GET | `/api/v1/admin/users/{id}/orders` | Ver pedidos de um usuário |

---

## ✅ Boas práticas aplicadas

- ✅ Versionamento da API
- ✅ Autenticação e autorização via roles
- ✅ Separacão por camadas (controller, service, repository)
- ✅ Tratamento de exceções personalizado
- ✅ Migrações com Flyway
- ✅ Configuração de ambientes com perfis Spring

---

## **⚙ Como Iniciar a API**

### **🌀 Clonar o repositório**

```
git clone https://github.com/kauahaymon/order-management-api.git
```

**Navegue até o Diretório do Projeto**:

```
cd order-management-api

```

### **🐳 Rodar com Docker (recomendado)**

Certifique-se de ter Docker **instalado** e **rodando.** Não tem docker? [**Docker Download**](https://www.docker.com/products/docker-desktop/)

Rode o comando abaixo na pasta raiz do projeto:

```
docker-compose up --build

```

O comando acima irá criar a imagem **Docker** e subir os contairners do **Banco Postgres** e da **Aplicação.**

Na primeira vez, ele irá baixar os arquivos necessários, aguarde alguns segundos.

Para rodar nas próximas vezes após tudo baixado, rode:

```
docker-compose up
```

A aplicação será executada em: [`http://localhost:8080`](http://localhost:8080/)

**🚀 Rodar com Maven (local)**

Pre requisitos:

- Ter **Java 21** instalado (`java -version`)
- Ter **Maven** instalado (`mvn -version`)
- Ter o **PostgreSQL** rodando localmente
1. Acesse seu PostgreSQL pelo **pgAdmin** ou pelo **Terminal**:

   Pelo **terminal**:

    ```
    psql -U postgres
    ```

- **Senha**: postgres
- Crie o banco `patos_database` manualmente.

    ```
    CREATE DATABASE order-management-db;
    ```


Rode o projeto:

```
./mvnw spring-boot:run
```

**🔍 Acessar a API**

- A aplicação será executada em: [**`http://localhost:8080`**](http://localhost:8080/)

---

## **📂 Como testar a API**

Você pode baixar a coleção do **Postman** para testar os endpoints da API.

**Como baixar a coleção JSON para o Postman:**

1. Clique no link abaixo para baixar o arquivo da coleção do Postman:

   [`Baixar coleção Postman`](https://github.com/kauahaymon/order-management-api/blob/main/test/Order%20Management.postman_collection.json)

2. Importe o arquivo JSON no de preferência no **Postaman** para começar a testar os endpoints da API.

## **👨‍💻 Desenvolvedor**

Se você quiser saber mais sobre mim ou entrar em contato, acesse meu perfil no [LinkedIn](https://www.linkedin.com/in/kauahaymon)