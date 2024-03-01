FROM maven:3.9.6-eclipse-temurin-11 AS builder

WORKDIR /src

COPY . .

RUN mvn package -f prospectos/pom.xml


FROM eclipse-temurin:11-jre-alpine

WORKDIR /bin

COPY --from=builder /src/prospectos/prospectos-application/target/prospectos-application-1.0.0-SNAPSHOT.jar .

CMD ["java", "-jar", "prospectos-application-1.0.0-SNAPSHOT.jar"]

# docker build --tag "academiafullstackdemo:prospectos-v1" -f .\prospectos\Dockerfile ".\"
# docker run -d -p 8080:8585 --name "msprospectos-v1" academiafullstackdemo:prospectos-v1