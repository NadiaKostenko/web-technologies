FROM maven:3.9-eclipse-temurin-21 as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:23-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/localhost.p12 localhost.p12

EXPOSE 7443

ENTRYPOINT ["java", "-jar", "app.jar"]