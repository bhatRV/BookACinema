
#                                                   Project: BookACinema
#### Author : RASHMI VISHNU

##  Description
This project implements and Simple Booking tickets app for any booking websites. Provides the following capabilities 

      -  get all available discounts
      
      -  get all Ticket prices
      
      -  Book an estimated price for ticket booking after applying the discounts

This also provides few basic validations around the inputs , which can be extended further based on the needs.

## Pre-requsites
The project utilises the following technologies:
- Java 11
- Spring Boot
- Gradle
- H2 in memory as the data store (with few pre-loaded entries)
- IntelliJ if you would like to extend the features

## Build & Run
The project utilises Gradle Wrapper.
To build the project, browse to the project root directory and simply execute:
```
./gradlew build
```
To run the application, execute:
```
./gradlew bootRun
```
This will initialise SpringBoot on port 8080 where the API can be accessed via:
```
http://localhost:8080
```

# Execution Details
NOTE: Pre loaded with some data through sql scripts

Preloaded data is provided through the SQL scripts under resources/data.sql
 
### Swagger ###
http://localhost:8888/swagger-ui/index.html

![img_2.png](img_2.png)

## Endpoints

Following are the APIs exposed: 
	{GET /v1/ticket/offers}: getAllOffers()
	{GET /v1/ticket/price}: getAllPriceDetails()
	{POST /v1/ticket/book}: bookACinema(CustomerRequest)

POST multiple customer data with the Phone numbers
```shell
http://localhost:8888/v1/phone-directory/phone_numbers/customers

curl --location 'http://localhost:8888/v1/phone-directory/customers' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--data '[
  {
    "firstName": "culpa dolore",
    "lastName": "mollit sed incididunt deserunt",
    "phoneNumbers": [
      {
        "number": "0411000111"
      }
    ]
  }
]'
```

GET all available discounts
```shell
'http://localhost:8080/v1/ticket/offers

curl --location 'http://localhost:8080/v1/ticket/offers' \
--header 'Accept: application/json'

```

GET all available prices
```shell
http://localhost:8080/v1/ticket/price

curl --location 'http://localhost:8080/v1/ticket/price' \
--header 'Accept: application/json'
```

 

# Technical Notes
Below are some details on technical aspects of the project and few learnings.

## Assumptions & Design considerations
1. Default port will be -8080, which can be modified
2. Only minimal validations are provided.
3. Comes with few pre-loaded entries, which can be extended by providing an add API
4. 2 seperate controllers to keep it extendable to provide more operations
5. Discount information is stored in DB to provide extendable options for future.

## Scope for Enhancements
1. Extensive test cases to cover more scenarios can be added
2. Validations can be enhanced to include more on the inputs. Basic validations are provided for now.
3. User Interface can be provided.
4. Various levels of testing can be included
5. More APIs to add and update directory can be included
6. Replace H2 Db, with proper db instance
7. Containerize for better deployment
8. Authontication and Security Checks can be added on top of the APIs to make it more secure


### Testing
Unit tests
TODO: Can be improved to include component,blackbox and controller tests etc.
![img_3.png](img_3.png)


## Access the Database (H2)

http://localhost:8080/h2-console/login.do

user: sa
password: password


#### Sample execution screen-shots:
Response for get on all available discounts
http://localhost:8080/v1/ticket/offers
response: 
![img.png](img.png)


Response for get on all available prices
http://localhost:8080/v1/ticket/price
response:
![img_1.png](img_1.png)

TicketBooking API response
![img_2.png](img_2.png)
#### health check
http://localhost:8080/actuator/health