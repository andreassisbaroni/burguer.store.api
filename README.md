# Burger Store Api
[![CircleCI](https://circleci.com/gh/andreassisbaroni/burger.store.api.svg?style=shield)](https://circleci.com/gh/andreassisbaroni/burger.store.api)
[![BCH compliance](https://bettercodehub.com/edge/badge/andreassisbaroni/burger.store.api?branch=master)](https://bettercodehub.com/)

### Requirements
 - Java 8
 - Docker
 - PostgreSql
 
### Steps to execute
 - Initially create a postgres database named `burgerstore`, then change the database properties in the 
 `application.properties` file.
 
This project can be run by the docker image, but is not yet published, so you will need to build the image.

#### Run with docker
 - To build the docker image, run the command `docker build -t burger.store.api`.
 - Run the docker image with the command `docker run --network =" host "-p 8080: 8080 burger.store.api`.

#### Run with maven
 - Build project with command `mvnw clean install`.
 - Run app with command `java -jar /target/burger.store.api.jar`.
