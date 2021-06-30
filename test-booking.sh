#!/usr/bin/env bash
#
# WARNING: Currently you will need to restart the server before start this test or
# you will get unexpected results.
#

uri_booking_service=${uri_service:-'http://localhost:8080/booking'}

_curl() {
	(set -x; curl "$1")
	echo
	echo "expected for above: $2"
	echo
}

_curl $uri_booking_service/SamsungGalaxyS9/user1         'BOOKED'
_curl $uri_booking_service/SamsungGalaxyS9/user1         'ALREADY_BOOKED'
_curl $uri_booking_service/SamsungGalaxyS9/user2         'BOOKED_BY_ANOTHER_USER'
_curl $uri_booking_service/SamsungGalaxyS9/user1/release 'RELEASED'
_curl $uri_booking_service                               'a list of bookings'
_curl $uri_booking_service/SamsungGalaxyS9/user1         'BOOKED'
_curl $uri_booking_service/SamsungGalaxyS9/user1         'ALREADY_BOOKED'
_curl $uri_booking_service/SamsungGalaxyS9/user1/release 'ALREADY_BOOKED'
_curl $uri_booking_service/SamsungGalaxyS9/user2         'BOOKED'

# Need more tests ...
