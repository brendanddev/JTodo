# JTodo: Modern Java MVC CRUD Todo Application

## Overview
JTodo is a full-stack Java web application for managing todo items, built using the MVC architecture. It features a modern JSP-based frontend, a robust backend with Servlets and JDBC, and a MySQL database. The project is containerized using Docker and orchestrated with Docker Compose for easy deployment.

## Features
- Create, Read, Update, Delete (CRUD) operations for todos
- Responsive, modern UI with custom CSS
- Date and time display in 12-hour format (AM/PM)
- MVC architecture (Model, View, Controller)
- MySQL database integration
- JSTL for dynamic content and formatting
- Containerized with Docker (Tomcat 9)
- Easy setup with Docker Compose

## Project Structure
```
jtodo/
├── Dockerfile
├── docker-compose.yml
├── init.sql
├── pom.xml
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/jtodo/
│       │       ├── controller/
│       │       │   └── TodoController.java
│       │       ├── model/
│       │       │   ├── Todo.java
│       │       │   └── TodoDAO.java
│       └── webapp/
│           ├── style.css
│           ├── todo-list.jsp
│           ├── todo-form.jsp
│           └── WEB-INF/
│               └── web.xml
└── plan.md
```

## Technical Details

### 1. Backend
- **Java Servlet (Controller):** Handles HTTP requests, routes actions (list, add, edit, delete), and manages redirects.
- **Model (Todo.java):** Represents a todo item with fields: id, title, description, status, createdAt.
- **DAO (TodoDAO.java):** Manages database operations using JDBC. Reads DB config from environment variables for Docker compatibility.

### 2. Frontend
- **JSP Views:**
  - `todo-list.jsp`: Displays all todos in a styled table with Edit/Delete buttons and formatted date/time.
  - `todo-form.jsp`: Form for adding/editing todos, styled for modern UX.
- **CSS (`style.css`):** Centralized, modern, responsive styles for all pages and buttons.
- **JSTL:** Used for loops, conditional rendering, and date formatting.

### 3. Database
- **MySQL:**
  - Table: `todos` (id, title, description, status, created_at)
  - Initialization: `init.sql` creates the table if not present.
  - Connection: Configured via environment variables in Docker Compose.

### 4. Containerization & Deployment
- **Dockerfile:**
  - Uses Tomcat 9 base image
  - Builds WAR from Maven and deploys to Tomcat
- **docker-compose.yml:**
  - Defines `app` (Java/Tomcat) and `db` (MySQL) services
  - Sets up environment variables for DB connection
  - Mounts volumes for persistent data and initialization

### 5. Build & Run
- **Build:**
  - Uses Maven (`pom.xml`) for dependencies: servlet-api, mysql-connector-java, jstl, junit
  - Run `docker-compose up --build -d` to build and start all services
- **Access:**
  - App: `http://localhost:3309/jtodo/todos`
  - DB: `localhost:3306` (internal to Docker)

### 6. Environment Variables
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` (set in docker-compose.yml)

### 7. Testing
- **JUnit:** Basic unit tests for model and DAO (extendable)
- **Manual Testing:** CRUD operations via browser

## Setup Instructions
1. **Clone the repository:**
  ```bash
  git clone https://github.com/yourusername/jtodo.git
  cd jtodo
  ```
2. **Build and start services:**
  ```bash
  docker-compose up --build -d
  ```
3. **Access the application:**
  - Open your browser and go to: `http://localhost:3309/jtodo/todos`
4. **Stop services:**
  ```bash
  docker-compose down
  ```

## Advanced Usage
- **Database Initialization:** The `init.sql` script automatically creates the `todos` table if it does not exist. You can modify this script to add more tables or seed data.
- **Environment Configuration:** All database credentials and connection details are managed via environment variables in `docker-compose.yml` for security and flexibility.
- **Hot Reload:** For development, you can mount your source code as a volume in Docker to enable hot reloads of JSPs and static files.
- **Testing:** Extend JUnit tests for DAO and Controller logic. Integrate with CI/CD pipelines for automated testing.

## Security Considerations
- **Database Credentials:** Never hardcode credentials. Use environment variables and secrets management for production deployments.
- **Input Validation:** All form inputs are validated server-side to prevent SQL injection and XSS attacks.
- **Session Management:** For production, implement authentication and session management to restrict access.

## Scalability & Extensibility
- **Horizontal Scaling:** Deploy multiple instances of the app behind a load balancer. Use Docker Swarm or Kubernetes for orchestration.
- **Database Scaling:** Use managed MySQL services or clustering for high availability.
- **Extending Features:**
  - Add user authentication and authorization
  - Implement RESTful APIs for mobile or third-party integration
  - Add categories, priorities, or due dates to todos
  - Integrate with frontend frameworks (React, Angular) for SPA experience

## Troubleshooting & FAQ
- **App not accessible:** Ensure Docker containers are running and ports are not blocked by firewalls.
- **Database errors:** Check logs for MySQL container, verify credentials and table existence.
- **Build failures:** Ensure Maven is installed and compatible with your JDK version. Clean and rebuild with `mvn clean package` if needed.
- **CSS/JS not updating:** Clear browser cache or force reload.

## References & Further Reading
- [Java Servlet Documentation](https://jakarta.ee/specifications/servlet/)
- [JSP & JSTL Guide](https://www.oracle.com/java/technologies/jsptl.html)
- [Docker Documentation](https://docs.docker.com/)
- [Tomcat 9 Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)
- [MySQL Reference Manual](https://dev.mysql.com/doc/)


## Troubleshooting
- **404/500 Errors:** Check servlet mapping in `web.xml`, ensure DB is running, verify table exists
- **DB Connection Issues:** Ensure environment variables are set, DB service is healthy
- **UI Issues:** Clear browser cache after CSS changes

## Customization
- Update CSS in `style.css` for branding and UI enhancements
- Extend model/DAO for more fields or relationships
- Add authentication, user management, or role-based access
- Integrate with external APIs or notification services


## License
MIT

## Author
Subodh
