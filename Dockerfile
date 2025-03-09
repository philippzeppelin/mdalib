# build
FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/mdalib-*.jar /app/mdalib.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mdalib.jar"]