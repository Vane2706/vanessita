FROM eclipse-temurin:17-jdk-jammy

# carpeta de trabajo
WORKDIR /app

# copiar jar generado
COPY target/*.jar app.jar

# exponer puerto
EXPOSE 1711

# ejecutar aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]