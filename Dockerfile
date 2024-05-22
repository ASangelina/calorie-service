# Usa uma imagem base do OpenJDK
FROM openjdk:21-jre-slim

# Copia o arquivo JAR da sua aplicação para o contêiner
COPY target/your-application.jar /app/your-application.jar

# Define o diretório de trabalho
WORKDIR /calorieservice

# Comando para iniciar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "your-application.jar"]
