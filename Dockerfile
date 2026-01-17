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
# We use the explicit name to avoid issues with multiple JARs
COPY --from=build /app/build/libs/posu.jar app.jar

# Define build arguments to allow inserting environment variables during build
ARG DATABASE_URL
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD

# Set environment variables from build arguments
# This satisfies the request to insert them into the docker image.
# At runtime, these can still be overridden by Render's environment variables.
ENV DATABASE_URL=$DATABASE_URL
ENV DATABASE_USERNAME=$DATABASE_USERNAME
ENV DATABASE_PASSWORD=$DATABASE_PASSWORD

# Expose the default port (Render will use its own PORT)
EXPOSE 8080

# Set the entrypoint to run the JAR, dynamically binding to Render's PORT
ENTRYPOINT ["sh", "-c", "exec java -jar app.jar --server.port=${PORT:-8080}"]
