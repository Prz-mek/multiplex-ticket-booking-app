#!/bin/sh

tput setaf 2
echo "Building app ..."
tput sgr0
echo
./mvnw clean package

tput setaf 2
echo "Running app ..."
tput sgr0
echo
java -jar target/multiplex-ticket-booking-app-0.0.1-SNAPSHOT.jar
