# Post Code API

Version:Api-0.0.1-SNAPSHOT

# Installation
-Post Code API is a maven project written in Java 8.
-To create database, you can use the "create_database.sql" file. 
    (Preferred Mysql on development process)
-Then import appropriate version of the data from the address :      https://www.freemaptools.com/download-uk-postcode-lat-lng.htm 

# Usage

Project has 3 API's included, which can be called via any REST API Testing Tool (Postman...etc)

-getDistance
-listPostCode
-updatePostCode

# Authentication

Basic Authentication is required. 
Default user        : postCodeAdmin
Default password    : 112233

# Data Format

-getDistance Example
GET /postcodeapi/getDistance?firstUkPostalCode=AB24 1WS&secondUkPostalCode=AB24 1XD
Host: localhost:8080
Authorization: Basic cG9zdENvZGVBZG1pbjoxMTIyMzM=
Cache-Control: no-cache


-listPostCode
GET /postCodeApi/listPostCode?page=1&start=0&limit=AA
Host: localhost:8080
Authorization: Basic cG9zdENvZGVBZG1pbjoxMTIyMzM=
Cache-Control: no-cache

-updatePostCode
POST /postCodeApi/updatePostCode HTTP/1.1
Host: localhost:8080
Authorization: Basic cG9zdENvZGVBZG1pbjoxMTIyMzM=
Content-Type: application/json
Cache-Control: no-cache

{
	"id":1,
    "postCode":"POSTCODE1",
    "latitude":58.14270109,
    "longitude":-2.09329500
}


# Credits

    http://www.freemaptools.com to provide the data for UK post codes.
