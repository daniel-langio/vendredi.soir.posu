# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-jammy AS build

# Set the working directory
WORKDIR /app

# Copy gradle wrapper and configuration files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Ensure gradlew has execute permissions
RUN chmod +x gradlew

# Download dependencies (this layer will be cached)
RUN ./gradlew dependencies --no-daemon || true

# Copy source code
COPY src src

# Build the application
RUN ./gradlew bootJar --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Render uses the PORT environment variable. Spring Boot uses SERVER_PORT.
ENV SERVER_PORT=${PORT:-8080}

# Expose the default port
EXPOSE 8080

# Environment variables for database connection (provided at runtime)
# We do not use ARG to avoid baking sensitive information into the image layers.
ENV DATABASE_URL=""
ENV DATABASE_USERNAME=""
ENV DATABASE_PASSWORD=""

# Set the entrypoint to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
