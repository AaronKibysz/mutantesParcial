# Etapa 1: Construcción del JAR
FROM gradle:7.6.0-jdk17-alpine AS build
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle build --no-daemon

# Etapa 2: Ejecutar el JAR
FROM openjdk:17-alpine
EXPOSE 9000
COPY --from=build /app/build/libs/mutant-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]