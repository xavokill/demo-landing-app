FROM maven:3.9.6-eclipse-temurin-11 AS builder

WORKDIR /src

COPY . .

RUN mvn package


FROM eclipse-temurin:11-jre-alpine

WORKDIR /bin

COPY --from=builder /src/asesores-application/target/asesores-application-1.0.0-SNAPSHOT.jar .

CMD ["java", "-jar", "asesores-application-1.0.0-SNAPSHOT.jar"]