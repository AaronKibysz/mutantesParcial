# Primera fase: Construcción del JAR
FROM alpine:latest as build

# Actualiza el contenedor de Alpine y agrega OpenJDK 17
RUN apk update
RUN apk add openjdk17

# Copia todos los archivos del proyecto al contenedor
COPY . .

# Permite que el script de gradle sea ejecutable
RUN chmod +x ./gradlew

# Construye el archivo JAR usando Gradle
RUN ./gradlew bootJar --no-daemon

# Verifica el contenido de la carpeta de build
RUN ls -l ./build/libs

# Segunda fase: Imagen ligera para ejecutar el JAR
FROM openjdk:17-alpine

# Expone el puerto 8080 para la aplicación
EXPOSE 8080

# Copia el JAR generado en la fase de build
COPY --from=build ./build/libs/parcial_mutantes-0.0.1-SNAPSHOT.jar ./app.jar

# Comando de entrada para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
