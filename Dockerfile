# Stage 1: Build
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /workspace/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk
WORKDIR /workspace/app
COPY --from=build /workspace/app/target/*-runner.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
