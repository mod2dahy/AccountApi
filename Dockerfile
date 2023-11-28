# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/openapi-spring-1.0.0.jar /app

# Make port 8080 available to the world outside this container
EXPOSE 8082

# Run application when the container launches
CMD ["java", "-jar", "openapi-spring-1.0.0.jar"]
