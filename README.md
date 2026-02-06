# Task Manager API

A RESTful backend API for task management built with Java Spring Boot. This API provides comprehensive endpoints for creating, managing, and tracking tasks with support for user authentication, priority levels, and status tracking.

## 📋 Overview

Task Manager API is a robust backend service that handles all task management operations. It provides RESTful endpoints for integration with any frontend application, mobile app, or third-party service.

## ✨ Features

### Core API Functionality
- ✅ **Task CRUD Operations**: Create, Read, Update, Delete tasks
- 👤 **User Management**: User registration and authentication
- 🔐 **JWT Authentication**: Secure API endpoints with token-based auth
- ⭐ **Priority Management**: Assign and filter tasks by priority (High, Medium, Low)
- 📅 **Due Date Tracking**: Set and manage task deadlines
- ✔️ **Status Updates**: Track task progress (Pending, In Progress, Completed)
- 🔍 **Search & Filter**: Advanced query capabilities
- 📊 **Task Statistics**: Get task summaries and analytics
- 🏷️ **Categories/Tags**: Organize tasks with custom categories

### Technical Features
- RESTful API design
- JSON response format
- Input validation
- Error handling and custom exceptions
- Database integration (MySQL/PostgreSQL/H2)
- Pagination support
- Sorting capabilities
- CORS configuration

## 🛠️ Tech Stack

- **Framework**: Spring Boot
- **Language**: Java 11+
- **Database**: MySQL / PostgreSQL / H2 (in-memory)
- **Security**: Spring Security + JWT
- **Build Tool**: Maven
- **ORM**: Spring Data JPA / Hibernate
- **Validation**: Bean Validation
- **Documentation**: Swagger/OpenAPI (optional)
- **Testing**: JUnit 5, Mockito

## 📁 Project Structure

```
TaskManager/
└── TaskManger/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/taskmanager/
    │   │   │       ├── controller/      # REST Controllers
    │   │   │       │   ├── TaskController.java
    │   │   │       │   ├── UserController.java
    │   │   │       │   └── AuthController.java
    │   │   │       ├── model/           # Entity Models
    │   │   │       │   ├── Task.java
    │   │   │       │   ├── User.java
    │   │   │       │   └── Category.java
    │   │   │       ├── repository/      # Data Repositories
    │   │   │       │   ├── TaskRepository.java
    │   │   │       │   └── UserRepository.java
    │   │   │       ├── service/         # Business Logic
    │   │   │       │   ├── TaskService.java
    │   │   │       │   └── UserService.java
    │   │   │       ├── dto/             # Data Transfer Objects
    │   │   │       │   ├── TaskDTO.java
    │   │   │       │   └── UserDTO.java
    │   │   │       ├── config/          # Configuration
    │   │   │       │   ├── SecurityConfig.java
    │   │   │       │   └── WebConfig.java
    │   │   │       ├── exception/       # Custom Exceptions
    │   │   │       │   └── ResourceNotFoundException.java
    │   │   │       └── Application.java # Main Class
    │   │   └── resources/
    │   │       ├── application.properties
    │   │       └── application-dev.properties
    │   └── test/
    │       └── java/                    # Unit & Integration Tests
    ├── pom.xml                          # Maven dependencies
    └── README.md
```

## 🚀 Getting Started

### Prerequisites

- Java JDK 11 or higher
- Maven 3.6+
- MySQL/PostgreSQL (or use H2 for development)
- Postman or any API testing tool (optional)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/T92-max/TaskManager.git
cd TaskManager/TaskManger
```

2. **Configure Database**

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# Server Configuration
server.port=8080

# JWT Configuration
jwt.secret=your-secret-key-here
jwt.expiration=86400000

# Logging
logging.level.root=INFO
```

**For H2 In-Memory Database (Development):**
```properties
spring.datasource.url=jdbc:h2:mem:taskdb
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

3. **Create Database** (if using MySQL/PostgreSQL)
```sql
CREATE DATABASE taskmanager_db;
```

4. **Build the project**
```bash
mvn clean install
```

5. **Run the application**
```bash
mvn spring-boot:run
```

The API will be available at: `http://localhost:8080`

## 🔌 API Endpoints

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "securePassword123"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "securePassword123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "john_doe"
}
```

### Task Endpoints

#### Get All Tasks
```http
GET /api/tasks
Authorization: Bearer {token}

Query Parameters:
- page=0
- size=10
- sort=dueDate,desc
- status=PENDING
- priority=HIGH
```

#### Get Task by ID
```http
GET /api/tasks/{id}
Authorization: Bearer {token}
```

#### Create Task
```http
POST /api/tasks
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "Complete project documentation",
  "description": "Write comprehensive API documentation",
  "priority": "HIGH",
  "status": "PENDING",
  "dueDate": "2026-02-15",
  "categoryId": 1
}
```

#### Update Task
```http
PUT /api/tasks/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "Updated task title",
  "status": "IN_PROGRESS",
  "priority": "MEDIUM"
}
```

#### Delete Task
```http
DELETE /api/tasks/{id}
Authorization: Bearer {token}
```

#### Search Tasks
```http
GET /api/tasks/search?keyword=documentation
Authorization: Bearer {token}
```

#### Get Tasks by Status
```http
GET /api/tasks/status/{status}
Authorization: Bearer {token}
```

#### Get Tasks by Priority
```http
GET /api/tasks/priority/{priority}
Authorization: Bearer {token}
```

### User Endpoints

#### Get User Profile
```http
GET /api/users/profile
Authorization: Bearer {token}
```

#### Update User Profile
```http
PUT /api/users/profile
Authorization: Bearer {token}
Content-Type: application/json

