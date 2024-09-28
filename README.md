# Room Booking Management API Service

## Problem Statement

Develop and deploy a RESTful API service using Spring Boot to streamline the room booking process for a hotel management aggregator application. The application persists data using MySQL and focuses on essential functionalities like user registration, hotel management, and room booking. It implements authentication and authorization mechanisms using JWT tokens and manages three user roles: `CUSTOMER`, `HOTEL MANAGER`, and `ADMIN`.

## Key Features

This project is a simplified version of an online room booking system with a focus on:
1. User registration, authentication, and role-based access control.
2. Hotel management, including viewing, creating, updating, and deleting hotel details.
3. Room booking management with restrictions based on user roles.

### Assumptions
- The application supports only a single type of room, and all bookings are for two guests.
- Any hotel manager can update any hotel's details (no ownership tracking).
- Another service handles check-in and check-out functionalities.
- JWT tokens are used for session management, ensuring secure access to private endpoints.
- The service operates with three roles: `CUSTOMER`, `HOTEL MANAGER`, and `ADMIN`.

## System Design

### Authentication and Authorization
- **JWT Tokens**: Used to authenticate users and manage sessions. Private endpoints are protected, accessible only to authenticated users with appropriate roles.
- **Roles**: The service implements three roles:
  - `CUSTOMER`: Can book rooms.
  - `HOTEL MANAGER`: Can update hotel details and cancel bookings.
  - `ADMIN`: Can create and delete hotels.
  
  
### API Endpoints

#### Public Endpoints (Accessible by Anyone)
- **User Registration**: Allows anyone to register by providing an email, password, first name, last name, and optionally a role.
  - If no role is provided, it defaults to `CUSTOMER`.
- **Login**: Generates a JWT token upon successful login.

#### Private Endpoints (Accessible by Authenticated Users)
- **Booking Management**: Only `CUSTOMER` role users can book a room. A single room can be booked per request. Only hotel managers can cancel a booking.
- **Hotel Management**:
  - **Create/Delete Hotels**: Only `ADMIN` users can perform these actions.
  - **Update Hotel Details**: Only `HOTEL MANAGER` users can update hotel details.
  - **View Hotels**: All authenticated users can view available hotels, but the endpoint is also accessible publicly.

## Project Setup

### Technologies Used
- **Spring Boot**: To create RESTful APIs.
- **MySQL**: To persist data (users, hotels, bookings).
- **JWT**: For authentication and authorization.
- **BCrypt**: For password encryption.
- **Spring Security**: For securing endpoints and handling role-based access control.

## Setup Instructions

### Prerequisites
- Java 21
- MySQL
- Maven/Gradle
- Postman or any API testing tool for testing endpoints

### Steps to Run
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/amanbisht9/stay-easy_room-booking_problem.git
   cd stay_easy
   ```

2. **Set up MySQL Database**:
   - Create a MySQL database:
     ```sql
     CREATE DATABASE sedb;
     ```
   - Configure database settings in `application.properties`:
     ```properties
     spring.datasource.username=yourUsername
     spring.datasource.password=yourPassword
     ```

## Postman API Document Link
   - https://documenter.getpostman.com/view/38136455/2sAXqzVdUQ
