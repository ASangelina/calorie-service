# Usar uma imagem base do Maven para construir o projeto
FROM maven:3.8.4-openjdk-21 AS build
WORKDIR /app
# Copiar o arquivo pom.xml e baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B
# Copiar o código do projeto e construir o JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Usar uma imagem base do OpenJDK para rodar o aplicativo
FROM openjdk:21-jdk-slim
WORKDIR /app
# Copiar o JAR do estágio de build para o novo estágio
COPY --from=build /app/target/*.jar app.jar
# Informar qual porta o contêiner expõe
EXPOSE 8080
# Comando para rodar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
