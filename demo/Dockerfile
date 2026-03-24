# Etapa 1: build
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar solo pom primero (cache y estabilidad)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar código fuente
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]