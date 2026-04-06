FROM eclipse-temurin:17-jre

# Maintainer Info
LABEL maintainer="habit-tracker"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available outside this container
EXPOSE 8080

# The application's built jar file
ARG JAR_FILE=target/habit-tracker-1.0-SNAPSHOT.jar

# Add the jar to the container
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java","-jar","/app.jar"]
