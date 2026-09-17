# 💎 CampusGigs — Projeto Diamante (FIAP)

> **Plataforma de contratação e publicação de serviços (freelas) entre estudantes universitários com autenticação stateless por JWT, controle de acesso baseado em papéis (RBAC) e integração declarativa com ViaCEP.**

---

## 👥 Integrantes da Equipe

<table>
<tr>
<th>Nome</th>
<th>RM</th>
<th>Turma</th>
<th>GitHub</th>
<th>LinkedIn</th>
</tr>

<tr>
<td>Enzo Okuizumi</td>
<td>561432</td>
<td>2TDSPG</td>
<td><a href="https://github.com/EnzoOkuizumiFiap">EnzoOkuizumiFiap</a></td>
<td><a href="https://www.linkedin.com/in/enzo-okuizumi-b60292256/">Enzo Okuizumi</a></td>
</tr>

<tr>
<td>Lucas Barros Gouveia</td>
<td>566422</td>
<td>2TDSPG</td>
<td><a href="https://github.com/LuzBGouveia">LuzBGouveia</a></td>
<td><a href="https://www.linkedin.com/in/lucas-barros-gouveia-09b147355/">Lucas Barros Gouveia</a></td>
</tr>

<tr>
<td>Milton Marcelino</td>
<td>564836</td>
<td>2TDSPG</td>
<td><a href="https://github.com/MiltonMarcelino">MiltonMarcelino</a></td>
<td><a href="http://linkedin.com/in/milton-marcelino-250298142">Milton Marcelino</a></td>
</tr>

</table>

---

## 📌 Checkpoints Obrigatórios (CP1 a CP5)

Abaixo estão descritos os 5 checkpoints avaliativos exigidos no projeto, mapeados diretamente aos **commits reais realizados pela equipe no repositório**, com as justificativas técnicas das decisões adotadas:

### 1️⃣ CP1 — Infraestrutura Docker e Migrations com Schema Inicial
* **Commits Reais**:
  * [`0467a91`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/0467a91) — `feat: ajustando compose.yml para funcionar o postgres!!` (*Enzo Okuizumi*)
  * [`9c07d19`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/9c07d19) — `feat: Adicionando os scripts para flyway e corrigindo auth.` (*Lucas Gouveia*)
  * [`731e045`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/731e045) — `feat: Criando classes e scripts base.` (*Lucas Gouveia*)
  * [`5068e04`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/5068e04) — `feat: Subindo projeto inicial no repositorio.` (*Lucas Gouveia*)
* **Decisão & Justificativa Técnica**:
  > *"Configuramos o contêiner do PostgreSQL via `compose.yaml` com suporte nativo do Spring Boot (`spring-boot-docker-compose`) e versionamento de banco via Flyway (`V1__create_table.sql` e `V2__load_tables.sql`). A decisão de automatizar a subida do banco e o versionamento das tabelas garantiu que o ambiente de desenvolvimento fosse 100% reprodutível e idempotente desde o primeiro commit."*

---

### 2️⃣ CP2 — Cadastro com Validação de Endereço e Autenticação Protegida
* **Commits Reais**:
  * [`0548e5a`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/0548e5a) — `feat: Add Role e senha em Usuário, Add métodos no UsuarioRepository utilizado em TokenService e AuthService, ajustes no SecurityConfig` (*Enzo Okuizumi*)
  * [`10c3485`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/10c3485) — `feat: add cep, cidade e uf em usuario e ajustando os DTOs!` (*Enzo Okuizumi*)
  * [`881d327`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/881d327) — `feat: Add DTOs no usuário!!` (*Enzo Okuizumi*)
  * [`4f849e7`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/4f849e7) — `feat: Adicionando service do usuario.` (*Lucas Gouveia*)
  * [`f7b58bd`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/f7b58bd) — `feat: Atualizando controller e service de Usuario.` (*Lucas Gouveia*)
* **Decisão & Justificativa Técnica**:
  > *"Adotamos o `BCryptPasswordEncoder` com custo de processamento 10 para hash unidirecional com salt em todas as senhas de usuários, garantindo que senhas nunca sejam armazenadas ou expostas em texto puro. Os dados cadastrais foram encapsulados em DTOs com Bean Validation estrito (`@NotBlank`, `@Email`, `@Pattern`) e preenchimento automático de localização."*

---

