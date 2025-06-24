# Gestão de Processos Judiciais API

API RESTful para gerenciar processos judiciais e agendamento de audiências.

## Pré-requisitos

Antes de começar, certifique-se de que você atendeu aos seguintes requisitos:

*   Java 11 ou mais recente instalado.
*   Maven instalado.

## Como Rodar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Rafaelraas/judicial-process-management-api.git
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd judicial-process-management-api
    ```

3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```

A API estará rodando em `http://localhost:8080`.

## Autenticação

A API utiliza autenticação baseada em token JWT. Para se autenticar, envie uma requisição `POST` para `/api/v1/auth/login` com o seguinte corpo:

```json
{
    "username": "user",
    "password": "password"
}
```

Você receberá um token JWT como resposta. Inclua este token no cabeçalho `Authorization` de todas as requisições subsequentes.

**Exemplo de Cabeçalho:**
```
Authorization: Bearer <seu-token-jwt>
```

## Documentação da API (Swagger)

A documentação da API está disponível via Swagger UI. Após iniciar a aplicação, acesse a seguinte URL no seu navegador:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints da API

Todos os endpoints estão sob o prefixo `/api/v1`.

### Processos

*   `GET /api/v1/processos`: Retorna uma lista de todos os processos judiciais.
    *   Filtros (Query Params): `status` (ATIVO, ARQUIVADO, SUSPENSO), `comarca`.
*   `GET /api/v1/processos/{id}`: Retorna um processo específico pelo seu ID.
*   `POST /api/v1/processos`: Cria um novo processo judicial.
*   `PUT /api/v1/processos/{id}`: Atualiza um processo existente.
*   `DELETE /api/v1/processos/{id}`: Deleta um processo.

### Audiências

*   `GET /api/v1/audiencias`: Retorna uma lista de todas as audiências.
*   `GET /api/v1/audiencias/{id}`: Retorna uma audiência específica pelo seu ID.
*   `POST /api/v1/audiencias`: Cria uma nova audiência.
*   `PUT /api/v1/audiencias/{id}`: Atualiza uma audiência existente.
*   `DELETE /api/v1/audiencias/{id}`: Deleta uma audiência.

### Agenda

*   `GET /api/v1/agenda`: Retorna a agenda de audiências de uma comarca em um determinado dia.
    *   Query Params: `comarca` (String), `dia` (LocalDate, formato: YYYY-MM-DD).

## Banco de Dados

Esta aplicação utiliza um banco de dados em memória H2. O banco de dados é populado com dados iniciais na inicialização e será limpo quando a aplicação for parada.

Você pode acessar o console do H2 em: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

**Configurações do H2 Console:**
*   **Driver Class:** `org.h2.Driver`
*   **JDBC URL:** `jdbc:h2:mem:testdb`
*   **User Name:** `sa`
*   **Password:** (deixe em branco)

## Executando os Testes

Para executar os testes automatizados do projeto, execute o seguinte comando no seu terminal:

```bash
mvn test
```

## Exemplos de Requisições (cURL)

Aqui estão alguns exemplos de como interagir com a API usando cURL.

**1. Obter o Token de Autenticação**

Primeiro, obtenha um token JWT para autenticar suas requisições.

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
-H "Content-Type: application/json" \
-d '{
    "username": "user",
    "password": "password"
}'
```

**Resposta Esperada:**

```json
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNzE5MjM4ODk5LCJpYXQiOjE3MTkyMDI4OTl9.example_token"
}
```

**2. Listar Todos os Processos**

Use o token obtido no cabeçalho `Authorization` para listar todos os processos. Substitua `SEU_TOKEN_AQUI` pelo token real.

```bash
curl -X GET http://localhost:8080/api/v1/processos \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

**3. Criar um Novo Processo**

```bash
curl -X POST http://localhost:8080/api/v1/processos \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN_AQUI" \
-d '{
    "numeroProcesso": "0000006-06.2025.1.01.0001",
    "comarca": "Comarca Sul",
    "vara": "3ª Vara Cível",
    "status": "ATIVO",
    "nomeReclamante": "Nova Reclamante",
    "nomeReclamado": "Empresa de Teste S.A.",
    "dataDistribuicao": "2025-06-24"
}'
```

**4. Consultar a Agenda de Audiências**

Consulte a agenda para uma comarca e um dia específicos.

```bash
curl -X GET "http://localhost:8080/api/v1/agenda?comarca=Comarca%20Central&dia=2025-06-26" \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```
