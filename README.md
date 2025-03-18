# 📖 Journal App

## 🔍 Overview
The **Journal App** is a secure, end-to-end encrypted (E2EE) journaling application built with **Spring Boot** and **MongoDB**. It allows users to securely create, manage, and store personal journal entries while ensuring privacy with authentication and encryption mechanisms.

With this app, a user can maintain their own collection of journals, track their thoughts over time, and update or delete entries as needed. The application is built with robust authentication and session management, ensuring that only authorized users can access their own journals.

## 🛠️ Tech Stack
- **Backend:** Spring Boot (Spring Security, Spring Data MongoDB, REST Controllers)
- **Database:** MongoDB
- **Security:** Spring Security with BCrypt password encoding

## 🚀 Features
- **User Authentication**: Secure login using Spring Security
- **CRUD Operations**: Create, Read, Update, Delete journal entries
- **JWT Authentication (Optional)**: Can be integrated for stateless authentication
- **MongoDB Storage**: Stores user and journal entry data
- **Session Management**: Stateless session policy using JWT or Basic Authentication
- **User-Specific Journals**: Each user has a personal collection of journals

## ⚙️ Setup & Installation
### 📌 Prerequisites
Ensure you have the following installed:
- Java 17+
- Maven
- MongoDB

### 📥 Clone the Repository
```sh
git clone https://github.com/your-repo/journalapp.git
cd journalapp
```

### 🗄️ Configure MongoDB
Ensure MongoDB is running and update `application.properties` with the database details:
```properties
spring.application.name=Journal App
spring.data.mongodb.uri=<your-mongodb-connection-uri>
spring.data.mongodb.database=journalDB
spring.data.mongodb.auto-index-creation=true
```
Replace `<your-mongodb-connection-uri>` with your actual MongoDB connection string.

### ▶️ Build & Run the Application
```sh
mvn spring-boot:run
```

## 🌐 API Endpoints
### 🔓 Public Endpoints
| Method | Endpoint        | Description       |
|--------|---------------|------------------|
| GET    | /health-check | Check server status |
| POST   | /create-user  | Register new user |

### 🔐 Protected Endpoints (Require Authentication)
| Method | Endpoint         | Description              |
|--------|----------------|-------------------------|
| PUT    | /user          | Update user details     |
| DELETE | /user          | Delete user account     |
| GET    | /journal       | Get all journal entries |
| POST   | /journal       | Create a new entry      |
| GET    | /journal/id/{id} | Get journal by ID     |
| PUT    | /journal/id/{id} | Update journal by ID  |
| DELETE | /journal/id/{id} | Delete journal by ID  |

## 📦 Dependencies
Defined in `pom.xml`:
- Spring Boot Web
- Spring Boot Data MongoDB
- Spring Boot Security
- Lombok
- JUnit (for testing)

## 🔮 Future Enhancements
- Implement JWT authentication
- Add user roles and permissions
- Deploy to cloud platform
- Add logging and monitoring
- Implement soft delete for journal entries

## 🎓 Acknowledgment
Special thanks to **Engineering Digest** for their educational content on Spring Boot, MongoDB, and security. The following YouTube playlist was instrumental in shaping this project:

[Spring Boot & MongoDB Course - Engineering Digest](https://www.youtube.com/playlist?list=PLA3GkZPtsafacdBLdd3p1DyRd5FGfr3Ue)

Also, thanks to **ChatGPT** for helping in creating this README file! 🚀

## 🤝 Contributing
Feel free to fork and contribute via pull requests!

## 📝 License
MIT License

