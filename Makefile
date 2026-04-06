# Makefile for Habit Tracker

.PHONY: clean build run test docker-build docker-run stop all

# Maven goals
clean:
	mvn clean

build:
	mvn clean install

run: docker-build
	-docker stop habit-tracker-app
	-docker rm habit-tracker-app
	docker run --name habit-tracker-app -p 8081:8080 -d habit-tracker
	@echo "Waiting for app to start..."
	@sleep 5
	@start http://localhost:8081 || open http://localhost:8081 || xdg-open http://localhost:8081

test:
	mvn test

# Docker commands
docker-build: build
	docker build -t habit-tracker .

docker-run:
	docker run --name habit-tracker-app -p 8081:8080 -d habit-tracker

stop:
	docker stop habit-tracker-app
	docker rm habit-tracker-app

all: clean build docker-build
