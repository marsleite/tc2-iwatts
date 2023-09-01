# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Define a working directory
WORKDIR /app

# Copy the JAR file built from your Spring project
COPY build/libs/tech-challenger-iwatts-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring app will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
