####
# Microservice1 dockerfile
####
FROM quay.io/devfile/maven:3.8.1-openjdk-17-slim

WORKDIR /build

# Build dependency offline to streamline build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:11-jdk
COPY --from=0 /build/target/ms-lookup-v3.jar /app/target/ms-lookup-v3.jar

ENTRYPOINT [ "java", "-jar", "/app/target/ms-lookup-v3.jar"]