### 3️⃣ CP3 — Emissão e Validação de Tokens JWT nos Endpoints Protegidos
* **Commits Reais**:
  * [`837355b`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/837355b) — `feat: CP3 - Emissão e validação de tokens (e corrigindo scripts do flyway).` (*Lucas Gouveia*)
  * [`0ae6d06`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/0ae6d06) — `feat: CP3 - Alterando classes para usar loginRequest.` (*Lucas Gouveia*)
  * [`c066926`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/c066926) — `feat: Deixando o AuthController mais limpo, usando @RequiredArgsConstructor!!` (*Enzo Okuizumi*)
  * [`7882c8c`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/7882c8c) — `feat: Configurando auth.` (*Lucas Gouveia*)
* **Decisão & Justificativa Técnica**:
  > *"Implementamos autenticação stateless com tokens JWT assinados assimetricamente por par de chaves RSA (2048 bits). Essa escolha arquitetural elimina a necessidade de sessões HTTP em memória no servidor e permite que os endpoints protegidos validem a autenticidade e expiração do Bearer Token de forma independente e segura."*

---

### 4️⃣ CP4 — Regras de Autorização por Papel (RBAC) e Regras de Negócio
* **Commits Reais**:
  * [`72873d6`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/72873d6) — `feat: CP4 - Regras de autorização por role implementadas.` (*Lucas Gouveia*)
  * [`b9851f6`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/b9851f6) — `feat: CP4 - Regras de autorização por role corrigidas.` (*Lucas Gouveia*)
  * [`d628206`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/d628206) — `feat: Adicionando cascade em Contratacao para ser deletado junto com outras classes.` (*Lucas Gouveia*)
  * [`48a0e8b`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/48a0e8b) — `feat: Ajustando contratacaoService, pois por algum motivo não estava construído corretamente o seu DTO!! Agora tudo ajustado (Espero)` (*Enzo Okuizumi*)
* **Decisão & Justificativa Técnica**:
  > *"Separamos rigorosamente autenticação de autorização através de RBAC (`ROLE_USER` e `ROLE_ADMIN`): qualquer aluno autenticado pode publicar e contratar freelas, porém a edição e exclusão de um serviço é exclusiva do seu dono, enquanto o `ADMIN` possui papel moderador para encerrar qualquer serviço. Também incluímos regras no `ContratacaoValidator` impedindo auto-contratação e contratações concorrentes do mesmo serviço."*

---

### 5️⃣ CP5 — Integração com Serviço Externo e Revisão Final
* **Commits Reais**:
  * [`39bb5d4`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/39bb5d4) — `feat: Só colocando campugigs nos endpoints para ficar mais bonito!! É isso` (*Enzo Okuizumi*)
  * [`3611480`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/3611480) — `feat: Adicionando update em Contratacao` (*Enzo Okuizumi*)
* **Decisão & Justificativa Técnica**:
  > *"Utilizamos a interface declarativa `@HttpExchange` do Spring Boot para consumir a API pública do ViaCEP sem acoplamento de bibliotecas externas, padronizamos as rotas com prefixo `/campusgigs` e centralizamos todos os erros e violações de regras no `GlobalExceptionHandler` sob a RFC 7807 (`ProblemDetail`), documentando todas as evidências de teste manual no Insomnia."*

---

## 🚀 Sobre o Projeto

O **CampusGigs** conecta estudantes universitários que oferecem habilidades e serviços (design, aulas particulares, desenvolvimento de software, manutenção) com outros alunos que necessitam dessas soluções.

A aplicação adota boas práticas de arquitetura RESTful, separação em camadas (`Controller`, `Service`, `Repository`, `DTO`), migrations versionadas e segurança ponta a ponta.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21** (LTS)
* **Spring Boot 4 / 3.x**
  * Spring Web & Bean Validation
  * Spring Data JPA & Hibernate
  * Spring Security & OAuth2 Resource Server (JWT)
  * Spring Boot Docker Compose Support
  * Declarative HTTP Interfaces (`@HttpExchange`)
* **PostgreSQL**
* **Flyway Migration**
* **Lombok**
* **Par de Chaves RSA (2048 bits)** para assinatura criptográfica de tokens
* **ViaCEP API** (Web Service externo de endereçamento)

---

## 📋 Como Executar o Projeto

### Pré-requisitos
* **JDK 21** instalado e configurado na variável de ambiente `JAVA_HOME`.
* **Docker Desktop** instalado e em execução.
* **IntelliJ IDEA**, **VS Code** ou terminal com Gradle Wrapper.

### 1. Iniciar o Ambiente
Ao iniciar a aplicação, o Spring Boot detecta automaticamente o arquivo `compose.yaml` e sobe o contêiner do PostgreSQL na porta `5432`. As migrations do Flyway (`V1`, `V2` e `V3`) são executadas automaticamente criando as tabelas, seeds e hashes BCrypt.

