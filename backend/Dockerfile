# ============================================================================
# TICKET-ADV146 — Spring Boot Dockerfile (multi-stage Maven -> JRE slim)
#
# TODO: Implement a two-stage Docker build:
#   Stage 1 (build): Use eclipse-temurin:25-jdk to compile the JAR with Maven.
#   Stage 2 (runtime): Copy ONLY the JAR onto a slim eclipse-temurin:25-jre-alpine image.
#   - Create a non-root user 'reconx' for security.
#   - EXPOSE port 8080.
#   - Use JAVA_OPTS env variable in ENTRYPOINT for runtime tuning.
# ============================================================================

FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw -q -B -DskipTests package
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/reconx-0.0.1-SNAPSHOT.jar"]
