# KeepItAlive (Site Monitor)

**KeepItAlive** is a lightweight, efficient website availability monitoring tool (Uptime Monitor) built with modern Java.

The application allows users to add URLs to a watchlist, automatically checks their status in the background, and displays the current state ("UP" or "DOWN") via a web dashboard or REST API.

## 🚀 Features

*   **Real-time Monitoring:** Regular background checks for website availability (HTTP GET).
*   **Web Dashboard:** Simple, clean interface to view the list of monitored sites and their statuses.
*   **REST API:** Endpoints to programmatically retrieve status data or add new sites.
*   **Background Tasks:** Automated scheduler running checks every 10 seconds.
*   **Status Logic:**
    *   🟢 **UP**: HTTP Status 200–299.
    *   🔴 **DOWN**: Connection errors, timeouts, or error codes (4xx, 5xx).
    *   🟠 **PENDING**: Site added but not yet checked.

## 🛠 Tech Stack

The project is built using a modern Java ecosystem:

*   **Language:** Java 21
*   **Framework:** Spring Boot
*   **Database:** PostgreSQL
*   **Frontend:** Thymeleaf + HTML
*   **Build Tool:** Maven
*   **Utilities:** Lombok

## ⚙️ Installation & Configuration

### 1. Clone the repository
```bash
git clone https://github.com/your-username/keepitalive.git
cd keepitalive
```

### 2. Database Setup
Create a PostgreSQL database and user. You can use the following SQL commands:

```sql
CREATE DATABASE site_monitor_db;
CREATE USER sitemonitor_user WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE site_monitor_db TO sitemonitor_user;
```

### 3. Verify Configuration
Ensure `src/main/resources/application.properties` matches your database setup:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/site_monitor_db
spring.datasource.username=sitemonitor_user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

## ▶️ How to Run

You can run the application directly using Maven:

```bash
mvn spring-boot:run
```

Or build the JAR file and run it:

```bash
mvn clean package
java -jar target/site-monitor-1.0-SNAPSHOT.jar
```

Once started, the application will be available at: **http://localhost:8080**