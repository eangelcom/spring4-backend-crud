# Spring 4 CRUD backend demo

# Requisites:

Backend to allow CRUP operations in some entity like, for example, a retail's company "Product" to store products, list all of them, retrieve a product by its SKU, update it and delete it. Technology required: Java 11, Maven and Spring Boot.

# Prerequisites

Java 11 and Maven installed.

# Arquitecture:

Software engineering best practices are followed, such as the application of SOLID principles, clean code, clean architecture and quality assurance with unit tests with JUnit 5 and Mockito, integration tests with Spring's MockMvc and coverage verification with Jacoco Maven's plugin.

An MVC architecture is implemented with a presentation layer, or REST controllers, a services layer with interfaces where business rules are implemented, and an access layer to data repositories.

The communication between these layers follows the principles of clean architecture with the use of adapters that transform DTOs from the services layer to DTOs from the presentation layer, allowing an independence between the two layers and reducing the need for characteristics of the framework in the middle layer where the business rules are located.

Technologically, characteristics of the Spring framework are used, such as automatic Spring Boot configurations, dependency injection, REST controllers and JPA repositories that significantly reduce the code, allowing greater productivity, less risk of errors, better code readability and greater test coverage.

As an example, the persistence of the data is done with an in-memory database, in this case H2 due to its popularity and small size.

# Instructions

# To build:

$ cd spring4-backend-crud

$ mvn clean install

# To execute:

$ cd spring4-backend-crud/target

$ java -jar crud-0.0.1-SNAPSHOT.jar

wait until a message like this appears: Started CrudApplication in n seconds

# To try:

Use any REST client, eg Postman, to consume endpoints: 

POST http://localhost:8080/api/v1/product

GET http://localhost:8080/api/v1/product

GET http://localhost:8080/api/v1/product/8406270

PUT http://localhost:8080/api/v1/product/1

DELETE http://localhost:8080/api/v1/product/1


Body example for POST and PUT endpoints:

{

    "sku": "8406270",
    
    "name": "Zapatilla Mujer",
    
    "brand": "SOME BRAND",
    
    "size": "37",
    
    "price": 42990,
    
    "principalImage": "https://thecompany.scene7.com/is/image/thecompany/8406270_1",
    
    "otherImagesList": [
    
        "https://thecompany.scene7.com/is/image/thecompany/881952283_1",
        
        "https://thecompany.scene7.com/is/image/thecompany/881952283_2"
        
    ] 
    
}


