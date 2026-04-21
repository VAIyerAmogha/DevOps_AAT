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
	@if command -v xdg-open >/dev/null 2>&1; then \
		xdg-open http://localhost:8081 >/dev/null 2>&1 || echo "Open http://localhost:8081 in your browser."; \
	elif command -v open >/dev/null 2>&1; then \
		open http://localhost:8081 >/dev/null 2>&1 || echo "Open http://localhost:8081 in your browser."; \
	else \
		echo "Open http://localhost:8081 in your browser."; \
	fi

test:
	mvn test

# Docker commands
docker-build: build
	docker build -t habit-tracker .

docker-run:
	-docker stop habit-tracker-app
	-docker rm habit-tracker-app
	docker run --name habit-tracker-app -p 8081:8080 -d habit-tracker

stop:
	docker stop habit-tracker-app
	docker rm habit-tracker-app

all: clean build docker-build
