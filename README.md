
#                                                   Project: BookACinema
#### Author : RASHMI VISHNU

##  Description
This project implements and Simple Booking tickets app for any booking websites. Provides the following capabilities 

      -  get all available discounts
      
      -  get all Ticket prices
      
      -  Book A ticket OR get an estimated price for ticket booking after applying the discounts
            ( Family discounts, Child Discount )

Note: This  provides few basic validations around the inputs , which can be extended further based on the needs. 
Note: Basic Testing is included, more negative cases can be covered.
Note: Design keeping in mind the ease of extendeding and adding more feeatures

## Pre-requsites
The project utilises the following technologies:
- Java 11
- Spring Boot 2.x
- Gradle 7.2
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
http://localhost:8080/swagger-ui/index.html
![img_5.png](img_5.png)
## Endpoints

Following are the APIs exposed: 
	{GET /v1/ticket/offers}: getAllOffers()
	{GET /v1/ticket/price}: getAllPriceDetails()
	{POST /v1/ticket/book}: bookACinema(CustomerRequest)

POST multiple customer data with the Phone numbers

```shell
curl --location 'http://localhost:8080/v1/ticket/book' \
--header 'Content-Type: application/json' \
--data '{
"transactionId": 4,
"customers": [
  
{
"name":"Daniel abc",
"age": 10
},
{
"name":"Daniel Anderson",
"age": 10
},
{
"name": "Mary Jones",
"age": 30
},
{
"name":
"Michelle Parker",
"age": 70
}
]
}'
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
6. Keeping in mind the possible extentions and addition of feature Factory Pattern is used to make it easily extendable.
7. Configurable Family size through application yaml properties.

## Scope for Enhancements
1. Extensive test cases to cover more scenarios can be added. Currently around 26 Test cases are added to cover some basic scenaios and end to end testing.
2. Validations can be enhanced to include more on the inputs. Basic validations are provided for now.
3. User Interface can be provided.
4. Various levels of testing can be included
5. More APIs to add and update can be included
6. Replace H2 Db, with proper db instance
7. Containerize for better deployment
8. Authontication and Security Checks can be added on top of the APIs to make it more secure
   


### Testing
Unit tests
TODO: Can be improved to include component,blackbox and controller tests etc.
TODO: Cover more validations and validation testing

Test Execution report 

![img_3.png](img_3.png)


Integration Testing with some basic functional cases

![img_4.png](img_4.png)


## Access the Database (H2)

http://localhost:8080/h2-console/login.do

user: sa
password: password


#### Sample execution screen-shots:
Response for get on all available discounts
http://localhost:8080/v1/ticket/offers
response: 
 
![img_10.png](img_10.png)

Response for get on all available prices
http://localhost:8080/v1/ticket/price
response:
 ![img_9.png](img_9.png)

TicketBooking API response

![img_2.png](img_2.png)


#### health check
http://localhost:8080/actuator/health


## Scneario Testing as per requirement 
Scenario 1:

![img_6.png](img_6.png)


Scenario 2:
![img_7.png](img_7.png)

Scenario 3:
![img_8.png](img_8.png)
