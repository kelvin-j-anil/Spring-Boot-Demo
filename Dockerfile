# ---- build stage (Gradle + JDK 21) ----
FROM gradle:8.7-jdk21 AS build
WORKDIR /home/gradle/project

# copy gradle wrapper and config files (Kotlin DSL supported)
COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* ./
COPY gradle.properties* ./

RUN chmod +x ./gradlew

# copy source & build (skip tests to speed up)
COPY src ./src
RUN ./gradlew --no-daemon clean bootJar -x test

# ---- runtime stage (Temurin JDK 21) ----
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# copy the fat jar from build stage
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar /app/app.jar --server.port=${PORT:-8080}"]
