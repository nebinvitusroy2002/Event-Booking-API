Event Booking API

Spring Boot REST API for listing events and booking tickets.The application uses Java, Spring Boot, and an in-memory H2 database with zero external setup.

Features:
1. View all available events
2. View details of a single event
3. Book tickets for an event
4. Automatically updates available ticket count
5. Prevents overbooking
6. Validates booking quantity
7. Records booking timestamps
8. Uses H2 Console for database inspection
9. Includes unit tests for booking logic

Configuration:
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.defer-datasource-initialization=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

Sample Data:
INSERT INTO events (id, name, available_slots) VALUES
(1, '🎸 Concert 2026', 100),
(2, '💻 Tech Workshop', 50);

API Endpoints:
1.Get All Events:
   GET /api/events
Response:
{
  "success": true,
  "events": [
    {
      "id": 1,
      "name": "🎸 Concert 2026",
      "availableSlots": 100
    }
  ]
}

2.Get Event by ID:
   GET /api/events/{id}
If not found:
{
  "success": false,
  "message": "No such event exist"
}

3.Book Tickets:
   POST /api/events/{id}/book
Request:
{
  "quantity": 3
}
Success:
{
  "success": true,
  "message": "🎉 Booked 3 tickets!",
  "remaining_slots": 97
}
Failure:
{
  "success": false,
  "message": "Only 50 tickets available, but you asked for 500!"
}

Unit Testing:
Booking logic is tested using JUnit 5 and Mockito.

Tests cover:
1. Successful booking
2. Negative quantity validation
3. Insufficient ticket handling

Design Notes:
1. Business logic is handled in the service layer
2. Controllers are kept thin
3. Validation is enforced before database updates
4. Booking timestamps are stored in a separate table for correctness
5. JSON responses follow REST best practices
