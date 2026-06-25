# 🔐 Access Control API

API REST de controle de acesso desenvolvida com **Spring Boot**. O sistema gerencia usuários, catracas, logs de acesso e acessos temporários, com autenticação via **JWT** e **Spring Security**.

---

## 🛠️ Tecnologias

![Java](https://img.shields.io/badge/Java_20-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.5-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=flat&logo=JSON%20web%20tokens&logoColor=white)

- Java 20
- Spring Boot 3.5
- Spring Security
- JWT (jjwt 0.12.3)
- Spring Data JPA / Hibernate
- PostgreSQL
- Lombok

---

## 📁 Estrutura do Projeto

```
src/main/java/com/maycool/access_control/
├── controller/         # Endpoints REST (recebem e respondem requisições)
├── service/            # Lógica de negócio
├── repository/         # Acesso ao banco de dados
├── entity/             # Modelos das tabelas do banco
├── enums/              # Tipos fixos do sistema (UserType, AccessType)
├── dto/
│   ├── request/        # Objetos recebidos nas requisições
│   └── response/       # Objetos retornados nas respostas
└── security/
    ├── jwt/            # Geração e validação de tokens JWT
    └── configuration/  # Configuração do Spring Security
```

---

## ✅ Pré-requisitos

- Java 17+
- Maven
- PostgreSQL rodando localmente

---

## ⚙️ Configuração

### 1. Crie o banco de dados no PostgreSQL

```sql
CREATE DATABASE "access-control-database";
```

### 2. Configure as variáveis de ambiente

Crie um arquivo `application.properties` em `src/main/resources/` baseado no `application-example.properties`:

```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.url=${DB_URL}
app.jwt.secret=${JWT_SECRET}
```

Ou defina as variáveis de ambiente diretamente:

| Variável      | Descrição                        | Exemplo                                                   |
|---------------|----------------------------------|-----------------------------------------------------------|
| `DB_USERNAME` | Usuário do PostgreSQL            | `postgres`                                                |
| `DB_PASSWORD` | Senha do PostgreSQL              | `sua_senha`                                               |
| `DB_URL`      | URL de conexão                   | `jdbc:postgresql://localhost:5432/access-control-database`|
| `JWT_SECRET`  | Chave secreta para assinar os tokens JWT | `uma_chave_longa_e_segura`                        |

### 3. Execute o projeto

```bash
mvn spring-boot:run
```

> A API estará disponível em `http://localhost:8083`.  
> O Hibernate cria as tabelas automaticamente na primeira execução.

---

## 🔑 Autenticação

A API usa autenticação **JWT**. Para acessar os endpoints protegidos:

**1. Faça login para obter o token:**

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "sua_senha"
}
```

**Resposta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**2. Use o token em todas as requisições seguintes:**

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

> ⚠️ O token expira em **24 horas**.

---

## 📡 Endpoints

### Autenticação

| Método | Rota             | Descrição                            | Auth |
|--------|------------------|--------------------------------------|------|
| `POST` | `/api/auth/login`| Realiza login e retorna o token JWT  | ❌   |

---

### Usuários — `/user`

| Método   | Rota              | Descrição                    |
|----------|-------------------|------------------------------|
| `GET`    | `/user`           | Lista todos os usuários      |
| `GET`    | `/user/{id}`      | Busca usuário por ID         |
| `POST`   | `/user`           | Cria um novo usuário         |
| `PUT`    | `/user/{id}`      | Atualiza o nome do usuário   |
| `PATCH`  | `/user/{id}/status` | Ativa ou desativa o usuário |

**Exemplo de criação de usuário:**

```json
{
  "name": "João Silva",
  "username": "joao",
  "password": "senha123",
  "rfidCode": "RFID001",
  "status": true,
  "userType": "EMPLOYEE"
}
```

> `userType` pode ser: `EMPLOYEE`, `VISITOR` ou `ADMIN`

---

### Catracas — `/turnstile`

| Método   | Rota                   | Descrição                       |
|----------|------------------------|---------------------------------|
| `GET`    | `/turnstile`           | Lista todas as catracas         |
| `GET`    | `/turnstile/{id}`      | Busca catraca por ID            |
| `POST`   | `/turnstile`           | Cadastra uma nova catraca       |
| `PUT`    | `/turnstile/{id}`      | Atualiza a localização da catraca |
| `PATCH`  | `/turnstile/{id}/status` | Ativa ou desativa a catraca   |

**Exemplo de criação de catraca:**

```json
{
  "location": "Entrada Principal",
  "status": true
}
```

---

### Logs de Acesso — `/access-log`

| Método | Rota          | Descrição                        |
|--------|---------------|----------------------------------|
| `GET`  | `/access-log` | Lista todo o histórico de acessos|
| `POST` | `/access-log` | Registra um novo evento de acesso|

**Exemplo de registro de log:**

```json
{
  "dateTime": "2026-06-24T22:00:00",
  "access": true,
  "accessType": "ENTRY",
  "user": { "id": 1 },
  "turnstile": { "id": 1 }
}
```

> `accessType` pode ser: `ENTRY` (entrada) ou `EXIT` (saída)

---

### Acessos Temporários — `/temporary-access`

| Método   | Rota                          | Descrição                               |
|----------|-------------------------------|-----------------------------------------|
| `GET`    | `/temporary-access`           | Lista todos os acessos temporários      |
| `POST`   | `/temporary-access`           | Cria um acesso temporário para um visitante |
| `PATCH`  | `/temporary-access/{id}/status` | Ativa ou desativa o acesso temporário |

**Exemplo de criação de acesso temporário:**

```json
{
  "active": true,
  "createdAt": "2026-06-24T08:00:00",
  "expiresAt": "2026-06-24T18:00:00",
  "user": { "id": 2 }
}
```

---

## 🔄 Fluxo de Autenticação

```
Cliente envia requisição com token JWT
        ↓
JwtFilter intercepta e extrai o token do cabeçalho Authorization
        ↓
JwtService valida a assinatura e extrai o username
        ↓
CustomDetailsService carrega o usuário do banco pelo username
        ↓
Spring Security verifica as permissões e libera o acesso ao endpoint
```

---

## 👤 Autor

Desenvolvido por **Maycool Vinicius**