### 2. Rodando via IDE (IntelliJ)
1. Abra o projeto no IntelliJ IDEA.
2. Aguarde a sincronização das dependências do Gradle.
3. Execute a classe principal: `fiap.com.br.campusgigs.CampusGigsApplication` (clique no botão verde **Run**).

### 3. Rodando via Linha de Comando (Terminal)
```bash
./gradlew bootRun
```
* **API Base URL**: `http://localhost:8080/campusgigs` (e rotas `/`)
* **Frontend Web Interativo**: `http://localhost:8080/` (ou `http://localhost:8080/index.html`)

---

## 🔐 Autenticação e Exemplo de Chamada Autenticada

### 1. Obter Token JWT (Login)
* **Endpoint**: `POST http://localhost:8080/campusgigs/login`
* **Requisição**:
```bash
curl -X POST http://localhost:8080/campusgigs/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "enzo@email.com",
    "senha": "123456"
  }'
```
* **Resposta (200 OK)**:
```json
{
  "token": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiZW56b0BlbWFpbC5jb20iLCJleHAiOjE3...",
  "usuario": {
    "id": 1,
    "nome": "Enzo Okuizumi",
    "email": "enzo@email.com",
    "role": "USER",
    "cep": "01001-000",
    "cidade": "São Paulo",
    "uf": "SP"
  }
}
```

### 2. Chamada Autenticada (Publicar Freela)
* **Endpoint**: `POST http://localhost:8080/campusgigs/servico`
* **Requisição**:
```bash
curl -X POST http://localhost:8080/campusgigs/servico \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <SEU_TOKEN_JWT>" \
  -d '{
    "titulo": "Monitoria de Java e Spring Boot",
    "descricao": "Aulas práticas de preparação para projetos e provas.",
    "categoria": "Educacao",
    "preco": 100.00
  }'
```
* **Resposta (201 Created)**:
```json
{
  "id": 9,
  "titulo": "Monitoria de Java e Spring Boot",
  "descricao": "Aulas práticas de preparação para projetos e provas.",
  "categoria": "Educacao",
  "preco": 100.0,
  "situacao": "ATIVO",
  "prestador": {
    "id": 1,
    "nome": "Enzo Okuizumi",
    "email": "enzo@email.com",
    "role": "USER",
    "cep": "01001-000",
    "cidade": "São Paulo",
    "uf": "SP"
  }
}
```

---

## 🛡️ Regras de Autorização e Evidência de Acesso Negado (403 Forbidden)

Conforme estabelecido nos requisitos de negócio:
* Qualquer aluno autenticado pode publicar e contratar serviços.
* Um usuário comum **só pode editar ou excluir os próprios serviços**.
* Apenas um **ADMIN** possui poder de moderação para encerrar qualquer serviço do catálogo.
* Um aluno **não pode contratar o próprio serviço** e um serviço só pode ter **uma contratação ativa**.

### Evidência de Teste de Acesso Negado por Papel (403 FORBIDDEN):
Tentativa de alteração de um serviço de outro usuário (usuário comum tentando alterar o serviço `1` que pertence a outro estudante):

* **Chamada**:
```bash
curl -X PUT http://localhost:8080/campusgigs/servico/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN_DE_OUTRO_USUARIO>" \
  -d '{
    "titulo": "Tentativa de Alteração Indevida",
    "descricao": "Tentando alterar o freela de outro colega.",
    "categoria": "Programacao",
    "preco": 999.00
  }'
```

* **Resposta Padronizada da API (403 FORBIDDEN — RFC 7807)**:
```json
{
  "type": "https://campusgigs.fiap.com.br/errors/status-error",
  "title": "403 FORBIDDEN",
  "status": 403,
  "detail": "Você não tem permissão para editar este serviço.",
  "instance": "/campusgigs/servico/1",
  "timestamp": "2026-09-17T12:52:31.929307800Z"
}
```

---

## 📸 Evidências dos Testes Manuais (Insomnia)

Abaixo estão registradas as capturas de tela comprovando o funcionamento dos endpoints nas suítes de testes manuais:

### 1. Autenticação e Geração de Token JWT
Geração do token JWT assinado via RSA para autenticação stateless dos usuários no sistema:

![Login no Insomnia](docs/Screenshot%202026-09-17%20093730.png)
*Figura 1: `POST /campusgigs/login` com emissão de token JWT e dados do usuário autenticado.*

