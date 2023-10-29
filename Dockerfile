# Use an OpenJDK base image
FROM openjdk:17-oracle

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/file-management-mostafa-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that your Spring Boot application is listening on (change to your actual port)
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]

#Command: docker build -t com.mostafa/mostafa.assessment:0.0.1-SNAPSHOT .