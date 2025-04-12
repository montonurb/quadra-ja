FROM maven:3.9-eclipse-temurin-21 AS builder
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]