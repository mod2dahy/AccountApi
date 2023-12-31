# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/AccountApi-0.0.1-SNAPSHOT.jar /app

# Make port 8082 available to the world outside this container
EXPOSE 8082

# Run application when the container launches
CMD ["java", "-jar", "AccountApi-0.0.1-SNAPSHOT.jar"]
