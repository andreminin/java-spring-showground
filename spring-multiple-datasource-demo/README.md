# User Management System

A demo application for user management + AI chat (Ollama) using Spring Boot 3 (backend) and Vue.js (frontend) with Oracle and PostgreSQL database.

You can use Ollama chat to search users and products in two datasources (in progress)
## Project Structure

```text
/
├── src/ # Spring Boot application
└── frontend-vue/ # Vue.js application
```


## Prerequisites

### For backend:
- Java 21 or higher
- Maven 3.6+
- Oracle Database 21
- Port 8080 available

### For frontend:
- Node.js 18+
- npm 6+
- Port 3000 available

## Oracle Database Setup

1. Install Oracle Database
2. Connect as sysdba:
```sql
   sqlplus / as sysdba
```

Create tablespace (if needed):

```sql
CREATE TABLESPACE user_management_ts
DATAFILE 'user_management.dbf' SIZE 100M
AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED;
```
Create user and grant privileges:

```sql

CREATE USER user_management IDENTIFIED BY password;
GRANT CONNECT, RESOURCE TO user_management;
GRANT UNLIMITED TABLESPACE TO user_management;
```
Test connection:
```sql

CONNECT user_management/password;
```
Backend (Spring Boot) Setup

    Navigate to backend directory:
    bash

 
Configure database connection in src/main/resources/application.yml:
yaml

```spring:
datasource:
url: jdbc:oracle:thin:@localhost:1521:ORCLCDB
username: user_management
password: password
```
Build the application:
```bash

mvn clean package
```
Run the application:
```bash

java -jar target/user-management-1.0.0.jar
```
Or using Maven:
```bash

mvn spring-boot:run
```
 Verify backend is running:

     Open http://localhost:8080/swagger-ui.html

     You should see Swagger UI with API documentation

Frontend (Vue.js) Setup

    Navigate to frontend directory:
    bash

cd frontend-vue

Install dependencies:
```bash

npm install
```

Start development server:
```bash

npm run serve
```
    Open http://localhost:3000 in your browser

Docker Deployment (Optional)
Backend in Docker:

    Build Docker image:
```  bash

docker build -t user-management-backend .
```

Run container:
```bash

docker run -p 8080:8080 \
-e SPRING_DATASOURCE_URL=jdbc:oracle:thin:@host.docker.internal:1521:ORCLCDB \
-e SPRING_DATASOURCE_USERNAME=user_management \
-e SPRING_DATASOURCE_PASSWORD=password \
user-management-backend
```

Frontend in Docker:

Build Docker image:
```bash

docker build -t user-management-frontend .
```

Run container:
```bash

docker run -p 3000:80 user-management-frontend
```

Usage

    Open the web application at http://localhost:3000

    Use the interface to:

        View user list

        Add new users

        Edit existing users

        Search users

        Delete users

API Endpoints

    GET /api/users - Get all users

    GET /api/users/{id} - Get user by ID

    POST /api/users - Create new user

    PUT /api/users/{id} - Update user

    DELETE /api/users/{id} - Delete user

    GET /api/users/search?query={} - Search users

Technologies
Backend:

    Spring Boot 2.7

    Spring Data JPA

    Oracle JDBC driver

    Springdoc OpenAPI (Swagger)

    Validation API

Frontend:

    Vue.js 3

    Bootstrap 5

    Axios

    Vue Router

Troubleshooting
Oracle Connection Issues:

    Ensure database is running

    Verify connection URL is correct

    Confirm user exists and has proper privileges

CORS Errors:

    Backend is already configured for frontend on localhost:3000

Build Errors:

    Ensure you're using correct Java and Maven versions

License

MIT License