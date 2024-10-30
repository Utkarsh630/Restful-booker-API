# RESTful Booker API Testing Framework

A comprehensive API testing framework for the **RESTful Booker API** using Java, RestAssured, TestNG, Log4j, and Faker.

## Features

- **E2E Test Coverage**: Validate all API endpoints (Authentication, Booking Creation, Retrieval, Update, Delete).
- **Dynamic Data Generation**: Utilizes [Faker](https://github.com/DiUS/java-faker) for random test data.
- **Logging**: Implements [Log4j](https://logging.apache.org/log4j/2.x/) for detailed logging of requests and responses.
- **Structured Codebase**: Organized classes for maintainability and scalability.

## Prerequisites

- **Java**: 11 or higher
- **Maven**
- **IDE**: IntelliJ IDEA or similar
- Access to the RESTful Booker API: [RESTful Booker API](https://restful-booker.herokuapp.com/)

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/restful-booker-api-testing.git
   cd restful-booker-api-testing

2. **Import the Project:** Open your IDE and import the cloned project as a Maven project.

3. **Add Dependencies:** Ensure the following are included in your `pom.xml` :
  ```bash
  <dependencies>
     <dependency> <!-- RestAssured --> </dependency>
     <dependency> <!-- TestNG --> </dependency>
     <dependency> <!-- Log4j --> </dependency>
     <dependency> <!-- Java Faker --> </dependency>
  </dependencies>
```
4. **Configure Log4j:** Create log4j2.xml in `src/test/resources`:
```bash
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <File name="FileLogger" fileName="logs/test.log" append="true">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"/>
        </File>
        <Console name="ConsoleLogger" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="FileLogger"/>
            <AppenderRef ref="ConsoleLogger"/>
        </Root>
    </Loggers>
</Configuration>
```
## Running Tests
To execute the tests, use your IDE's test runner or run the following command:
```bash
mvn clean test
```

## Test Cases Overview
***Authentication Tests:*** Validate both successful and failed authentication scenarios.

***Booking Tests:*** Create, retrieve, update, and delete bookings with various input scenarios.

## Project Structure
***api.models:*** POJO classes for API request/response.

***api.clients:*** RestAssuredClient for handling API requests.

***api.tests:*** Test classes implementing TestNG framework.


***Feel free to adjust any sections or add specific details relevant to your project!***

