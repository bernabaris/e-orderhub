FROM common-lib:1.0.0 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/order-service-0.0.1-SNAPSHOT.jar order-service-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080