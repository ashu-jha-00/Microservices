# Hotel Rating Microservices Project

This is a microservices-based application built with Spring Boot that manages hotels, users, and ratings. The project consists of multiple independent services that work together through service discovery.

## Services

### 1. Service Registry (Port: 8761)
- Netflix Eureka Server for service discovery
- Allows services to register and discover each other
- Located in [`ServiceRegistry`](ServiceRegistry)

### 2. User Service (Port: 8081)
- Manages user information 
- Communicates with Hotel and Rating services
- Uses MySQL database
- Located in [`UserService`](UserService)

### 3. Hotel Service (Port: 8082)
- Manages hotel information
- Provides hotel details to other services
- Uses MySQL database
- Located in [`HotelService`](HotelService)

### 4. Rating Service (Port: 8083)
- Manages user ratings for hotels
- Used by User Service to get ratings
- Uses MySQL database
- Located in [`RatingService`](RatingService)

## Technology Stack

- Java 17
- Spring Boot 3.5.x
- Spring Cloud (Netflix Eureka)
- Spring Data JPA
- MySQL Database
- Maven
- Lombok
- RestTemplate for inter-service communication

## Database Configuration

Each service uses its own MySQL database:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/[database_name]
    username: root
    password: [your_password]
    driver-class-name: com.mysql.cj.jdbc.Driver
eureka:
  instance:
    prefer-ip-address: true
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: http://localhost:8761/eureka
```

MicroServices/
├── ServiceRegistry/    # Eureka Server
├── UserService/        # User Management
├── HotelService/       # Hotel Management
└── RatingService/      # Rating Management


## Building and Running

1. Start MySQL server
2. Start the Service Registry (Eureka Server)
3. Start all other services (User, Hotel, Rating)

Services can be started using Maven:
```bash
mvn spring-boot:run
```

## Endpoints

### User Service
- `POST /users` - Create user
- `GET /users` - Get all users
- `GET /users/{userId}` - Get specific user

### Hotel Service
- `POST /hotels` - Create hotel
- `GET /hotels` - Get all hotels
- `GET /hotels/{hotelId}` - Get specific hotel

### Rating Service
- `POST /ratings` - Create rating
- `GET /ratings` - Get all ratings
- `GET /ratings/users/{userId}` - Get ratings by user
- `GET /ratings/hotels/{hotelId}` - Get ratings by hotel

## Dependencies

The project uses Spring Cloud version 2025.0.0 and includes these main dependencies:

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-cloud-starter-netflix-eureka-client`
- `spring-cloud-starter-netflix-eureka-server`
- `mysql-connector-j`
- `lombok`

