# README #

## Url Shortener Service ##

This service is responsible for providing REST API endpoints that will enable users/ clients to generate an identifier or hash code to which this URL corresponds. On the other hand, the user can also access the url by the generated hash code.

### Technical summary ###

- This is a Kotlin and Spring Boot application. 
- Throughout the project reactive programming approach is preferred as much as possible with the combination of Kotlin coroutines. 
- Endpoints are implemented using Spring Webflux module. 
- Application exposes Prometheus metrics via Spring Actuators. 
- Application includes unit and integration tests having a high coverage. Tests are implemented
  JUnit 5, MockK and Spring MockK libraries.


- Todo:
1. Apply cache mechanism for every get request
2. Have a separate code generator service to manage hashes
3. Deployment pipeline

https://raw.githubusercontent.com/micflib/url-shortener-service/master/uss-system-design.png


#### How do I get set up? ####

1. Clone this repo and execute `./mvnw spring-boot:run`. Or inside an IDE, run the class EmployeeManagementServiceApp

   ```sh
    git clone https://github.com/micflib/url-shortener-service.git
    ```
2. Build Maven - The service uses Maven as a build automation tool.  To build, run `./mvnw clean install`

#### Running the service locally

In order to run this service locally, you can use the provided Docker Compose file to start:
- an instance of MongoDB 

`docker-compose up -d`

Then start the service selecting the `local` Spring Boot profile

`./mvnw spring-boot:run -Dspring-boot.run.profiles=local`

This service persist data to Mongo DB.
http://localhost:8081/db/url-shortener-service/ (Mongo Express)
