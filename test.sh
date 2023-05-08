#!/bin/sh

tput setaf 2
echo "This id brief presentation of Multiplex API"
tput sgr0
echo

tput setaf 2
echo "Getting screenings within given time span"
tput sgr0
curl -v 'http://localhost:8080/api/screening?from=2023-05-29T14:30:00&to=2023-05-29T19:30:00'
echo

tput setaf 2
echo "Getting details of specified screening"
tput sgr0
curl -v 'http://localhost:8080/api/screening/6'
echo


tput setaf 2
echo "Making reservation"
tput sgr0
curl -X POST -v 'http://localhost:8080/api/reservation' \
	-H 'Content-Type: application/json; charset=utf-8' \
	--data-binary '@body.json'
echo


tput setaf 2
echo "Getting reservation summary"
tput sgr0
curl  -v 'http://localhost:8080/api/reservation/1'
echo

