# Projeto - Conecta Solidaria ESG | DevOps

**Integrante:** Bruna Leticia Martins da Silva  
**Curso:** Analise e Desenvolvimento de Sistemas - FIAP  
**Stack principal:** Java 17, Spring Boot, Oracle, Flyway, Docker, Docker Compose e GitHub Actions.

O Conecta Solidaria e uma API ESG voltada ao pilar Social, conectando pedidos de ajuda, doacoes e alertas comunitarios. Nesta etapa o projeto foi adaptado para um fluxo DevOps completo, com build, testes, imagem Docker e deploy automatizado em staging e producao.

## Como executar localmente com Docker

### Pre-requisitos

- Docker Desktop com Docker Compose v2.
- Acesso ao banco Oracle utilizado no projeto.

### Configuracao

1. Copie `.env.example` para `.env`.
2. Preencha `DB_USERNAME` e `DB_PASSWORD` e, se necessario, altere `DB_URL`.
3. Troque as senhas de API do arquivo `.env`.

### Staging

```bash
docker compose --profile staging up -d --build app-staging
```

A aplicacao ficara em:

```text
http://localhost:8081
http://localhost:8081/actuator/health
```

### Producao

```bash
docker compose --profile production up -d --build app-production
```

A aplicacao ficara em:

```text
http://localhost:8082
http://localhost:8082/actuator/health
```

### Encerrar ambientes

```bash
docker compose --profile staging --profile production down
```

Tambem existem scripts prontos em `scripts/` para Bash e PowerShell.

## Pipeline CI/CD

A ferramenta escolhida foi **GitHub Actions**, configurada em `.github/workflows/ci-cd.yml`.

O pipeline possui quatro etapas principais:

1. **Build e testes** - configura Java 17, executa `mvn clean test package`, publica relatorios do Surefire e o JAR.
2. **Build da imagem Docker** - constroi a imagem da aplicacao e publica a imagem compactada como artefato do workflow.
3. **Deploy staging** - carrega a mesma imagem, injeta os secrets do environment `staging`, sobe o perfil Docker Compose e valida `/actuator/health`.
4. **Deploy producao** - executado somente na branch `main`, usa o environment `production`, sobe o container de producao e executa smoke test no health endpoint.

### Secrets necessarios nos environments do GitHub

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `API_ADMIN_USER`
- `API_ADMIN_PASSWORD`
- `API_USER`
- `API_USER_PASSWORD`

Recomenda-se configurar aprovacao manual no environment `production` em **Settings > Environments > production**.

## Containerizacao

O projeto utiliza um Dockerfile multi-stage:

```dockerfile
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -q -DskipTests clean package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN groupadd --system spring && useradd --system --gid spring spring \
    && mkdir -p /app/logs \
    && chown -R spring:spring /app
COPY --from=build --chown=spring:spring /workspace/target/esgapi.jar /app/app.jar
USER spring:spring
EXPOSE 8080
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
```

Estrategias adotadas:

- build multi-stage para manter a imagem final menor;
- execucao com usuario nao-root;
- configuracao por variaveis de ambiente;
- volumes separados para logs de staging e producao;
- rede Docker dedicada `esg-network`;
- profiles do Compose para separar os dois ambientes;
- Flyway continua responsavel pelas migracoes do schema Oracle.

## Testes automatizados

O projeto inclui testes com JUnit 5, Mockito e MockMvc em:

```text
src/test/java/br/com/fiap/esgapi/controller/ApiControllerSmokeTest.java
```

Os testes validam HTTP 200 para listagens dos principais controllers sem depender do Oracle, permitindo feedback rapido no CI.

Execucao local:

```bash
mvn clean test
```

## Prints do funcionamento

> **Importante:** os arquivos abaixo sao marcadores. Substitua pelos prints reais obtidos no GitHub Actions e na execucao local antes de entregar. O passo a passo esta em `docs/GUIA_EVIDENCIAS.md`.

### Pipeline - build e testes

![Build e testes](docs/evidencias/01-pipeline-build-test.png)

### Pipeline - deploy staging

![Deploy staging](docs/evidencias/02-pipeline-staging.png)

### Pipeline - deploy producao

![Deploy producao](docs/evidencias/03-pipeline-producao.png)

### Staging funcionando

![Staging](docs/evidencias/04-staging-health.png)

### Producao funcionando

![Producao](docs/evidencias/05-producao-health.png)

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.3.3
- Spring Web
- Spring Data JPA / Hibernate
- Spring Security
- Spring Boot Actuator
- Oracle Database
- Flyway
- Maven
- JUnit 5
- Mockito / MockMvc
- Docker
- Docker Compose
- GitHub Actions

## Estrutura principal

```text
fiap-esg-api-devops/
├── .github/workflows/ci-cd.yml
├── docs/
├── scripts/
├── src/
├── .dockerignore
├── .env.example
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Checklist de entrega

| Item | OK |
|---|:---:|
| Projeto compactado em .ZIP com estrutura organizada | ☑ |
| Dockerfile funcional | ☑ |
| docker-compose.yml com volumes, variaveis e rede | ☑ |
| Pipeline com build, testes, staging e producao | ☑ |
| README.md com instrucoes | ☑ |
| Documentacao tecnica em PDF | ☑ |
| Evidencias reais de pipeline/deploy substituidas nos marcadores | ☐ |
| Deploy executado em staging e producao | ☐ |

Os dois ultimos itens devem ser marcados apos a execucao do workflow e captura dos prints reais.
