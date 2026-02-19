# Interview Booking Backend

This is the backend API for the **Interview Booking System**. It provides endpoints to manage interviewers, candidates, and interview bookings.

## Table of Contents

* Features
* Tech Stack
* Getting Started
* API Endpoints
* Database
* Contributing
* License

## Features

* Manage **Interviewers** (CRUD)
* Manage **Candidates** (CRUD)
* Book and cancel **interviews**
* View available **slots**
* Validation and error handling
* RESTful API design

## Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Database:** MySQL / H2 (for development)
* **Build Tool:** Maven
* **Dependencies:**

  * Spring Web
  * Spring Data JPA
  * Spring Validation
  * Lombok

## Getting Started

### Prerequisites

* Java 17+
* Maven
* MySQL (or H2 for dev)

### Installation

1. Clone the repository:

```bash
git clone https://github.com/yourusername/interview-booking-backend.git
cd interview-booking-backend
```

2. Configure database in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/interview_booking
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

3. Build and run the project:

```bash
mvn clean install
mvn spring-boot:run
```

The API will be available at: `http://localhost:8080`

## API Endpoints

### Interviewers

* `GET /api/interviewers` – List all interviewers
* `GET /api/interviewers/{id}` – Get interviewer by ID
* `POST /api/interviewers` – Add new interviewer
* `PUT /api/interviewers/{id}` – Update interviewer
* `DELETE /api/interviewers/{id}` – Delete interviewer

### Candidates

* `GET /api/candidates` – List all candidates
* `GET /api/candidates/{id}` – Get candidate by ID
* `POST /api/candidates` – Add new candidate
* `PUT /api/candidates/{id}` – Update candidate
* `DELETE /api/candidates/{id}` – Delete candidate

### Bookings

* `GET /api/bookings` – List all bookings
* `GET /api/bookings/{id}` – Get booking by ID
* `POST /api/bookings` – Book an interview
* `PUT /api/bookings/cancel/{id}` – Cancel a booking

## Database

* **Interviewers Table**

  * id, name, email, skills
* **Candidates Table**

  * id, name, email, experience
* **Bookings Table**

  * id, interviewer_id, candidate_id, date_time, status

## Contributing

1. Fork the repository
2. Create a new branch (`git checkout -b feature/xyz`)
3. Commit your changes (`git commit -m 'Add xyz feature'`)
4. Push to the branch (`git push origin feature/xyz`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.
