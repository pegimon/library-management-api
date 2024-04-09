# library-management-api
this is a spring boot backend application for library management

## Before You Run the application
1. Make sure you have Java 17 or higher installed in your system
2. Make sure you have docker installed in your system
## How to run the application
1. First you need to run docker compose to start the mysql database. You can do this by running the following command in the root directory of the project
```
docker-compose up
```
2. If you have maven installed in your system, you can run the application by running the following command in the root directory of the project
```
mvn spring-boot:run
```
3. If you don't have maven installed in your system, you can run the application by running the following command in the root directory of the project
```
./mvnw spring-boot:run
```
4. The application will start running on port 3000. You can access the application by visiting the following URL in your browser
```
http://localhost:3000
```
5. If you are using IntelliJ IDEA, you can run the application by running the `LibraryManagementApplication` class

## How to run the tests
1. If you have maven installed in your system, you can run the tests by running the following command in the root directory of the project
```
mvn test
```
2. If you don't have maven installed in your system, you can run the tests by running the following command in the root directory of the project
```
./mvnw test
```
3. If you are using IntelliJ IDEA, you can run the tests by running the `LibraryManagementApplicationTests` class

## API Documentation
The API documentation is available at the following URL make sure to run the application first
```
http://localhost:3000/swagger-ui.html
```
