Account API with Swagger
This repository contains the source code for the Account API, a Spring Boot application for managing user accounts. The API provides endpoints for creating new accounts with an initial credit and retrieving information about existing accounts. Swagger is integrated to document and visualize the API.

Getting Started
Prerequisites
Make sure you have the following installed on your machine:

Java Development Kit (JDK) 11 or later
Maven
Build and Run
Clone this repository to your local machine:

bash
Copy code
git clone git@github.com:mod2dahy/AccountApi.git
Navigate to the project directory:

bash
Copy code
cd account-api
Build the project using Maven:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
java -jar target/openapi-spring-1.0.0.jar
The API, along with Swagger documentation, will be accessible at http://localhost:8082/swagger-ui.html.

API Endpoints
1. Create a New User Account
Endpoint: POST /api/accounts

Description: Creates a new user account with the specified initial credit.

Request Parameters:

customerId (required): Customer ID
initialCredit (required): Initial credit for the account
Responses:

200 OK: User account created successfully
Content: JSON representation of the created account
404 Not Found: Account not found
Content: "Account Not Found"
2. Get User Account Information
Endpoint: GET /api/accounts

Description: Retrieves information about a user account.

Request Parameters:

accountId (required): Account ID
Responses:

200 OK: User account information retrieved successfully
Content: JSON representation of the account
404 Not Found: Account not found
Content: null
202 Accepted: Account not active
Content: null
Swagger Documentation
Swagger documentation is available at http://localhost:8082/swagger-ui.html. You can use this interface to explore the API endpoints, view request and response structures, and test the endpoints.

Docker Configuration
The application can also be run as a Docker container. The Dockerfile is configured to use the official OpenJDK 11 runtime as the parent image. It exposes port 8082 and runs the application using the JAR file.

Dockerfile
Copy code
# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/openapi-spring-1.0.0.jar /app

# Make port 8082 available to the world outside this container
EXPOSE 8082

# Run the application when the container launches
CMD ["java", "-jar", "openapi-spring-1.0.0.jar"]
Build the Docker image and run the container:

bash
Copy code
docker build -t account-api .
docker run -p 8082:8082 account-api
The API, along with Swagger documentation, will be accessible at http://localhost:8082/swagger-ui.html.

Swagger YAML File
You can download the Swagger YAML file [here](http://localhost:8082/v3/api-docs.yaml) to use for client generation or other documentation purposes.
