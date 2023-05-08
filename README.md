# multiplex-ticket-booking-app

## Description

This is an API for seat reservation system for a multiplex cinema.
It allows users to browse screenings and make reservations.
It is written in Java and uses Spring Boot framework and H2 in-memory database.

## Build and run

#### Requirements
* Java 17

#### Start the app

To build and start the application you can use ```start.sh``` script

```
./start.sh
```
It is likely that you will have to change mod of some files to executable before running the script. You can do this with command below.
```
chmod +x mvnw start.sh test.sh
```

## Endpoints

* GET /screening?from={datetime}&to={datetime} : This endpoint returns a list of movies and screenings available in the given time interval. The response is a JSON array of objects, each containing the movie title, screening id, and screening time.
* GET /screening/{id} : This endpoint returns information about a specific screening, identified by its id. The response is a JSON object containing the movie title, screening time, screening room number, and available seats. Seats are represented as array of objects.
* POST /reservation : This endpoint creates a new reservation for a screening. The request body is a JSON object containing the screening id, ids and type of tickets, and the name and surname of the person making the reservation.
* GET /reservation/{id} : This endpoint returns information about a specific reservation, identified by its id. The response is a JSON object containing the reservation id, the screening id, the movie title, the screening time, the number and type of tickets, the name and surname of the person who made the reservation, the total amount to pay, and the reservation expiration time.

## Demo requests

You can see simple use case runing ```test.sh``` script
```
./test/sh
```



You can also use swagger-ui. It is avaiable on address: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