![Login Contratante](docs/Screenshot%202026-09-17%20102145.png)
*Figura 2: `POST /campusgigs/login` para o perfil de contratante.*

---

### 2. Gestão e Consulta de Usuários
Endpoints protegidos para consulta de alunos cadastrados no sistema:

![Listagem de Usuários](docs/Screenshot%202026-09-17%20093917.png)
*Figura 3: `GET /campusgigs/usuario` retornando a listagem geral com Bearer Token.*

![Consulta de Usuário por ID](docs/Screenshot%202026-09-17%20094411.png)
*Figura 4: `GET /campusgigs/usuario/1` trazendo detalhes do usuário específico.*

---

### 3. Catálogo e Gestão de Serviços (Freelas)
Exibição do catálogo público e operações de edição e encerramento com regras de autorização:

![Catálogo Público de Serviços](docs/Screenshot%202026-09-17%20094147.png)
*Figura 5: `GET /campusgigs/servico` catálogo público acessível sem autenticação prévia.*

![Edição de Serviço pelo Dono](docs/Screenshot%202026-09-17%20101051.png)
*Figura 6: `PUT /campusgigs/servico/11` prestador atualizando os dados de seu próprio serviço.*

![Encerramento de Serviço](docs/Screenshot%202026-09-17%20101725.png)
*Figura 7: `PATCH /campusgigs/servico/11/encerrar` serviço encerrado com sucesso.*

---

### 4. Ciclo de Vida de Contratações
Endpoints de contratação com validação de serviço ativo, prevenção de auto-contratação e atualização via `PUT`:

![Criação de Contratação](docs/Screenshot%202026-09-17%20102644.png)
*Figura 8: `POST /campusgigs/contratacao` contratação criada com situação inicial `SOLICITADA` (HTTP 201).*

![Atualização de Contratação](docs/Screenshot%202026-09-17%20110322.png)
*Figura 9: `PUT /campusgigs/contratacao/7` atualização de contratação vinculando novo serviço com sucesso (HTTP 200).*

![Listagem de Contratações](docs/Screenshot%202026-09-17%20094252.png)
*Figura 10: `GET /campusgigs/contratacao` listagem completa de serviços contratados.*

---

### 5. Visão Geral das Coleções no Insomnia
Estruturação organizada de requisições cobrindo todos os módulos do sistema:

![Coleção Completa no Insomnia](docs/Screenshot%202026-09-17%20094238.png)
*Figura 11: Estrutura modular de endpoints (Login, Usuário, Serviço e Contratação) configurada no Insomnia.*

---

## 🗺️ Mapa de Endpoints da API

| Método | Endpoint | Permissão | Descrição |
| :--- | :--- | :--- | :--- |
| `POST` | `/campusgigs/usuario` | Público | Cadastro de usuário com validação de CEP via ViaCEP |
| `GET` | `/campusgigs/usuario` | Autenticado | Listagem geral de usuários cadastrados |
| `GET` | `/campusgigs/usuario/{id}` | Autenticado | Consulta de usuário por ID |
| `PUT` | `/campusgigs/usuario/{id}` | `USER`, `ADMIN` | Atualização de dados cadastrais |
| `DELETE`| `/campusgigs/usuario/{id}` | `ADMIN` | Remoção de usuário |
| `POST` | `/campusgigs/login` | Público | Autenticação com e-mail/senha e geração de JWT |
| `GET` | `/campusgigs/servico` | Público | Catálogo aberto de freelas disponíveis |
| `GET` | `/campusgigs/servico/{id}` | Público | Detalhes de um freela específico |
| `POST` | `/campusgigs/servico` | `USER`, `ADMIN` | Publicação de um novo freela |
| `PUT` | `/campusgigs/servico/{id}` | `USER` (Dono) | Edição dos dados do próprio freela |
| `PATCH`| `/campusgigs/servico/{id}/encerrar` | Dono ou `ADMIN` | Encerramento do freela |
| `DELETE`| `/campusgigs/servico/{id}` | Dono ou `ADMIN` | Exclusão do freela |
| `GET` | `/campusgigs/contratacao` | `USER`, `ADMIN` | Listagem de contratações |
| `GET` | `/campusgigs/contratacao/{id}` | `USER`, `ADMIN` | Detalhes de uma contratação por ID |
| `POST` | `/campusgigs/contratacao` | `USER`, `ADMIN` | Contratação de freela ativo de outro estudante |
| `PUT` | `/campusgigs/contratacao/{id}` | Envolvidos / `ADMIN` | Atualização do serviço contratado |
| `DELETE`| `/campusgigs/contratacao/{id}` | Envolvidos / `ADMIN` | Cancelamento/exclusão de contratação |

