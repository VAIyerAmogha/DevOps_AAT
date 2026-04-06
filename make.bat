@echo off
if "%1"=="clean" goto clean
if "%1"=="build" goto build
if "%1"=="run" goto run
if "%1"=="test" goto test
if "%1"=="docker-build" goto docker-build
if "%1"=="docker-run" goto docker-run
if "%1"=="stop" goto stop
if "%1"=="all" goto all

echo Target "%1" not found.
goto end

:clean
mvn clean
goto end

:build
mvn clean install
goto end

:run
echo Starting the pipeline...
call mvn clean install
docker build -t habit-tracker .
:: Remove any older container with the same name if it exists...
docker stop habit-tracker-app >nul 2>&1
docker rm habit-tracker-app >nul 2>&1
echo Deploying Docker container...
docker run --name habit-tracker-app -p 8081:8080 -d habit-tracker
echo Waiting for the application to spring up...
timeout /t 5 /nobreak
start http://localhost:8081
goto end

:test
mvn test
goto end

:docker-build
call mvn clean install
docker build -t habit-tracker .
goto end

:docker-run
docker run --name habit-tracker-app -p 8081:8080 -d habit-tracker
goto end

:stop
docker stop habit-tracker-app
docker rm habit-tracker-app
goto end

:all
call mvn clean install
docker build -t habit-tracker .
goto end

:end
