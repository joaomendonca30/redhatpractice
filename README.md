# Aplicação Java com Spring Boot e Apache Camel

## Descrição

Esta aplicação Java foi construída utilizando o framework Spring Boot em conjunto com o framework Apache Camel. O objetivo da aplicação é dispor um timer que public e consome mensagens do broker.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Apache Camel

## Inicio

Este projeto foi iniciado a partir do seguinte comando:
```maven
mvn archetype:generate -Dfilter=camel-archetype-spring-boot

É possível utilizar diferentes filtros para alcançar o archetype desejado.

## Configuração

1. Certifique-se de ter o Java instalado em sua máquina.
2. Clone este repositório.
3. Abra o projeto em sua IDE de preferência (por exemplo, IntelliJ IDEA, Eclipse).
4. Certifique-se de ter configurado as dependências do Maven corretamente.

## Execução

1. Compile o projeto.
2. Execute a classe principal `Application.java`.
3. Aguarde até que o timer comece a consultar a API e armazenar as informações na memória.
4. Acesse a API exposta para visualizar as informações.

## Exemplo de Uso

### Consultar informações

- Endpoint: `/api/informacoes`
- Método: GET
- Resposta:
  ```json
  {
    "dados": "informacao1, informacao2, informacao3, ..."
  }
