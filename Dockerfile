# Use the official Gradle image to create a build artifact.
FROM gradle:8.7.0-jdk21 as builder

# Copy local code to the container image.
WORKDIR /app
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

# Build a release artifact.
RUN gradle clean build --no-daemon

# Use OpenJDK for the runtime image.
# Use OpenJDK for the runtime image.
FROM amazoncorretto:21-alpine3.19

# Install Dockerize
ENV DOCKERIZE_VERSION v0.7.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
  && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
  && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

WORKDIR /app

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/build/libs/*.jar /app/spring-boot-application.jar

# Use Dockerize to wait for Neo4j to startup, then run the Spring Boot application
CMD ["dockerize", "-wait", "tcp://neo4j:7687", "-timeout", "30s", "java", "-jar", "/app/spring-boot-application.jar"]
