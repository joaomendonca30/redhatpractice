# Etapa 1: Construção
FROM maven:3.8.6-eclipse-temurin-17 AS builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /build

# Copia os arquivos do projeto para o contêiner
COPY pom.xml .
COPY src ./src

# Executa o build do Maven para compilar o projeto e gerar o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagem Final
FROM eclipse-temurin:17-jre

RUN mkdir /app

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/myspringbootapplication.jar

EXPOSE 8081
EXPOSE 8080

RUN chown -R 1001:1001 /app

USER 1001

HEALTHCHECK CMD curl --fail http://localhost:8080 || exit 1

ENTRYPOINT java -jar /app/myspringbootapplication.jar