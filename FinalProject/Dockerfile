# Use the official OpenJDK image as the base
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and permissions
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy the pom.xml and source code
COPY pom.xml .
COPY src /app/src

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Create the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
