# Habit Tracker

A simple Spring Boot habit tracking application containerized with Docker and ready for CI/CD pipelining.

## Project Structure

- `src/` - The source code for the application (Java/Spring Boot and frontend assets).
- `pom.xml` - Maven configuration file detailing the project dependencies and build plugins.
- `Dockerfile` - Contains instructions to build the Docker image of the application using the Spring Boot JAR inside an Eclipse Temurin JRE base image.
- `Jenkinsfile` - Contains the declarative Jenkins CI/CD pipeline script to orchestrate the build, test, and containerization process.
- `Makefile` - Unix Makefile containing simplified short-hand aliases for standard build tools.
- `make.bat` - Windows Batch script parallel to the Makefile, mapping short commands to specific pipelines for Windows environments.

## How we pipeline

The pipeline follows standard Continuous Integration and Delivery (CI/CD) practices:
1. **Clean**: Removes any old build artifacts from previous runs to ensure a clean source.
2. **Build & Test**: Compiles the Java application and runs any tests to guarantee the integrity of the application.
3. **Artifact generation**: Packages the compiled code into a runnable `.jar` file inside the `/target` directory.
4. **Containerization**: Packages the complete execution environment (Java JRE + `.jar`) into a Docker image, ensuring this application will behave identically across any platform.
5. **Deployment / Run**: Starts the Docker container, exposing internal application ports to local ones.

## Available Commands

Whether you use Windows (via `make.bat`) or a Unix-like system (via `Makefile`), the tools accept the same target parameters.

Run commands using:
- Ubuntu/Linux/macOS: `make [command]`
- Windows (CMD/PowerShell): `make.bat [command]`

### Ubuntu Prerequisites

Ensure these tools are installed and available on your PATH:
- Java 17+
- Maven (`mvn`)
- Docker
- GNU Make (`make`)

Example setup on Ubuntu:
```bash
sudo apt update
sudo apt install -y openjdk-17-jdk maven make docker.io
sudo usermod -aG docker $USER
```

Then log out and log back in (or restart) so Docker group permissions apply.

## Ubuntu Quick Start

If you want a single guided setup path on Ubuntu:

1. Run setup once:
```bash
./setup-ubuntu.sh
```
2. Log out and back in.
3. Run the app:
```bash
./run-ubuntu.sh
```

This script validates Maven and Docker availability, verifies Docker daemon access, then runs `make run`.

### `clean`
Runs `mvn clean` to purge the `target/` directory and remove old compile outputs and generated class files.

### `build`
Runs `mvn clean install` to compile the application from scratch and package the JAR file without running Docker.

### `test`
Runs the unit tests via `mvn test`.

### `docker-build`
Compiles the application via Maven and then builds a Docker image tagged `habit-tracker`.

### `docker-run`
Start the built `habit-tracker` Docker container on local port `8081` (runs in detached mode).

### `run` (Complete Workflow)
Runs the full end-to-end pipeline automatically:
1. Cleans the directory and compiles code to an updated `.jar`.
2. Packages the new JAR into the Docker image.
3. Cleans up any old and running container with the name `habit-tracker-app`.
4. Starts the fresh container to serve traffic over localhost port 8081.
5. Automatically opens your default web browser to the application page.

### `stop`
Stops the running `habit-tracker-app` container and removes its instance.

### `all`
Runs a full cleaning, build, and docker-build process but stops short of running the application.
