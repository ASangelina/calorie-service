#fase de build
FROM amazoncorretto:21-alpine   as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package

# fase para gerar a imagem docker de prod
FROM build as prod
WORKDIR /app
COPY --from=build /app/target/*.jar /app/application.jar
EXPOSE 9090:9090
CMD ["java", "-jar", "application.jar"]