{
  "username": "new_username",
  "email": "newemail@example.com"
}
```

### Category Endpoints

#### Get All Categories
```http
GET /api/categories
Authorization: Bearer {token}
```

#### Create Category
```http
POST /api/categories
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "Work",
  "description": "Work-related tasks"
}
```

## 📊 Data Models

### Task Entity
```java
{
  "id": 1,
  "title": "Complete project documentation",
  "description": "Write comprehensive API documentation",
  "priority": "HIGH",           // HIGH, MEDIUM, LOW
  "status": "IN_PROGRESS",      // PENDING, IN_PROGRESS, COMPLETED
  "dueDate": "2026-02-15",
  "createdAt": "2026-02-06T10:30:00",
  "updatedAt": "2026-02-06T14:20:00",
  "userId": 1,
  "categoryId": 1
}
```

### User Entity
```java
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "createdAt": "2026-01-01T00:00:00"
}
```

### Category Entity
```java
{
  "id": 1,
  "name": "Work",
  "description": "Work-related tasks"
}
```

## 🗄️ Database Schema

### Tables

**users**
- id (PK, Auto Increment)
- username (Unique)
- email (Unique)
- password (Encrypted)
- created_at
- updated_at

**tasks**
- id (PK, Auto Increment)
- title
- description
- priority (ENUM: HIGH, MEDIUM, LOW)
- status (ENUM: PENDING, IN_PROGRESS, COMPLETED)
- due_date
- created_at
- updated_at
- user_id (FK)
- category_id (FK)

**categories**
- id (PK, Auto Increment)
- name
- description
- user_id (FK)

## 🔐 Security

### JWT Authentication Flow
1. User registers or logs in
2. Server generates JWT token
3. Client includes token in Authorization header
4. Server validates token for protected endpoints

### Password Security
- Passwords encrypted using BCrypt
- Minimum password requirements enforced
- Secure password reset flow

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=TaskServiceTest
```

### Integration Tests
```bash
mvn verify
```

### Test Coverage
- Unit tests for service layer
- Integration tests for controllers
- Repository tests
- Security tests

## 📦 Dependencies

Key dependencies in `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    
    <!-- JWT -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
    </dependency>
    
    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
</dependencies>
```

## 🚢 Deployment

### Build for Production
```bash
mvn clean package -DskipTests
```

### Run JAR File
```bash
java -jar target/taskmanager-api-1.0.0.jar
```

### Docker Deployment

Create `Dockerfile`:
```dockerfile
FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/taskmanager-api.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:
```bash
docker build -t taskmanager-api .
docker run -p 8080:8080 taskmanager-api
```

### Environment Variables
```bash
export DB_URL=jdbc:mysql://production-db:3306/taskmanager_db
export DB_USERNAME=prod_user
export DB_PASSWORD=prod_password
export JWT_SECRET=production-secret-key
```

## 📝 API Documentation

### Swagger/OpenAPI

Add Swagger dependency to `pom.xml`:
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.14</version>
</dependency>
```

Access Swagger UI at: `http://localhost:8080/swagger-ui.html`

## 🔧 Configuration Profiles

### Development (`application-dev.properties`)
```properties
server.port=8080
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
logging.level.root=DEBUG
```

### Production (`application-prod.properties`)
```properties
server.port=8080
spring.jpa.hibernate.ddl-auto=validate
logging.level.root=WARN
```

Run with profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 🐛 Error Handling

### Standard Error Response
```json
{
  "timestamp": "2026-02-06T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Task with id 999 not found",
  "path": "/api/tasks/999"
}
```

### HTTP Status Codes
- `200` - Success
- `201` - Created
- `204` - No Content
- `400` - Bad Request
- `401` - Unauthorized
- `403` - Forbidden
- `404` - Not Found
- `500` - Internal Server Error

## 🧪 Testing with Postman

1. Import the API collection
2. Set environment variables:
   - `BASE_URL`: http://localhost:8080
   - `TOKEN`: Your JWT token
3. Test endpoints in sequence:
   - Register user
   - Login (save token)
   - Create task
   - Get tasks
   - Update task
   - Delete task

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/NewFeature`)
3. Commit changes (`git commit -m 'Add NewFeature'`)
4. Push to branch (`git push origin feature/NewFeature`)
5. Open Pull Request

### Development Guidelines
- Follow Spring Boot best practices
- Write unit tests for new features
- Update API documentation
- Use meaningful commit messages
- Follow RESTful conventions

## 🔮 Future Enhancements

- [ ] Task attachments/file uploads
- [ ] Email notifications for due dates
- [ ] Recurring tasks
- [ ] Task sharing and collaboration
- [ ] Task comments and notes
- [ ] Advanced filtering and search
- [ ] Export tasks to PDF/CSV
- [ ] Rate limiting
- [ ] API versioning
- [ ] WebSocket support for real-time updates
- [ ] OAuth2 integration
- [ ] Multi-tenancy support

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**T92-max**
- GitHub: [@T92-max](https://github.com/T92-max)

---

**Built with ☕ and Spring Boot**
