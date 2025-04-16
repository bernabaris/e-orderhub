FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8084