---

## ⚖️ Tratamento Centralizado de Erros (RFC 7807)

Todas as falhas da aplicação são interceptadas pelo `GlobalExceptionHandler` e devolvem a estrutura padronizada **RFC 7807 ProblemDetail**:

| Código HTTP | Título do Erro | Tipo / URI | Descrição do Tratamento |
| :--- | :--- | :--- | :--- |
| `400 BAD REQUEST` | Requisição Inválida | `.../errors/bad-request` | Falhas de validação nos campos do DTO (`MethodArgumentNotValidException`) |
| `400 BAD REQUEST` | 400 BAD_REQUEST | `.../errors/status-error` | Violação de regra de negócio (auto-contratação, contratação duplicada ou serviço inativo) |
| `401 UNAUTHORIZED` | Não Autorizado | `.../errors/unauthorized` | Token ausente, inválido ou credenciais incorretas |
| `403 FORBIDDEN` | Acesso Negado | `.../errors/forbidden` ou `.../status-error` | Usuário autenticado sem permissão para o recurso solicitado |
| `404 NOT FOUND` | Não Encontrado | `.../errors/not-found` ou `.../status-error` | Recurso ou rota inexistente (`NoResourceFoundException`) |
| `405 METHOD NOT ALLOWED`| Método Não Permitido | `.../errors/method-not-allowed` | Método HTTP não suportado para o endpoint |
| `409 CONFLICT` | Conflito de Dados | `.../errors/conflict` | E-mail já cadastrado no sistema (`DataIntegrityViolationException`) |
| `503 SERVICE UNAVAILABLE`| Serviço Externo Indisponível | `.../errors/external-service-timeout` | Timeout ou falha de conectividade na API do ViaCEP |

---

## 📑 Resumo dos Commits por Checkpoint no Repositório

| Checkpoint | Commit Hash | Autor | Mensagem do Commit | Decisão Técnica / Entrega |
| :---: | :---: | :---: | :--- | :--- |
| **CP1** | [`0467a91`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/0467a91) | Enzo Okuizumi | `feat: ajustando compose.yml para funcionar o postgres!!` | Configuração do Docker Compose integrado ao Spring Boot com PostgreSQL 16. |
| **CP1** | [`9c07d19`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/9c07d19) | Lucas Gouveia | `feat: Adicionando os scripts para flyway e corrigindo auth.` | Versionamento da criação das tabelas iniciais e carga de seeds via Flyway. |
| **CP2** | [`0548e5a`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/0548e5a) | Enzo Okuizumi | `feat: Add Role e senha em Usuário, Add métodos no UsuarioRepository...` | Modelagem de roles, senhas protegidas com BCrypt e métodos de busca. |
| **CP2** | [`10c3485`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/10c3485) | Enzo Okuizumi | `feat: add cep, cidade e uf em usuario e ajustando os DTOs!` | Estruturação dos DTOs de usuário com validações e campos de endereço. |
| **CP3** | [`837355b`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/837355b) | Lucas Gouveia | `feat: CP3 - Emissão e validação de tokens (e corrigindo scripts do flyway).` | Emissão de tokens JWT RSA e validação stateless no Resource Server. |
| **CP3** | [`0ae6d06`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/0ae6d06) | Lucas Gouveia | `feat: CP3 - Alterando classes para usar loginRequest.` | Padronização do payload de login e desacoplamento de credenciais. |
| **CP4** | [`72873d6`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/72873d6) | Lucas Gouveia | `feat: CP4 - Regras de autorização por role implementadas.` | Aplicação de regras RBAC no SecurityFilterChain e validações de serviço. |
| **CP4** | [`b9851f6`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/b9851f6) | Lucas Gouveia | `feat: CP4 - Regras de autorização por role corrigidas.` | Refinamento das permissões de USER/ADMIN e tratamento de autorização. |
| **CP5** | [`39bb5d4`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/39bb5d4) | Enzo Okuizumi | `feat: Só colocando campugigs nos endpoints para ficar mais bonito!! É isso` | Padronização das rotas com prefixo `/campusgigs` e suporte raiz. |
| **CP5** | [`3611480`](https://github.com/LuzBGouveia/CampusGigs_ProjetoDiamante/commit/3611480) | Enzo Okuizumi | `feat: Adicionando update em Contratacao` | Implementação do fluxo de atualização em contratações com regras de domínio. |

