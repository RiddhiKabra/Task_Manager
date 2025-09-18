A full-stack Task Manager Web Application built with Java Spring Boot (backend), Angular (frontend), MySQL (database), secured with JWT authentication, containerized using Docker, and enhanced with Trie-based search algorithms for efficient task lookup.

🚀 Features

🔐 Authentication & Security

User registration & login with JWT-based authentication

Role-based access (User/Admin)

Secure password storage using BCrypt

✅ Task Management

Create, update, delete tasks

Track tasks with statuses: TODO, IN_PROGRESS, DONE

Task prioritization (sort by priority & due date)

Task search using:

Prefix-based search with Trie

Keyword matching (fallback)

🎨 Frontend (Angular)

Responsive UI built with Angular 20 + SCSS

Login/Register screens with reactive forms

Dashboard to manage tasks

Integration with backend APIs

🗄️ Backend (Spring Boot)

RESTful APIs with Spring Web

Data persistence with Spring Data JPA + Hibernate

Validation & exception handling

Unit tests & integration tests with JUnit & MockMvc

🐳 Deployment (Docker)

Backend packaged as a Docker container

Frontend served via Angular build + Dockerized Nginx

MySQL database running inside Docker container

🛠️ Tech Stack

Frontend: Angular 20, SCSS, TypeScript Backend: Spring Boot 3, Spring Security, JWT, JPA, Hibernate Database: MySQL Containerization: Docker Algorithms: Trie (prefix search), Comparator-based sorting Testing: JUnit, MockMvc Tools: Maven, Node.js, Postman

⚡ Setup Instructions 1️⃣ Clone the Repository git clone https://github.com/pranav280601/Task-Manager-Application cd task-manager

2️⃣ Backend Setup (Spring Boot) cd backend mvn clean install mvn spring-boot:run

Backend runs at: http://localhost:8080

3️⃣ Frontend Setup (Angular) cd frontend npm install ng serve

Frontend runs at: http://localhost:4200

4️⃣ Docker Setup (Full App + DB) docker-compose up --build

Backend → http://localhost:8080

Frontend → http://localhost:4200

MySQL DB → localhost:3306

🔑 API Endpoints Auth

POST /api/auth/register → Register new user

POST /api/auth/login → Login & get JWT

Tasks

GET /api/tasks → Get all tasks

GET /api/tasks/sorted → Get tasks sorted by priority & due date

GET /api/tasks/search/{keyword} → Search tasks by keyword/Trie

POST /api/tasks → Create new task

PUT /api/tasks/{id} → Update task

DELETE /api/tasks/{id} → Delete task

📸 Screenshots

(Add login, register, dashboard screenshots here later)

🚀 Future Enhancements

Add task categories & labels

Drag-and-drop Kanban board

Notifications & reminders


Multi-user collaboration
